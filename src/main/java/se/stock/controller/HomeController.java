package se.stock.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.stock.po.Stock;
import se.stock.service.HomeService;
import se.stock.vo.Response;

import javax.annotation.Resource;

/**
 * @author jh
 * @date 2020/7/2
 */
@RestController
@RequestMapping("/home")
@CrossOrigin
@Validated
public class HomeController {

    /**
     * 搜索类型
     */
    private static final String PARAM_ERROR = "参数有误";


    @Resource(name = "HomeService")
    HomeService homeService;


    /**
     * 论文高级检索
     *
     * @param code 股票代码
     * @return 股票数据
     */
    @GetMapping("/search")
    public Response searchStock(@RequestParam String code) {
        if (code == null || "".equals(code)) {
            return Response.buildFailure(PARAM_ERROR);
        }
        Stock s = homeService.get(code);
        return Response.buildSuccess(s);


    }
}
