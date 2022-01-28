package AfkoAPI.Repository;

import AfkoAPI.Model.BlacklistEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlacklistRepository extends JpaRepository<BlacklistEntry, String> {
}
