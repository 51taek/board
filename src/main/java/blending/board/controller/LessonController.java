package blending.board.controller;

import blending.board.dto.lesson.LessonMemberResponse;
import blending.board.dto.lesson.LessonRequest;
import blending.board.dto.lesson.LessonResponse;
import blending.board.dto.lesson.RegisterLessonRequest;
import blending.board.entity.LessonMember;
import blending.board.result.ResultCode;
import blending.board.result.ResultResponse;
import blending.board.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static blending.board.result.ResultCode.*;

@RestController
@RequestMapping("lesson")
@RequiredArgsConstructor
@Slf4j
public class LessonController {

    private final LessonService lessonService;

    @GetMapping("/all")
    public ResponseEntity<Page<LessonResponse>> findAll() {
        return ResponseEntity.ok().body(lessonService.findAll());
    }

    @PostMapping("")
    public ResponseEntity<ResultResponse> upload(@RequestBody LessonRequest lessonRequest){
        lessonService.upload(lessonRequest);
        return ResponseEntity.ok().body(ResultResponse.of(UploadLessonSuccess));
    }

    @DeleteMapping("/{lessonId}")
    public ResponseEntity<String> delete(@PathVariable UUID lessonId) {
        return ResponseEntity.ok().body(lessonService.delete(lessonId));
    }

    @GetMapping("/{lessonId}")
    public ResponseEntity<LessonResponse> findOne(@PathVariable UUID lessonId) {
        return ResponseEntity.ok().body(lessonService.findOne(lessonId));
    }

    @PostMapping("/register")
    public ResponseEntity<ResultResponse> register(@RequestBody RegisterLessonRequest request){
        log.info("requets = {}", request);
        lessonService.register(request);
        return ResponseEntity.ok().body(ResultResponse.of(RegisterLessonSuccess));
    }

    @GetMapping("/{lessonId}/members")
    public ResponseEntity<Page<LessonMemberResponse>> findLessonMembers(@PathVariable UUID lessonId){
        return ResponseEntity.ok().body(lessonService.findLessonMembers(lessonId));
    }
}
