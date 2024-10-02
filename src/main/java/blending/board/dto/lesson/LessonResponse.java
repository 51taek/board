package blending.board.dto.lesson;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class LessonResponse {

    private UUID id;

    private String title;

    private String content;

    private int price;

    private int time;

    private String writer;

    @Builder
    public LessonResponse(UUID id, String title, String content, int price, int time, String writer) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.price = price;
        this.time = time;
        this.writer = writer;
    }
}
