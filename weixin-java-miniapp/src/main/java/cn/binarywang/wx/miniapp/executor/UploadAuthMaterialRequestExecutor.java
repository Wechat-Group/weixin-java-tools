package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.WxMaUploadAuthMaterialResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;

import java.io.File;
import java.io.IOException;

/**
 * 小程序认证上传补充材料
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 *
 * @author penhuozhu
 * @since 2024/01/07
 */
public abstract class UploadAuthMaterialRequestExecutor<H, P> implements RequestExecutor<WxMaUploadAuthMaterialResult, File> {
    protected RequestHttp<H, P> requestHttp;

    public UploadAuthMaterialRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    @Override
    public void execute(String uri, File data, ResponseHandler<WxMaUploadAuthMaterialResult> handler, WxType wxType) throws WxErrorException, IOException {
        handler.handle(this.execute(uri, data, wxType));
    }

    public static RequestExecutor<WxMaUploadAuthMaterialResult, File> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new ApacheUploadAuthMaterialRequestExecutor(requestHttp);
            case JODD_HTTP:
                return new JoddHttpUploadAuthMaterialRequestExecutor(requestHttp);
            case OK_HTTP:
                return new OkHttpUploadAuthMaterialRequestExecutor(requestHttp);
            default:
                return null;
        }
    }
}
