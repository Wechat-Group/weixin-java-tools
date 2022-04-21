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


  private Integer status;

  @SerializedName("data_list")
  private List<ExportData> dataList;


  @Data
  public static class ExportData {

    private String url;

    private Integer size;

    private String md5;
  }
}
