package me.chanjar.weixin.cp.corpgroup.service;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.corpgroup.WxCpCorpGroupCorpGetTokenReq;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpAgentPerm;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpDepartment;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpUser;

import java.util.List;

/**
 * 互联企业相关接口
 *
 * @author libo Email: 422423229@qq.com
 * @since 27/2/2023 9:57 PM
 */
public interface WxCpLinkedCorpService {
  WxCpLinkedCorpAgentPerm getLinkedCorpAgentPerm(WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException;

  WxCpLinkedCorpUser getLinkedCorpUser(String userId, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException;

  List<WxCpLinkedCorpUser> getLinkedCorpSimpleUserList(String departmentId, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException;

  List<WxCpLinkedCorpUser> getLinkedCorpUserList(String departmentId, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException;

  List<WxCpLinkedCorpDepartment> getLinkedCorpDepartmentList(String departmentId, WxCpCorpGroupCorpGetTokenReq req) throws WxErrorException;

}
