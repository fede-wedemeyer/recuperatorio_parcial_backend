package federico.wedemeyer8.parcial.repositories;

import federico.wedemeyer8.parcial.models.Genre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
