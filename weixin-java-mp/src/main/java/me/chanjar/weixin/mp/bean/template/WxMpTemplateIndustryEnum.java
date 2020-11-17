package me.chanjar.weixin.mp.bean.template;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 模版消息行业枚举.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * @date 2019-10-18
 */
@Getter
@AllArgsConstructor
public enum WxMpTemplateIndustryEnum {
  /**
   * IT科技 - 互联网|电子商务
   */
  E_COMMERCE("IT科技", "互联网|电子商务", 1),
  /**
   * IT科技 - IT软件与服务
   */
  IT_SOFTWARE_AND_SERVICES("IT科技", "IT软件与服务", 2),
  /**
   * IT科技 - IT硬件与设备
   */
  IT_HARDWARE_AND_EQUIPMENT("IT科技", "IT硬件与设备", 3),
  /**
   * IT科技 - 电子技术
   */
  ELECTRONIC_TECHNIQUE("IT科技", "电子技术", 4),
  /**
   * IT科技 - 通信与运营商
   */
  COMMUNICATION_AND_OPERATOR("IT科技", "通信与运营商", 5),
  /**
   * IT科技 - 网络游戏
   */
  ONLINE_GAME("IT科技", "网络游戏", 6),
  /**
   * 金融业 - 银行
   */
  BANK("金融业", "银行", 7),
  /**
   * 金融业 - 证券|基金|理财|信托（实际是这个）
   */
  FUND("金融业", "证券基金理财信托", 8),
  /**
   * 金融业 - 保险
   */
  INSURANCE("金融业", "保险", 9),
  /**
   * 餐饮 - 餐饮
   */
  REPAST("餐饮", "餐饮", 10),
  /**
   * 酒店旅游 - 酒店
   */
  HOTEL("酒店旅游", "酒店", 11),
  /**
   * 酒店旅游 - 旅游
   */
  TRAVEL("酒店旅游", "旅游", 12),
  /**
   * 运输与仓储 - 快递
   */
  EXPRESS("运输与仓储", "快递", 13),
  /**
   * 运输与仓储 - 物流
   */
  LOGISTICS("运输与仓储", "物流", 14),
  /**
   * 运输与仓储 - 仓储
   */
  STORAGE("运输与仓储", "仓储", 15),
  /**
   * 教育 - 培训
   */
  CULTIVATE("教育", "培训", 16),
  /**
   * 教育 - 院校
   */
  ACADEMY("教育", "院校", 17),
  /**
   * 政府与公共事业 - 学术科研
   */
  ACADEMIC_RESEARCH("政府与公共事业", "学术科研", 18),
  /**
   * 政府与公共事业 - 交警
   */
  TRAFFIC_POLICE("政府与公共事业", "交警", 19),
  /**
   * 政府与公共事业 - 博物馆
   */
  MUSEUM("政府与公共事业", "博物馆", 20),
  /**
   * 政府与公共事业 - 公共事业非盈利机构
   */
  PUBLIC_WORKS_NONPROFIT("政府与公共事业", "公共事业非盈利机构", 21),
  /**
   * 医药护理 - 医药医疗
   */
  MEDICAL_HEALTH("医药护理", "医药医疗", 22),
  /**
   * 医药护理 - 护理美容
   */
  CARE_AND_BEAUTY("医药护理", "护理美容", 23),
  /**
   * 医药护理 - 保健与卫生
   */
  HEALTH_AND_HYGIENE("医药护理", "保健与卫生", 24),
  /**
   * 交通工具 - 汽车相关
   */
  AUTOMOTIVE_RELATED("交通工具", "汽车相关", 25),
  /**
   * 交通工具 - 摩托车相关
   */
  MOTORCYCLE_CORRELATION("交通工具", "摩托车相关", 26),
  /**
   * 交通工具 - 火车相关
   */
  THE_TRAIN_RELATED("交通工具", "火车相关", 27),
  /**
   * 交通工具 - 飞机相关
   */
  THE_PLANE_RELATED("交通工具", "飞机相关", 28),
  /**
   * 房地产 - 房地产|建筑（实际上是这个）
   */
  ARCHITECTURE("房地产", "建筑", 29),
  /**
   * 房地产 - 物业
   */
  REAL_ESTATE("房地产", "物业", 30),
  /**
   * 消费品 - 消费品
   */
  CONSUMER_GOODS("消费品", "消费品", 31),
  /**
   * 商业服务 - 法律
   */
  LEGISLATION("商业服务", "法律", 32),
  /**
   * 商业服务 - 会展
   */
  CONVENTION_AND_EXHIBITION("商业服务", "会展", 33),
  /**
   * 商业服务 - 中介服务
   */
  INTERMEDIARY_SERVICES("商业服务", "中介服务", 34),
  /**
   * 商业服务 - 认证
   */
  AUTHENTICATION("商业服务", "认证", 35),
  /**
   * 商业服务 - 审计
   */
  AUDIT("商业服务", "审计", 36),
  /**
   * 文体娱乐 - 传媒
   */
  MASS_MEDIA("文体娱乐", "传媒", 37),
  /**
   * 文体娱乐 - 体育
   */
  SPORTS("文体娱乐", "体育", 38),
  /**
   * 文体娱乐 - 娱乐休闲
   */
  LEISURE_AND_ENTERTAINMENT("文体娱乐", "娱乐休闲", 39),
  /**
   * 印刷 - 印刷
   */
  PRINTING("印刷", "印刷", 40),
  /**
   * 其它 - 其它
   */
  OTHER("其它", "其它", 41);

  /**
   * 主行业（一级行业）
   */
  public final String firstClass;
  /**
   * 副行业（二级行业）
   */
  public final String secondClass;
  /**
   * 行业代码
   */
  public final Integer code;

  /**
   * 查找行业
   *
   * @param firstClass  主行业名称
   * @param secondClass 副行业名称
   * @return .
   */
  public static WxMpTemplateIndustryEnum findByClass(String firstClass, String secondClass) {
    for (WxMpTemplateIndustryEnum industryEnum : WxMpTemplateIndustryEnum.values()) {
      if (industryEnum.firstClass.equals(firstClass) && industryEnum.secondClass.contains(secondClass)) {
        return industryEnum;
      }
    }

    return null;
  }

  /**
   * 查找行业
   *
   * @param code 行业编码
   * @return .
   */
  public static WxMpTemplateIndustryEnum findByCode(int code) {
    for (WxMpTemplateIndustryEnum industryEnum : WxMpTemplateIndustryEnum.values()) {
      if (industryEnum.code == code) {
        return industryEnum;
      }
    }

    return null;
  }
}
