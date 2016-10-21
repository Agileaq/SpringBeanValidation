package arc.validator.service;

import arc.validator.bean.User;
import arc.validator.customize.NameDuplicated;
import arc.validator.mapper.UserMapper;
import org.hibernate.validator.constraints.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.List;

/**
 * Created by Arc on 20/10/2016.
 */
@Service
@Validated
public class UserService {

    @Autowired
    private UserMapper userMapper;

    public void insert(@NotNull@NameDuplicated String username, @NotNull@Email String email,
                       @Pattern(regexp = "^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\\\d{8}$") String phone) {
        User user = new User();
        user.setEmail(email).setName(username).setPhone(phone);
        userMapper.insert(user);
    }

    public @Valid User getUser(){
        return userMapper.getUserByName("DDDDD").get(0);
    }

    public @Valid List<User> getUserByName(@NotNull String name){
        return userMapper.getUserByName(name);
    }


}

