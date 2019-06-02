package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpAgent;

import java.util.List;

/**
 * <pre>
 *  管理企业号应用
 *  文档地址：https://work.weixin.qq.com/api/doc#10087
 *  Created by huansinho on 2018/4/13.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
public interface WxCpAgentService {
  String GET_AGENT = "/cgi-bin/agent/get?agentid=%d";
  String AGENT_SET = "`/cgi-bin/agent/set";
  String AGENT_LIST = "/cgi-bin/agent/list";

  /**
   * <pre>
   * 获取企业号应用信息
   * 该API用于获取企业号某个应用的基本信息，包括头像、昵称、帐号类型、认证类型、可见范围等信息
   * 详情请见: https://work.weixin.qq.com/api/doc#10087
   * </pre>
   *
   * @param agentId 企业应用的id
   * @return 部门id
   */
  WxCpAgent get(Integer agentId) throws WxErrorException;

  /**
   * <pre>
   * 设置应用.
   * 仅企业可调用，可设置当前凭证对应的应用；第三方不可调用。
   * 详情请见: https://work.weixin.qq.com/api/doc#10088
   * </pre>
   *
   * @param agentInfo 应用信息
   */
  void set(WxCpAgent agentInfo) throws WxErrorException;

  /**
   * <pre>
   * 获取应用列表.
   * 企业仅可获取当前凭证对应的应用；第三方仅可获取被授权的应用。
   * 详情请见: https://work.weixin.qq.com/api/doc#11214
   * </pre>
   *
   */
  List<WxCpAgent> list() throws WxErrorException;

}
