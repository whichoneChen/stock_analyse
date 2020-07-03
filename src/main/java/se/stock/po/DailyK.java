package se.stock.po;

/**
 * @author jh
 * @date 2020/7/3
 */
public class DailyK {

    private String date;
    private String close;
    private String volume;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getClose() {
        return close;
    }

    public void setClose(String close) {
        this.close = close;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
