package com.notedown.api.configuration.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.notedown.api.configuration.security.JwtTokenUtil;
import com.notedown.api.configuration.security.JwtUser;
import com.notedown.api.configuration.security.user.Role;
import com.notedown.api.configuration.security.user.User;
import com.notedown.api.configuration.security.user.UserService;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

@RestController
public class UserRestController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    @Qualifier("jwtUserDetailsService")
    private UserDetailsService userDetailsService;
    
    @Autowired
    private UserService userService;

    @RequestMapping(value = "user", method = RequestMethod.GET)
    public JwtUser getAuthenticatedUser(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader).substring(7);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        return (JwtUser) userDetailsService.loadUserByUsername(username);
    }
    
//    @RequestMapping(value = "${jwt.route.authentication.path}/registration", method = RequestMethod.POST)
//    public String registerUser(HttpServletRequest request) {
//        String token = request.getHeader(tokenHeader).substring(7);
//        String username = jwtTokenUtil.getUsernameFromToken(token);
//        return (JwtUser) userDetailsService.loadUserByUsername(username);
//    }
    
    
    
    @org.springframework.transaction.annotation.Transactional
    void createUserIfNotFound(String username, String password, String firstname,
                              String lastname, String email, boolean enabled,
                              Date lastPasswordResetDate, List<Role> roles) {
        boolean existsUsersByEmail = userService.existUserByEmail(email);
        if (!existsUsersByEmail) {
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setFirstname(firstname);
            user.setLastname(lastname);
            user.setEmail(email);
            user.setEnabled(enabled);
            user.setLastPasswordResetDate(lastPasswordResetDate);
            user.setRoles(roles);
            userService.save(user);
        }
    }

}
