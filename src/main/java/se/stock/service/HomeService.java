package se.stock.service;

import se.stock.po.Stock;
import se.stock.vo.RankList;
import se.stock.vo.RankStockVO;
import se.stock.vo.SidResult;

import java.util.List;

/**
 * @author cyl
 * @date 2020/7/2
 */


public interface HomeService {

    /**
     * 选取最近季度10只利润涨幅最高的股票信息
     *
     * @return
     */
    RankList getRankList();

    /**
     * 根据输入的股票代码返回相应的sid，若数据库没有则返回-1
     *
     * @param code 股票代码
     * @return sid
     */
    SidResult SearchStockSid(String code);

}
