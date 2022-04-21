package me.chanjar.weixin.cp.bean.export;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.cp.bean.WxCpBaseResp;

import java.util.List;

/**
 * 异步导出响应
 *
 * @author zhongjun
 * @date 2022/4/21
 **/
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpExportResult extends WxCpBaseResp {
  private static final long serialVersionUID = -8673839248829238966L;

  /**
   * 任务状态:0-未处理，1-处理中，2-完成，3-异常失败
   */
  private Integer status;

  @SerializedName("data_list")
  private List<ExportData> dataList;


  @Data
  public static class ExportData {

    /**
     * 数据下载链接,支持指定Range头部分段下载。有效期2个小时
     */
    private String url;

    /**
     * 密文数据大小
     */
    private Integer size;

    /**
     * 密文数据md5
     */
    private String md5;
  }
}
