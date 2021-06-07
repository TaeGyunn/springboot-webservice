package book.taegyun.com.domain.user;

import book.taegyun.com.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
//사용자정보를 담당할 도메인 User 클래스

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private  String picture;
    
    // JPA로 데이터베이스로 저장할 때 Enum값을 어떤 형태로 저장할지를 결정
    // 기본적으로 int로 된 숫자가 저장
    // 숫자로 저장되면 데이터베이스로 확일할 때 그값이 무슨 코드를 의미하는지 알 수가 없다.
    // 그래서 문자열(EnumType.STRING)로 저장될 수 있도록 선언한다ㄴ
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    public User(String name, String email, String picture, Role role){
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture){
        this.name = name;
        this.picture = picture;

        return this;
    }

    public String getRoleKey(){
        return this.role.getKey();
    }

}
