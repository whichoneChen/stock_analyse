package se.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import se.stock.po.DailyKStock;
import se.stock.po.IndustryLevel;
import se.stock.po.Stock;

import java.util.List;

@Mapper
public interface IndustryMapper {

    /**
     * 选取特定板块下的预测标准
     * @param industry 板块名称
     * @return
     */
    @Select("SELECT * FROM predict WHERE industry=#{industry}")
    @ResultType(IndustryLevel.class)
    IndustryLevel selectLevelByIndustry(String industry);

    /**
     * 以成交额最大的股为龙头股
     * @param industry 板块名称
     * @return
     */
    @Select("SELECT basicinfo.*, MAX(amount) from basicinfo, daily_k WHERE date=\"2020-06-22\" AND industry=#{industry} " +
            "AND basicinfo.sid=daily_k.sid")
    @ResultType(Stock.class)
    Stock selectDominateStock(String industry);

    /**
     * 选取特定股票过去1年的日k信息
     * @param sid
     * @return
     */
    @Select("SELECT date, open, close, high, low from daily_k WHERE sid=#{sid} and " +
            "date <= \"2020-06-22\" and date >= \"2019-06-21\"")
    @ResultType(DailyKStock.class)
    List<DailyKStock> selectDailyKInfo(Integer sid);


    /**
     * 选择特定板块下的所有股票
     * @param industry 板块名称
     * @return
     */
    @Select("SELECT * FROM hs300 WHERE industry=#{industry}")
    @ResultType(Stock.class)
    List<Stock> selectStockByIndustry(String industry);

}
