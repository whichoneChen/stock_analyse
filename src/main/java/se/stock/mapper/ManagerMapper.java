package se.stock.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import se.stock.po.Manager;

import java.util.LinkedList;

/**
 * @author jh
 * @date 2020/7/2
 */
@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {

    /**
     * 查找指定股票的所有高管
     *
     * @param sid 股票在数据库中的id
     * @return 指定股票的所有高管列表
     */
    @Select("select * from managers where sid=#{sid}")
    LinkedList<Manager> selectBySid(Integer sid);
}
