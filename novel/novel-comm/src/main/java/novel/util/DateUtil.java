package novel.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangxiaodan on 2018/7/6.
 */
public class DateUtil {
    public static final String LONG_FORMAT = "yyyy-MM-dd hh:mm:ss";


    public static Date parse(String date, String format) {
        if (StringUtils.isNotBlank(date)) {
            try {
                return new SimpleDateFormat(format).parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
