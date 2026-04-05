package com.zara.interpreter.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.view.RedirectView;

@Controller
public class UIController {

    @GetMapping("/")
    public RedirectView index() {
        return new RedirectView("/index.html");
    }

    @GetMapping("/docs")
    public RedirectView docs() {
        return new RedirectView("/docs.html");
    }
}
