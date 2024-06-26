package site.leui.ssiach5ex1;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(
            HttpServletRequest request,
            HttpServletResponse response,
            Authentication authentication
    ) throws IOException, ServletException {

        // read 권한만 가능
        Optional<? extends GrantedAuthority> auth = authentication.getAuthorities().stream()
                .filter(a -> a.getAuthority().equals("read"))
                .findFirst();

        if (auth.isPresent()) {
            response.sendRedirect("/home");
        } else {
            response.sendRedirect("/error");
        }
    }
}
