package me.chanjar.weixin.common.executor;

import me.chanjar.weixin.common.bean.CommonUploadParam;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;

import java.io.IOException;

/**
 * 通用文件上传执行器
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 * created on  2024/01/11
 */
public abstract class CommonUploadRequestExecutor<H, P> implements RequestExecutor<String, CommonUploadParam> {

  protected RequestHttp<H, P> requestHttp;

  public CommonUploadRequestExecutor(RequestHttp<H, P> requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, CommonUploadParam data, ResponseHandler<String> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  /**
   * 构造通用文件上传执行器
   *
   * @param requestHttp 请求信息
   * @return 执行器
   */
  @SuppressWarnings({"rawtypes", "unchecked"})
  public static RequestExecutor<String, CommonUploadParam> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new CommonUploadRequestExecutorApacheImpl(requestHttp);
      case JODD_HTTP:
        return new CommonUploadRequestExecutorJoddHttpImpl(requestHttp);
      case OK_HTTP:
        return new CommonUploadRequestExecutorOkHttpImpl(requestHttp);
      default:
        throw new IllegalArgumentException("不支持的http执行器类型：" + requestHttp.getRequestType());
    }
  }
}
