package me.chanjar.weixin.common.util.http;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.conn.DnsResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 微信DNS域名解析器，将微信域名绑定到指定IP
 * --------------------------------------------
 * 适用于服务器端调用微信服务器需要开通出口防火墙情况
 * 
 * Created by Andy Huo on 17/03/28.
 */
public class WxDnsResolver implements DnsResolver {
	
	protected final Logger log = LoggerFactory.getLogger(WxDnsResolver.class);
	
	private static Map<String, InetAddress[]> MAPPINGS = new  HashMap<String, InetAddress[]>();
	
	private final static String WECHAT_API_URL = "api.weixin.qq.com";
	
	private String wxApiIp;
	
	public WxDnsResolver(String ip){
		
		this.wxApiIp = ip;
		this.init();
	}
	
	private void init(){
		if(log.isDebugEnabled()){
			log.debug("init wechat dns config with ip {}", wxApiIp);
		}
		try {
			MAPPINGS.put(WECHAT_API_URL, new InetAddress[]{InetAddress.getByName(wxApiIp)});
		} catch (UnknownHostException e) {
			//如果初始化DNS配置失败则使用默认配置,不影响服务的启动
			log.error("init WxDnsResolver error", e);
			MAPPINGS = new  HashMap<String, InetAddress[]>();
		}
		
	}

	@Override
	public InetAddress[] resolve(String host) throws UnknownHostException {
		
		
		return MAPPINGS.containsKey(host) ? MAPPINGS.get(host) : new InetAddress[0];
	}

	public String getWxApiIp() {
		return wxApiIp;
	}

	public void setWxApiIp(String wxApiIp) {
		this.wxApiIp = wxApiIp;
		this.init();
	}
}
