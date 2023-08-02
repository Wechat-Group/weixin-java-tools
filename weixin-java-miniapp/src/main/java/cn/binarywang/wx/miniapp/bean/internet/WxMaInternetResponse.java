package cn.binarywang.wx.miniapp.bean.internet;

import cn.binarywang.wx.miniapp.bean.WxMaBaseResponse;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.List;

/**
 *
 *
 * <pre>
 *  获取用户encryptKey。 用户最近三次的加密key，每个key的存活时间为3600s。
 * 【小程序-服务端-网络】网络相关接口.
 *  文档地址：https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/internet/internet.getUserEncryptKey.html
 *  微信返回报文：
 *  {
 *     "errcode":0,
 *     "errmsg":"ok",
 *     "key_info_list":
 *     [
 *         {
 *             "encrypt_key":"VI6BpyrK9XH4i4AIGe86tg==",
 *             "version":10,
 *             "expire_in":3597,
 *             "iv":"6003f73ec441c386",
 *             "create_time":1616572301
 *         },
 *         {
 *             "encrypt_key":"aoUGAHltcliiL9f23oTKHA==",
 *             "version":9,
 *             "expire_in":0,
 *             "iv":"7996656384218dbb",
 *             "create_time":1616504886
 *         },
 *         {
 *             "encrypt_key":"MlZNQNnRQz3zXHHcr6A3mA==",
 *             "version":8,
 *             "expire_in":0,
 *             "iv":"58a1814f88883024",
 *             "create_time":1616488061
 *         }
 *     ]
 * }
 * </pre>
 *
 * @author <a href="https://github.com/chutian0124">chutian0124</a>
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxMaInternetResponse extends WxMaBaseResponse implements Serializable {

  private static final long serialVersionUID = 6254922047193011785L;
  /**
   * 用户最近三次的加密key列表
   */
  @SerializedName("key_info_list")
  List<WxMaInternetUserKeyInfo> keyInfoList;
}
