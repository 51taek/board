package blending.board.controller;

import blending.board.dto.member.ProfileResponse;
import blending.board.result.ResultResponse;
import blending.board.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("post")
public class PostController {

    private final PostService postService;

//    @PostMapping
//    public ResponseEntity<ResultResponse> upload(UploadPostRequest request) {
//        postService.upload();
//        return ResponseEntity.ok().body(ResultResponse.of(UploadPos))
//    }
}
