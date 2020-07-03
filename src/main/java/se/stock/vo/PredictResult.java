package se.stock.vo;

/**
 * @author jh
 * @date 2020/7/2
 */
public class PredictResult {

    /**
     * 数据集的sid
     * 没有就-1
     * 让前端提示"非沪深三百股"
     */
    private Integer sid;

    /**
     * 分析结果
     * 如 "eps呈上升。股本环比增长40%远高于（远大于，高于10个百分点）净利润环比增长23%，不推荐"
     */
    private String analysis;



    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis;
    }
}
