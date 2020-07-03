package se.stock.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.ArrayList;
import java.util.List;

/**
 * @author cyl
 */
@TableName("daily_k")
public class DailyKStock {

    @TableField("date")
    private String date;

    @TableField("open")
    private String open;

    @TableField("close")
    private String close;

    @TableField("high")
    private String high;

    @TableField("low")
    private String low;

    /**
     * 方便转换
     * @return
     */
    public List<String> toList(){
        List<String> res = new ArrayList<>();
        res.add(date);
        res.add(open);
        res.add(close);
        res.add(high);
        res.add(low);
        return res;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getOpen() {
        return open;
    }

    public void setOpen(String open) {
        this.open = open;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }
}
