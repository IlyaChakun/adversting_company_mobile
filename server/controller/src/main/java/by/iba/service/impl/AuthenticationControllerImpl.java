package by.iba.service.impl;

import by.iba.AuthenticationController;
import by.iba.service.UserService;
import by.iba.dto.req.user.UserAuthReqDTO;
import by.iba.dto.req.user.UserPasswordRecoveryReqDTO;
import by.iba.dto.req.user.UserReq;
import by.iba.dto.resp.user.AccessTokenDTO;
import by.iba.dto.resp.user.RespStatusDTO;
import by.iba.filter.JwtUtil;
import by.iba.helper.ControllerHelper;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
@Validated
public class AuthenticationControllerImpl implements AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final ObjectMapper objectMapper;
    private final JwtUtil jwtUtil;

    @Override
    public ResponseEntity<RespStatusDTO> registerUser(@RequestBody @Valid UserReq userReq, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        userService.save(userReq);

        return ResponseEntity
                .status(201)
                .body(new RespStatusDTO("REGISTRATION_IS_SUCCESSFUL"));

    }

    @Override
    public ResponseEntity<AccessTokenDTO> login(@RequestBody @Valid UserAuthReqDTO userAuthReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
                userAuthReqDTO.getLogin(),
                userAuthReqDTO.getPassword()
        );

        authenticationManager.authenticate(authenticationToken);

        return ResponseEntity
                .ok()
                .body(objectMapper.convertValue(
                        jwtUtil.generateToken(userService.login(userAuthReqDTO)
                        ), AccessTokenDTO.class));
    }

    @Override
    public ResponseEntity<RespStatusDTO> updateUserPassword(@RequestBody @Valid UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        userService.recoverPassword(userPasswordRecoveryReqDTO);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("PASSWORD_WAS_CHANGED"));
    }

}
