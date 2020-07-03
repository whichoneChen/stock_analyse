package se.stock.vo;

/**
 * report:
 *     {
 *         date:"2020-03-31",
 *         netProfit:"1111.12",//净利润
 *         epsTTM:"123.12",//每股收益
 *         totalShare:"123.12",//总股本
 *         YOYNI:"12.3%",//净利润同比增长率
 *         LRRNI:"12.3%",//净利润环比增长率
 *         YOYEPSBasic:"12.3%"//基本每股收益同比增长率
 *     }
 * @author jh
 * @date 2020/7/2
 */
public class QuarterReportVO {

    /**
     * 统计截止日期
     * 2020-03-31
     */
    private String date;

    /**
     * 净利润
     * 1111.12
     */
    private String netProfit;

    /**
     * 每股收益
     * 123.12
     */
    private String epsTTM;

    /**
     * 总股本
     * 123.12
     */
    private String totalShare;

    /**
     * 净利润同比增长率
     * "12.3%"
     */
    private String YOYNI;

    /**
     * 净利润环比增长率
     * "12.3%"
     */
    private String LRRNI;

    /**
     * 基本每股收益同比增长率
     * "12.3%"
     */
    private String YOYEPSBasic;


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(String netProfit) {
        this.netProfit = netProfit;
    }

    public String getEpsTTM() {
        return epsTTM;
    }

    public void setEpsTTM(String epsTTM) {
        this.epsTTM = epsTTM;
    }

    public String getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(String totalShare) {
        this.totalShare = totalShare;
    }

    public String getYOYNI() {
        return YOYNI;
    }

    public void setYOYNI(String YOYNI) {
        this.YOYNI = YOYNI;
    }

    public String getLRRNI() {
        return LRRNI;
    }

    public void setLRRNI(String LRRNI) {
        this.LRRNI = LRRNI;
    }

    public String getYOYEPSBasic() {
        return YOYEPSBasic;
    }

    public void setYOYEPSBasic(String YOYEPSBasic) {
        this.YOYEPSBasic = YOYEPSBasic;
    }


    @Override
    public String toString(){
        return super.toString();
    }
}
