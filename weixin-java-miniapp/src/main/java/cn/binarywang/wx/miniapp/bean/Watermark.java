package cn.binarywang.wx.miniapp.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * 数据水印.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2020-05-25
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Watermark implements Serializable {
  private static final long serialVersionUID = 2375642809946928650L;

  private String timestamp;
  private String appid;
}
