package me.chanjar.weixin.mp.api;

import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.bean.tag.WxTagListUser;
import me.chanjar.weixin.mp.bean.tag.WxUserTag;

import java.util.List;

/**
 * 用户标签管理相关接口
 * Created by Binary Wang on 2016/9/2.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMpUserTagService {
    /**
     * <pre>
     * 创建标签
     * 一个公众号，最多可以创建100个标签。
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">用户标签管理</a>
     * 接口url格式： https://api.weixin.qq.com/cgi-bin/tags/create?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param name 标签名字（30个字符以内）
     * @return the wx user tag
     * @throws WxErrorException the wx error exception
     */
    WxUserTag tagCreate(String name) throws WxErrorException;

    /**
     * <pre>
     * 获取公众号已创建的标签
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">用户标签管理</a>
     * 接口url格式： https://api.weixin.qq.com/cgi-bin/tags/get?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @return the list
     * @throws WxErrorException the wx error exception
     */
    List<WxUserTag> tagGet() throws WxErrorException;

    /**
     * <pre>
     * 编辑标签
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">用户标签管理</a>
     * 接口url格式： https://api.weixin.qq.com/cgi-bin/tags/update?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param tagId the tag id
     * @param name  the name
     * @return the boolean
     * @throws WxErrorException the wx error exception
     */
    Boolean tagUpdate(Long tagId, String name) throws WxErrorException;

    /**
     * <pre>
     * 删除标签
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">用户标签管理</a>
     * 接口url格式： https://api.weixin.qq.com/cgi-bin/tags/delete?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param tagId the tag id
     * @return the boolean
     * @throws WxErrorException the wx error exception
     */
    Boolean tagDelete(Long tagId) throws WxErrorException;

    /**
     * <pre>
     * 获取标签下粉丝列表
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">用户标签管理</a>
     * 接口url格式： https://api.weixin.qq.com/cgi-bin/user/tag/get?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param tagId      the tag id
     * @param nextOpenid the next openid
     * @return the wx tag list user
     * @throws WxErrorException the wx error exception
     */
    WxTagListUser tagListUser(Long tagId, String nextOpenid)
    throws WxErrorException;

    /**
     * <pre>
     * 批量为用户打标签
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">用户标签管理</a>
     * 接口url格式： https://api.weixin.qq.com/cgi-bin/tags/members/batchtagging?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param tagId   the tag id
     * @param openids the openids
     * @return the boolean
     * @throws WxErrorException the wx error exception
     */
    boolean batchTagging(Long tagId, String[] openids) throws WxErrorException;

    /**
     * <pre>
     * 批量为用户取消标签
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">用户标签管理</a>
     * 接口url格式： https://api.weixin.qq.com/cgi-bin/tags/members/batchuntagging?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param tagId   the tag id
     * @param openids the openids
     * @return the boolean
     * @throws WxErrorException the wx error exception
     */
    boolean batchUntagging(Long tagId, String[] openids) throws WxErrorException;


    /**
     * <pre>
     * 获取用户身上的标签列表
     * 详情请见：<a href="http://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140837&token=&lang=zh_CN">用户标签管理</a>
     * 接口url格式： https://api.weixin.qq.com/cgi-bin/tags/getidlist?access_token=ACCESS_TOKEN
     * </pre>
     *
     * @param openid the openid
     * @return 标签Id的列表 list
     * @throws WxErrorException the wx error exception
     */
    List<Long> userTagList(String openid) throws WxErrorException;

}
