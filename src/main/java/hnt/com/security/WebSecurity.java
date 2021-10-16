package hnt.com.security;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .authorizeRequests()
                    .antMatchers("/index", "/").hasRole("IN")
                    .antMatchers("doLogin").permitAll()
                .and().formLogin().loginPage("/portfolioLogin")
                    .defaultSuccessUrl("/index")
                    .loginProcessingUrl("/doLogin")
                    .failureUrl("/portfolioLogin?error=true")
                .and().logout()
                    .logoutUrl("/portfolioLogout")
                    .logoutSuccessUrl("/portfolioLogin")
                .and().sessionManagement()
                    .invalidSessionUrl("/portfolioLogin")
                    .sessionFixation().migrateSession()
        ;
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return new UserDetailsServiceImpl();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new CustomPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProvider());
    }
}