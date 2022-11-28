package zw.dreamhub.services;

import org.springframework.http.ResponseEntity;
import zw.dreamhub.domain.dto.payload.ContactDto;
import zw.dreamhub.domain.dto.responses.ApiResponse;
import zw.dreamhub.domain.models.Contact;

import java.util.Collection;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/11/2022
 */

public interface ContactsService {
    ResponseEntity<Collection<Contact>> getContacts(
            String name,
            String email,
            Long number
    );

    ResponseEntity<ApiResponse> createContact(ContactDto dto);

    ResponseEntity<ApiResponse> modifyContact(
            String id,
            ContactDto dto);

    ResponseEntity<ApiResponse> deleteContact(
            String id);
}
