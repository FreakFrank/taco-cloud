package sia.tacocloud.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.tacocloud.enums.View;
import sia.tacocloud.objects.RegistrationForm;
import sia.tacocloud.repositories.UserRepository;

@Controller
@RequestMapping("/register")
public class RegistrationController {
    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public RegistrationController (UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping
    public String registerForm(){
        return View.REGISTRATION_FORM.getValue();
    }

    @PostMapping
    public String processRegistration (RegistrationForm form) {
        userRepository.save(form.toUser(passwordEncoder));
        return View.REDIRECT.getValue() + View.LOGIN.getValue();
    }
}
