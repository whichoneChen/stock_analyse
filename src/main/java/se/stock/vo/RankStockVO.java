package se.stock.vo;

public class RankStockVO {

    private Integer sid;

    private String code;

    private String name;

    private String yoy;

    private String industry;

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getYoy() {
        return yoy;
    }

    public void setYoy(String yoy) {
        this.yoy = yoy;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }
}
