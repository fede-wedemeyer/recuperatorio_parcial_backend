package federico.wedemeyer8.parcial.repositories;

import federico.wedemeyer8.parcial.models.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Integer> {


}
