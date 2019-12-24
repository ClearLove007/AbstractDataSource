package com.example.intercepetor.utils.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: XueWeiDong
 * @Description:
 * @Date: 14:21 2019/12/23
 */
public class ExcelWriteUtil {
    protected static final DateFormat DEFAULT_FORMAT_DATE = new SimpleDateFormat("yyyy-MM-dd");
    protected static final DateFormat DEFAULT_FORMAT_DATE_TIME = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private static final String[] DATE_FORMATS_TRY_PARSE = new String[] { "yyyyMMddHHmmss", "yyyy-MM-dd HH:mm:ss", "yyyy-MM-dd", "yyyy/MM/dd", "yyyyMMdd", "yyyy-MM", "yyyy/MM", "yyyyMM", "yyyy" };

    public static final int DEFAULT_SCALE = 2;
    public static final int DEFAULT_ROUNDING_MODE = BigDecimal.ROUND_HALF_UP;

    public static final String[] YES_NO = new String[] { "YES", "NO" };

    public static void writeBoolean(Cell cell, Object value, String[] options) {
        String strValue = value == null ? "" : value.toString();
        boolean isFalse =
                (strValue.equals("0") || strValue.equalsIgnoreCase("false") || strValue.equalsIgnoreCase("NO") || strValue.equalsIgnoreCase("F") || strValue.equalsIgnoreCase("N")
                        || strValue.equalsIgnoreCase("") || strValue.equals("0.0"));
        cell.setCellValue(isFalse ? options[1] : options[0]);

    }

    public static void writeBoolean(Cell cell, Object value) {
        writeBoolean(cell, value, YES_NO);
    }

    public static void writeInt(Cell cell, Object value) {
        if (value == null) {
            cell.setCellValue("");
            return;
        }
        if (value instanceof Integer || value instanceof Long) {
            cell.setCellValue(value.toString());
        } else if (value instanceof String) {
            cell.setCellValue((String) value);
        } else {
            cell.setCellValue(Double.valueOf(value.toString()).intValue() + "");
        }
    }

    public static void writeDouble(Cell cell, Object value, Integer scale, Integer roundingMode) {
        double val = BigDecimal.valueOf(Double.valueOf(value.toString())).setScale(scale, roundingMode).doubleValue();
        cell.setCellValue(val);
    }

    public static void writeDouble(Cell cell, Object value) {
        if (value == null) {
            cell.setCellValue("");
            return;
        }
        double val = BigDecimal.valueOf(Double.valueOf(value.toString())).setScale(DEFAULT_SCALE, DEFAULT_ROUNDING_MODE).doubleValue();
        cell.setCellValue(String.valueOf(val));
    }

    public static void writeDoubleRaw(Cell cell, Double value) {
        cell.setCellValue(value);
    }

    public static void writeDoubleRaw(Cell cell, Float value) {
        cell.setCellValue(value);
    }

    public static void writeDateRaw(Cell cell, Date value) {
        writeDate(cell, value, DATE_FORMATS_TRY_PARSE[1]);
    }

    public static void writeString(Cell cell, String value) {
        if (value == null) {
            cell.setCellValue("");
            return;
        }
        cell.setCellValue(value);
    }

    public static void writeDate(Cell cell, Date value, String format) {
        if (value == null) {
            cell.setCellValue("");
            return;
        }
        cell.setCellValue(new SimpleDateFormat(format).format(value));
    }

    public static void writeDate(Cell cell, String value, String format) {
        Date date = tryParseDate(value);
        writeDate(cell, date, format);
    }

    private static Date tryParseDate(String sValue) {
        Date date = null;
        if (StringUtils.isEmpty(sValue)) {
            return date;
        }
        SimpleDateFormat fmt = new SimpleDateFormat();
        for (String format : DATE_FORMATS_TRY_PARSE) {
            fmt.applyPattern(format);
            try {
                date = fmt.parse(sValue);
            } catch (Exception e) {
                continue;
            }
            if (date != null) {
                return date;
            }
        }
        return null;
    }

}
