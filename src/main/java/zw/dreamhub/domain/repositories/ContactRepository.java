package zw.dreamhub.domain.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.dreamhub.domain.models.Contact;

import java.util.List;
import java.util.Optional;

@Repository
public interface ContactRepository extends JpaRepository<Contact, String> {
    List<Contact> findByNameIgnoreCaseOrEmailIgnoreCaseOrNumbers_Number(String name, String email, long number);
    Optional<Contact> findByNameIgnoreCase(String name);

    @Override
    Optional<Contact> findById(String s);
}