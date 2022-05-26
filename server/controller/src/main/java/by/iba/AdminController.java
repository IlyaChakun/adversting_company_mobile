package by.iba;

import by.iba.dto.req.user.UserRolesReqDTO;
import by.iba.dto.resp.user.RespStatusDTO;
import by.iba.dto.resp.user.UsersDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping(path = "/api/v1/admin")
public interface AdminController {

    @GetMapping("/users")
    ResponseEntity<UsersDTO> getUsers();

    @PatchMapping("/users/role/{id}")
    ResponseEntity<RespStatusDTO> updateUserRole(@PathVariable Long id, @RequestBody @Valid UserRolesReqDTO userRolesReqDTO, BindingResult bindingResult);

}
