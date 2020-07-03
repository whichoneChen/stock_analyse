package se.stock.vo;

import java.util.List;

public class IndustryVO {
    private String goodlevel;
    private String badlevel;
    private DominateStock dominateStock;
    private List<String> stocks;

    public String getGoodlevel() {
        return goodlevel;
    }

    public void setGoodlevel(String goodlevel) {
        this.goodlevel = goodlevel;
    }

    public String getBadlevel() {
        return badlevel;
    }

    public void setBadlevel(String badlevel) {
        this.badlevel = badlevel;
    }

    public DominateStock getDominateStock() {
        return dominateStock;
    }

    public void setDominateStock(DominateStock dominateStock) {
        this.dominateStock = dominateStock;
    }

    public List<String> getStocks() {
        return stocks;
    }

    public void setStocks(List<String> stocks) {
        this.stocks = stocks;
    }
}
