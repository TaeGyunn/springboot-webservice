package book.taegyun.com.web;

import book.taegyun.com.service.posts.PostsService;
import book.taegyun.com.web.dto.PostsSaveRequestDto;
import book.taegyun.com.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")
    //@PathVariable은 url경로에 변수를 넣어주는것
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }
    @DeleteMapping("/api/v1/posts/{id}")
    public long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }

}

