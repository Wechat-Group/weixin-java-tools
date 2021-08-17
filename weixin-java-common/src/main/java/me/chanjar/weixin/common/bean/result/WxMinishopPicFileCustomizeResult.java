package me.chanjar.weixin.common.bean.result;

import lombok.Data;

import java.io.Serializable;

@Data
public class WxMinishopPicFileCustomizeResult implements Serializable {
  private String mediaId;
  private String tempImgUrl;
}
