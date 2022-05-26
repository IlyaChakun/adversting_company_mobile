package by.iba.dto.req.user;

import by.iba.dto.resp.user.UserRoleDTO;
import lombok.*;

import javax.validation.Valid;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserRolesReqDTO {

    @Valid
    private List<UserRoleDTO> roles;

}
