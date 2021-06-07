package book.taegyun.com;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@SpringBootApplication
//Application 클래스는 앞으로 만들 프로젝트의 메인 클래스가 된다.
public class Application {
    public static void main(String[] args){
        /*main메소드에서 실행하는 SpringApplication.run으로 인해 내장 WAS를 실행한다.
        내장 WAS란 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는것을 이야기한다.
        이렇게 되면 항상 서버에 톰캣을 설치할 필요가 없게 되고, 스프 링 부트로 만들어진 Jar 파일0로 실행하면 된다.
        */
        SpringApplication.run(Application.class, args);
    }
}
