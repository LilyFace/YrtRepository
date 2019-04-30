import com.itheima.dao.OrdersDao;
import com.itheima.domain.Orders;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 测试订单
 * @Date: 2019/04/25 17:58
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext*.xml"})
public class OrdersDaoTest {
    @Autowired
    private OrdersDao ordersDao;

    /**
     * @param
     * @description: 测试订单查询所有
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/25 18:03
     * @throws:
     **/
    @Test
    public void findAll() {
        List<Orders> list = null;
        int page = 1;
        int size = 4;
        try {
            list = ordersDao.findAll(page, size);
            for (Orders order : list) {
                //System.out.println(order);
                System.out.println(order.getProduct());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
