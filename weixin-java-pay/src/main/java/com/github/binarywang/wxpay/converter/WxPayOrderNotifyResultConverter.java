package com.github.binarywang.wxpay.converter;

import com.github.binarywang.wxpay.bean.WxPayOrderNotifyCoupon;
import com.github.binarywang.wxpay.bean.result.WxPayOrderNotifyResult;
import com.google.common.base.Function;
import com.google.common.collect.Maps;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.converters.reflection.AbstractReflectionConverter;
import com.thoughtworks.xstream.converters.reflection.ReflectionProvider;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.mapper.Mapper;
import org.apache.commons.lang3.StringUtils;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WxPayOrderNotifyResultConverter extends AbstractReflectionConverter {

  public WxPayOrderNotifyResultConverter(Mapper mapper, ReflectionProvider reflectionProvider) {
    super(mapper, reflectionProvider);
  }

  @Override
  @SuppressWarnings("rawtypes")
  public boolean canConvert(Class type) {
    return type.equals(WxPayOrderNotifyResult.class);
  }

  @Override
  public void marshal(Object original, HierarchicalStreamWriter writer, MarshallingContext context) {
    super.marshal(original, writer, context);
    WxPayOrderNotifyResult obj = (WxPayOrderNotifyResult) original;
    List<WxPayOrderNotifyCoupon> list = obj.getCouponList();
    if (list == null || list.size() == 0) {
      return;
    }
    for (int i = 0; i < list.size(); i++) {
      WxPayOrderNotifyCoupon coupon = list.get(i);
      writer.startNode("coupon_id_" + i);
      writer.setValue(coupon.getCouponId());
      writer.endNode();
      writer.startNode("coupon_type_" + i);
      writer.setValue(coupon.getCouponType());
      writer.endNode();
      writer.startNode("coupon_fee_" + i);
      writer.setValue(coupon.getCouponFee() + "");
      writer.endNode();
    }
  }

  @Override
  protected void marshallField(MarshallingContext context, Object newObj, Field field) {
    if (field.getName().equals("couponList")) {
      return;
    } else {
      super.marshallField(context, newObj, field);
    }
  }

  @Override
  public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
    WxPayOrderNotifyResult obj = new WxPayOrderNotifyResult();

    List<Field> fields = new ArrayList<>(Arrays.asList(obj.getClass().getDeclaredFields()));
    fields.addAll(Arrays.asList(obj.getClass().getSuperclass().getDeclaredFields()));
    Map<String, Field> fieldMap = getFieldMap(fields);

    List<WxPayOrderNotifyCoupon> coupons = new ArrayList<>(10);
    while (reader.hasMoreChildren()) {
      reader.moveDown();
      if (fieldMap.containsKey(reader.getNodeName())) {
        Field field = fieldMap.get(reader.getNodeName());
        setFieldValue(context, obj, field);
      } else if (StringUtils.startsWith(reader.getNodeName(), "coupon_id_")) {
        String id = (String) context.convertAnother(obj, String.class);
        getIndex(coupons, reader.getNodeName()).setCouponId(id);
      } else if (StringUtils.startsWith(reader.getNodeName(), "coupon_type_")) {
        String type = (String) context.convertAnother(obj, String.class);
        getIndex(coupons, reader.getNodeName()).setCouponType(type);
      } else if (StringUtils.startsWith(reader.getNodeName(), "coupon_fee_")) {
        Integer fee = (Integer) context.convertAnother(obj, Integer.class);
        getIndex(coupons, reader.getNodeName()).setCouponFee(fee);
      }
      reader.moveUp();
    }

    obj.setCouponList(coupons);
    return obj;
  }

  private void setFieldValue(UnmarshallingContext context, WxPayOrderNotifyResult obj, Field field) {
    Object val = context.convertAnother(obj, field.getType());
    try {
      if (val != null) {
        PropertyDescriptor pd = new PropertyDescriptor(field.getName(), obj.getClass());
        pd.getWriteMethod().invoke(obj, val);
      }
    } catch (Exception e) {
    }
  }

  private Map<String, Field> getFieldMap(List<Field> fields) {
    Map<String, Field> fieldMap = Maps.uniqueIndex(fields, new Function<Field, String>() {
      @Override
      public String apply(Field field) {
        if (field.isAnnotationPresent(XStreamAlias.class)) {
          return field.getAnnotation(XStreamAlias.class).value();
        }
        return field.getName();
      }
    });
    return fieldMap;
  }

  private WxPayOrderNotifyCoupon getIndex(List<WxPayOrderNotifyCoupon> coupons, String nodeName) {
    Integer index = Integer.valueOf(StringUtils.substring(nodeName, nodeName.lastIndexOf("_") + 1));
    if (index >= coupons.size() || coupons.get(index) == null) {
      coupons.add(index, new WxPayOrderNotifyCoupon());
    }
    return coupons.get(index);
  }
}
