package com.springsecurity.jwt.config;

import com.springsecurity.jwt.service.EmployeeService;
import com.springsecurity.jwt.util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Slf4j
@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private EmployeeService employeeService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String headerRequest = request.getHeader("Authorization");
        String userEmail = null;
        if (headerRequest != null && headerRequest.startsWith("Bearer ")) {

            try {
                userEmail = jwtUtil.getUsernameFromToken(headerRequest.substring(7));
            } catch (Exception e) {
                e.printStackTrace();
            }

            UserDetails userDetails = employeeService.loadUserByUsername(userEmail);

            if (userEmail != null && SecurityContextHolder.getContext().getAuthentication() == null) {

                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                        new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            } else {
                System.out.println("Token is not validated");
            }
        }

        filterChain.doFilter(request, response);
    }
}