package by.iba.service.impl;

import by.iba.service.UserRolesService;
import by.iba.service.UserService;
import by.iba.dto.req.user.*;
import by.iba.dto.resp.user.UserResp;
import by.iba.dto.resp.user.UserRoleDTO;
import by.iba.entity.user.User;
import by.iba.entity.user.UserRole;
import by.iba.exception.UserIsAlreadyExistException;
import by.iba.exception.ResourceNotFoundException;
import by.iba.exception.UserRoleNotFoundException;
import by.iba.mapper.UserMapper;
import by.iba.repository.UserRepository;
import by.iba.repository.UserRolesRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private static final String STANDARD_ROLE = "USER";
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder bCryptPasswordEncoder;
    private final ObjectMapper objectMapper;
    private final UserRolesService userRolesService;
    private final UserRolesRepository userRolesRepository;

    @Override
    @Transactional
    public List<UserResp> findAll() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    @Override
    @Transactional(readOnly = true)
    public UserResp findById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);
        UserResp userResp = userMapper.toDto(user);

        user.getRoles().forEach(it->
                        userResp.getRoles().add(new UserRoleDTO(it.getName()))
        );

        return userResp;
    }

    @Override
    public UserResp findByEmail(String email) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(ResourceNotFoundException::new);

        UserResp userResp = userMapper.toDto(user);
        userResp.setRoles(user.getRoles()
                .stream()
                .map(x-> new UserRoleDTO(x.getName()))
                .collect(Collectors.toList()));

        return userResp;
    }

    @Override
    @Transactional
    public UserResp save(UserReq userReq) {

        User user = objectMapper.convertValue(userReq, User.class);
        user.setImageUrl(userReq.getImage());
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.getRoles().add(userRolesService.findByName(STANDARD_ROLE));

        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new UserIsAlreadyExistException();
        }

        userRepository.save(user);

        return userMapper.toDto(user);
    }

    @Override
    @Transactional
    public User login(UserAuthReqDTO userAuthReqDTO) {

        User user = userRepository.findByEmail(userAuthReqDTO.getLogin())
                .orElseThrow(() -> new BadCredentialsException("BAD_CREDENTIALS"));

//        if (Objects.nonNull(user.getBannedDate())) {
//            throw new UserHasBeenBannedException("User account is disabled.");
//        }

        userRepository.save(user);

        return user;
    }

    @Override
    @Transactional
    public UserResp update(Long id, UserPersonalDataReqDTO userPersonalDataReqDTO) {
//
//        UserEntity user = userRepository.findById(id)
//                .orElseThrow(UserNotFoundException::new);
//
//        UserEntity userEntity = userMapper.toEntity(user, userPersonalDataReqDTO);
//
//        userRepository.save(user);
//
//        return userMapper.fillFromInDTO(user);
        return null;
    }

    @Override
    @Transactional
    public void updatePassword(Long id, UserCredentialsReqDTO userChangeCredentialsReqDTO) {

        User user = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if (Objects.isNull(userChangeCredentialsReqDTO.getLogin())
                || Objects.isNull(userChangeCredentialsReqDTO.getNewPassword())
                || Objects.isNull(userChangeCredentialsReqDTO.getOldPassword())
                || Objects.isNull(userChangeCredentialsReqDTO.getConfirmedPassword())
                || (user.getPassword()).equals(bCryptPasswordEncoder.encode(userChangeCredentialsReqDTO.getOldPassword()))
                || !userChangeCredentialsReqDTO.getNewPassword().equals(userChangeCredentialsReqDTO.getConfirmedPassword())) {
            throw new BadCredentialsException("BAD_CREDENTIALS");
        }

        user.setPassword(bCryptPasswordEncoder.encode(userChangeCredentialsReqDTO.getNewPassword()));

        userRepository.save(user);

    }

    @Override
    @Transactional
    public void updateAvatar(Long id, UserAvatarReqDTO userAvatarReqDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        user.setImageUrl(userAvatarReqDTO.getImage());

        userRepository.save(user);
    }

    @Override
    @Transactional
    public void updateUserRole(Long id, UserRolesReqDTO userRolesReqDTO) {
        User user = userRepository.findById(id)
                .orElseThrow(ResourceNotFoundException::new);

        if (userRolesReqDTO.getRoles().isEmpty()) {
            throw new UserRoleNotFoundException();
        }

        Set<UserRole> roles = new HashSet<>();

        for (UserRoleDTO role : userRolesReqDTO.getRoles()) {
            UserRole userRole = userRolesRepository.findByName(role.getRole())
                    .orElseThrow(UserRoleNotFoundException::new);
            roles.add(userRole);
        }

        user.setRoles(roles);
        userRepository.save(user);

    }

    @Override
    @Transactional
    public void recoverPassword(UserPasswordRecoveryReqDTO userPasswordRecoveryReqDTO) {
        User user = userRepository.findByEmail(userPasswordRecoveryReqDTO.getLogin())
                .orElseThrow(ResourceNotFoundException::new);

        if (!userPasswordRecoveryReqDTO.getNewPassword().equals(userPasswordRecoveryReqDTO.getConfirmedPassword())
                && userPasswordRecoveryReqDTO.getNewPassword() != null) {
            throw new BadCredentialsException("BAD_CREDENTIALS");
        }

        user.setPassword(bCryptPasswordEncoder.encode(userPasswordRecoveryReqDTO.getNewPassword()));

        userRepository.save(user);
    }

}
