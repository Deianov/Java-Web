package exam.web;

import exam.model.service.UserServiceModel;
import javax.servlet.http.HttpSession;

public abstract class BaseController {
    protected static final String ORG_BINDING_RESULT = "org.springframework.validation.BindingResult.";

    public BaseController() {
    }

    public String redirect() {
        return "redirect:/";
    }

    public String redirect(String path) {
        return "redirect:" + (path == null ? "/" : path);
    }

    protected boolean isAuthorizedUser(HttpSession session) {

        if (session == null) {
            return false;
        }

        Object sessionUser = session.getAttribute("user");

        if (sessionUser != null) {
            UserServiceModel user = (UserServiceModel) sessionUser;
            return user != null;
        }
        return false;
    }
}
