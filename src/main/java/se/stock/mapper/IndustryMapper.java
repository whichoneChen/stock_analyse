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
    @Select("SELECT basicinfo.* FROM basicinfo, \n" +
            "(SELECT sid,amount FROM (\n" +
            "SELECT hs300.sid, amount FROM daily_k, hs300 WHERE date=\"2020-06-22\" and daily_k.sid=hs300.sid and industry=#{industry}) x\n" +
            "ORDER BY amount desc LIMIT 1) y\n" +
            "WHERE basicinfo.sid = y.sid")
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
