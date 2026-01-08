package com.foundit.controller;

import com.foundit.model.LostItem;
import com.foundit.repository.LostItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class LostItemController {

    private final LostItemRepository lostItemRepository;

    public LostItemController(LostItemRepository lostItemRepository) {
        this.lostItemRepository = lostItemRepository;
    }

    // OPEN LOST ITEM PAGE
    @GetMapping("/lost")
    public String lostItemPage(Model model) {
        model.addAttribute("lostItem", new LostItem());
        return "lost-item"; // MUST MATCH HTML FILE NAME
    }

    // SUBMIT LOST ITEM
    @PostMapping("/lost")
    public String submitLostItem(@ModelAttribute LostItem lostItem) {
        lostItemRepository.save(lostItem);
        return "redirect:/dashboard";
    }
}
