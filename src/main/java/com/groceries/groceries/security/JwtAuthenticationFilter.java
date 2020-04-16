package com.groceries.groceries.security;

import com.groceries.groceries.repositories.UserRepository;
import com.groceries.groceries.utils.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Autowired
    UserRepository userRepository;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        String header = req.getHeader(AppConstants.keyAuthorisation);
        String email = null;
        String authToken = null;
        /*if (header != null)
        {
            authToken = header;
            try {
                email = jwtTokenUtil.getUsernameFromToken(authToken);

                Users users = userRepository.findByEmail(email);

                if(authToken.equals(users.getJwt_token())){
                    // Do nothing
                }else{
                    email = null;
                }

            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            }
        } else {
            logger.warn("couldn't find authorization string, will ignore the header");
        }*/
        if (header != null) {
            authToken = header;
            try {
                email = jwtTokenUtil.getUsernameFromToken(authToken);

//                Users users = userRepository.findByEmail(email);
//
//                if (users.getJwt_token() == null || !users.getJwt_token().matches(header))
//                    email = null;
            } catch (IllegalArgumentException e) {
                logger.error("an error occured during getting username from token", e);
            }
        } else {
            logger.warn("couldn't find bearer string, will ignore the header");
        }
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(email);

            System.out.println("userDetails" + userDetails);

            //if (jwtTokenUtil.validateToken(authToken, userDetails))
            // {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(userDetails, null, Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN")));
            authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(req));
            logger.info("authenticated user " + email + ", setting security context");
            SecurityContextHolder.getContext().setAuthentication(authentication);
            //}
        }

        chain.doFilter(req, res);
    }
}