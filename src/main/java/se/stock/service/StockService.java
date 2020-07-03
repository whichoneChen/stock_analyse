package se.stock.service;

import se.stock.form.PredictForm;
import se.stock.vo.PredictResult;
import se.stock.vo.StockInfo;

/**
 * @author jh
 * @date 2020/7/2
 */
public interface StockService {
    /**
     * 获取指定股票的数据
     * @param sid 数据库里的sid
     * @return 股票数据
     */
    StockInfo getStock(Integer sid);

    /**
     * 根据传来的数据提供分析结果
     * @param form 表单数据
     * @return 分析结果
     */
    PredictResult predict(PredictForm form);
}