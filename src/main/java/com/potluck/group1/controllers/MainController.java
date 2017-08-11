package com.potluck.group1.controllers;

import com.potluck.group1.models.PotluckGuest;
import com.potluck.group1.models.SearchBox;
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
//            model.addAttribute("confirmmsg", false);
            return "index";
        }
//        model.addAttribute("confirmmsg", true);
        potluckRepo.save(potluckGuest);

        return "guestaddedconfirmation";
    }

    @GetMapping("/potluckguestlist")
    public String showPotluckGuests (Model model) {
        Iterable<PotluckGuest> potluckGuests = potluckRepo.findAll();

        model.addAttribute("potluckguests", potluckGuests);

        return "potluckguestlist";
    }


    @PostMapping("/guestaddedconfirmation")
    public String guestAddedConfirmation (@ModelAttribute("potluckGuest") PotluckGuest potluckGuest, Model model) {
        model.addAttribute("potluckGuest", potluckGuest);

        return "guestaddedconfirmation";
    }





    @PostMapping("/searchList")
    public String showSearchList (@ModelAttribute("searchObject") SearchBox searchObject, Model model) {

        // test output to console
        System.out.println("The search you entered was: " + searchObject.getSearchEntry());

        // now search the db for dishes containing whatever the user just entered in the search form
        // we are using out custom query method we defined in our repository
        List<PotluckGuest> someGuests = potluckRepo.findByDishTitleContaining(searchObject.getSearchEntry());

        // more test output to console
        System.out.println("List contains this many items: " + someGuests.size());
        // iterate through someGuests List, and print out the dish title for each one
        for (PotluckGuest plg: someGuests) {
            System.out.println("Dish Found: " + plg.getDishTitle());
            System.out.println("First name: " + plg.getFirstName());
        }

        // add the new List to the model, so it can be displayed in searchList.html
        // apparently you can add both an Iterable and a List to a model, how convenient!
        model.addAttribute("searchBoxResultList", someGuests);

        return "searchList";
    }

}