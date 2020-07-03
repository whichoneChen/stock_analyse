package se.stock.vo;

import se.stock.po.QuarterReportPO;

import java.util.Collection;
import java.util.LinkedList;

/**
 * @author jh
 * @date 2020/7/2
 */
public class StockInfo {

    /**
     * [股东的名字(职位)]
     * 如 stockHolders:["jh(jyh的父亲)","jyh（jh的儿子）"]
     */
    private LinkedList<String> stockHolders;

    /**
     * 股票名
     */
    private String name;

    /**
     * 股票代码
     */
    private String code;

    /**
     * 季报列表
     * reports:[//按日期从新到旧(降序)
     *     {
     *         date:"2020-03-31",
     *         netProfit:"1111.12",//净利润
     *         epsTTM:"123.12",//每股收益
     *         totalShare:"123.12",//总股本
     *         YOYNI:"12.3%",//净利润同比增长率
     *         LRRNI:"12.3%",//净利润环比增长率
     *         YOYEPSBasic:"12.3%"//基本每股收益同比增长率
     *     }
     * ]
     */
    private Collection<QuarterReportVO> reports;

    /**
     * 分析结果
     * "eps呈上升。股本环比增长40%远高于（远大于，高于10个百分点）净利润环比增长23%，不推荐"
     */
    private String analysis;



    public Collection<String> getStockHolders() {
        return stockHolders;
    }

    public void setStockHolders(LinkedList<String> stockHolders) {
        this.stockHolders = stockHolders;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Collection<QuarterReportVO> getReports() {
        return reports;
    }

    public void setReports(Collection<QuarterReportVO> reports) {
        this.reports = reports;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
