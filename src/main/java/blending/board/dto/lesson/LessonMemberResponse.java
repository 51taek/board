package blending.board.dto.lesson;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class LessonMemberResponse {

    private UUID id;

    private String name;

    public LessonMemberResponse(UUID id, String name) {
        this.id = id;
        this.name = name;
    }
}
