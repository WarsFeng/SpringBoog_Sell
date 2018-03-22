package shop.warscat.sell.utils.seriallazer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Date;

/**
 * Created with IntelliJ IDEA.
 * Description: Date转换时间
 * User: wars
 * Date: 2018-03-22
 * Time: 16:24
 */

public class Date2LongSeriallazer extends JsonSerializer<Date> {

    @Override
    public void serialize(Date date, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeNumber(date.getTime()/1000);
    }
}
