package se.stock.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

/**
 * @author cyl
 */
@TableName("growth")
public class Growth {
    @TableField("sid")
    private Integer sid;

    @TableField("YOYNI")
    private double YOYNI;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public double getYOYNI() {
        return YOYNI;
    }

    public void setYOYNI(double YOYNI) {
        this.YOYNI = YOYNI;
    }
}
