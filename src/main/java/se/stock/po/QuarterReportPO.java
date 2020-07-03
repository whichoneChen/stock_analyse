package se.stock.po;

import se.stock.utils.DateUtil;
import se.stock.vo.QuarterReportVO;

import java.time.LocalDateTime;

/**
 * 需要将格式转换为VO类
 * report:
 * {
 * date:"2020-03-31",
 * netProfit:"1111.12",//净利润
 * epsTTM:"123.12",//每股收益
 * totalShare:"123.12",//总股本
 * YOYNI:"12.3%",//净利润同比增长率
 * LRRNI:"12.3%",//净利润环比增长率
 * YOYEPSBasic:"12.3%"//基本每股收益同比增长率
 * }
 *
 * @author jh
 * @date 2020/7/2
 */
public class QuarterReportPO {

    /**
     * sid
     */
    private Integer sid;

    /**
     * 股票代码
     */
    private String codeId;

    /**
     * 发布日期
     * 2020-03-31
     */
    private LocalDateTime pubDate;

    /**
     * 统计截止日期
     * 2020-03-31
     */
    private LocalDateTime statDate;

    /**
     * 净利润同比增长率
     * 0.123
     */
    private Double YOYNI;

    /**
     * 净利润环比增长率
     * 0.112
     */
    private Double LRRNI;

    /**
     * 基本每股收益同比增长率
     * 0.112
     */
    private Double YOYEPSBasic;


    /**
     * 每股收益
     * 123.12
     */
    private Double epsTTM;

    /**
     * 总股本
     * 123.12
     */
    private Double totalShare;


    /**
     * 净利润
     * 11309000000.000000
     */
    private Double netProfit;

    public QuarterReportVO toVO() {
        QuarterReportVO vo = new QuarterReportVO();
        vo.setDate(DateUtil.toStringDate(this.statDate));
        vo.setEpsTTM(epsTTM.toString());
        vo.setLRRNI(LRRNI.toString());
        vo.setNetProfit(netProfit.toString());
        vo.setTotalShare(totalShare.toString());
        vo.setYOYEPSBasic(YOYEPSBasic.toString());
        vo.setYOYNI(YOYNI.toString());
        return vo;
    }


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getCodeId() {
        return codeId;
    }

    public void setCodeId(String codeId) {
        this.codeId = codeId;
    }

    public LocalDateTime getPubDate() {
        return pubDate;
    }

    public void setPubDate(LocalDateTime pubDate) {
        this.pubDate = pubDate;
    }

    public LocalDateTime getStatDate() {
        return statDate;
    }

    public void setStatDate(LocalDateTime statDate) {
        this.statDate = statDate;
    }

    public Double getYOYNI() {
        return YOYNI;
    }

    public void setYOYNI(Double YOYNI) {
        this.YOYNI = YOYNI;
    }

    public Double getYOYEPSBasic() {
        return YOYEPSBasic;
    }

    public void setYOYEPSBasic(Double YOYEPSBasic) {
        this.YOYEPSBasic = YOYEPSBasic;
    }

    public Double getEpsTTM() {
        return epsTTM;
    }

    public void setEpsTTM(Double epsTTM) {
        this.epsTTM = epsTTM;
    }

    public Double getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(Double totalShare) {
        this.totalShare = totalShare;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

    public Double getLRRNI() {
        return LRRNI;
    }

    public void setLRRNI(Double LRRNI) {
        this.LRRNI = LRRNI;
    }

}
