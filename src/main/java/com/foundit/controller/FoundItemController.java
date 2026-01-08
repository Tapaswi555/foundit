package com.foundit.controller;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.foundit.model.FoundItem;
import com.foundit.model.User;
import com.foundit.repository.FoundItemRepository;
import com.foundit.repository.UserRepository;

@Controller
@RequestMapping("/found")
public class FoundItemController {

    private final FoundItemRepository foundRepo;
    private final UserRepository userRepo;

    public FoundItemController(FoundItemRepository foundRepo, UserRepository userRepo) {
        this.foundRepo = foundRepo;
        this.userRepo = userRepo;
    }

    @GetMapping
    public String foundForm(Model model) {
        model.addAttribute("foundItem", new FoundItem());
        return "found-item";
    }
    @PostMapping
    public String saveFound(
            @RequestParam("itemName") String itemName,
            @RequestParam("foundLocation") String foundLocation,
            @RequestParam("description") String description,
            @RequestParam("keptAt") String keptAt,
            @RequestParam("image") MultipartFile image,
            Authentication auth
    ) throws IOException {

        User user = userRepo.findByEmail(auth.getName());

        FoundItem found = new FoundItem();
        found.setItemName(itemName);
        found.setFoundLocation(foundLocation);
        found.setDescription(description);
        found.setKeptAt(keptAt);
        found.setReportedBy(user);

        if (!image.isEmpty()) {
            found.setImage(image.getBytes());
        }

        foundRepo.save(found);

        return "redirect:/dashboard";
    }

}
