## 스프링 시큐리티에서 인증 흐름을 위한 주 계약을 나타내는 인터페이스

- UserDetails: 스프링 시큐리티가 관리하는 사용자를 나타낸다.
- GrantedAuthority: 애플리케이션의 목적 내에서 사용자에게 허용되는 작업을 정의한다. (e.g. 읽기, 쓰기, 삭제 등)
- UserDetailsService: 사용자 이름으로 사용자 세부 정보를 검색하는 객체를 나타낸다.
- UserDetailsManager: UserDetailsService 보다 더 구체적인 계약이다. 사용자 이름으로 검색하는 것 이외에 사용자 컬렉션이나 특정 사용자를 변경할 수도 있다.
- PasswordEncoder: 암호를 암호화 또는 해시하는 방법과 주어진 인코딩된 문자열을 일반 텍스트 암호화 비교하는 방법을 지정한다.
