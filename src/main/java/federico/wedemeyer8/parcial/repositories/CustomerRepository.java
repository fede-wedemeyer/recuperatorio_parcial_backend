package federico.wedemeyer8.parcial.repositories;

import federico.wedemeyer8.parcial.models.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {
}
