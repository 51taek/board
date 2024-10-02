package blending.board.dto.lesson;

import lombok.Data;

@Data
public class LessonRequest {

    private String title;

    private String content;

    private int price;

    private int time;
}
