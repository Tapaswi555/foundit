package com.foundit.controller;

import com.foundit.repository.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    private final UserRepository users;
    private final LostItemRepository lost;
    private final FoundItemRepository found;

    public AdminController(UserRepository u, LostItemRepository l, FoundItemRepository f){
        this.users=u; this.lost=l; this.found=f;
    }

    @GetMapping("/admin")
    public String admin(Model m){
        m.addAttribute("users", users.count());
        m.addAttribute("lost", lost.count());
        m.addAttribute("found", found.count());
        return "admin";
    }
}
