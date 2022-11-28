package zw.dreamhub.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.dreamhub.domain.models.MobileNumber;


@Repository
public interface MobileNumberRepository extends JpaRepository<MobileNumber, String> {
}