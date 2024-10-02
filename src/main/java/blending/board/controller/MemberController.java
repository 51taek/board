package blending.board.controller;

import blending.board.dto.member.DeleteMember;
import blending.board.dto.member.LoginRequest;
import blending.board.dto.member.SignUpRequest;
import blending.board.service.MemberService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@Slf4j
@RequestMapping("member")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@Valid @RequestBody LoginRequest loginRequest){
        return ResponseEntity.ok().body(memberService.login(loginRequest));
    }

    @PostMapping("/signup")
    public ResponseEntity<String> signup(@RequestBody SignUpRequest signUpRequest) {
        return ResponseEntity.ok().body(memberService.signUp(signUpRequest));
    }

    @DeleteMapping("delete")
    public ResponseEntity<String> delete(@RequestBody DeleteMember deleteMember) {
        return ResponseEntity.ok().body(memberService.delete(deleteMember));
    }

}
