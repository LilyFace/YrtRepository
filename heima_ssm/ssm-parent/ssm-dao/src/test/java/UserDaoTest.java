import com.itheima.dao.UserDao;
import com.itheima.domain.Role;
import com.itheima.domain.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 测试用户持久层
 * @Date: 2019/04/27 14:14
 * @Modified By:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext*.xml"})
public class UserDaoTest {
    @Autowired
    private UserDao userDao;

    @Test
    public void findById() {
        try {
            UserInfo userInfo = userDao.findById("FCFAF2B7B91142799AAADB7B1194B77B");
            System.out.println("\033[31;4m" + userInfo.getUsername() + "\033[0m");
            System.out.println("================================");
            List<Role> roles = userInfo.getRoles();
            System.out.println("\033[31;4m" + roles + "\033[0m");
            System.out.println("================================");
            for (Role role : roles) {
                System.out.println("\033[31;4m" + role.getPermissions() + "\033[0m");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
