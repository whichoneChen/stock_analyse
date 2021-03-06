package se.stock.serviceImpl;

import org.springframework.stereotype.Service;
import se.stock.form.PredictForm;
import se.stock.mapper.ManagerMapper;
import se.stock.mapper.SearchMapper;
import se.stock.mapper.StockMapper;
import se.stock.po.DailyK;
import se.stock.po.Manager;
import se.stock.po.QuarterReportPO;
import se.stock.po.Stock;
import se.stock.service.StockService;
import se.stock.utils.DateUtil;
import se.stock.vo.PredictResult;
import se.stock.vo.QuarterReportVO;
import se.stock.vo.StockInfo;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
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
        report.setYOYEPSBasic((form.getEps() - reportPos.getFirst().getEpsTTM()) / reportPos.getFirst().getEpsTTM());
        report.setYOYNI((form.getEps() - reportPos.getLast().getEpsTTM()) / reportPos.getLast().getEpsTTM());

        // 将传入的数据作为最新季报来分析
        QuarterReportPO lastQ = reportPos.getFirst();
        report.setLRRNI((report.getNetProfit() - lastQ.getNetProfit()) / lastQ.getNetProfit());
        reportPos.addFirst(report);

        // 生成分析结果
        String analysis = analyze(reportPos);
        pr.setAnalysis(analysis);

        return pr;
    }

    @Override
    public LinkedList<LinkedList<String>> getTransaction(Integer sid) {
        //[["2020-01-01","3561","12313444"]] //日期，闭盘，成交量
        LinkedList<DailyK> dailyKs = stockMapper.selectDailyK(sid);
        LinkedList<LinkedList<String>> result = new LinkedList<>();
        dailyKs.forEach(o -> result.add(new LinkedList<String>() {{
            add(o.getDate());
            add(o.getClose());
            add(o.getVolume());
        }}));
        return result;
    }

    /**
     * 获取季报
     *
     * @param sid   股票id
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
        String r1_p1 = "eps呈上升。股本环比增长";
        String r1_p2 = "远大于净利润环比增长";
        String r1_p3 = ",不推荐";

        String r2_p1 = "eps同比下降";
        String r2_p2 = "eps环比下降";
        String r2_p3 = ",不推荐";

        String r3_p1 = "单季eps成长";
        String r3_p2 = "，同比增长";
        String r3_p3 = "，强烈推荐";

        String r4_p1 = "股本大幅度增长";
        String r4_p2 = "、净利润增长";
        String r4_p3 = "、EPS下降";
        String r4_p4 = "，不推荐（理由：摊薄）";

        String r5_p1 = "上一季度剧增";
        String r5_p2 = "，这一季度EPS下降";
        String r5_p3 = ",不能买";

        String r6 = "去年eps中，四季度eps持续增长，可以买";
        String r7 = "eps同比增长超过30%或环比增长超过30%推荐";

        String defaultR = "没有符合涨势的特征，不推荐";

        QuarterReportPO newest = reports.get(0);
        LocalDateTime new_publish_date = newest.getStatDate();
        boolean is_first_season = false;
        if(new_publish_date.getMonth().equals(Month.MARCH)){
            is_first_season=true;
        }
        QuarterReportPO second = reports.get(1);
        QuarterReportPO fifth = reports.get(4);
        double eps_huan_bi = (newest.getEpsTTM() - second.getEpsTTM()) / second.getEpsTTM();
        double eps_tong_bi = newest.getYOYEPSBasic();
        double gu_ben_huan_bi = (newest.getTotalShare() - second.getTotalShare()) / second.getTotalShare();
        double gu_ben_tong_bi = (newest.getTotalShare() - fifth.getTotalShare()) / fifth.getTotalShare();
        double jin_li_run_huan_bi = newest.getLRRNI();
        double jin_li_run_tong_bi = newest.getYOYNI();

        //1、eps呈上升。股本环比增长>（远大于，高于10个百分点）净利润环比增长，不推荐
        //即eps最近这个季度的环比上升，但是股本环比远大于净利润环比
        if ((!is_first_season)&&eps_huan_bi > 0 && gu_ben_huan_bi - jin_li_run_huan_bi > 0.1) {
            return r1_p1+gu_ben_huan_bi+r1_p2+jin_li_run_huan_bi+r1_p3;
        }

        if ((is_first_season)&&eps_tong_bi > 0 && gu_ben_tong_bi - gu_ben_tong_bi > 0.1) {
            return "eps呈上升。股本同比增长"+gu_ben_tong_bi+"远大于净利润同比增长"+gu_ben_tong_bi+",不推荐";
        }

        //2、eps同比、环比下降巨大，不推荐
        //最近季度的eps同比、环比都下降30%
        if (eps_tong_bi <= -0.3) {
            return r2_p1+eps_tong_bi+r2_p3;
        }else if(eps_huan_bi <= -0.3){
            return r2_p2+eps_huan_bi+r2_p3;
        }

        //3、单季成长，同比增长，强烈推荐
        //单季指只考虑最新的eps环比增长、同比也增长
        if (eps_huan_bi > 0 && eps_tong_bi > 0) {
            return r3_p1+eps_huan_bi+r3_p2+eps_tong_bi+r3_p3;
        }

        //4、股本大幅度增长、净利润增长、EPS下降，不推荐（理由：摊薄）
        //最近这个季度的股本环比增长都大于40%，净利润环比增长是正，eps环比是负
        if ((!is_first_season)&&gu_ben_huan_bi > 0.4  && jin_li_run_huan_bi > 0 && eps_huan_bi < 0) {
            return r4_p1+gu_ben_huan_bi+r4_p2+jin_li_run_huan_bi+r4_p3+eps_huan_bi+r4_p4;
        }
        //5、上一季度剧增，这一季度亏损（EPS下降）不能买
        //这一季度是指最新的季报eps环比是负，上一季度(第二新的季报)的eps环比增长30%
        if (eps_huan_bi < 0) {
            QuarterReportPO third = reports.get(2);
            double second_eps_huan_bi = (second.getEpsTTM() - third.getEpsTTM()) / third.getEpsTTM();
            if (second_eps_huan_bi > 0.3) {
                return r5_p1+second_eps_huan_bi+r5_p2+eps_huan_bi+r5_p3;
            }
        }

        //6、仅分析去年eps，第四季度>第三季度>第二季>第一季，可以买
        //不考虑最新的一季，考虑前面4季的eps
        boolean flag = true;
        for (int i = 0; i < reports.size() - 1; i++) {
            if (reports.get(i).getEpsTTM() <= reports.get(i + 1).getEpsTTM()) {
                flag = false;
                break;
            }
        }
        if (flag) {
            return r6;
        }

        //7、eps同比增长超过30%或环比增长超过30%推荐
        if (eps_huan_bi > 0.3 || eps_tong_bi > 0.3) {
            return r7;
        }

        if(is_first_season&&eps_tong_bi>0){
            return "首季，且eps同比增长"+eps_tong_bi+"可以考虑购买";
        }

        return defaultR;
    }

}
