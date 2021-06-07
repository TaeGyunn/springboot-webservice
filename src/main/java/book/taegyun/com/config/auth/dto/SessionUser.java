package book.taegyun.com.config.auth.dto;

import book.taegyun.com.domain.user.User;
import lombok.Getter;

//java.io.Serializable 인터페이스를 상속받은 객체는 직렬화 할 수 있는 기본 조건입니다.
import java.io.Serializable;

@Getter
// 인증된 사용자 정보만 필요하다.
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}
