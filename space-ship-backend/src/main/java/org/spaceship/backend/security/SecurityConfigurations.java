package org.spaceship.backend.security;

import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableWebSecurity
public class SecurityConfigurations {

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        // TODO: 06/08/2023 Add login and logout
//        http.authorizeHttpRequests((requests) -> requests
////                    .requestMatchers("/", "/login").permitAll()
////                    .requestMatchers("/", "/logout").permitAll()
////                    .anyRequest().authenticated()
//                    .anyRequest().permitAll()
//                )
//                .formLogin((form) -> form
//                        .loginPage("/login")
//                        .permitAll()
//                )
//                .logout(LogoutConfigurer::permitAll);
//
//        return http.build();
//    }

//    @Bean
//    public UserDetailsService userDetailsService() {
//        // TODO: 05/08/2023 Solve this issue.
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user")
//                .password("password")
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }
}
