package by.iba.dto.req.user;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserAvatarReqDTO implements Serializable {

    @NotBlank(message = "Image cannot be empty")
    private String image;

}
