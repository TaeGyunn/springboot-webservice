package book.taegyun.com.web.dto;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HelloResponseDtoTest {

    @Test
    public void lombok_function_test(){
        //given
        String name = "test";
        int amount = 1000;

        //when
        HelloResponseDto dto = new HelloResponseDto(name,amount);

        //then
        /* assertj라는 테스트 검증 라이브러리 검증 메소드이다. 검증하고 싶은 대상을 메소드인자로 받는다. 메소드체이닝이 지원된다.
         Junit의 기본 assertThat이 아닌 assertj의 assertThat을 사용하였다.
         Junit과 비교하여 assertj의 장점은 다음과 같다.
         CoreMatchers와 달리 추가적으로 라이브러리가 필요하지 않다.
         자동완성이 좀 더 확실하게 지원된다.
         */
        // isEqualTo는 assertj의 동등 비교 메소드이다. 같은때만 성공이다.
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);

    }
}
