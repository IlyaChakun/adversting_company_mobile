package by.iba.service.impl;

import by.iba.UserController;
import by.iba.dto.req.user.UserAvatarReqDTO;
import by.iba.dto.req.user.UserCredentialsReqDTO;
import by.iba.dto.req.user.UserPersonalDataReqDTO;
import by.iba.dto.resp.user.RespStatusDTO;
import by.iba.dto.resp.user.UserResp;
import by.iba.helper.ControllerHelper;
import by.iba.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@AllArgsConstructor
public class UserControllerImpl implements UserController {

    private final UserService userService;

    @Override
    public UserResp getMe() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username;
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }

        UserResp userResp = userService.findByEmail(username);

        return userResp;
    }

    @Override
    public ResponseEntity<UserResp> getUserById(@PathVariable Long id) {

        UserResp user = userService.findById(id);

        return ResponseEntity
                .ok()
                .body(user);
    }

    @Override
    public ResponseEntity<UserResp> update(@PathVariable Long id, @RequestBody @Valid UserPersonalDataReqDTO userPersonalDataReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        UserResp user = userService.update(id, userPersonalDataReqDTO);

        return ResponseEntity
                .ok()
                .body(user);
    }

    @Override
    public ResponseEntity<RespStatusDTO> updateAvatar(@PathVariable Long id, @RequestBody @Valid UserAvatarReqDTO userAvatarReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        userService.updateAvatar(id, userAvatarReqDTO);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("User avatar was changed"));
    }

    @Override
    public ResponseEntity<RespStatusDTO> updatePassword(@PathVariable Long id, @RequestBody @Valid UserCredentialsReqDTO userCredentialsReqDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        userService.updatePassword(id, userCredentialsReqDTO);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("User password was changed"));
    }

}
