package site.leui.ssiach7;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
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
        http.httpBasic();

        http.csrf().disable();

        // 모든 요청에 대해 액세스를 허용한다.
//        http.authorizeRequests()
//                .anyRequest()
//                .permitAll();

        // 사용자가 엔드포인트에 접근하기 위한 조건 지정
//        http.authorizeRequests()
//                .anyRequest()
//                .hasAuthority("WRITE");

        // SpEL을 사용한 권한 확인
        http.authorizeRequests()
                .mvcMatchers(HttpMethod.POST, "/hello").permitAll()
                .mvcMatchers(HttpMethod.GET, "/hello").authenticated()
                .anyRequest().access("hasAuthority('READ') and !hasAuthority('DELETE')");
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails user1 = User.withUsername("john")
                .password("12345")
                .authorities("READ")
                .build();

        UserDetails user2 = User.withUsername("jane")
                .password("12345")
                .authorities("READ", "WRITE", "DELETE")
                .build();

        manager.createUser(user1);
        manager.createUser(user2);

        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

}
