package se.stock.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * @author jh
 * @date 2020/7/2
 */
public class PredictForm {
    public static final String INVALID_CODE = "股票代码格式错误";
    public static final String INVALID_QUARTER = "季度格式错误";
    public static final String INVALID_PUBLISH_DATE = "发布日期格式错误";
    public static final String SHOULD_NOT_EMPTY = "请输入必填数据";

    /**
     * 股票代码
     */
    @Size(min = 6, max = 6, message = INVALID_CODE)
    private String code;

    /**
     * 季度，为 1,2,3,4
     */
    @Min(value = 1, message = INVALID_QUARTER)
    @Max(value = 4, message = INVALID_QUARTER)
    private Integer quarter;

    /**
     * 发布日期，如2020-06-30
     */
    @Size(min = 10, max = 10, message = INVALID_PUBLISH_DATE)
    private String statDate;

    /**
     * 净利润
     */
    @NotNull(message = SHOULD_NOT_EMPTY)
    private Double netProfit;

    /**
     * 每股收益
     */
    @NotNull(message = SHOULD_NOT_EMPTY)
    private Double eps;

    /**
     * 总股本
     */
    @NotNull(message = SHOULD_NOT_EMPTY)
    private Double totalShare;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Integer getQuarter() {
        return quarter;
    }

    public void setQuarter(Integer quarter) {
        this.quarter = quarter;
    }

    public String getStatDate() {
        return statDate;
    }

    public void setStatDate(String statDate) {
        this.statDate = statDate;
    }

    public Double getNetProfit() {
        return netProfit;
    }

    public void setNetProfit(Double netProfit) {
        this.netProfit = netProfit;
    }

    public Double getEps() {
        return eps;
    }

    public void setEps(Double eps) {
        this.eps = eps;
    }

    public Double getTotalShare() {
        return totalShare;
    }

    public void setTotalShare(Double totalShare) {
        this.totalShare = totalShare;
    }
}
