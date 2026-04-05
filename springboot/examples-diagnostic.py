#!/usr/bin/env python3
"""
ZARA Interpreter - Examples Diagnostic Tool
Tests all example programs and provides detailed diagnostics
"""

import json
import urllib.request
import os
from pathlib import Path

class ZARADiagnostics:
    def __init__(self, base_url="http://localhost:8080"):
        self.base_url = base_url
        self.examples_dir = "/home/ishant57/Zara/ZARA-Interpreter-Engine/examples"
    
    def test_api_health(self):
        """Test if API is responding"""
        try:
            response = urllib.request.urlopen(f"{self.base_url}/api/health")
            result = json.loads(response.read().decode())
            return True, result
        except Exception as e:
            return False, str(e)
    
    def execute_code(self, code, session_id="diag-test"):
        """Execute ZARA code via API"""
        request_data = {
            'code': code,
            'sessionId': session_id
        }
        
        req = urllib.request.Request(
            f"{self.base_url}/api/execute",
            data=json.dumps(request_data).encode('utf-8'),
            headers={'Content-Type': 'application/json'},
            method='POST'
        )
        
        try:
            with urllib.request.urlopen(req) as response:
                return json.loads(response.read().decode())
        except Exception as e:
            return {'success': False, 'error': str(e)}
    
    def test_example_files(self):
        """Test all .zara files in examples directory"""
        results = {}
        if not os.path.exists(self.examples_dir):
            return {'error': f'Examples directory not found: {self.examples_dir}'}
        
        for file in sorted(os.listdir(self.examples_dir)):
            if file.endswith('.zara'):
                filepath = os.path.join(self.examples_dir, file)
                with open(filepath, 'r') as f:
                    code = f.read()
                
                result = self.execute_code(code)
                results[file] = {
                    'success': result.get('success', False),
                    'output': result.get('output', []),
                    'error': result.get('error', ''),
                    'execution_time_ms': result.get('executionTimeMs', 0)
                }
        
        return results
    
    def test_web_ui_examples(self):
        """Test example code snippets from web UI"""
        examples = {
            'Addition': 'set x = 10\nset y = 20\nshow x + y',
            'String Concatenation': 'set name = "ZARA"\nshow "Hello " + name',
            'Conditional': 'set x = 5\nwhen x > 3: show "X is big"',
            'Loop': 'loop 5: show "ZARA"'
        }
        
        results = {}
        for name, code in examples.items():
            result = self.execute_code(code)
            results[name] = {
                'success': result.get('success', False),
                'output': result.get('output', []),
                'error': result.get('error', ''),
                'execution_time_ms': result.get('executionTimeMs', 0)
            }
        
        return results
    
    def test_advanced_features(self):
        """Test more advanced ZARA features"""
        advanced = {
            'Nested Conditionals': 'set x = 5\nwhen x > 3:\n    set y = 10\n    when y > 5: show "Both true"',
            'Nested Loops': 'set i = 1\nloop 2:\n    set j = 1\n    loop 2: show i\n    set i = i + 1',
            'Complex Arithmetic': 'set a = 10\nset b = 3\nset c = 20\nset result = (a + b) * c - 5\nshow result',
            'String Operations': 'set msg = "ZARA"\nset greeting = "Welcome to "\nshow greeting + msg'
        }
        
        results = {}
        for name, code in advanced.items():
            result = self.execute_code(code)
            results[name] = {
                'success': result.get('success', False),
                'output': result.get('output', []),
                'error': result.get('error', ''),
                'execution_time_ms': result.get('executionTimeMs', 0)
            }
        
        return results
    
    def run_full_diagnostics(self):
        """Run all diagnostic tests"""
        print("=" * 70)
        print("ZARA INTERPRETER - EXAMPLES DIAGNOSTIC REPORT")
        print("=" * 70)
        print()
        
        # Test API health
        print("1. API HEALTH CHECK")
        print("-" * 70)
        health_ok, health_result = self.test_api_health()
        if health_ok:
            print(f"✅ API is responding")
            print(f"   Service: {health_result.get('service')}")
            print(f"   Version: {health_result.get('version')}")
            print(f"   Status: {health_result.get('status')}")
        else:
            print(f"❌ API is not responding: {health_result}")
            return
        print()
        
        # Test example files
        print("2. TESTING EXAMPLE FILES (.zara)")
        print("-" * 70)
        file_results = self.test_example_files()
        for file, result in sorted(file_results.items()):
            status = "✅" if result['success'] else "❌"
            print(f"{status} {file}")
            if result['output']:
                print(f"   Output: {result['output']}")
            if result['error']:
                print(f"   Error: {result['error']}")
            print(f"   Execution Time: {result['execution_time_ms']}ms")
        print()
        
        # Test web UI examples
        print("3. TESTING WEB UI EXAMPLE SNIPPETS")
        print("-" * 70)
        ui_results = self.test_web_ui_examples()
        for name, result in ui_results.items():
            status = "✅" if result['success'] else "❌"
            print(f"{status} {name}")
            if result['output']:
                print(f"   Output: {result['output']}")
            if result['error']:
                print(f"   Error: {result['error']}")
            print(f"   Execution Time: {result['execution_time_ms']}ms")
        print()
        
        # Test advanced features
        print("4. TESTING ADVANCED FEATURES")
        print("-" * 70)
        advanced_results = self.test_advanced_features()
        for name, result in advanced_results.items():
            status = "✅" if result['success'] else "❌"
            print(f"{status} {name}")
            if result['output']:
                print(f"   Output: {result['output']}")
            if result['error']:
                print(f"   Error: {result['error']}")
            print(f"   Execution Time: {result['execution_time_ms']}ms")
        print()
        
        # Summary
        print("=" * 70)
        print("SUMMARY")
        print("=" * 70)
        total_tests = (len(file_results) + len(ui_results) + len(advanced_results))
        passed = sum(1 for r in [*file_results.values(), *ui_results.values(), *advanced_results.values()] if r['success'])
        print(f"Total Tests: {total_tests}")
        print(f"Passed: {passed}")
        print(f"Failed: {total_tests - passed}")
        print(f"Success Rate: {(passed/total_tests)*100:.1f}%")
        print()
        
        if passed == total_tests:
            print("✅ ALL TESTS PASSED - No issues detected!")
        else:
            print("❌ SOME TESTS FAILED - See details above")
        print()

if __name__ == "__main__":
    diag = ZARADiagnostics()
    diag.run_full_diagnostics()
