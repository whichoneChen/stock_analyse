package se.stock.serviceImpl;

import org.springframework.stereotype.Service;
import se.stock.form.PredictForm;
import se.stock.mapper.ManagerMapper;
import se.stock.mapper.SearchMapper;
import se.stock.mapper.StockMapper;
import se.stock.po.Manager;
import se.stock.po.QuarterReportPO;
import se.stock.po.Stock;
import se.stock.service.StockService;
import se.stock.utils.DateUtil;
import se.stock.vo.PredictResult;
import se.stock.vo.QuarterReportVO;
import se.stock.vo.StockInfo;

import javax.annotation.Resource;
import java.util.LinkedList;

/**
 * @author jh
 * @date 2020/7/2
 */
@Service("Stock")
public class StockServiceImpl implements StockService {

    @Resource
    private StockMapper stockMapper;

    @Resource
    private SearchMapper searchMapper;

    @Resource
    private ManagerMapper managerMapper;

    @Override
    public StockInfo getStock(Integer sid) {
        StockInfo si = new StockInfo();

        // 查找股票名、股票代码
        Stock s = searchMapper.selectById(sid);
        si.setCode(s.getCode());
        si.setName(s.getName());

        // 查找股东
        LinkedList<Manager> stockholders = managerMapper.selectBySid(sid);
        LinkedList<String> holdersString = new LinkedList<>();
        stockholders.forEach(o -> holdersString.add(o.getName() + "(" + o.getPosition() + ")"));
        si.setStockHolders(holdersString);

        // 查找季报
        LinkedList<QuarterReportPO> reportPos = getReports(sid, 5);

        // 生成季报VO
        LinkedList<QuarterReportVO> reportVos = new LinkedList<>();
        reportPos.forEach(o -> reportVos.add(o.toVO()));
        si.setReports(reportVos);

        // 生成分析结果
        String analysis = analyze(reportPos);
        si.setAnalysis(analysis);

        return si;
    }

    @Override
    public PredictResult predict(PredictForm form) {
        PredictResult pr = new PredictResult();

        // 判断是不是沪深300股，如果不是则返回-1
        Stock s = searchMapper.selectByCode(form.getCode());
        Integer is = stockMapper.selectHS300BySid(s.getSid());
        if (is == 0) {
            pr.setSid(-1);
            return pr;
        }
        pr.setSid(s.getSid());

        // 查找以往季报
        LinkedList<QuarterReportPO> reportPos = getReports(s.getSid(), 4);
        QuarterReportPO report = new QuarterReportPO();
        report.setStatDate(DateUtil.parse(form.getStatDate()));
        report.setEpsTTM(form.getEps());
        report.setNetProfit(form.getNetProfit());
        report.setTotalShare(form.getTotalShare());

        // 将传入的数据作为最新季报来分析
        QuarterReportPO lastQ = reportPos.getFirst();
        report.setLRRNI((report.getNetProfit() - lastQ.getNetProfit()) / lastQ.getNetProfit());
        reportPos.addFirst(report);

        // 生成分析结果
        String analysis = analyze(reportPos);
        pr.setAnalysis(analysis);

        return pr;
    }

    /**
     * 获取季报
     *
     * @param sid 股票id
     * @param limit 季报数量
     * @return 季报
     */
    private LinkedList<QuarterReportPO> getReports(Integer sid, Integer limit) {
        LinkedList<QuarterReportPO> reportPos = stockMapper.selectQuarters(sid, limit + 1);
        for (int i = 0; i < reportPos.size() - 1; i++) {
            QuarterReportPO former = reportPos.get(i);
            QuarterReportPO latter = reportPos.get(i + 1);
            former.setLRRNI(((former.getNetProfit() - latter.getNetProfit()) / latter.getNetProfit()));
        }
        reportPos.removeLast();
        return reportPos;
    }

    private String analyze(LinkedList<QuarterReportPO> reports) {
//        String r1 = "eps呈上升。股本环比增长>（远大于，高于10个百分点）净利润环比增长，不推荐";
//        String r2 = "eps不高，但是同比增长超过50%";
//        String r3 = "eps同比、环比下降巨大，不推荐";
//        String r4 = "单季成长，同比增长，强烈推荐";
//        String r5 = "股本大幅度增长、净利润增长、EPS下降，不推荐（理由：摊薄）";
//        String r6 = "上一季度剧增，这一季度亏损（EPS下降）不能买";
//        String r7 = "仅分析去年eps，第三季度>第二季>第一季，可以买";
//
//        String defaultR = "没有符合涨势的特征，不推荐";

        // todo : jyh快做

        String result = "jyh快做";
        return result;
    }

}
