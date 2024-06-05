package site.leui.ssiach6.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final AuthenticationProviderService authenticationProviderService;

    public SecurityConfig(AuthenticationProviderService authenticationProviderService) {
        this.authenticationProviderService = authenticationProviderService;
    }

    String[] PERMIT_ALL = {"/h2-console/**"};

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .defaultSuccessUrl("/main", true)
            .and()
                .authorizeHttpRequests()
                .antMatchers(PERMIT_ALL)
                .permitAll()
            .and()
                .csrf()
                .ignoringAntMatchers("/h2-console/**")
            .and()
                .headers()
                .frameOptions()
                .sameOrigin()
        ;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authenticationProviderService);
    }
}
