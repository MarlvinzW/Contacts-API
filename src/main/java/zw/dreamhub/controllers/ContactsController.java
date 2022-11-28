package zw.dreamhub.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.dreamhub.domain.dto.payload.ContactDto;
import zw.dreamhub.domain.dto.responses.ApiResponse;
import zw.dreamhub.domain.models.Contact;
import zw.dreamhub.services.ContactsService;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import java.util.Collection;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/11/2022
 */

@RestController
@RequestMapping(path = "${app.url.unSecured}/")
@Produces(MediaType.APPLICATION_JSON_VALUE)
@Consumes(MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class ContactsController {

    private final ContactsService service;

    @GetMapping("contacts")
    ResponseEntity<Collection<Contact>> getContacts(
            @RequestParam(name = "name", required = false) String name,
            @RequestParam(name = "email", required = false) String email,
            @RequestParam(name = "number", required = false) Long number
    ) {
        return service.getContacts(name, email, number);
    }

    @PostMapping("contacts")
    ResponseEntity<ApiResponse> createContact(@RequestBody ContactDto dto) {
        return service.createContact(dto);
    }

    @PutMapping("contacts")
    ResponseEntity<ApiResponse> modifyContact(
            @RequestParam("id") String id,
            @RequestBody ContactDto dto) {
        return service.modifyContact(id, dto);
    }

    @DeleteMapping("contacts")
    ResponseEntity<ApiResponse> deleteContact(
            @RequestParam("id") String id) {
        return service.deleteContact(id);
    }

}
