package book.taegyun.com.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
// 1개 이상 Bean을 등록하고 있음을 명시하는 어노테이션
@Configuration
@EnableJpaAuditing  // JPA Auditing 활성화
//SpringBootApplication 어노테이션으로 인해 스프링 부트의 자동설정, 스프링 bean읽기와 생성을 모두 자동으로 설정된다.
//특히나 어노테션이 있는 위치부터 설정을 읽어가기 때문에 이 클래스는 항상 프로젝트의 최상단에 위치해야만 한다.
public class JpaConfig {
}
