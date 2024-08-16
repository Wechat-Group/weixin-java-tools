package me.chanjar.weixin.open.bean.icp;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import me.chanjar.weixin.open.bean.result.WxOpenResult;

/**
 * @author xzh
 * @Description 人脸核验任务结果
 * @createTime 2024/08/14 10:52
 */
@Getter
@Setter
@NoArgsConstructor
public class WxOpenIcpCreateIcpVerifyTaskResult extends WxOpenResult {

  private static final long serialVersionUID = -8960874090439615220L;

  /**
   * 人脸核验任务id
   */
  @JsonProperty("task_id")
  private String taskId;

}
