package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.security.Principal;

@Controller
//@RequestMapping("app")
public class LoginController {

    @Autowired
    private UserRepository userRepository;

//    public LoginController(OAuth2AuthorizedClientService authorizedClientService) {
//        this.authorizedClientService = authorizedClientService;
//    }

    @GetMapping("/login")
    public ModelAndView login() {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("login");
        return mav;
    }

    @GetMapping("secure/article-details")
    public ModelAndView getAllUserArticles() {
        ModelAndView mav = new ModelAndView();
        mav.addObject("users", userRepository.findAll());
        mav.setViewName("user/list");
        return mav;
    }

    @GetMapping("/error")
    public ModelAndView error() {
        ModelAndView mav = new ModelAndView();
        String errorMessage= "You are not authorized for the requested data.";
        mav.addObject("errorMsg", errorMessage);
        mav.setViewName("403");
        return mav;
    }

    @PostMapping("/app-logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if(auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/?logout";
    }

//    @RequestMapping("/*")
//    public String getUserOauth(Principal user) {
//        StringBuffer userInfo= new StringBuffer();
//
//        return userInfo.toString();
//    }

//    private StringBuffer getOauth2LoginInfo(Principal user){
//        StringBuffer protectedInfo = new StringBuffer();
//        OAuth2AuthenticationToken authToken = ((OAuth2AuthenticationToken) user);
//        OAuth2AuthorizedClient authClient = this.authorizedClientService.loadAuthorizedClient(authToken.getAuthorizedClientRegistrationId(), authToken.getName());
//        return protectedInfo;
//    }

//    private StringBuffer getUsernamePasswordLoginInfo(Principal user)
//    {
//        StringBuffer usernameInfo = new StringBuffer();
//
//        UsernamePasswordAuthenticationToken token = ((UsernamePasswordAuthenticationToken) user);
//        if(token.isAuthenticated()){
//            User u = (User) token.getPrincipal();
//            usernameInfo.append("Welcome, " + u.getUsername());
//        }
//        else{
//            usernameInfo.append("NA");
//        }
//        return usernameInfo;
//    }
}
