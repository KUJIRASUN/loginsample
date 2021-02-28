package com.example.demo.controllers;

import com.example.demo.controllers.form.SignupForm;
import com.example.demo.controllers.form.validation.SignupFormValidator;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class SignupController {

    private final SignupFormValidator signupFormValidator;

    private final UserService userService;

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SignupController(UserService userService, SignupFormValidator signupFormValidator) {
        this.signupFormValidator = signupFormValidator;
        this.userService = userService;
    }

    @GetMapping("/signup")
    public String getSignUp(SignupForm signupForm) {
        logger.info("start -- show signup page.");
//        model.addAttribute("signupForm", signupForm);
        logger.info("end   -- show signup page.");
        return "signup";
    }

    @PostMapping("/signup")
    public String doSignup(@Validated SignupForm signupForm, BindingResult result, Model model) {
        logger.info("start -- do signup.");

        if(result.hasErrors()) {
            model.addAttribute("signupForm", signupForm);
            logger.info("end   -- do signup with error.");
            return "signup";
        }

        userService.register(signupForm.getUserName(), signupForm.getPassword());

        logger.info("end   -- do signup.");
        return "signupDone";
    }
}
