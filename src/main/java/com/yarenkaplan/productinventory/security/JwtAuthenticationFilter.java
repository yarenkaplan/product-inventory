package com.yarenkaplan.productinventory.security;

import com.yarenkaplan.productinventory.services.auth.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtService jwtService, UserDetailsService userDetailsService) {
        this.jwtService = jwtService;
        this.userDetailsService = userDetailsService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        //getting header info that carries credential info about request
        String authHeader = request.getHeader("Authorization");

        //if there is no jwt then there is nothing to proceed
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        //removing starting with "Bearer " part
        String jwtToken = authHeader.substring(7);

        //extracting username from jwt
        String username = jwtService.extractUsername(jwtToken);

        //if extracted username is not authenticated, then proceed
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            //loading username from the system
            UserDetails userDetails = userDetailsService.loadUserByUsername(username);

            //checking whether this jwt belong to related user or not
            if (jwtService.isTokenValid(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }

        //finishing jwt process and letting request continue
        filterChain.doFilter(request, response);
    }
}
