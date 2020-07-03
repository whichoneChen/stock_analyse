package se.stock.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author jh
 * @date 2020/7/2
 */
@TableName("stock")
public class Manager {

    /**
     * 股票id
     */
    @TableField("sid")
    private Integer sid;

    /**
     * 高管名字
     */
    @TableField("name")
    private String name;

    /**
     * 职位
     */
    @TableField("position")
    private String position;

    /**
     * 薪水
     */
    @TableField("annual_salary")
    private String annualSalary;


    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getAnnualSalary() {
        return annualSalary;
    }

    public void setAnnualSalary(String annualSalary) {
        this.annualSalary = annualSalary;
    }
}
