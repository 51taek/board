package blending.board.service;

import blending.board.config.jwt.AuthUtils;
import blending.board.dto.musicData.MusicDataRequest;
import blending.board.dto.musicData.MusicDataResponse;
import blending.board.entity.Member;
import blending.board.entity.MusicData;
import blending.board.repository.MusicDataRepository;
import blending.board.result.exception.MusicDataNotFoundException;
import blending.board.result.exception.NotWriterException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MusicDataService {

    private final MusicDataRepository musicDataRepository;
    private final AuthUtils authUtils;

    public Page<MusicDataResponse> findAll(){

        PageRequest pageRequest = PageRequest.of(0, 10);

        Page<MusicData> all = musicDataRepository.findAll(pageRequest);
        log.info("music data all = {}", all);

        Page<MusicDataResponse> response = all.map(
                musicData -> new MusicDataResponse(musicData.getTitle(), musicData.getContent(), musicData.getWriter().getName()));

        return response;
    }

    public void upload(MusicDataRequest musicDataRequest) {

        Member loginMember = authUtils.getLoginMember();


        MusicData musicData = MusicData.builder()
                .title(musicDataRequest.getTitle())
                .content(musicDataRequest.getContent())
                .writer(loginMember)
                .build();

        musicDataRepository.save(musicData);

    }

    public MusicDataResponse findOne(UUID musicDataId) {
        MusicData musicData = musicDataRepository.findById(musicDataId)
                .orElseThrow(MusicDataNotFoundException::new);

        return MusicDataResponse.builder()
                .title(musicData.getTitle())
                .content(musicData.getContent())
                .writer(musicData.getWriter().getName())
                .build();
    }

    public void delete(UUID musicDataId) {

        Member loginMember = authUtils.getLoginMember();

        MusicData musicData = musicDataRepository.findById(musicDataId)
                .orElseThrow(MusicDataNotFoundException::new);

        if(musicData.getWriter().getId() != loginMember.getId()){
            throw new NotWriterException();
        }

        musicDataRepository.delete(musicData);
    }
}
