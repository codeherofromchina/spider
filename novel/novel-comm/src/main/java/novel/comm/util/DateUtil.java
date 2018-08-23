package novel.comm.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by wangxiaodan on 2018/7/6.
 */
public class DateUtil {
    public static final String LONG_FORMAT = "yyyy-MM-dd hh:mm:ss";

    /**
     * 将字符串转换为日期
     * @param date
     * @param format
     * @return
     */
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

    /**
     * 格式化日期成字符串
     * @param date
     * @param formatPattern
     * @return
     */
    public static String format(Date date, String formatPattern) {
        if (date != null) {
            return new SimpleDateFormat(formatPattern).format(date);
        }
        return null;
    }
}
