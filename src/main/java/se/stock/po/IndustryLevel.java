package se.stock.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author cyl
 */
@TableName("predict")
public class IndustryLevel {

    @TableField("industry")
    private String industry;

    @TableField("goodlevel")
    private double goodlevel;

    @TableField("badlevel")
    private double badlevel;

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public double getGoodlevel() {
        return goodlevel;
    }

    public void setGoodlevel(double goodlevel) {
        this.goodlevel = goodlevel;
    }

    public double getBadlevel() {
        return badlevel;
    }

    public void setBadlevel(double badlevel) {
        this.badlevel = badlevel;
    }
}
