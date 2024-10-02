package blending.board.dto.musicData;

import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
public class MyMusicDataResponse {

    private UUID id;

    private String title;

    private String content;

    @Builder
    public MyMusicDataResponse(UUID id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }
}
