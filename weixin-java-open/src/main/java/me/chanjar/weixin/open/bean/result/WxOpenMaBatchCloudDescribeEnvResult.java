package me.chanjar.weixin.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.List;

/**
 * 微信第三方平台-批量代云开发-环境信息
 *
 * @author caiqy
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaBatchCloudDescribeEnvResult extends WxOpenResult {

  private static final long serialVersionUID = -2824724885377487126L;

  @SerializedName("info_list")
  private List<EnvInfoItem> infoList;

  @Data
  public static class EnvInfoItem {
    /**
     * 环境ID
     */
    @SerializedName("env")
    private String env;

    /**
     * 环境别名
     */
    @SerializedName("alias")
    private String alias;

    /**
     * 创建时间
     */
    @SerializedName("create_time")
    private String createTime;

    /**
     * 最后修改时间
     */
    @SerializedName("update_ime")
    private String updateIme;

    /**
     * 环境状态
     */
    @SerializedName("status")
    private String status;

    /**
     * tcb产品套餐ID
     */
    @SerializedName("package_id")
    private String packageId;

    /**
     * 套餐中文名称
     */
    @SerializedName("package_name")
    private String packageName;

    /**
     * 数据库示例ID
     */
    @SerializedName("dbinstance_id")
    private String dbInstanceId;

    /**
     * 静态存储ID
     */
    @SerializedName("bucket_id")
    private String bucketId;
  }

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
