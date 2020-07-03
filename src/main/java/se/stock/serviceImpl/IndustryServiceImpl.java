package se.stock.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.stock.mapper.IndustryMapper;
import se.stock.po.DailyKStock;
import se.stock.po.IndustryLevel;
import se.stock.po.Stock;
import se.stock.service.IndustryService;
import se.stock.vo.DominateStock;
import se.stock.vo.IndustryVO;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@Service("IndustryService")
@Transactional(rollbackFor = Exception.class)
public class IndustryServiceImpl implements IndustryService {

    @Resource
    IndustryMapper industryMapper;

    @Override
    public IndustryVO getIndustryInfo(String industry) {
        IndustryVO industryVO = new IndustryVO();
        DominateStock dominateStock = new DominateStock();
        List<List<String>> dailyKInfo = new ArrayList<>();
        List<String> codeList = new ArrayList<>();

        DecimalFormat df = new DecimalFormat("0.00%");

        //获取判断标准
        IndustryLevel industryLevel = industryMapper.selectLevelByIndustry(industry);
        if (industryLevel == null){return null;}
        industryVO.setGoodlevel(df.format(industryLevel.getGoodlevel()));
        industryVO.setBadlevel(df.format(industryLevel.getBadlevel()));

        //获取龙头股信息
        Stock stock = industryMapper.selectDominateStock(industry);
        dominateStock.setCode(stock.getCode());
        dominateStock.setName(stock.getName());

        //获取龙头股日k
        List<DailyKStock> dailyKStockList = industryMapper.selectDailyKInfo(stock.getSid());
        for (DailyKStock dailyKStock : dailyKStockList){
            dailyKInfo.add(dailyKStock.toList());
        }
        dominateStock.setDailyk(dailyKInfo);

        //获取板块下的所有股票代码
        List<Stock> industryStock = industryMapper.selectStockByIndustry(industry);
        for (Stock s : industryStock){
            codeList.add(s.getCode());
        }

        industryVO.setDominateStock(dominateStock);
        industryVO.setStocks(codeList);

        return industryVO;
    }
}
