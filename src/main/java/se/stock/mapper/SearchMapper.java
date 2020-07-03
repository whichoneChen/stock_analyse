package se.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import se.stock.po.Growth;
import se.stock.po.Stock;

import java.util.List;

/**
 * @author cyl
 * @date 2020/7/2
 */
@Mapper
public interface SearchMapper extends BaseMapper<Stock> {

    /**
     * 通过股票代码查找股票
     *
     * @param code 股票代码
     * @return 股票
     */
    @Select("select * from hs300 where code = #{code}")
    @ResultType(Stock.class)
    Stock selectByCode(String code);

    /**
     * 选取10只净利润同比增长率最高的股票信息
     *
     * @return
     */
    @Select("SELECT sid, YOYNI FROM growth WHERE statDate=\"2020-03-31\" ORDER BY YOYNI desc LIMIT 10")
    @ResultType(Growth.class)
    List<Growth> selectTopTen();

    /**
     * 根据sid获取股票信息
     * @param sid
     * @return
     */
    @Select("select * from basicinfo where sid = #{sid}")
    @ResultType(Stock.class)
    Stock selctBySid(Integer sid);
}
