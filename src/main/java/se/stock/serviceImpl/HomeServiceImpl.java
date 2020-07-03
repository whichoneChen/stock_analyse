package se.stock.serviceImpl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.stock.mapper.SearchMapper;
import se.stock.po.Growth;
import se.stock.po.Stock;
import se.stock.service.HomeService;
import se.stock.vo.RankList;
import se.stock.vo.RankStockVO;
import se.stock.vo.SidResult;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author cyl
 * @date 2020/7/2
 */
@Service("HomeService")
@Transactional(rollbackFor = Exception.class)
public class HomeServiceImpl /*extends ServiceImpl<SearchMapper, Stock>*/ implements HomeService {
    //上面的注释是用来复用已有的方法的，ServiceImpl是mybatis-plus自带的类，实现了一系列方法

    @Resource
    SearchMapper searchMapper;


    @Override
    public RankList getRankList() {
        List<Growth> growthinfo = searchMapper.selectTopTen();
        List<RankStockVO> result = new ArrayList<>();
        DecimalFormat df = new DecimalFormat("0.00%");

        for(Growth growth : growthinfo){
            RankStockVO rankStockVO = new RankStockVO();
            rankStockVO.setSid(growth.getSid());
            rankStockVO.setYoy(df.format(growth.getYOYNI()*0.01));
            Stock stock = searchMapper.selctBySid(growth.getSid());
            rankStockVO.setCode(stock.getCode());
            rankStockVO.setIndustry(stock.getIndustry());
            rankStockVO.setName(stock.getName());
            result.add(rankStockVO);
        }
        RankList rankList = new RankList();
        rankList.setStockList(result);
        return rankList;
    }

    @Override
    public SidResult SearchStockSid(String code) {
        Stock stock = searchMapper.selectByCode(code);
        Integer result = -1;
        if(stock != null){
            result = stock.getSid();
        }
        SidResult res = new SidResult();
        res.setSid(result);
        return res;
    }
}
