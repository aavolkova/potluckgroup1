package com.potluck.group1.controllers;

import com.potluck.group1.models.PotluckGuest;
import com.potluck.group1.repository.PotluckRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;
import java.util.Iterator;
import java.util.List;

@Controller
public class MainController {

    @Autowired
    PotluckRepo  potluckRepo;



    // home page
    @GetMapping("/index")
    public String indexPageGet(Model model) {
        model.addAttribute("potluckGuest", new PotluckGuest());
        return "index";
    }


    @PostMapping ("/index")
    public String indexPagePost(@Valid @ModelAttribute("potluckGuest") PotluckGuest potluckGuest, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            return "index";
        }
        model.addAttribute("confirmmsg", true);
        potluckRepo.save(potluckGuest);

        return "index";
    }

    @GetMapping("/potluckguestlist")
    public String showPotluckGuests (Model model) {

        Iterable<PotluckGuest> potluckGuests = potluckRepo.findAll();

//        for(PotluckGuest plg: potluckGuests){
//            String dishTitle = plg.getDishTitle();
//            String capDishTitle = dishTitle.toUpperCase();
//
//
//        }



        model.addAttribute("potluckguests", potluckGuests);


        List<PotluckGuest> someGuests = potluckRepo.findByDishTitleContaining("chick");

        System.out.println("List contains" + someGuests.size());

        for (PotluckGuest plg: someGuests) {
            System.out.println("Dish Found: " + plg.getDishTitle());
            System.out.println("Dish Found: " + plg.getDishTitle());
        }

        return "potluckguestlist";





    }

}