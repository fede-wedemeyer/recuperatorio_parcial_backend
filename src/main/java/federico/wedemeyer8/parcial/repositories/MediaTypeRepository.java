package federico.wedemeyer8.parcial.repositories;


import federico.wedemeyer8.parcial.models.MediaType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MediaTypeRepository extends JpaRepository<MediaType, Integer> {
}
