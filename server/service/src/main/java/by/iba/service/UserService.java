package by.iba.service;

import by.iba.dto.req.user.*;
import by.iba.dto.resp.user.UserResp;
import by.iba.entity.user.User;

import java.util.List;

public interface UserService {

    List<UserResp> findAll();

    UserResp findById(Long id);

    UserResp findByEmail(String email);

    UserResp save(UserReq userReq);

    User login(UserAuthReqDTO userAuthReqDTO);

    UserResp update(Long id, UserPersonalDataReqDTO userPersonalDataReqDTO);

    void updatePassword(Long id, UserCredentialsReqDTO userCredentialsReqDTO);

    void updateAvatar(Long id, UserAvatarReqDTO userAvatarReqDTO);

    void updateUserRole(Long id, UserRolesReqDTO userRolesReqDTO);

    void recoverPassword(UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO);

}
