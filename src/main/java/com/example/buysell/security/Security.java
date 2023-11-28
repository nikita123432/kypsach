package com.example.buysell.security;
import com.example.buysell.services.DetailsServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class Security{
    DetailsServiceImpl detailsService;;
    private BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder(8);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((request) -> request
                        .requestMatchers("/inpr/**", "/about/**").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/registr","/styles/**")
                        .permitAll().anyRequest().authenticated())
                .formLogin((form) -> form
                        .loginPage("/auth")
                        .permitAll()
                        .defaultSuccessUrl("/inpr").permitAll())
                .logout((logout) -> logout.permitAll());
        return http.build();
    }
    //отвечает за авторизацию с бд
    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider dao = new DaoAuthenticationProvider();
        dao.setUserDetailsService(detailsService);
        dao.setPasswordEncoder(encoder());
        return dao;
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(8);
    }

}
