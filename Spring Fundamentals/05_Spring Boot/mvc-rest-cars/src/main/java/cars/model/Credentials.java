package cars.model;

import lombok.*;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Credentials {
    @NonNull
    @NotNull
    private String username;

    @NonNull
    @NotNull
    @Length(min = 4, max = 30)
    private String password;
}
