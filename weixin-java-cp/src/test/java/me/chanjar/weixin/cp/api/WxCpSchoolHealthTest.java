package me.chanjar.weixin.cp.api;

import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetHealthReportStat;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetReportJobIds;
import me.chanjar.weixin.cp.bean.school.health.WxCpGetReportJobInfo;
import me.chanjar.weixin.cp.config.WxCpConfigStorage;
import me.chanjar.weixin.cp.demo.WxCpDemoInMemoryConfigStorage;
import org.testng.annotations.Test;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 企业微信家校应用 健康上报接口.
 * https://developer.work.weixin.qq.com/document/path/93676
 *
 * @author <a href="https://github.com/0katekate0">Wang_Wong</a>
 * @date: 2022/5/31 9:10
 */
@Slf4j
public class WxCpSchoolHealthTest {

  private static WxCpConfigStorage wxCpConfigStorage;
  private static WxCpService cpService;

  @Test
  public void test() throws WxErrorException {

    InputStream inputStream = ClassLoader.getSystemResourceAsStream("test-config.xml");
    WxCpDemoInMemoryConfigStorage config = WxCpDemoInMemoryConfigStorage.fromXml(inputStream);

    wxCpConfigStorage = config;
    cpService = new WxCpServiceImpl();
    cpService.setWxCpConfigStorage(config);
    String currDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());


    /**
     * 获取健康上报任务ID列表
     * https://developer.work.weixin.qq.com/document/path/93677
     */
    WxCpGetReportJobIds reportJobids = cpService.getSchoolHealthService().getReportJobIds(null, null);
    log.info("返回的reportJobids为：{}", reportJobids.toJson());

    /**
     * 获取健康上报任务详情
     * https://developer.work.weixin.qq.com/document/path/93678
     */
    WxCpGetReportJobInfo reportJobInfo = cpService.getSchoolHealthService().getReportJobInfo(null, currDate);
    log.info("返回的reportJobInfo为：{}", reportJobInfo.toJson());

    /**
     * 获取健康上报使用统计
     * https://developer.work.weixin.qq.com/document/path/93676
     */
    String date = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    WxCpGetHealthReportStat healthReportStat = cpService.getSchoolHealthService().getHealthReportStat(date);
    log.info("返回的healthReportStat为：{}", healthReportStat.toJson());

  }

}
