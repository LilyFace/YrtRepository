import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 测试持久层
 * @Date: 2019/04/23 16:32
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext*.xml"})
public class ProductDaoTest {
    @Autowired
    private ProductDao productDao;

    /**
     * @param
     * @description: 测试查询所有
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/23 16:35
     * @throws:
     **/
    @Test
    public void testFindAll() {
        try {
            List<Product> list = productDao.findAll();
            for (Product product : list) {
                System.out.println(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
