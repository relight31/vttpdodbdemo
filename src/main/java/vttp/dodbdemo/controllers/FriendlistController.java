package vttp.dodbdemo.controllers;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import vttp.dodbdemo.repository.FriendlistRepo;

@Controller
public class FriendlistController {
    private Logger logger = Logger.getLogger(FriendlistController.class.getName());

    @Autowired
    private FriendlistRepo friendlistRepo;

    @GetMapping(path = "/")
    public String landing(Model model) {
        model.addAttribute("friends", friendlistRepo.getAllFriends());
        return "index";
    }

    @PostMapping(path = "/")
    public String addFriendRecord(Model model, @RequestBody MultiValueMap<String, String> form) {
        if (!friendlistRepo.checkEmailExists(form.getFirst("email"))) {
            boolean created = friendlistRepo.createFriendRecord(form);
            if (created) {
                model.addAttribute("message", "Successfully added record!");
                logger.log(Level.INFO, "Successfully created record");
            } else {
                model.addAttribute("message", "Could not add record!");
                logger.log(Level.INFO, "Record creation failed");
            }
        } else {
            model.addAttribute("message", "Record already exists!");
            logger.log(Level.INFO, "Record for " + form.getFirst("email") + " already exists");
        }
        model.addAttribute("friends", friendlistRepo.getAllFriends());
        return "index";
    }
}
