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
   *
   * @param tagName 标签名
   */
  String create(String tagName) throws WxErrorException;

  /**
   * 更新标签.
   *
   * @param tagId   标签id
   * @param tagName 标签名
   */
  void update(String tagId, String tagName) throws WxErrorException;

  /**
   * 删除标签.
   *
   * @param tagId 标签id
   */
  void delete(String tagId) throws WxErrorException;

  /**
   * 获得标签列表.
   */
  List<WxCpTag> listAll() throws WxErrorException;

  /**
   * 获取标签成员.
   *
   * @param tagId 标签ID
   */
  List<WxCpUser> listUsersByTagId(String tagId) throws WxErrorException;

  /**
   * 增加标签成员.
   *
   * @param tagId    标签id
   * @param userIds  用户ID 列表
   * @param partyIds 企业部门ID列表
   */
  WxCpTagAddOrRemoveUsersResult addUsers2Tag(String tagId, List<String> userIds, List<String> partyIds) throws WxErrorException;

  /**
   * 移除标签成员.
   *
   * @param tagId    标签id
   * @param userIds  用户id列表
   * @param partyIds 企业部门ID列表
   */
  WxCpTagAddOrRemoveUsersResult removeUsersFromTag(String tagId, List<String> userIds, List<String> partyIds) throws WxErrorException;


  /**
   * 获取标签成员.
   * 对应: http://qydev.weixin.qq.com/wiki/index.php?title=管理标签 中的get接口
   *
   * @param tagId 标签id
   */
  WxCpTagGetResult get(String tagId) throws WxErrorException;

}
