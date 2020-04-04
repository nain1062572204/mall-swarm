import com.wang.mall.front.FrontApplication;
import com.wang.mall.front.domain.OrderParam;
import com.wang.mall.front.service.OmsOrderService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

/**
 * @author 王念
 * @create 2020-03-30 22:52
 */
@SpringBootTest(classes = {FrontApplication.class})
@RunWith(SpringRunner.class)
public class OrderServiceTest {
    @Autowired
    private OmsOrderService orderService;

    @Test
    public void testGeneratorOrder() {
        OrderParam.ProductInfo productInfo1 = new OrderParam.ProductInfo();
        productInfo1.setProductSkuId(140L);
        productInfo1.setQuantity(2);
        OrderParam.ProductInfo productInfo2 = new OrderParam.ProductInfo();
        productInfo2.setProductSkuId(141L);
        productInfo2.setQuantity(2);
        OrderParam orderParam = OrderParam.builder().productInfos(Arrays.asList(productInfo1, productInfo2)).build();
        orderService.generateOrder(orderParam);
    }
}
