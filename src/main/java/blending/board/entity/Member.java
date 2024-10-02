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
public class Member {

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name="uuid2", strategy = "uuid2")
    @Column(name = "member_id")
    private UUID id;

    private String username;

    private String name;

    private String password;

    private String musicInterest;

    private String musicGenreLike;

    private String profileMusic;

    private String role;

    @OneToMany(mappedBy = "member")
    @JsonManagedReference
    private List<LessonMember> memberLessons = new ArrayList<>();

    @Builder
    public Member(String username, String name, String password) {
        this.username = username;
        this.password = password;
        this.name = name;
    }

    public void createProfile(String musicInterest, String musicGenreLike, String profileMusic){
        this.musicInterest = musicInterest;
        this.musicGenreLike = musicGenreLike;
        this.profileMusic = profileMusic;
    }

    public void changePassword(String password){
        this.password = password;
    }

    public void addLessonMember(LessonMember lessonMember) {
        memberLessons.add(lessonMember);
    }
}
