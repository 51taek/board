package blending.board.service;

import blending.board.config.jwt.AuthUtils;
import blending.board.dto.lesson.LessonMemberResponse;
import blending.board.dto.lesson.LessonRequest;
import blending.board.dto.lesson.LessonResponse;
import blending.board.dto.lesson.RegisterLessonRequest;
import blending.board.entity.LessonMember;
import blending.board.entity.Member;
import blending.board.entity.Lesson;
import blending.board.repository.LessonMemberRepository;
import blending.board.repository.LessonRepository;
import blending.board.repository.MemberRepository;
import blending.board.result.exception.LessonNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class LessonService {

    private final LessonRepository lessonRepository;
    private final AuthUtils authUtils;
    private final LessonMemberRepository lessonMemberRepository;
    private final MemberRepository memberRepository;

    public Page<LessonResponse> findAll() {
        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<Lesson> all = lessonRepository.findAll(pageRequest);

        return all.map(post -> new LessonResponse(post.getId(), post.getTitle(),
                post.getContent(), post.getPrice(), post.getTime(), post.getTeacher().getName()));
    }

    public void upload(LessonRequest lessonRequest) {

        //TODO: JWT를 이용해서 로그인 유저 정보 가져오기

        Member loginMember = authUtils.getLoginMember();

        Lesson lesson = Lesson.builder()
                .title(lessonRequest.getTitle())
                .content(lessonRequest.getContent())
                .price(lessonRequest.getPrice())
                .time(lessonRequest.getTime())
                .teacher(loginMember)
                .build();

        lessonRepository.save(lesson);
    }

    public String delete(UUID postId) {

        //TODO: 예외처리
        // 현재 : RUNTIME -> 변경 후 : 커스텀한 POST_NOT_FOUND
        Lesson lesson = lessonRepository.findById(postId)
                .orElseThrow(RuntimeException::new);

        lessonRepository.delete(lesson);

        return "게시물 삭제 성공";
    }

    public LessonResponse findOne(UUID postId) {
        Lesson lesson = lessonRepository.findById(postId)
                .orElseThrow(RuntimeException::new);

        LessonResponse lessonResponse = LessonResponse.builder()
                .id(postId)
                .title(lesson.getTitle())
                .content(lesson.getContent())
                .price(lesson.getPrice())
                .time(lesson.getTime())
                .writer(lesson.getTeacher().getName())
                .build();

        return lessonResponse;
    }

    public void register(RegisterLessonRequest request) {
        Lesson lesson = lessonRepository.findById(request.getId())
                .orElseThrow(LessonNotFoundException::new);

        log.info("lesson = {}", lesson);

        Member loginMember = authUtils.getLoginMember();

        log.info("loginMember = {}", loginMember);

        LessonMember lessonMember = LessonMember.builder()
                .lesson(lesson)
                .member(loginMember)
                .build();

        log.info("lessonMember = {}", lessonMember);


        lessonMemberRepository.save(lessonMember);

        lesson.addLessonMember(lessonMember);
        loginMember.addLessonMember(lessonMember);

        lessonRepository.save(lesson);
        memberRepository.save(loginMember);
    }

    public Page<LessonMemberResponse> findLessonMembers(UUID lessonId) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(LessonNotFoundException::new);

        List<LessonMember> lessonMembers = lesson.getLessonMembers();

        List<LessonMemberResponse> lessonMemberResponses = lessonMembers.stream()
                .map(lessonMember ->
                        new LessonMemberResponse(lessonMember.getMember().getId(), lessonMember.getMember().getName()))
                .collect(Collectors.toList());

        PageRequest pageRequest = PageRequest.of(0, 10);
        int start = (int)pageRequest.getOffset();
        int end = (start + pageRequest.getPageSize()) > lessonMemberResponses.size() ? lessonMemberResponses.size() : (start + pageRequest.getPageSize());
        Page<LessonMemberResponse> lessonMemberPage = new PageImpl<>(lessonMemberResponses.subList(start, end), pageRequest, lessonMemberResponses.size());

        return lessonMemberPage;
    }

}
