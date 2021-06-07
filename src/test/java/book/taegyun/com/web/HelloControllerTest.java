package book.taegyun.com.web;

import book.taegyun.com.config.auth.SecurityConfig;
import book.taegyun.com.web.HelloController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//테스트를 진행할 때 JUnit에 내장된 실행자외에 다른 실행자를 실행시킨다.
//여기서는 SpringRunner라는 스프링 실행자를 사용함 즉, 스프링부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@RunWith(SpringRunner.class)
//여러 스프링 테스트 어노테이션 중, web에 집중할 수 있는 어노테이션이다.
//선언할 경우 @Controller, @ControllerAdvice등을 사용할 수 있다. 단, @Service, @Component, @Repositoy등은 사용할 수 업다.
// 여기서는 컨트롤러만 사용하기 때문에 사용
@WebMvcTest(controllers = HelloController.class, excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
})
public class HelloControllerTest {
    //스프링이 관리하는 빈을 주입받는다
    @Autowired
    //웹 API 테스트할 때 사용한다. 스프링 mvc테스트의 시작점
    private MockMvc mvc;

    @WithMockUser(roles = "USER")
    @Test
    public void hello_return() throws Exception{
        String hello = "hello";
        //mvc.perform(get"/hello") -> MockMvc를 통해 /hello주소로 http get요청을 한다. 체이닝 지원가능
        //.andExpect(status().isOk()) -> mvc.perform결과를 검증 흔히 알고있는 200,404,500등의 상태를 검증
        //.andExpect(content().string(hello)) -> mvc.perform의 결과를 검증 응답 본문의 내용을 검증 hello를 리턴하기 떄문에 이값이맞는지 검증
        mvc.perform(get("/hello")).andExpect(status().isOk()).andExpect(content().string(hello));
    }
    @WithMockUser(roles = "USER")
    @Test
    public void helloDto_return() throws Exception{
        String name = "hello";
        int amount = 1000;
        
        //param -> api테스트 할때 사용될 요청 파라미터를 설정한다 값은 String만 허용
        //jsonPath -> json 응답값을 필드별로 검증할 수 있는 메소드이다. $를 기준으로 필드명을 명시한다.
        mvc.perform(get("/hello/dto").param("name",name).param("amount",String.valueOf(amount)))
                .andExpect(status().isOk()).andExpect((ResultMatcher) jsonPath("$.name",is(name))).andExpect((ResultMatcher) jsonPath("$.amount",is(amount)));
    }
}
