package by.iba.dto.req.user;

import by.iba.validation.ReqValidation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserCredentialsReqDTO implements Serializable {

    @NotBlank(message = "Login cannot be empty")
    private String login;

    @NotBlank(message = "Old password cannot be empty")
    private String oldPassword;

    @Size(message = "Length is too large or too small",
            min = ReqValidation.MIN_PASSWORD_LENGTH,
            max = ReqValidation.MAX_PASSWORD_LENGTH)
    @NotBlank(message = "New password cannot be empty")
    private String newPassword;

    @NotBlank(message = "Confirmed password cannot be empty")
    private String confirmedPassword;

}
