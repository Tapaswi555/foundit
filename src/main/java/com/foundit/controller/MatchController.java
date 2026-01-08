package com.foundit.controller;

import com.foundit.model.LostItem;
import com.foundit.service.MatchService;
import com.foundit.repository.LostItemRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/match")
public class MatchController {

    private final MatchService matchService;
    private final LostItemRepository lostRepo;

    public MatchController(MatchService matchService,
                           LostItemRepository lostRepo) {
        this.matchService = matchService;
        this.lostRepo = lostRepo;
    }

    @GetMapping("/{lostId}")
    public String showMatches(@PathVariable Long lostId, Model model) {

        LostItem lostItem = lostRepo.findById(lostId).orElse(null);

        if (lostItem == null) {
            return "redirect:/dashboard";
        }

        model.addAttribute("lostItem", lostItem);
        model.addAttribute("matches",
                matchService.findMatches(lostItem));

        return "matches";
    }
}
