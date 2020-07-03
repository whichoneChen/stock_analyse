package se.stock.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Objects;

/**
 * @author jh
 * @date 2020/7/2
 */
@TableName("basicinfo")
public class Stock {

    @TableId(value = "sid", type = IdType.AUTO)
    private Integer sid;

    @TableField("update_time")
    private String updateTime;

    @TableField("code")
    private String code;

    @TableField("industry")
    private String industry;

    @TableField("name")
    private String name;

    @TableField("main_business")
    private String mainBusiness;

    @TableField("capital_stock")
    private String capitalStock;

    @TableField("circulate_capital_stock")
    private String circulateCapitalStock;

    @TableField("public_date")
    private String publicDate;

    @TableField("issue_date")
    private String issueDate;

    @TableField("issue_way")
    private String issueWay;

    @TableField("issue_price_per_share")
    private String issuePricePerShare;

    @TableField("issue_volume")
    private String issueVolume;

    @TableField("online_purchase_date_up_limit")
    private String onlinePurchaseDateUpLimit;

    @TableField("online_purchase_date_down_limit")
    private String onlinePurchaseDateDownLimit;

    @TableField("operating_cash_flow_per_share")
    private String operatingCashFlowPerShare;

    @TableField("preliminary_earnings_estimate")
    private String preliminaryEarningsEstimate;



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stock that = (Stock) o;
        return sid.equals(that.sid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sid, name);
    }


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMainBusiness() {
        return mainBusiness;
    }

    public void setMainBusiness(String mainBusiness) {
        this.mainBusiness = mainBusiness;
    }

    public String getCapitalStock() {
        return capitalStock;
    }

    public void setCapitalStock(String capitalStock) {
        this.capitalStock = capitalStock;
    }

    public String getCirculateCapitalStock() {
        return circulateCapitalStock;
    }

    public void setCirculateCapitalStock(String circulateCapitalStock) {
        this.circulateCapitalStock = circulateCapitalStock;
    }

    public String getPublicDate() {
        return publicDate;
    }

    public void setPublicDate(String publicDate) {
        this.publicDate = publicDate;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssueWay() {
        return issueWay;
    }

    public void setIssueWay(String issueWay) {
        this.issueWay = issueWay;
    }

    public String getIssuePricePerShare() {
        return issuePricePerShare;
    }

    public void setIssuePricePerShare(String issuePricePerShare) {
        this.issuePricePerShare = issuePricePerShare;
    }

    public String getIssueVolume() {
        return issueVolume;
    }

    public void setIssueVolume(String issueVolume) {
        this.issueVolume = issueVolume;
    }

    public String getOnlinePurchaseDateUpLimit() {
        return onlinePurchaseDateUpLimit;
    }

    public void setOnlinePurchaseDateUpLimit(String onlinePurchaseDateUpLimit) {
        this.onlinePurchaseDateUpLimit = onlinePurchaseDateUpLimit;
    }

    public String getOnlinePurchaseDateDownLimit() {
        return onlinePurchaseDateDownLimit;
    }

    public void setOnlinePurchaseDateDownLimit(String onlinePurchaseDateDownLimit) {
        this.onlinePurchaseDateDownLimit = onlinePurchaseDateDownLimit;
    }

    public String getOperatingCashFlowPerShare() {
        return operatingCashFlowPerShare;
    }

    public void setOperatingCashFlowPerShare(String operatingCashFlowPerShare) {
        this.operatingCashFlowPerShare = operatingCashFlowPerShare;
    }

    public String getPreliminaryEarningsEstimate() {
        return preliminaryEarningsEstimate;
    }

    public void setPreliminaryEarningsEstimate(String preliminaryEarningsEstimate) {
        this.preliminaryEarningsEstimate = preliminaryEarningsEstimate;
    }
}
