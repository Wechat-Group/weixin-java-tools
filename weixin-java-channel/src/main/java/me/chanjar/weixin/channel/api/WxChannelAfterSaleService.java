package me.chanjar.weixin.channel.api;


import java.util.List;
import me.chanjar.weixin.channel.bean.after.AfterSaleInfoResponse;
import me.chanjar.weixin.channel.bean.after.AfterSaleListResponse;
import me.chanjar.weixin.channel.bean.base.WxChannelBaseResponse;
import me.chanjar.weixin.channel.bean.complaint.ComplaintOrderResponse;
import me.chanjar.weixin.common.error.WxErrorException;

/**
 * 视频号小店 售后服务接口
 *
 * @author <a href="https://github.com/lixize">Zeyes</a>
 */
public interface WxChannelAfterSaleService {

  /**
   * 获取售后单列表
   *
   * @param beginCreateTime 订单创建启始时间 unix时间戳
   * @param endCreateTime   订单创建结束时间，end_create_time减去begin_create_time不得大于24小时
   * @param nextKey         翻页参数，从第二页开始传，来源于上一页的返回值
   * @return 售后单列表
   *
   * @throws WxErrorException 异常
   */
  AfterSaleListResponse listIds(Long beginCreateTime, Long endCreateTime, String nextKey)
    throws WxErrorException;

  /**
   * 获取售后单详情
   *
   * @param afterSaleOrderId 售后单号
   * @return 售后单信息
   *
   * @throws WxErrorException 异常
   */
  AfterSaleInfoResponse get(String afterSaleOrderId) throws WxErrorException;

  /**
   * 同意退款
   *
   * @param afterSaleOrderId 售后单号
   * @param addressId        同意退货时传入地址id
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse accept(String afterSaleOrderId, String addressId) throws WxErrorException;

  /**
   * 拒绝售后
   *
   * @param afterSaleOrderId 售后单号
   * @param rejectReason     拒绝原因
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse reject(String afterSaleOrderId, String rejectReason) throws WxErrorException;

  /**
   * 上传退款凭证
   *
   * @param afterSaleOrderId 售后单号
   * @param desc             退款凭证描述
   * @param certificates     退款凭证图片列表
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse uploadRefundEvidence(String afterSaleOrderId, String desc, List<String> certificates)
    throws WxErrorException;

  /**
   * 商家补充纠纷单留言
   *
   * @param complaintId 纠纷单号
   * @param content     留言内容，最多500字
   * @param mediaIds    图片media_id列表，所有留言总图片数量最多20张
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse addComplaintMaterial(String complaintId, String content, List<String> mediaIds)
    throws WxErrorException;

  /**
   * 商家举证
   *
   * @param complaintId 纠纷单号
   * @param content     举证内容，最多500字
   * @param mediaIds    图片media_id列表，所有留言总图片数量最多20张
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  WxChannelBaseResponse addComplaintEvidence(String complaintId, String content, List<String> mediaIds)
    throws WxErrorException;

  /**
   * 获取纠纷单
   *
   * @param complaintId 纠纷单号
   * @return BaseResponse
   *
   * @throws WxErrorException 异常
   */
  ComplaintOrderResponse getComplaint(String complaintId) throws WxErrorException;
}
