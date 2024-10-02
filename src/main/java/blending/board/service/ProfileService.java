package blending.board.service;

import blending.board.config.jwt.AuthUtils;
import blending.board.dto.lesson.LessonResponse;
import blending.board.dto.member.ChangePasswordRequest;
import blending.board.dto.member.ProfileResponse;
import blending.board.dto.musicData.MusicDataResponse;
import blending.board.dto.musicData.MyMusicDataResponse;
import blending.board.dto.profile.CreateProfileRequest;
import blending.board.entity.LessonMember;
import blending.board.entity.Member;
import blending.board.entity.Lesson;
import blending.board.entity.MusicData;
import blending.board.repository.LessonMemberRepository;
import blending.board.repository.MemberRepository;
import blending.board.repository.LessonRepository;
import blending.board.repository.MusicDataRepository;
import blending.board.result.exception.MemberNotFoundException;
import blending.board.result.exception.PasswordIsNotNewException;
import blending.board.result.exception.PasswordNotMatchException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProfileService {

    private final MemberRepository memberRepository;
    private final LessonRepository lessonRepository;
    private final LessonMemberRepository lessonMemberRepository;
    private final MusicDataRepository musicDataRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final AuthUtils authUtils;

    public ProfileResponse myProfile() {
        Member loginMember = authUtils.getLoginMember();

        return ProfileResponse.builder()
                .name(loginMember.getName())
                .musicInterest(loginMember.getMusicInterest())
                .musicGenreLike(loginMember.getMusicGenreLike())
                .profileMusic(loginMember.getProfileMusic())
                .build();
    }

    public void createProfile(CreateProfileRequest request) {
        Member loginMember = authUtils.getLoginMember();

        loginMember.createProfile(
                request.getMusicInterest(),
                request.getMusicGenreLike(),
                request.getProfileMusic());

        memberRepository.save(loginMember);
    }

    public void changePassword(ChangePasswordRequest request) {

        Member loginMember = authUtils.getLoginMember();

        if (!bCryptPasswordEncoder.matches(request.getOldPassword(), loginMember.getPassword())) {
            throw new PasswordNotMatchException();
        }

        if (request.getOldPassword().equals(request.getNewPassword())) {
            throw new PasswordIsNotNewException();
        }

        final String newPassword = bCryptPasswordEncoder.encode(request.getNewPassword());
        loginMember.changePassword(newPassword);
        memberRepository.save(loginMember);
    }

    public List<LessonResponse> myLessons() {

        Member loginMember = authUtils.getLoginMember();
        log.info("loginMember = {}", loginMember);

        List<LessonMember> memberLessons = loginMember.getMemberLessons();

        log.info("member lesson = {}", memberLessons);

        List<LessonResponse> lessonResponseList= new ArrayList<>();

        for (LessonMember memberLesson : memberLessons) {
            LessonResponse lessonResponse = new LessonResponse(
                    memberLesson.getLesson().getId(),
                    memberLesson.getLesson().getTitle(),
                    memberLesson.getLesson().getContent(),
                    memberLesson.getLesson().getPrice(),
                    memberLesson.getLesson().getTime(),
                    memberLesson.getLesson().getTeacher().getName()
            );
            lessonResponseList.add(lessonResponse);
        }
        log.info("lesson Response List = {}", lessonResponseList);
        return lessonResponseList;
    }

    public List<MyMusicDataResponse> myMusicData() {

        Member loginMember = authUtils.getLoginMember();

        Optional<List<MusicData>> musicDataListOptional = musicDataRepository.findAllByWriter(loginMember.getId());

        log.info("musicDataList = {}", musicDataListOptional);

        List<MyMusicDataResponse> response = new ArrayList<>();

        if (musicDataListOptional.isPresent()) {
            List<MusicData> musicDataList = musicDataListOptional.get();
            for (MusicData musicData : musicDataList) {
                MyMusicDataResponse myMusicDataResponse = MyMusicDataResponse.builder()
                        .id(musicData.getId())
                        .title(musicData.getTitle())
                        .content(musicData.getContent())
                        .build();

                response.add(myMusicDataResponse);
            }
        }

        return response;
    }

}
