package blending.board.dto.member;

import blending.board.entity.Lesson;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
public class ProfileResponse {

    private String name;

    private String musicInterest;

    private String musicGenreLike;

    private String profileMusic;

    @Builder
    public ProfileResponse(String name, String musicInterest, String musicGenreLike, String profileMusic) {
        this.name = name;
        this.musicInterest = musicInterest;
        this.musicGenreLike = musicGenreLike;
        this.profileMusic = profileMusic;
    }
}
