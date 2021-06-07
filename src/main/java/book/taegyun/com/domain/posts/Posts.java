package book.taegyun.com.domain.posts;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

/*  롬복은 자바 개발할 때 자주사용하는 getter,setter,기본생성자,toString등을 어노테이션으로 자동생성해준다.
 *  서비스 초기 구축단계에선 테이블 설계(여기선 Entity)설계가 빈번하게 변경되는데, 이때 롬복의 어노테이션들은 코드 변경량을 최소화시켜주기
 *  때문에 적극적으로 사용된다.
 */
import book.taegyun.com.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/*어노테이션 순서를 주요 어노테이션을 클래스에 가깝게 둔다.
    @Eintity는 JPA(자바 표준 명세서)의 어노테이션이며, @Getter와 @NoArgsConstructor은 롬복의 어노테이션이다.
    롬복 콤드는 단순화시켜주기는 하지만 필수 어노테이션은 아니다. 그러다 보니 @Entity를 클래스에 더 가깝게 두었다.
*/
// 클래스 내 모든 필드의 Getter메소드를 자동생성
@Getter
//기본 생성자 자동 추가  public Posts(){}와 같은 효과
@NoArgsConstructor
//테이블과 링크될 크래스임을 나타낸다.
@Entity
/*여기서 Posts클래스는 실제 DB의 테이블과 매칭될 클래스이며 Entity클래스라고도 한다.
  JPA를 사용하면 DB데이터에 작업할 경우 실제 쿼리를 날리기보다는, 이 Entity클래스의 수정을 통해 작업한다.
*/
public class Posts extends BaseTimeEntity {
    //해당 테이블의 PK필드를 나타낸다.
    @Id
    //PK생성 규칙을 나타낸다.
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    //테이블의 컬럼을 나타내며 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 된다.
    //사용하는 이유는, 기본값 외에 축로 변경이 필요한 옵션이 있으면 사용 ex)문자열의 경우 varchar(255)가 기본이지만 사이즈를 500으로 늘릴때
    //왠만하면 Entity의 PK는 Long타입의 Auto_increment를 추천한다.
    @Column(length = 500, nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;
    
    //해당 클래스의 빌더 패턴 클래스를 생성
    //생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    @Builder
    public Posts(String title, String content, String author){
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
