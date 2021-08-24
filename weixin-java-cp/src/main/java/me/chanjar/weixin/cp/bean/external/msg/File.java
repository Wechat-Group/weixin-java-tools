package me.chanjar.weixin.cp.bean.external.msg;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 文件
 *
 * @author pg
 * @date 2021-6-21
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class File implements Serializable {

  private static final long serialVersionUID = -7751578591573716022L;

  @SerializedName("media_id")
  private String mediaId;
}
