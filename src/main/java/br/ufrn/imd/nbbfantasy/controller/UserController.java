package br.ufrn.imd.nbbfantasy.controller;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import br.ufrn.imd.nbbfantasy.command.UserRegistrationCommand;
import br.ufrn.imd.nbbfantasy.entity.User;
import br.ufrn.imd.nbbfantasy.service.UserService;

@Controller
@RequestMapping("/registration")
public class UserController {

    @Autowired
    private UserService userService;

    @ModelAttribute("user")
    public UserRegistrationCommand userRegistrationDto() {
        return new UserRegistrationCommand();
    }

    @GetMapping
    public String showRegistrationForm(Model model) {
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@ModelAttribute("user") @Valid UserRegistrationCommand userDto,
                                      BindingResult result){

        Optional<User> existing = userService.findByEmail(userDto.getEmail());
        if (existing.isEmpty()) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        } else if (result.hasErrors()){
            return "registration";
        }
        userService.save(userDto);
        return "redirect:/registration?success";
    }

}
