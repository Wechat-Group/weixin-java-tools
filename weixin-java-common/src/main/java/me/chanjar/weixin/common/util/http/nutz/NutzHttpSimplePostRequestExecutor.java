package me.chanjar.weixin.common.util.http.nutz;

import java.io.IOException;

import org.nutz.http.Header;
import org.nutz.http.Http;
import org.nutz.http.ProxySwitcher;
import org.nutz.http.Response;
import org.nutz.http.Sender;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.SimplePostRequestExecutor;

/**
 * .
 *
 * @author ecoolper
 * @date 2017/5/4
 */
public class NutzHttpSimplePostRequestExecutor extends SimplePostRequestExecutor<Sender, ProxySwitcher> {
	
  public NutzHttpSimplePostRequestExecutor(RequestHttp<Sender, ProxySwitcher>  requestHttp) {
    super(requestHttp);
    if (requestHttp.getRequestHttpProxy() != null) {
		Http.setProxySwitcher(requestHttp.getRequestHttpProxy());
	}
  }

  @Override
  public String execute(String uri, String postEntity, WxType wxType) throws WxErrorException, IOException {
    Response response = Http.post3(uri, postEntity, Header.create().asJsonContentType()	, 5000);
	return handleResponse(wxType, response.getContent());
  }

}
