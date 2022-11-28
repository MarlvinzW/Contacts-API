package zw.dreamhub.domain.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Marlvin Chihota
 * Email marlvinchihota@gmail.com
 * Created on 28/11/2022
 */

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Contact extends BaseEntity {

    @Column(unique = true)
    private String name;

    @Email
    private String email;
    @Column(columnDefinition = "TEXT")
    private String address;

    @OneToMany(cascade = {CascadeType.PERSIST}, fetch = FetchType.EAGER)
    private Set<MobileNumber> numbers = new HashSet<>(Collections.emptySet());
}
