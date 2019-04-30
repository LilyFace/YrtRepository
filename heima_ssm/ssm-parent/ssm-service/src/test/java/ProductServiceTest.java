import com.itheima.dao.ProductDao;
import com.itheima.domain.Product;
import com.itheima.service.ProductService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @Author: YangRunTao
 * @Description: 测试业务层
 * @Date: 2019/04/23 18:27
 * @Modified By:
 */
@SuppressWarnings("JavaDoc")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring/applicationContext*.xml"})
public class ProductServiceTest {
    @Autowired
    private ProductService productService;

    /**
     * @param
     * @description: 查询所有
     * @return: void
     * @author: YangRunTao
     * @date: 2019/04/23 18:29
     * @throws:
     **/
    @Test
    public void testFindAll() {
        try {
            List<Product> list = productService.findAll();
            for (Product product : list) {
                System.out.println(product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
