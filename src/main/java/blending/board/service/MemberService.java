package blending.board.service;

import blending.board.config.jwt.JwtUtils;
import blending.board.dto.member.*;
import blending.board.entity.Member;
import blending.board.repository.MemberRepository;
import blending.board.repository.LessonRepository;
import blending.board.result.exception.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MemberService {

    private final MemberRepository memberRepository;
    private final LessonRepository lessonRepository;

    private final AuthenticationManager authenticationManager;

    private final JwtUtils jwtUtils;
    private final PasswordEncoder passwordEncoder;


    public String login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);


//        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());
        return jwt;
    }

    public String signUp(SignUpRequest signUpRequest) {

        try {
            Optional<Member> oldMember = memberRepository.findByUsername(signUpRequest.getUsername());

            if(!oldMember.isEmpty()){
                throw new RuntimeException();
            }

            String encodePw = passwordEncoder.encode(signUpRequest.getPassword());

            Member member = Member.builder()
                    .password(encodePw)
                    .username(signUpRequest.getUsername())
                    .name(signUpRequest.getName())
                    .build();
            memberRepository.save(member);
        } catch (Exception e) {
            return e.getMessage();
        }
        return "회원가입 완료";
    }

    public String delete(DeleteMember deleteMember) {
        Member member = memberRepository.findByUsername(deleteMember.getUsername())
                .orElseThrow(MemberNotFoundException::new);
        try{
            memberRepository.delete(member);
        }catch (Exception e){
            return e.getMessage();
        }
        return "회원 탈퇴 완료";
    }

//    public ProfileResponse myProfile(){
//
//        Member member = memberRepository.findById()
//                .orElseThrow(MemberNotFoundException::new);
//
//        Optional<Post> postResult = postRepository.findByMemberId(member.getId());
//
//        List<Post> myPost = postResult
//                .map(Collections::singletonList)
//                .orElse(Collections.emptyList());
//
//        ProfileResponse result = ProfileResponse.builder()
//                .name(member.getName())
//                .myPosts(myPost)
//                .build();
//
//        return result;
//    }

//    public void changePassword(ChangePasswordRequest request){
//        Member member = memberRepository.findById()
//                .orElseThrow(MemberNotFoundException::new);
//
//        if(member.getPassword() != request.getOldPassword()){
//            throw new PasswordNotMatchException();
//        }
//
//        member.changePassword(request.getNewPassword());
//        memberRepository.save(member);
//    }

}
