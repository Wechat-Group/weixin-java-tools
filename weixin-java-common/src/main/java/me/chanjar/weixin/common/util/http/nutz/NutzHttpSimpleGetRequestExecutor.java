package me.chanjar.weixin.common.util.http.nutz;

import java.io.IOException;

import org.nutz.http.Http;
import org.nutz.http.ProxySwitcher;
import org.nutz.http.Response;
import org.nutz.http.Sender;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimpleGetRequestExecutor;

/**
 * .
 *
 * @author ecoolper
 * @date 2017/5/4
 */
public class NutzHttpSimpleGetRequestExecutor extends SimpleGetRequestExecutor<Sender, ProxySwitcher> {

	public NutzHttpSimpleGetRequestExecutor(RequestHttp<Sender, ProxySwitcher> requestHttp) {
		super(requestHttp);
		if (requestHttp.getRequestHttpProxy() != null) {
			Http.setProxySwitcher(requestHttp.getRequestHttpProxy());
		}

	}

	@Override
	public String execute(String uri, String queryParam, WxType wxType) throws WxErrorException, IOException {
		if (queryParam != null) {
			if (uri.indexOf('?') == -1) {
				uri += '?';
			}
			uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
		}

		Response response = Http.get(uri);

		return handleResponse(wxType, response.getContent());
	}

}
