package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.freepublish.WxMpFreePublishInfo;
import me.chanjar.weixin.mp.bean.freepublish.WxMpFreePublishList;
import me.chanjar.weixin.mp.bean.freepublish.WxMpFreePublishStatus;

/**
 * 微信 发布能力 接口.
 *
 * @author dragon
 * @date 2021-10-23
 */
public interface WxMpFreePublishService {

  /**
   * 发布接口 - 只有默认必填参数
   * 开发者需要先将图文素材以草稿的形式保存（见“草稿箱/新建草稿”，如需从已保存的草稿中选择，见“草稿箱/获取草稿列表”），选择要发布的草稿 media_id 进行发布
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/freepublish/submit?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Publish/Publish.html
   * </pre>
   *
   * @param mediaId        要发布的草稿的media_id
   * @throws WxErrorException .
   */
  String submit(String mediaId) throws WxErrorException;

  /**
   * 发布状态轮询接口
   * 开发者可以尝试通过下面的发布状态轮询接口获知发布情况。
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/freepublish/get?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Publish/Get_status.html
   * </pre>
   *
   * @param publishId 发布任务id
   * @throws WxErrorException .
   */
  WxMpFreePublishStatus getPushStatus(String publishId) throws WxErrorException;

  /**
   * 删除发布
   * 发布成功之后，随时可以通过该接口删除。此操作不可逆，请谨慎操作。
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/freepublish/delete?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Publish/Delete_posts.html
   * </pre>
   *
   * @param articleId 成功发布时返回的 article_id
   * @param index 要删除的文章在图文消息中的位置，第一篇编号为1，该字段不填或填0会删除全部文章
   * @throws WxErrorException .
   */
  Boolean deletePush(String articleId, Integer index) throws WxErrorException;

  /**
   * 删除发布 - 此条发布的所有内容，不指定文章编号
   * 发布成功之后，随时可以通过该接口删除。此操作不可逆，请谨慎操作。
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/freepublish/delete?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Publish/Delete_posts.html
   * </pre>
   *
   * @param articleId 成功发布时返回的 article_id
   * @throws WxErrorException .
   */
  Boolean deletePushAllArticle(String articleId) throws WxErrorException;

  /**
   * 通过 article_id 获取已发布文章
   * 开发者可以通过 article_id 获取已发布的图文信息。
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/freepublish/getarticle?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Publish/Get_article_from_id.html
   * </pre>
   *
   * @param articleId 要获取的草稿的article_id
   * @return 已发布文章信息
   * @throws WxErrorException .
   */
  WxMpFreePublishInfo getArticleFromId(String articleId) throws WxErrorException;

  /**
   * 获取成功发布列表 - 支持选择是否返回：图文消息的具体内容
   *
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/draft/batchget?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Publish/Get_publication_records.html
   * </pre>
   *
   * @param offset    分页页数，从0开始 从全部素材的该偏移位置开始返回，0表示从第一个素材返回
   * @param count     每页数量 返回素材的数量，取值在1到20之间
   * @param noContent 1 表示不返回 content 字段，0 表示正常返回，默认为 0
   * @return 草稿信息列表
   * @throws WxErrorException .
   */
  WxMpFreePublishList getPublicationRecords(int offset, int count, int noContent) throws WxErrorException;

  /**
   * 获取成功发布列表 - 默认返回 图文消息的具体内容
   * <pre>
   * 请求地址：POST https://api.weixin.qq.com/cgi-bin/draft/batchget?access_token=ACCESS_TOKEN
   * 文档地址：https://developers.weixin.qq.com/doc/offiaccount/Publish/Get_publication_records.html
   * </pre>
   *
   * @param offset 分页页数，从0开始 从全部素材的该偏移位置开始返回，0表示从第一个素材返回
   * @param count  每页数量 返回素材的数量，取值在1到20之间
   * @return
   * @throws WxErrorException
   */
  WxMpFreePublishList getPublicationRecords(int offset, int count) throws WxErrorException;

}
