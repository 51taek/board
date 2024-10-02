package blending.board.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Lesson extends BaseTimeEntity{

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    private UUID id;

    private String title;

    private String content;

    private int price;

    private int time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member teacher;

    @OneToMany(mappedBy = "lesson")
    @JsonManagedReference
    private List<LessonMember> lessonMembers = new ArrayList<>();

    @Builder
    public Lesson(String title, String content, int price, int time, Member teacher) {
        this.title = title;
        this.content = content;
        this.price = price;
        this.time = time;
        this.teacher = teacher;
    }

    public void addLessonMember(LessonMember member) {
        lessonMembers.add(member);
    }
}
