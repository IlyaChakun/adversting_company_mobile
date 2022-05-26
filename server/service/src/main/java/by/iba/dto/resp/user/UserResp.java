package by.iba.dto.resp.user;

import by.iba.common.dto.core.FullAbstractResp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResp extends FullAbstractResp {

    private String firstName;
    private String lastName;
    private String email;
    private List<UserRoleDTO> roles;
    private String imageUrl;
    @JsonIgnore
    private String password;

}
