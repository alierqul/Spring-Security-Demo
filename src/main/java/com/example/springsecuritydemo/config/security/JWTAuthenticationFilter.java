package com.example.springsecuritydemo.config.security;

import com.example.springsecuritydemo.service.UserService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationFilter extends OncePerRequestFilter {
    private UserService userService;
    private JWTTokenHelper jwtTokenHelper;

    public JWTAuthenticationFilter(UserService userService, JWTTokenHelper jwtTokenHelper) {
        this.userService =userService;
        this.jwtTokenHelper=jwtTokenHelper;

    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {


        String authToken=jwtTokenHelper.getToken(request);

        if(null!=authToken) {

            String userName=jwtTokenHelper.getUsernameFromToken(authToken);

            if(null!=userName) {

                UserDetails userDetails= userService.loadUserByUsername(userName);

                if(jwtTokenHelper.validateToken(authToken, userDetails)) {

                    UsernamePasswordAuthenticationToken authentication=new UsernamePasswordAuthenticationToken(userDetails, null,userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetails(request));

                    SecurityContextHolder.getContext().setAuthentication(authentication);

                }

            }

        }

        filterChain.doFilter(request, response);



    }
}
