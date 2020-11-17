package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpAgentWorkBench;

/**
 * @author songshiyu
 * @date : create in 16:16 2020/9/27
 * @description: 工作台自定义展示：https://work.weixin.qq.com/api/doc/90000/90135/92535
 */
public interface WxCpAgentWorkBenchService {

  void setWorkBenchTemplate(WxCpAgentWorkBench wxCpAgentWorkBench) throws WxErrorException;

  String getWorkBenchTemplate(Long agentid) throws WxErrorException;

  void setWorkBenchData(WxCpAgentWorkBench wxCpAgentWorkBench) throws WxErrorException;
}
