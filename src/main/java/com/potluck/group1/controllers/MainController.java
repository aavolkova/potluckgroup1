package com.potluck.group1.controllers;

import com.potluck.group1.models.PotluckGuest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.validation.Valid;

@Controller
public class MainController {


    // home page
    @GetMapping(value = {"/", "/index"})
    public String indexPageGet(Model model) {
//        model.addAttribute("someUser", new User());
        return "index";
    }


    @RequestMapping(value = {"/", "/index"}, method = RequestMethod.POST)
    public String indexPagePost(@Valid @ModelAttribute("potluckGuest") PotluckGuest potluckGuest, BindingResult bindingResult) {


        if (bindingResult.hasErrors()) {
            return "index";
        }


        return "potluckconfirm";
    }

}