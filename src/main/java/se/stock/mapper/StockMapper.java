package se.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.type.JdbcType;
import se.stock.po.DailyK;
import se.stock.po.QuarterReportPO;
import se.stock.po.Stock;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.LinkedList;

/**
 * @author jh
 * @date 2020/7/2
 */
@Mapper
public interface StockMapper extends BaseMapper<QuarterReportPO> {

    /**
     * 查找最近几次的季报
     *
     * @param sid   股票在数据库中的id
     * @param limit 最近几次
     * @return 最近几次的季报
     */
    @Select("select g.sid as sid, g.codeId as codeId, " +
            "   STR_TO_DATE(g.pubDate, '%Y-%m-%d') as pubDate, STR_TO_DATE(g.statDate, '%Y-%m-%d') as statDate, " +
            "   g.YOYNI as YOYNI, g.YOYEPSBasic as YOYEPSBasic, " +
            "   p.epsTTM as epsTTM, p.totalShare as totalShare, p.netProfit as netProfit " +
            "   from growth g, profit p " +
            "   where g.sid=#{sid} and g.sid=p.sid " +
            "   and g.statDate=p.statDate " +
            "   order by statDate desc " +
            "   limit #{limit}")
    @Results(id = "QuarterReport", value = {
            @Result(column = "sid", property = "sid", javaType = Integer.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "codeId", property = "codeId", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "pubDate", property = "pubDate", javaType = LocalDateTime.class, jdbcType = JdbcType.DATE),
            @Result(column = "statDate", property = "statDate", javaType = LocalDateTime.class, jdbcType = JdbcType.DATE),
            @Result(column = "YOYNI", property = "YOYNI", javaType = Double.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "YOYEPSBasic", property = "YOYEPSBasic", javaType = Double.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "epsTTM", property = "epsTTM", javaType = Double.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "totalShare", property = "totalShare", javaType = Double.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "netProfit", property = "netProfit", javaType = Double.class, jdbcType = JdbcType.VARCHAR)
    })
    LinkedList<QuarterReportPO> selectQuarters(Integer sid, Integer limit);

    /**
     * 查找是否是沪深300股，不是的话返回 0
     *
     * @param sid 股票id
     * @return 数量
     */
    @Select("select count(*) from hs300 where sid=#{sid}")
    Integer selectHS300BySid(Integer sid);

    /**
     * 查找日K数据
     *
     * @param sid 股票id
     * @return 日K列表
     */
    @Select("select date, close, volume from daily_k " +
            "   where sid=#{sid} and STR_TO_DATE(date, '%Y-%m-%d') >= DATE('2020-01-01')" +
            "   order by STR_TO_DATE(date, '%Y-%m-%d')")
    @Results(id = "DailyK", value = {
            @Result(column = "date", property = "date", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "close", property = "close", javaType = String.class, jdbcType = JdbcType.VARCHAR),
            @Result(column = "volume", property = "volume", javaType = String.class, jdbcType = JdbcType.VARCHAR)
    })
    LinkedList<DailyK> selectDailyK(Integer sid);

}
