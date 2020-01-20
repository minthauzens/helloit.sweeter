package lv.helloit.bootcamp.sweeter.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
@EnableWebSecurity
//@EnableScheduling
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final SweeterAuthenticationProvider sweeterAuthenticationProvider;

    public WebSecurityConfig(SweeterAuthenticationProvider sweeterAuthenticationProvider) {
        this.sweeterAuthenticationProvider = sweeterAuthenticationProvider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authenticationProvider(sweeterAuthenticationProvider)
                .sessionManagement()
                    .and()
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/sign-up", "/styles.css", "/sweetScripts.js").permitAll()
                    .antMatchers("/actuator/**").permitAll()
                    .anyRequest().authenticated()
                    .and()
                .formLogin()
                    .loginPage("/login").permitAll()
                    .defaultSuccessUrl("/sweets")
                    .failureUrl("/login?error=true")
                    .and()
                .logout()
                .permitAll();
    }

    @Bean
    @Override
    public UserDetailsService userDetailsService() {
        UserDetails user =
                User.withDefaultPasswordEncoder()
                        .username("test")
                        .password("test")
                        .roles("USER")
                        .build();

        return new InMemoryUserDetailsManager(user);
    }

//    @Scheduled(cron)
//    public void dummy() {
//        System.out.println(System.currentTimeMillis());
//    }
}