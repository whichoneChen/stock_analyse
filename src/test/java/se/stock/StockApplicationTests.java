package se.stock;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import se.stock.service.StockService;
import se.stock.utils.DateUtil;
import se.stock.vo.Response;
import se.stock.vo.StockInfo;

import javax.annotation.Resource;
import java.time.LocalDateTime;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
class StockApplicationTests {

    @Resource(name = "Stock")
    StockService stockService;


    @Test
    void getStockTest() {
        StockInfo si = stockService.getStock(1);
        System.out.println();
    }

    @Test
    void predictTest() {
        String time = "2020-03-02";
        LocalDateTime t = DateUtil.parse(time);
        System.out.println();
    }

}
