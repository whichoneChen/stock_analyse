package se.stock.controller;

import org.springframework.web.bind.annotation.*;
import se.stock.form.PredictForm;
import se.stock.service.StockService;
import se.stock.vo.Response;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.Min;

/**
 * @author jh
 * @date 2020/7/2
 */
@RestController("/")
public class StockController {

    private static final String INVALID_PARAM = "参数错误";

    @Resource(name = "Stock")
    private StockService stockService;

    @GetMapping("/stock")
    public Response getStock(@RequestParam("sid")
                             @Min(value = 1, message = INVALID_PARAM)
                                     Integer sid) {
        return Response.buildSuccess(stockService.getStock(sid));
    }

    @PostMapping("/predict")
    public Response predict(@RequestBody @Valid PredictForm form) {
        return Response.buildSuccess(stockService.predict(form));
    }
}
