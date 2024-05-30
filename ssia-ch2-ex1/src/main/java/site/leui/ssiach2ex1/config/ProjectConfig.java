package site.leui.ssiach2ex1.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

@Configuration
public class ProjectConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .httpBasic()
                    .and()
                .authorizeRequests()
                .anyRequest().authenticated(); // 모든 요청에 인증이 필요함

        /*
        // 모든 요청을 허용함
            .and()
        .authorizeRequests()
                .anyRequest().permitAll();
         */
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager userDetailsService = new InMemoryUserDetailsManager();

        // 사용자 생성
        UserDetails user = User.withUsername("test")
                .password("test")
                .authorities("read")
                .build();

        // UserDetailsService에서 관리하도록 사용자 추가
        userDetailsService.createUser(user);

        return userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        // UserDetailsService 재정의 시 필수적으로 재정의해야 한다.
        return NoOpPasswordEncoder.getInstance();
    }
}
