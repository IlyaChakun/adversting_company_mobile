package by.iba;

import by.iba.dto.req.user.UserAuthReqDTO;
import by.iba.dto.req.user.UserPasswordRecoveryReqDTO;
import by.iba.dto.req.user.UserReq;
import by.iba.dto.resp.user.AccessTokenDTO;
import by.iba.dto.resp.user.RespStatusDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@RequestMapping("/api/v1/auth")
public interface AuthenticationController {

    @PostMapping(value = "/registration")
    ResponseEntity<RespStatusDTO> registerUser(@RequestBody @Valid UserReq userRegistrationInDTO, BindingResult bindingResult);

    @PostMapping()
    ResponseEntity<AccessTokenDTO> login(@RequestBody @Valid UserAuthReqDTO userAuthReqDTO, BindingResult bindingResult);

    @PutMapping("/password_recovery")
    ResponseEntity<RespStatusDTO> updateUserPassword(@RequestBody @Valid UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO, BindingResult bindingResult);

}
