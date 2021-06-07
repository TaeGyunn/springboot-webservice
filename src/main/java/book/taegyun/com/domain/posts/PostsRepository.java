package book.taegyun.com.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/*  보통 ibatis나 MyBatis 등에서 Dao(Data Access Object)라고 불리는 DB Layer접근자이다.
*   JPA에선 Repository라고 부르며 인터페이스로 생성한다.
*   단순히 인터페이스를 생성 후, JpaRepository<Entity클래스, PK타입>를 상속하면 기본적인 CRUD메소드가 자동으로 생성된다.
*   @Repository를 추가할 필요도 없다. 여기서 주의할점은 Entity클래스와 기본 Entity Repository는 함께 위치해야한다.
*   둘은 밀접한 관계이고, Entity클래스는 기본 Repository없이는 제대로 역할을 할 수가 없다.
* */
public interface PostsRepository extends JpaRepository<Posts, Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
