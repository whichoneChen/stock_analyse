package se.stock.controller;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.stock.po.Stock;
import se.stock.service.HomeService;
import se.stock.vo.Response;
import se.stock.vo.SidResult;

import javax.annotation.Resource;

/**
 * @author cyl
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
     * 搜索框，根据股票代码查找sid
     *
     * @param code 股票代码
     * @return sid
     */
    @GetMapping("/search")
    public Response searchStock(@RequestParam String code) {
        if (code == null || "".equals(code)) {
            return Response.buildFailure(PARAM_ERROR);
        }
        SidResult res = homeService.SearchStockSid(code);
        return Response.buildSuccess(res);
    }

    /**
     * 获取涨幅最高的10只股票
     * @return
     */
    @GetMapping("/rankList")
    public Response getTopTenStock(){

        return Response.buildSuccess(homeService.getRankList());
    }
}
