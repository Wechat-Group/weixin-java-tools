package me.chanjar.weixin.qidian.bean.common;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

@Data
public class QidianResponse {
	private static Map<Integer, String> errorCodesMap = new HashMap<Integer, String>() {
		private static final long serialVersionUID = 1125349909878104934L;
		{
			put(-1, "系统繁忙");
			put(0, "请求成功");
			put(40001, "获取access_token时AppSecret错误，或者access_token无效");
			put(40002, "不合法的凭证类型");
			put(40003, "不合法的OpenID");
			put(40004, "不合法的媒体文件类型");
			put(40005, "不合法的文件类型");
			put(40006, "不合法的文件大小");
			put(40007, "不合法的媒体文件id");
			put(40008, "不合法的消息类型");
			put(40009, "不合法的图片文件大小");
			put(40010, "不合法的语音文件大小");
			put(40011, "不合法的视频文件大小");
			put(40012, "不合法的缩略图文件大小");
			put(40013, "不合法的APPID");
			put(40014, "不合法的access_token");
			put(40015, "不合法的菜单类型");
			put(40016, "不合法的按钮个数");
			put(40017, "不合法的按钮个数");
			put(40018, "不合法的按钮名字长度");
			put(40019, "不合法的按钮KEY长度");
			put(40020, "不合法的按钮URL长度");
			put(40021, "不合法的菜单版本号");
			put(40022, "不合法的子菜单级数");
			put(40023, "不合法的子菜单按钮个数");
			put(40024, "不合法的子菜单按钮类型");
			put(40025, "不合法的子菜单按钮名字长度");
			put(40026, "不合法的子菜单按钮KEY长度");
			put(40027, "不合法的子菜单按钮URL长度");
			put(40028, "不合法的自定义菜单使用用户");
			put(40029, "不合法的oauth_code");
			put(40030, "不合法的refresh_token");
			put(40031, "不合法的openid列表");
			put(40032, "不合法的openid列表长度");
			put(40033, "不合法的请求字符，不能包含\\uxxxx格式的字符");
			put(40035, "不合法的参数");
			put(40038, "不合法的请求格式");
			put(40039, "不合法的URL长度");
			put(40050, "不合法的分组id");
			put(40051, "分组名字不合法");
			put(41001, "缺少access_token参数");
			put(41002, "缺少appid参数");
			put(41003, "缺少refresh_token参数");
			put(41004, "缺少secret参数");
			put(41005, "缺少多媒体文件数据");
			put(41006, "缺少media_id参数");
			put(41007, "缺少子菜单数据");
			put(41008, "缺少oauth code");
			put(41009, "缺少openid");
			put(42001, "access_token超时");
			put(42002, "refresh_token超时");
			put(42003, "oauth_code超时");
			put(43001, "需要GET请求");
			put(43002, "需要POST请求");
			put(43003, "需要HTTPS请求");
			put(43004, "需要接收者关注");
			put(43005, "需要好友关系");
			put(44001, "多媒体文件为空");
			put(44002, "POST的数据包为空");
			put(44003, "图文消息内容为空");
			put(44004, "文本消息内容为空");
			put(45001, "多媒体文件大小超过限制");
			put(45002, "消息内容超过限制");
			put(45003, "标题字段超过限制");
			put(45004, "描述字段超过限制");
			put(45005, "链接字段超过限制");
			put(45006, "图片链接字段超过限制");
			put(45007, "语音播放时间超过限制");
			put(45008, "图文消息超过限制");
			put(45009, "接口调用超过限制");
			put(45010, "创建菜单个数超过限制");
			put(45015, "回复时间超过限制");
			put(45016, "系统分组，不允许修改");
			put(45017, "分组名字过长");
			put(45018, "分组数量超过上限");
			put(46001, "不存在媒体数据");
			put(46002, "不存在的菜单版本");
			put(46003, "不存在的菜单数据");
			put(46004, "不存在的用户");
			put(47001, "解析JSON/XML内容错误");
			put(48001, "api功能未授权");
			put(50001, "用户未授权该api");
		}
	};
	private Integer code = 0;
	private String msg;
	private Integer errcode = 0;
	private String errmsg = "ok";
	private String errmsgChinese;

	public String getErrmsgChinese() {
		if (errcode != null && errmsgChinese == null) {
			errmsgChinese = errorCodesMap.get(errcode);
		}
		return errmsgChinese;
	}
}
