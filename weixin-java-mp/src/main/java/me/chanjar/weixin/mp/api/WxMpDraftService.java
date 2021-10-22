package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.draft.WxMpAddDraft;
import me.chanjar.weixin.mp.bean.draft.WxMpDraftInfo;
import me.chanjar.weixin.mp.bean.draft.WxMpDraftList;
import me.chanjar.weixin.mp.bean.draft.WxMpUpdateDraft;

/**
 * 微信 草稿箱 接口.
 *
 * @author dragon
 * @date 2021-10-22
 */
public interface WxMpDraftService {

  /**
   * 新建草稿 - 只有默认必填参数
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/draft/add?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Draft_Box/Add_draft.html
   * </pre>
   *
   * @param title        标题
   * @param content      图文消息的具体内容，支持HTML标签，必须少于2万字符，小于1M，且此处会去除JS,涉及图片url必须来源 "上传图文消息内的图片获取URL"接口获取。外部图片url将被过滤。
   * @param thumbMediaId 图文消息的封面图片素材id（必须是永久MediaID）
   * @throws WxErrorException .
   */
  String addDraft(String title, String content, String thumbMediaId) throws WxErrorException;

  /**
   * 新建草稿 - 完整参数
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/draft/add?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Draft_Box/Add_draft.html
   * </pre>
   *
   * @param addDraft 新建草稿信息
   * @throws WxErrorException .
   */
  String addDraft(WxMpAddDraft addDraft) throws WxErrorException;

  /**
   * 修改草稿 - 完整参数
   * 正常情况下调用成功时，errcode将为0。错误时微信会返回错误码等信息，请根据错误码查询错误信息
   * <pre>
   * 请求地址： POST https://api.weixin.qq.com/cgi-bin/draft/update?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Draft_Box/Update_draft.html
   * </pre>
   *
   * @param updateDraftInfo 修改草稿信息
   * @throws WxErrorException .
   */
  Boolean updateDraft(WxMpUpdateDraft updateDraftInfo) throws WxErrorException;

  /**
   * 获取草稿信息
   *
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/draft/get?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Draft_Box/Get_draft.html
   * </pre>
   *
   * @param mediaId 要获取的草稿的media_id
   * @return 草稿信息
   * @throws WxErrorException .
   */
  WxMpDraftInfo getDraft(String mediaId) throws WxErrorException;

  /**
   * 删除草稿
   * 正常情况下调用成功时，errcode将为0。错误时微信会返回错误码等信息，请根据错误码查询错误信息。
   * 多次删除同一篇草稿，也返回 0.
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/draft/delete?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Draft_Box/Delete_draft.html
   * </pre>
   *
   * @param mediaId 要删除的草稿的media_id
   * @throws WxErrorException .
   */
  Boolean delDraft(String mediaId) throws WxErrorException;

  /**
   * 获取草稿列表
   *
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/draft/batchget?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Draft_Box/Get_draft_list.html
   * </pre>
   *
   * @param offset    分页页数，从0开始 从全部素材的该偏移位置开始返回，0表示从第一个素材返回
   * @param count     每页数量 返回素材的数量，取值在1到20之间
   * @param noContent 1 表示不返回 content 字段，0 表示正常返回，默认为 0
   * @return 草稿信息列表
   * @throws WxErrorException .
   */
  WxMpDraftList listDraft(int offset, int count, int noContent) throws WxErrorException;

  /**
   * 获取草稿列表
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/draft/batchget?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Draft_Box/Get_draft_list.html
   * </pre>
   *
   * @param offset 分页页数，从0开始 从全部素材的该偏移位置开始返回，0表示从第一个素材返回
   * @param count  每页数量 返回素材的数量，取值在1到20之间
   * @return
   * @throws WxErrorException
   */
  WxMpDraftList listDraft(int offset, int count) throws WxErrorException;

  /**
   * 获取草稿数量
   * 开发者可以根据本接口来获取草稿的总数。此接口只统计数量，不返回草稿的具体内容。
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/draft/count?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Draft_Box/Count_drafts.html
   * </pre>
   *
   * @return 草稿的总数
   * @throws WxErrorException .
   */
  Long countDraft() throws WxErrorException;

}
