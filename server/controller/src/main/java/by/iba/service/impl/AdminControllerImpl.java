package by.iba.service.impl;

import by.iba.AdminController;
import by.iba.dto.resp.user.RespStatusDTO;
import by.iba.dto.resp.user.UserResp;
import by.iba.dto.resp.user.UsersDTO;
import by.iba.helper.ControllerHelper;
import by.iba.service.UserService;
import by.iba.dto.req.user.UserRolesReqDTO;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@Validated
public class AdminControllerImpl implements AdminController {

    private final UserService userService;

    @Override
    public ResponseEntity<UsersDTO> getUsers() {
        List<UserResp> users = userService.findAll();

        return ResponseEntity
                .ok()
                .body(new UsersDTO(users));
    }

    @Override
    public ResponseEntity<RespStatusDTO> updateUserRole(@PathVariable Long id, @RequestBody UserRolesReqDTO userChangeRoleInDTO, BindingResult bindingResult) {
        ControllerHelper.checkBindingResultAndThrowExceptionIfInvalid(bindingResult);

        userService.updateUserRole(id, userChangeRoleInDTO);

        return ResponseEntity
                .ok()
                .body(new RespStatusDTO("USER_ROLES_WAS_CHANGED"));
    }

}
