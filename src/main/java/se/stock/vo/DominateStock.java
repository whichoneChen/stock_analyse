package se.stock.vo;

import java.util.List;

public class DominateStock {
    private String name;
    private String code;
    private List<List<String>> dailyk;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<List<String>> getDailyk() {
        return dailyk;
    }

    public void setDailyk(List<List<String>> dailyk) {
        this.dailyk = dailyk;
    }
}
