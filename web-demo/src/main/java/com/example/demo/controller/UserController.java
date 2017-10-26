package com.example.demo.controller;

import com.example.demo.domain.UserCreateForm;
import com.example.demo.domain.validator.UserCreateFormValidator;
import com.example.demo.service.user.IUserService;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Optional;

@Log4j
@Controller
public class UserController {

    @Autowired
    private IUserService userService;
    @Autowired
    private UserCreateFormValidator userCreateFormValidator;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(@RequestParam Optional<String> error) {
        log.debug("Getting login page, error={}" + error);
        return "login";
    }

    @RequestMapping("/success")
    public String success(Model model) {
        model.addAttribute("userName", "张三");
        return "index";
    }

    @RequestMapping("/")
    public String home(Model model) {
        model.addAttribute("userName", "张三");
        return "home";
    }
    @RequestMapping("/fail")
    public String fail(Model model) {
        model.addAttribute("userName", "张三");
        return "fail";
    }
    @InitBinder("form")
    public void initBinder(WebDataBinder binder) {
        binder.addValidators(userCreateFormValidator);
    }

    @PreAuthorize("@currentUserServiceImpl.canAccessUser(principal, #id)")
    @RequestMapping("/user/{id}")
    public ModelAndView getUserPage(@PathVariable Long id) {
        log.debug("Getting user page for user={}" + id);

        return new ModelAndView("user");
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.GET)
    public ModelAndView getUserCreatePage() {
        log.debug("Getting user create form");
        return new ModelAndView("user_create", "form", new UserCreateForm());
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @RequestMapping(value = "/user/create", method = RequestMethod.POST)
    public String handleUserCreateForm(@Valid @ModelAttribute("form") UserCreateForm form, BindingResult bindingResult) {
        log.debug(String.format("Processing user create form={%s}, bindingResult={%s}", form, bindingResult));
        if (bindingResult.hasErrors()) {
            // failed validation
            return "user_create";
        }
        try {
//            userService.create(form);
        } catch (DataIntegrityViolationException e) {
            // probably email already exists - very rare case when multiple admins are adding same user
            // at the same time and form validation has passed for more than one of them.
            log.warn("Exception occurred when trying to save the user, assuming duplicate email", e);
            bindingResult.reject("email.exists", "Email already exists");
            return "user_create";
        }
        // ok, redirect
        return "redirect:/users";
    }
}