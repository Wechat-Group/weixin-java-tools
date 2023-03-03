package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.corpgroup.*;

import java.util.List;

/**
 * @Project: WxJava
 * @Package: me.chanjar.weixin.cp.api
 * @Description: 企业互联相关接口
 * @Author: libo
 * @Email: 422423229@qq.com
 * @Date: 27/2/2023 9:57 PM
 */
public interface WxCpCorpGroupService {
  List<WxCpCorpGroupCorp> listAppShareInfo(Integer agentId,Integer businessType,String corpId,Integer limit,String cursor) throws WxErrorException;
}
