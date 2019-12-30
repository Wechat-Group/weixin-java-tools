package me.chanjar.weixin.cp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.cp.bean.WxCpTag;
import me.chanjar.weixin.cp.bean.WxCpTagAddOrRemoveUsersResult;
import me.chanjar.weixin.cp.bean.WxCpTagGetResult;
import me.chanjar.weixin.cp.bean.WxCpUser;

import java.util.List;

/**
 * <pre>
 *  标签管理接口.
 *  Created by BinaryWang on 2017/6/24.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxCpTagService {

  /**
   * 创建标签.
   * <pre>
   * 请求地址：https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token=ACCESS_TOKEN
   * 文档地址：https://work.weixin.qq.com/api/doc#90000/90135/90210
   * </pre>
   *
   * @param name 标签名称，长度限制为32个字以内（汉字或英文字母），标签名不可与其他标签重名。
   * @param id   标签id，非负整型，指定此参数时新增的标签会生成对应的标签id，不指定时则以目前最大的id自增。
   * @return 标签id
   * @throws WxErrorException .
   */
  String create(String name, Integer id) throws WxErrorException;

  /**
   * 创建标签.
   *
   * @param tagName 标签名
   * @return 标签id
   * @throws WxErrorException .
   * @deprecated 建议使用 {@link #create(String, Integer)}，其中后面的参数可以为空
   */
  @Deprecated
  String create(String tagName) throws WxErrorException;

  /**
   * 更新标签.
   *
   * @param tagId   标签id
   * @param tagName 标签名
   * @throws WxErrorException .
   */
  void update(String tagId, String tagName) throws WxErrorException;

  /**
   * 删除标签.
   *
   * @param tagId 标签id
   * @throws WxErrorException .
   */
  void delete(String tagId) throws WxErrorException;

  /**
   * 获得标签列表.
   *
   * @return 标签列表
   * @throws WxErrorException .
   */
  List<WxCpTag> listAll() throws WxErrorException;

  /**
   * 获取标签成员.
   *
   * @param tagId 标签ID
   * @return 成员列表
   * @throws WxErrorException .
   */
  List<WxCpUser> listUsersByTagId(String tagId) throws WxErrorException;

  /**
   * 获取标签成员.
   * 对应: http://qydev.weixin.qq.com/wiki/index.php?title=管理标签 中的get接口
   *
   * @param tagId 标签id
   * @return .
   * @throws WxErrorException .
   */
  WxCpTagGetResult get(String tagId) throws WxErrorException;

  /**
   * 增加标签成员.
   *
   * @param tagId    标签id
   * @param userIds  用户ID 列表
   * @param partyIds 企业部门ID列表
   * @return .
   * @throws WxErrorException .
   */
  WxCpTagAddOrRemoveUsersResult addUsers2Tag(String tagId, List<String> userIds, List<String> partyIds) throws WxErrorException;

  /**
   * 移除标签成员.
   *
   * @param tagId    标签id
   * @param userIds  用户id列表
   * @param partyIds 企业部门ID列表
   * @return .
   * @throws WxErrorException .
   */
  WxCpTagAddOrRemoveUsersResult removeUsersFromTag(String tagId, List<String> userIds, List<String> partyIds) throws WxErrorException;

}
