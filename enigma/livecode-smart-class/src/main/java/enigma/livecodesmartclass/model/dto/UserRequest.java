package enigma.livecodesmartclass.model.dto;

import enigma.livecodesmartclass.constant.Role;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {
    private String id;

    @NotBlank
    @NotNull
    private String username;

    @NotBlank
    @NotNull
    private String email;

    @NotNull
    @NotBlank
    private String fullName;

    @NotNull
    @NotBlank
    private Role role;

    private String courseId;
}
