package zw.dreamhub.domain.dto.payload;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the {@link zw.dreamhub.domain.models.MobileNumber} entity
 */
@Data
public class MobileNumberDto {
    @NotBlank
    @NotNull
    @Size(min = 9, max = 10)
    private Long number;
}