package blending.board.repository;

import blending.board.entity.Member;
import blending.board.entity.MusicData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MusicDataRepository extends JpaRepository<MusicData, UUID> {

    @Query("select m.id, m.title, m.content from MusicData m where m.writer.id =: memberId")
    Optional<List<MusicData>> findAllByWriter(UUID memberId);
}
