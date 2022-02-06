package me.codinginterview.techinterviewserver.presentation.handler;

import lombok.AllArgsConstructor;
import me.codinginterview.techinterviewserver.domain.user.User;
import me.codinginterview.techinterviewserver.domain.user.UserRegisterService;
import me.codinginterview.techinterviewserver.presentation.utils.UserMapper;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/register")
@AllArgsConstructor
public class UserController {
    private final UserRegisterService userRegisterService;

    @GetMapping
    public void register(HttpServletResponse httpServletResponse) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication instanceof OAuth2AuthenticationToken) {
            User savedUser = userRegisterService.register(UserMapper.fromOauth2(((OAuth2AuthenticationToken) authentication).getAuthorizedClientRegistrationId(),
                                                                                ((OAuth2AuthenticationToken) authentication).getPrincipal()));
            Cookie sessionCookie = new Cookie(CookieName.SESSION.name(), savedUser.getSession());
            sessionCookie.setPath("/");
            httpServletResponse.addCookie(sessionCookie);
        }
        httpServletResponse.setHeader(HttpHeaders.LOCATION, "/");
        httpServletResponse.setStatus(HttpStatus.FOUND.value());
    }
}
