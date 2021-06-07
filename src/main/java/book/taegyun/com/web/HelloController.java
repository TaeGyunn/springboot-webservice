package book.taegyun.com.web;

import book.taegyun.com.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/* 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줍니다.
*  예전에는 @ResponseBody를 각 메소드마다 선언했던 것을 한번에 사용할 수 있게 해준다고 생각하면 된다.
*/
@RestController
public class HelloController {
    //Http Method인 Get의 요청을 받을 수 있는 API를 만들어줍니다.
    @GetMapping("/hello")
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    //@RequestParam -> 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션이다.
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        return new HelloResponseDto(name, amount);
    }
}
