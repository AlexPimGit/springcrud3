package crud.config.handler;

import crud.model.Role;
import crud.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@Component
public class LoginSuccessHandler implements AuthenticationSuccessHandler {
/*
хэндлер (обработчик), содержащий в себе алгоритм действий при успешной аутентификации. Например,
тут мы можем отправить админа на админку после логина, а юзера на главную страницу сайта и тп
authentication- объект аутентификации, который был создан в процессе аутентификации.
 */
//Вызывается, когда пользователь успешно прошел аутентификацию.
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest,
                                        HttpServletResponse httpServletResponse,
                                        Authentication authentication) throws IOException, ServletException {
        User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        httpServletResponse.setStatus(HttpServletResponse.SC_OK);
        Set<Role> roles = authUser.getRoles();
        Role adminRole = new Role("ADMIN");
        Role userRole = new Role("USER");
        if(roles.contains(adminRole)){
            httpServletResponse.sendRedirect("/users");
        } else if(roles.contains(userRole)){
            httpServletResponse.sendRedirect("/userdata");

        }
    }
}