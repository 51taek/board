package blending.board.controller;

import blending.board.dto.musicData.MusicDataRequest;
import blending.board.dto.musicData.MusicDataResponse;
import blending.board.result.ResultResponse;
import blending.board.service.MusicDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

import static blending.board.result.ResultCode.*;

@RestController
@RequestMapping("/music-data")
@RequiredArgsConstructor
public class MusicDataController {

    private final MusicDataService musicDataService;

    @GetMapping("/all")
    public ResponseEntity<Page<MusicDataResponse>> findAll(){
        return ResponseEntity.ok().body(musicDataService.findAll());
    }

    @PostMapping()
    public ResponseEntity<ResultResponse> upload(@RequestBody MusicDataRequest musicDataRequest){
        musicDataService.upload(musicDataRequest);
        return ResponseEntity.ok().body(ResultResponse.of(UploadMusicDataSuccess));
    }

    @GetMapping("/{musicDataId}")
    public ResponseEntity<MusicDataResponse> findOne(@PathVariable UUID musicDataId){
        return ResponseEntity.ok().body(musicDataService.findOne(musicDataId));
    }

    @DeleteMapping("/{musicDataId}")
    public ResponseEntity<ResultResponse> deleteMusicData(@PathVariable UUID musicDataId){
        musicDataService.delete(musicDataId);
        return ResponseEntity.ok().body(ResultResponse.of(DeleteMusicDataSuccess));
    }
}
