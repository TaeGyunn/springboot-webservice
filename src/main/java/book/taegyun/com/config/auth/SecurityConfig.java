package book.taegyun.com.config.auth;

import book.taegyun.com.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
//Spring Security 설정들을 활성화시켜준다.
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //csrf().disable.headers().frameOptions().disable()
        //h2-console 화면을 사용하기 위해 해당 옵션들을 disable합니다.
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                // URL별 권한관리를 설정하는 옵션의 시작점
                .authorizeRequests()
                // 권한 관리 대상을 지정하는 옵션. URL,HTTP메소드별로 관리 가능
                // "/"로 지정된 URL들은 permitAll()옵션을 통해 전체 열람 권한을 주었다.
                .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                // "/api/v1/**"주소를 가진 PAI는 USER권한을 가진 사람만 가능하도록 함
                .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                //anyRequest -> 설정된 값들 이외 나머지 URL을 나타냄
                //여기서 authenticated를 추가하여 나머지 url들은 로그인한 사용자들에게 허용
                .anyRequest().authenticated()
                .and()
                .logout()
                .logoutSuccessUrl("/")
                .and()
                // OAuth2로그인 기능에 대한 여러 설정의 진입점
                .oauth2Login()
                // OAuth2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당
                .userInfoEndpoint()
                // 소셜 로그인 성공시 후속 조치를 진행할 UserService 인터페이스의 구현체를 등록
                // 리소스 서버(즉, 소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시가능
                .userService(customOAuth2UserService);
    }

}
