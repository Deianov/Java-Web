package bg.softuni.oauth.web;

import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {

    @GetMapping("/user")
    private Map<String, Object> user(@AuthenticationPrincipal OAuth2User principal) {
        //If github - get login, if FB get name
        //id, name, email -> from FB
        //id, login, avatar_url -> from Github
        HashMap<String, Object> userProps = new HashMap<>();
        userProps.put("id", principal.getAttribute("id"));
        if (principal.getAttribute("login") == null) {
            userProps.put("username", principal.getAttribute("name"));
            userProps.put("mail", principal.getAttribute("email"));
            userProps.put("url", "/pic/3.png");
        } else {
            userProps.put("username", principal.getAttribute("login"));
            userProps.put("mail", "example@example.bg");
            userProps.put("url", principal.getAttribute("avatar_url"));
        }
        return userProps;
    }

    @GetMapping("/logout")
    public void logout(HttpSession httpSession) {
        httpSession.invalidate();
    }
}
