package arc.validator.mapper;

import arc.validator.bean.User;
import arc.validator.model.UserPersistLayer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arc on 21/10/2016.
 */
@Component
public class UserMapper {
    public List<User> getUserByName(String name){
        List<User> clonedUsers = UserPersistLayer.getClonedAllUsers();
        if(name == null) {
            return null;
        }
        List<User> users = new ArrayList<>();
        for(User user : clonedUsers) {
            if(name.equals(user.getName())) {
                users.add(user);
            }
        }
        return users;
    }

    public void insert(User user) {
        if(user != null) {
            List<User> allUsers = UserPersistLayer.getAllUsers();
            allUsers.add(user);
        }
    }
}
