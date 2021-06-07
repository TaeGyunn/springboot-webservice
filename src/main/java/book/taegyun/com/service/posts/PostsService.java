package book.taegyun.com.service.posts;

import book.taegyun.com.domain.posts.Posts;
import book.taegyun.com.domain.posts.PostsRepository;
import book.taegyun.com.web.dto.PostsListResponseDto;
import book.taegyun.com.web.dto.PostsResponseDto;
import book.taegyun.com.web.dto.PostsSaveRequestDto;
import book.taegyun.com.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {
    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto){
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto){
        // IllegalArgumentException <- 잘못된 인자를 넘어줬을때 생기는 오류
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("해당 게시글이 없습니다. id="+ id));
        posts.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostsResponseDto findById(Long id){
        Posts entity = postsRepository.findById(id).orElseThrow(() -> new IllegalArgumentException(
                "해당 게시글이 없습니다. id=" + id));

        return new PostsResponseDto(entity);
    }
    //readonly=true을 주게되면 트랜잭션 범위는 유지하되, 조회기능만 남겨두어 조회속도가 개선됨
    @Transactional(readOnly = true)
    public Object findAllDesc(){
        //map(PostsListResponseDto::new) -> .map(posts -> new PostListResponseDto(posts))
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id){
        Posts posts = postsRepository.findById(id).orElseThrow(() -> new
                IllegalArgumentException("해당 게시글이 없습니다. id=" + id));
        //JpaRepository에서 이미 delete 메소드를지원하고 있으니 이를 활용
        // 엔티티를 파라미터로 삭제할 수도 있고, deleteById 메소드를 이용하면 id로 삭제할 수 도 있다.
        // 존재하는 Posts인지 확인을 위해 엔티티 조회 후 그대로 삭제한다.
        postsRepository.delete(posts);
    }
}
