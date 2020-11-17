package me.chanjar.weixin.common.util.http.nutz;

import java.io.File;
import java.io.IOException;

import org.nutz.http.Http;
import org.nutz.http.ProxySwitcher;
import org.nutz.http.Response;
import org.nutz.http.Sender;
import org.nutz.lang.util.NutMap;

import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.MediaUploadRequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;

/**
 * .
 *
 * @author ecoolper
 * @date 2017/5/5
 */
public class NutzHttpMediaUploadRequestExecutor extends MediaUploadRequestExecutor<Sender, ProxySwitcher> {
	public NutzHttpMediaUploadRequestExecutor(RequestHttp<Sender, ProxySwitcher> requestHttp) {
		super(requestHttp);
		if (requestHttp.getRequestHttpProxy() != null) {
			Http.setProxySwitcher(requestHttp.getRequestHttpProxy());
		}
	}

	@Override
	public WxMediaUploadResult execute(String uri, File file, WxType wxType) throws WxErrorException, IOException {
		Response response = Http.post2(uri, NutMap.NEW().addv("media", file), 100000);
		return WxMediaUploadResult.fromJson(response.getContent());
	}
}
