import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CooperaRepository extends JpaRepository<Item, Long> {
    // Additional query methods can be defined here if needed
}