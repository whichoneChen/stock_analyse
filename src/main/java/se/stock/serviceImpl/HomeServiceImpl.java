package se.stock.serviceImpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.stock.mapper.SearchMapper;
import se.stock.po.Stock;
import se.stock.service.HomeService;

import javax.annotation.Resource;

/**
 * @author jh
 * @date 2020/7/2
 */
@Service("HomeService")
@Transactional(rollbackFor = Exception.class)
public class HomeServiceImpl /*extends ServiceImpl<SearchMapper, Stock>*/ implements HomeService {
    //上面的注释是用来复用已有的方法的，ServiceImpl是mybatis-plus自带的类，实现了一系列方法

    @Resource
    SearchMapper searchMapper;

    @Override
    public Stock get(String code) {
        return searchMapper.selectByCode(code);
    }


}
