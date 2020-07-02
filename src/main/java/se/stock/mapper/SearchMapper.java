package se.stock.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import se.stock.po.Stock;

/**
 * @author jh
 * @date 2020/7/2
 */
@Mapper
public interface SearchMapper extends BaseMapper<Stock> {

    /**
     * 通过机构名查找机构
     *
     * @param code 股票代码
     * @return 股票
     */
    @Select("select * from basicinfo where code = #{code}")
    @ResultType(Stock.class)
    Stock selectByCode(String code);

}
