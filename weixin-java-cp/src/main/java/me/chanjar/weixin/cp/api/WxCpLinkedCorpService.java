package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.corpgroup.*;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpAgentPerm;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpDepartment;
import me.chanjar.weixin.cp.bean.linkedcorp.WxCpLinkedCorpUser;

import java.util.List;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.api
 * @Description: 互联企业相关接口
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 27/2/2023 9:57 PM
 */
public interface WxCpLinkedCorpService {
  WxCpLinkedCorpAgentPerm getLinkedCorpAgentPerm() throws WxErrorException;

  WxCpLinkedCorpUser getLinkedCorpUser(String userId) throws WxErrorException;

  List<WxCpLinkedCorpUser> getLinkedCorpSimpleUserList(String departmentId) throws WxErrorException;

  List<WxCpLinkedCorpUser> getLinkedCorpUserList(String departmentId) throws WxErrorException;

  List<WxCpLinkedCorpDepartment> getLinkedCorpDepartmentList(String departmentId) throws WxErrorException;

}
