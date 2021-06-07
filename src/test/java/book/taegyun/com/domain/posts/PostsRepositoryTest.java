package book.taegyun.com.domain.posts;

import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

// save, fineAll기능을 테스트할수있다.

//테스트를 진행할 때 JUnit에 내장된 실행자외에 다른 실행자를 실행시킨다.
//여기서는 SpringRunner라는 스프링 실행자를 사용함 즉, 스프링부트 테스트와 JUnit 사이에 연결자 역할을 한다.
@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {
    //빈주입
    @Autowired
    PostsRepository postsRepository;
    //Junit에서 단위테스트가 끝날때마다 수행되는 메소드를 지정
    //보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침범을 막기 위해 사용한다.
    //여러 테스트가 동시에 수행디면 테스트용 데이터베이스인 h2에 데이터가 그래도 남아 다음 테스트 실행시 실패할 수 있다.
    @After
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";
        //테이블 posts에 insert/update쿼리를 실행시킨다.
        //id값이 있다면 update, 없다면 insert쿼리가 실행된다.
        postsRepository.save(Posts.builder().title(title).content(content).author("taekuen98@naver.com").build());

        //when
        //테이블 posts에 있는 모든 데이터를 조회해오는 메소드
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){

        //given
        LocalDateTime now = LocalDateTime.of(2021,5,26,0,0,0);
        postsRepository.save(Posts.builder()
        .title("title").content("content").author("author").build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate=" + posts.getCreateDate()+" modifiedDate="+posts.getModifiedDate());

        assertThat(posts.getCreateDate().isAfter(now));
        assertThat(posts.getModifiedDate().isAfter(now));
    }

}
