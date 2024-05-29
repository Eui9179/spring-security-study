##엔드포인트 권한 부여 구성 재정의

기본적으로 아무 설정을 하지 않으면 HTTP Basic 인증 방법을 사용한다. 대부분 애플리케이션에서는 적합하지 않아서 `WebSecurityConfigurerAdapter` 클래스를 재정의하여 사용한다.
