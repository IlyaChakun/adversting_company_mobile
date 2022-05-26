package by.iba.mapper;

import by.iba.common.mapper.core.SimpleAbstractMapper;
import by.iba.dto.resp.user.UserResp;
import by.iba.entity.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserMapper extends SimpleAbstractMapper<User, UserResp> {

    @Autowired
    public UserMapper() {
        super(User.class, UserResp.class);
    }

}
