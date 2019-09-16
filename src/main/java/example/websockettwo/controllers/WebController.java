package example.websockettwo.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class WebController {
    public static final Logger logger = LoggerFactory.getLogger(WebController.class);

    @GetMapping("/")
    public String getIndexPage() {
        return "index";
    }
}