package be.pxl.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

@Controller
public class MappingController {

    @GetMapping("/gedicht")
    public String writer() {
        return "poem.html";
    }

    //TODO --> list of teams (controller, teams.html)
    @GetMapping("/profile")
    public String reader(Model model) {
        List<String> userList = new ArrayList<>();
        userList.add("Robby Quintiens");
        userList.add("Junior Java Developer");
        model.addAttribute("profile", userList);
        return "profile.html";
    }
}
