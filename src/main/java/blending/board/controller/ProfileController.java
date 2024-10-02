package blending.board.controller;

import blending.board.dto.lesson.LessonResponse;
import blending.board.dto.member.ChangePasswordRequest;
import blending.board.dto.member.ProfileResponse;
import blending.board.dto.musicData.MusicDataResponse;
import blending.board.dto.musicData.MyMusicDataResponse;
import blending.board.dto.profile.CreateProfileRequest;
import blending.board.result.ResultCode;
import blending.board.result.ResultResponse;
import blending.board.service.MemberService;
import blending.board.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static blending.board.result.ResultCode.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("profile")
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping()
    public ResponseEntity<ProfileResponse> myProfile(){
        return ResponseEntity.ok().body(profileService.myProfile());
    }

    @PostMapping("")
    public ResponseEntity<ResultResponse> createProfile(@RequestBody CreateProfileRequest request){
        profileService.createProfile(request);
        return ResponseEntity.ok().body(ResultResponse.of(CreateProfileSuccess));
    }

    @PutMapping("change-password")
    public ResponseEntity<ResultResponse> changePassword(@RequestBody ChangePasswordRequest request){
        profileService.changePassword(request);
        return ResponseEntity.ok().body(ResultResponse.of(ChangePasswordSuccess));
    }

    @GetMapping("lessons")
    public ResponseEntity<List<LessonResponse>> myLessons(){
        return ResponseEntity.ok().body(profileService.myLessons());
    }

    @GetMapping("music-data")
    public ResponseEntity<List<MyMusicDataResponse>> myMusicData(){
        return ResponseEntity.ok().body(profileService.myMusicData());
    }
}
