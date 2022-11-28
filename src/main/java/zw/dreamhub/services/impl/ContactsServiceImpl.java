package zw.dreamhub.services.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.factory.Mappers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import zw.dreamhub.domain.dto.payload.ContactDto;
import zw.dreamhub.domain.dto.responses.ApiResponse;
import zw.dreamhub.domain.mappers.ContactMapper;
import zw.dreamhub.domain.models.Contact;
import zw.dreamhub.domain.repositories.ContactRepository;
import zw.dreamhub.services.ContactsService;

import java.util.Collection;
import java.util.Optional;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/11/2022
 */

@Service
@RequiredArgsConstructor
@Slf4j
public class ContactsServiceImpl implements ContactsService {

    private final ContactRepository repository;

    @Override
    public ResponseEntity<Collection<Contact>> getContacts(String name, String email, Long number) {
        // when all parameters are null
        if (name == null && email == null && number == null) {
            return ResponseEntity.ok().body(repository.findAll());
        }
        // filter values
        else {
            return ResponseEntity.ok().body(repository.findByNameIgnoreCaseOrEmailIgnoreCaseOrNumbers_Number(name, email, number));
        }
    }

    @Override
    public ResponseEntity<ApiResponse> createContact(ContactDto dto) {
        // filter by name which is unique
        Optional<Contact> contact = repository.findByNameIgnoreCase(dto.getName());
        return contact.map(value ->
                // contact exists
                ResponseEntity.badRequest().
                        body(new ApiResponse(false, "Contact with that name already exists"))
        ).orElseGet(() -> {
            // dto mapping
            ContactMapper mapper = Mappers.getMapper(ContactMapper.class);
            Contact newContact = mapper.contactDtoToContact(dto);
            // persists
            repository.save(newContact);
            // response
            return ResponseEntity.ok().body(new ApiResponse(true, newContact));
        });
    }

    @Override
    public ResponseEntity<ApiResponse> modifyContact(String id, ContactDto dto) {
        // filter contacts
        Optional<Contact> contact = repository.findById(id);
        // update
        return contact.map(value -> {

                    try {
                        // dto mapping
                        ContactMapper mapper = Mappers.getMapper(ContactMapper.class);
                        mapper.updateContactFromContactDto(dto, value);
                        // persists
                        repository.save(value);
                        // response
                        return ResponseEntity.ok().body(new ApiResponse(true, value));
                    } catch (Exception e) {
                        log.info("Contact update exception : {}", e.getMessage());
                        return ResponseEntity.badRequest().body(new ApiResponse(false, "Bad request"));
                    }

                })
                // contact not found
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                new ApiResponse(false, "Contact not found"))
                );
    }

    @Override
    public ResponseEntity<ApiResponse> deleteContact(String id) {
        // filter by id
        Optional<Contact> contact = repository.findById(id);
        return contact.map(value -> {
                    // delete
                    repository.delete(value);
                    // response
                    return ResponseEntity.ok().body(new ApiResponse(true, "Success"));
                })
                // contact not found
                .orElseGet(() ->
                        ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                                new ApiResponse(false, "Contact not found"))
                );
    }
}
