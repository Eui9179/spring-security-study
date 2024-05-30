package site.leui.ssiach3ex1.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public class InMemoryUserDetailsService implements UserDetailsService {

    private final List<UserDetails> users;

    public InMemoryUserDetailsService(List<UserDetails> users) {
        this.users = users;
    }

    /*
     * 인증 구현은 loadUserByUsername(String username) 메서드를 호출해 주어진 사용자 이름을 가진 사용자의 세부 정보를 얻는다.

     * 인증 필터 -> 인증 관리자 -> 인증 공급자
     * AuthenticationFilter -> AuthenticationManager -> AuthenticationProvider

     * AuthenticationProvider가 인증 논리에서 UserDetailsService의 loadUserByUsername() 메서드를 이용해 세부정보를 로드
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return users.stream()
                .filter(
                        u -> u.getUsername().equals(username)
                )
                .findFirst()
                .orElseThrow(
                        () -> new UsernameNotFoundException("User not found")
                );
    }
}
