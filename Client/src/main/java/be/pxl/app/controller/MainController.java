package be.pxl.app.controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import java.security.Principal;

@Controller
public class MainController {


    @GetMapping("/main")
    public ModelAndView positions(Authentication auth) {
        ModelAndView model = new ModelAndView();
        model.addObject("username", auth.getName());
        model.addObject("authorities", auth.getAuthorities());
        return model;
    }
}
