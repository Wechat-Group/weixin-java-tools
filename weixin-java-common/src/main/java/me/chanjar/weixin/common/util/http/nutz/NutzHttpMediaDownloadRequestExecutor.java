package me.chanjar.weixin.common.util.http.nutz;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang3.StringUtils;
import org.nutz.http.Http;
import org.nutz.http.ProxySwitcher;
import org.nutz.http.Response;
import org.nutz.http.Sender;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.fs.FileUtils;
import me.chanjar.weixin.common.util.http.BaseMediaDownloadRequestExecutor;
import me.chanjar.weixin.common.util.http.HttpResponseProxy;
import me.chanjar.weixin.common.util.http.RequestHttp;

/**
 * .
 *
 * @author ecoolper
 * @date 2017/5/5
 */
public class NutzHttpMediaDownloadRequestExecutor extends BaseMediaDownloadRequestExecutor<Sender, ProxySwitcher> {
	public NutzHttpMediaDownloadRequestExecutor(RequestHttp<Sender, ProxySwitcher> requestHttp, File tmpDirFile) {
		super(requestHttp, tmpDirFile);
		if (requestHttp.getRequestHttpProxy() != null) {
			Http.setProxySwitcher(requestHttp.getRequestHttpProxy());
		}
	}

	@Override
	public File execute(String uri, String queryParam, WxType wxType) throws WxErrorException, IOException {
		if (queryParam != null) {
			if (uri.indexOf('?') == -1) {
				uri += '?';
			}
			uri += uri.endsWith("?") ? queryParam : '&' + queryParam;
		}

		Response response = Http.get(uri);

		String contentType = response.getHeader().get("Content-Type");
		if (contentType != null && contentType.startsWith("application/json")) {
			// application/json; encoding=utf-8 下载媒体文件出错
			throw new WxErrorException(WxError.fromJson(response.getContent(), wxType));
		}

		String fileName = new HttpResponseProxy(response).getFileName();
		if (StringUtils.isBlank(fileName)) {
			return null;
		}

		String baseName = FilenameUtils.getBaseName(fileName);
		if (StringUtils.isBlank(fileName) || baseName.length() < 3) {
			baseName = String.valueOf(System.currentTimeMillis());
		}

		return FileUtils.createTmpFile(response.getStream(), baseName, FilenameUtils.getExtension(fileName),
				super.tmpDirFile);
	}

}
