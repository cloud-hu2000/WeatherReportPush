import com.CloudHu.weather.entity.User;
import com.CloudHu.weather.service.UserService;
import com.CloudHu.weather.service.impl.UserServiceImpl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {
    public static void main(String[] args) throws IOException {
        Map<String, String> map = new HashMap<>();
        map.put("phone","18313861184");
        map.put("province","广东");
        map.put("city","广州");
        UserServiceImpl userService = new UserServiceImpl();

        userService.addUser(map);
    }
}
