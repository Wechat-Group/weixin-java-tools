package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.invoice.reimburse.*;

import java.util.List;

/**
 * 电子发票报销方相关接口
 * 接口文档: https://developers.weixin.qq.com/doc/offiaccount/WeChat_Invoice/E_Invoice/Reimburser_API_List.html
 *
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021 -03-23
 */
public interface WxMpReimburseInvoiceService {

    /**
     * 查询报销发票信息
     *
     * @param request {@link InvoiceInfoRequest} 查询报销发票信息参数
     * @return {@link InvoiceInfoResponse} 查询结果
     * @throws WxErrorException 查询失败时
     */
    InvoiceInfoResponse getInvoiceInfo(InvoiceInfoRequest request) throws WxErrorException;


    /**
     * 批量查询报销发票信息
     *
     * @param request {@link InvoiceBatchRequest} 批量查询报销发票信息参数对象
     * @return {@link InvoiceInfoResponse} 查询结果列表
     * @throws WxErrorException 查询失败时
     */
    List<InvoiceInfoResponse> getInvoiceBatch(InvoiceBatchRequest request) throws WxErrorException;


    /**
     * 更新发票状态
     *
     * @param request {@link UpdateInvoiceStatusRequest} 更新发票状态参数
     * @throws WxErrorException 更新失败时
     */
    void updateInvoiceStatus(UpdateInvoiceStatusRequest request) throws WxErrorException;


    /**
     * 批量更新发票状态
     *
     * @param request {@link UpdateStatusBatchRequest} 批量更新发票状态参数
     * @throws WxErrorException 更新失败时
     */
    void updateStatusBatch(UpdateStatusBatchRequest request) throws WxErrorException;
}
