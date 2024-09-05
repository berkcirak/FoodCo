package com.example.FoodCo.Config;

import com.example.FoodCo.Service.JWTService;
import com.example.FoodCo.Service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.context.ApplicationContext;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Component
public class JwtFilter extends OncePerRequestFilter {

    private JWTService jwtService;
    private ApplicationContext applicationContext;

    public JwtFilter(JWTService jwtService, ApplicationContext applicationContext){
        this.jwtService=jwtService;
        this.applicationContext=applicationContext;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        String token = null;
        String username=null;

        if (authHeader != null && authHeader.startsWith("Bearer ")){
            token = authHeader.substring(7); //token cıkarılır
            username = jwtService.extractUsername(token); //token dan username bilgisi alınır
        }
        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null){ //username null degilse ve kullanıcı henüz dogrulanmamıssa

            UserDetails userDetails = applicationContext.getBean(UserDetailsServiceImpl.class).loadUserByUsername(username); //username e karsılık gelen kullanıcı bilgileri alınır

            if (jwtService.validateToken(token, userDetails)){ //token gecerli mi?
                UsernamePasswordAuthenticationToken authToken=new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities()); //nesne olusturuldu
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request)); //request verileri alındı
                SecurityContextHolder.getContext().setAuthentication(authToken);
            }
        }
        filterChain.doFilter(request, response);
    }
}
