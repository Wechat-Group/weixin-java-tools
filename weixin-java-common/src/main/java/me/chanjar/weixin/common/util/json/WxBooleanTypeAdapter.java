package me.chanjar.weixin.common.util.json;

import com.google.gson.JsonParseException;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;
import org.apache.commons.lang3.BooleanUtils;

import java.io.IOException;

/**
 * <pre>
 * Gson 布尔类型类型转换器
 * Created by Binary Wang on 2017-7-8.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public class WxBooleanTypeAdapter extends TypeAdapter<Boolean> {
  @Override
  public void write(JsonWriter out, Boolean value) throws IOException {
    if (value == null) {
      out.nullValue();
    } else {
      out.value(value);
    }
  }

  @Override
  public Boolean read(JsonReader in) throws IOException {
    JsonToken peek = in.peek();
    switch (peek) {
      case BOOLEAN:
        return in.nextBoolean();
      case NULL:
        in.nextNull();
        return null;
      case NUMBER:
        return BooleanUtils.toBoolean(in.nextInt());
      case STRING:
        return BooleanUtils.toBoolean(in.nextString());
      default:
        throw new JsonParseException("Expected BOOLEAN or NUMBER but was " + peek);
    }
  }
}
