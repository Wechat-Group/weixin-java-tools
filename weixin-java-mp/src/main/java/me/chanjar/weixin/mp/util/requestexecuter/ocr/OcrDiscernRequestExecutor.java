package me.chanjar.weixin.mp.util.requestexecuter.ocr;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;

import java.io.File;
import java.io.IOException;

/**
 * @author : zhayueran
 * @Date: 2019/6/27 15:06
 * @Description:
 */
public abstract class OcrDiscernRequestExecutor<H, P> implements RequestExecutor<String, File> {
    protected RequestHttp<H, P> requestHttp;

    public OcrDiscernRequestExecutor(RequestHttp requestHttp) {
        this.requestHttp = requestHttp;
    }

    @Override
    public void execute(String uri, File data, ResponseHandler<String> handler) throws WxErrorException, IOException {
        handler.handle(this.execute(uri, data));
    }

    public static RequestExecutor<String, File> create(RequestHttp requestHttp) {
        switch (requestHttp.getRequestType()) {
            case APACHE_HTTP:
                return new OcrDiscernApacheHttpRequestExecutor(requestHttp);
            default:
                return null;
        }
    }
}
