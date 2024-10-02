package blending.board.service;


import blending.board.config.jwt.UserDetailsImpl;
import blending.board.entity.Member;
import blending.board.repository.MemberRepository;
import blending.board.result.exception.MemberNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



@Service
@RequiredArgsConstructor
public class UserDetailsImplService implements UserDetailsService {

    private final MemberRepository memberRepository;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member = memberRepository.findByUsername(username)
                .orElseThrow((MemberNotFoundException::new));

        return UserDetailsImpl.build(member);
    }

}

