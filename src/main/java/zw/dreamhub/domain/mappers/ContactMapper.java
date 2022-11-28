package zw.dreamhub.domain.mappers;

import org.mapstruct.*;
import zw.dreamhub.domain.dto.payload.ContactDto;
import zw.dreamhub.domain.models.Contact;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/11/2022
 */

@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE, componentModel = "spring")
public interface ContactMapper {
    Contact contactDtoToContact(ContactDto contactDto);

    ContactDto contactToContactDto(Contact contact);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Contact updateContactFromContactDto(ContactDto contactDto, @MappingTarget Contact contact);
}
