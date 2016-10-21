package arc.validator.model;

import arc.validator.bean.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Arc on 21/10/2016.
 */
public class UserPersistLayer {
    private static final List<User> dbUsers = new ArrayList<>();
    static {
        dbUsers.add(new User().setPhone("13244422345").setEmail("ddd1@aaa.com").setName("test1name").setFirstName("test1FirstName"));
        dbUsers.add(new User().setPhone("15344422345").setEmail("ddd2@aaa.com").setName("test2name").setFirstName("test2FirstName"));
        dbUsers.add(new User().setPhone("12144422345").setEmail("ddd3@aaa.com").setName("test3name").setFirstName("test3FirstName"));
        dbUsers.add(new User().setPhone("13244552345").setEmail("ddd4@aaa.com").setName("test4name").setFirstName("test4FirstName"));
        dbUsers.add(new User().setPhone("13246564345").setEmail("ddd5@aaa.com").setName("test5name").setFirstName("test5FirstName"));
        dbUsers.add(new User().setPhone("13244444451").setEmail("ddd6@aaa.com").setName("test6name").setFirstName("test6FirstName"));
        dbUsers.add(new User().setPhone("13244425545").setEmail("ddd7@aaa.com").setName("test7name").setFirstName("test7FirstName"));
        dbUsers.add(new User().setPhone("13244426645").setEmail("ddd8@aaa.com").setName("test8name").setFirstName("test8FirstName"));
        dbUsers.add(new User().setPhone("13244488345").setEmail("ddd9@aaa.com").setName("test9name").setFirstName("test9FirstName"));
        dbUsers.add(new User().setPhone("13244429995").setEmail("ddd10@aaa.com").setName("test10name").setFirstName("test10FirstName"));
        dbUsers.add(new User().setPhone("18988888888").setEmail("ddd11@aaa.com").setName("test11name").setFirstName("test11FirstName"));
        dbUsers.add(new User().setPhone("18988888888").setEmail("ddd11@aaa.com").setName("test11name").setFirstName("test11FirstName"));
        dbUsers.add(new User().setPhone("18988888888").setEmail("ddd11@aaa.com").setName("test11name").setFirstName("test11FirstName"));
        dbUsers.add(new User().setPhone("BBB").setName("DDDDD"));
    }

    public static List<User> getClonedAllUsers(){
        List<User> users = null;
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(dbUsers);

            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream oim = new ObjectInputStream(bis);
            users = (List<User>)oim.readObject();

            oos.close();
            oim.close();
        } catch(Exception e) {
            e.printStackTrace();
        }
        return users;
    }

    public static List<User> getAllUsers(){
        return dbUsers;
    }

}
