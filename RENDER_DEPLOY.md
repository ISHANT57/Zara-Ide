# ZARA Interpreter - Render Deployment Guide

## Quick Start on Render

### 1. Prerequisites
- GitHub account with repository containing this code
- Render account (https://render.com)
- Java 17 compatible environment

### 2. Deployment Options

#### Option A: Using Render Dashboard (Recommended for beginners)

1. **Connect Repository**
   - Go to https://dashboard.render.com/new/web
   - Connect your GitHub repository
   - Select the branch to deploy (e.g., `main`)

2. **Configure Service**
   - **Name**: `zara-interpreter`
   - **Runtime**: `Node` (Render will detect Java from root directory)
   - **Build Command**: `cd springboot && mvn clean package -DskipTests -q`
   - **Start Command**: `cd springboot && java -Xmx300m -Xms75m -jar target/zara-interpreter-1.0.0.jar`

3. **Environment Variables** (add these in Dashboard)
   ```
   PORT=10000
   JAVA_OPTS=-Xmx300m -Xms75m
   ```

4. **Deploy**
   - Click "Create Web Service"
   - Render will build and deploy automatically

#### Option B: Using render.yaml (Infrastructure as Code)

1. **Ensure these files exist in project root:**
   - `render.yaml` ✓
   - `system.properties` ✓
   - `Procfile` ✓

2. **Push to GitHub**
   ```bash
   git add render.yaml system.properties Procfile
   git commit -m "Add Render deployment configuration"
   git push
   ```

3. **Deploy from Render Dashboard**
   - Render will automatically read `render.yaml`
   - No manual configuration needed

### 3. Health Check & Monitoring

**Health Endpoint**: `https://your-service.onrender.com/api/health`
- Should return: `{"status":"up"}`
- Used by Render to monitor service health

**Logs**: View in Render Dashboard → Logs tab

### 4. Common Issues & Solutions

| Issue | Solution |
|-------|----------|
| **Port binding error** | Ensure `application.properties` uses `${PORT:8080}` |
| **Build timeout** | Render free tier has 10-minute timeout; Maven build should complete in ~2-3 min |
| **Out of memory** | Free tier has 512MB limit; `-Xmx300m -Xms75m` is optimized for this |
| **Static files not serving** | Ensure `index.html` is in `springboot/src/main/resources/static/` |

### 5. Performance Optimization

For Render's free tier:
- Memory: 512MB total (Java uses 300MB, OS uses 212MB)
- CPU: Shared
- Disk: 1GB (ephemeral - resets on redeploy)

**To improve performance:**
1. Upgrade to paid tier
2. Increase Java heap: `-Xmx512m -Xms256m` (on paid tier)
3. Enable CDN caching in Render dashboard

### 6. Continuous Deployment

**Auto-deploy on GitHub push:**
1. Connect GitHub repo to Render
2. Render automatically redeploys when you push to the connected branch
3. Deployments take ~3-5 minutes

### 7. API Examples

Once deployed, test the API:

```bash
# Health check
curl https://your-service.onrender.com/api/health

# Execute code
curl -X POST https://your-service.onrender.com/api/execute \
  -H "Content-Type: application/json" \
  -d '{"code":"show \"Hello from Render\""}'
```

### 8. Troubleshooting

**Check logs in real-time:**
```
Render Dashboard → Select Service → Logs
```

**Test locally before deploying:**
```bash
cd springboot
mvn clean package
java -jar target/zara-interpreter-1.0.0.jar
# Visit http://localhost:8080
```

## File Structure for Deployment

```
ZARA-Interpreter-Engine/
├── render.yaml              ✓ (Render config)
├── Procfile                 ✓ (Build process)
├── system.properties        ✓ (Java version)
├── .env.example            ✓ (Environment template)
└── springboot/
    ├── pom.xml
    ├── src/
    │   ├── main/
    │   │   ├── java/com/zara/interpreter/
    │   │   └── resources/
    │   │       ├── application.properties  (Updated ✓)
    │   │       ├── static/index.html
    │   │       └── templates/index.html
    │   └── test/
    └── target/zara-interpreter-1.0.0.jar
```

## Deployment Status

**Ready for Render**: ✅

All required files are configured and optimized for Render's free tier.
