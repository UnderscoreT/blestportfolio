package xyz.obeyblessingportfolio.web;

import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import xyz.obeyblessingportfolio.data.dto.UserCredentialsDto;
import xyz.obeyblessingportfolio.utils.Logger;

@Controller
@RequiredArgsConstructor
@RequestMapping("")
public class SitemapController {

    @GetMapping({"/", "/index","/home"})
    public String getHomePage(HttpServletRequest request) {
        var message = "Remote connection from user: %s (%s)".formatted(request.getRemoteUser(), request.getRemoteAddr());
        Logger.getLogger().printMessage(message);
        return "index";
    }

    @GetMapping({"/island"})
    public String getIslandPage(HttpServletRequest request) {
        var message = "Remote connection from user: %s (%s)".formatted(request.getRemoteUser(), request.getRemoteAddr());
        Logger.getLogger().printMessage(message);
        return "island";
    }

    @GetMapping({"/register"})
    public String getRegistrationPage(HttpServletRequest request, Model model) {
        var message = "Remote connection from user: %s (%s)".formatted(request.getRemoteUser(), request.getRemoteAddr());
        Logger.getLogger().printMessage(message);

        var newUser = new UserCredentialsDto();
        model.addAttribute("newUser", newUser);

        return "register";
    }
    @GetMapping({"/misc"})
    public String getMiscPage(HttpServletRequest request) {
        var message = "Remote connection from user: %s (%s)".formatted(request.getRemoteUser(), request.getRemoteAddr());
        Logger.getLogger().printMessage(message);
        return "misc";
    }

}
