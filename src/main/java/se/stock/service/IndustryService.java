package se.stock.service;

import se.stock.vo.IndustryVO;

/**
 * @author cyl
 */
public interface IndustryService {
    /**
     * 获取板块信息
     * @param industry 板块名称
     * @return
     */
    IndustryVO getIndustryInfo(String industry);

}
