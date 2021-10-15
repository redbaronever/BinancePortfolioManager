package hnt.com;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().and()
                .authorizeRequests()
                .antMatchers("/index").hasRole("manager")
                //.anyRequest().authenticated()
                .and()
                .formLogin().loginPage("/portfolioLogin")
                .defaultSuccessUrl("/index")
                .loginProcessingUrl("/doLogin")
                .failureUrl("/portfolioLogin?error=true")
                .and().logout()
                .logoutUrl("/portfolioLogout")
                .logoutSuccessUrl("/portfolioLogin")
        ;
    }
}