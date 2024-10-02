package blending.board.repository;

import blending.board.entity.LessonMember;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LessonMemberRepository extends JpaRepository<LessonMember, UUID> {


}
