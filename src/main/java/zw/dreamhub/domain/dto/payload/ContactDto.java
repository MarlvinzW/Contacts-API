package zw.dreamhub.domain.dto.payload;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * A DTO for the {@link zw.dreamhub.domain.models.Contact} entity
 */
@Data
public class ContactDto {
    @NotBlank
    @NotNull
    private String name;
    @Email
    @NotBlank
    @NotNull
    private String email;
    @NotBlank
    @NotNull
    private String address;
    @NotEmpty
    private Set<MobileNumberDto> numbers;
}