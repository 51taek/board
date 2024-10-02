package blending.board.dto.musicData;

import lombok.Builder;
import lombok.Data;

@Data
public class MusicDataResponse {

    private String title;

    private String content;

    private String writer;

    @Builder
    public MusicDataResponse(String title, String content, String writer) {
        this.title = title;
        this.content = content;
        this.writer = writer;
    }
}
