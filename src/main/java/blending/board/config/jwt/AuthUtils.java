package blending.board.config.jwt;

import blending.board.entity.Member;
import blending.board.repository.MemberRepository;
import blending.board.result.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class AuthUtils {

    private final MemberRepository memberRepository;

    public Member getLoginMember(){
        String name = SecurityContextHolder.getContext().getAuthentication().getName();
        Member member = memberRepository.findByUsername(name)
                .orElseThrow(MemberNotFoundException::new);

        return member;
    }
}
