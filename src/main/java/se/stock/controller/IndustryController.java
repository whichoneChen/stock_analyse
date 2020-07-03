package se.stock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import se.stock.service.HomeService;
import se.stock.service.IndustryService;
import se.stock.vo.IndustryVO;
import se.stock.vo.Response;

import javax.annotation.Resource;

/**
 * @author cyl
 * @date 2020/7/2
 */
@RestController
@RequestMapping("/industry")
@CrossOrigin
@Validated
public class IndustryController {

    /**
     * 错误信息
     */
    private static final String PARAM_ERROR = "没有此版块";
    private static final String EMPTY_PARAM_ERROR = "参数不能为空";

    @Resource(name = "IndustryService")
    IndustryService industryService;

    /**
     * 获取版块信息
     * @param name
     * @return
     */
    @GetMapping("")
    public Response getIndustryInfo(@RequestParam String name){
        if(name == null || name == "" ){
            return Response.buildFailure(EMPTY_PARAM_ERROR);
        }
        IndustryVO industryVO = industryService.getIndustryInfo(name);
        if (industryVO == null){
            return Response.buildFailure(PARAM_ERROR);
        }
        return Response.buildSuccess(industryVO);
    }


}
