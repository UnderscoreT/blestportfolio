package xyz.obeyblessingportfolio.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.obeyblessingportfolio.data.dto.UserCredentialsDto;
import xyz.obeyblessingportfolio.data.user.UserProfile;
import xyz.obeyblessingportfolio.services.RegistrationService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api/v1/registration")
public class RegistrationController {
     private final RegistrationService registrationService;
    @PostMapping("/newuser")
    public String registerNewUser(@ModelAttribute("newUser") UserCredentialsDto userCredentialsDto) {
        if(Objects.isNull(userCredentialsDto))return "redirect:/register?noCreds";
    if(registrationService.userExists(userCredentialsDto))
            return "redirect:/register?userExists";
    if(!Objects.equals(userCredentialsDto.getPassword(), userCredentialsDto.getRePassword()))
        return "redirect:/register?passMissMatch";
        var dtf  = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        var now = LocalDateTime.now();
        var model = new UserProfile();
        model.setUsername(userCredentialsDto.getUsername());
        model.setEmail(userCredentialsDto.getEmail());
        model.setEncryptedPassword(new BCryptPasswordEncoder().encode(userCredentialsDto.getPassword()));
        //should use a js to make sure that 2 passwords really match b4 submitting
        model.setCreatedOn(dtf.format(now));
        model.setLastUpdated(model.getCreatedOn());
        registrationService.save(model);
        return "redirect:/index";
     }
}
