package com.study.page.util;

import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * Description: 拼音操作的工具类
 */
@Slf4j
public class PinYinUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(PinYinUtil.class);

    public static class PinYinMsg {
        public String pinyin;
        /**
         * 首字母缩写
         */
        public String acronym;
    }

    /**
     * 不递归取出所有的拼音串，每个中文只取出第一个音
     */
    public static PinYinMsg getPinYinString(String chineseCharString) {
        if (chineseCharString != null
                && !"".equalsIgnoreCase(chineseCharString.trim())) {
            char[] srcChar;
            srcChar = chineseCharString.toCharArray();
            // 汉语拼音格式输出类
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();

            // 输出设置，大小写，音标方式等
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

            String[][] temp = new String[chineseCharString.length()][];
            for (int i = 0; i < srcChar.length; i++) {
                char c = srcChar[i];
                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
                    /* 如果是中文将转转成拼音 */
                    try {
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(
                                srcChar[i], hanYuPinOutputFormat);
                    } catch (BadHanyuPinyinOutputFormatCombination e) {
                        LOGGER.error("PinYinUtil Word is ->{}", chineseCharString);
                        LOGGER.error("PinYinUtil is error ->{}", e);
                    }
                } else {
                    /* 如果是其他字符将保留 */
                    temp[i] = new String[]{String.valueOf(srcChar[i])};
                }
            }
            PinYinMsg ret = new PinYinMsg();
            {
                StringBuilder sb = new StringBuilder();

                for (String[] pinYinChar : temp) {
                    if (pinYinChar != null && pinYinChar.length > 0) {
                        sb.append(pinYinChar[0]);
                    }
                }
                ret.pinyin = sb.toString();
            }

            {
                StringBuilder sb = new StringBuilder();

                for (String[] pinYinChar : temp) {
                    if (pinYinChar != null && pinYinChar.length > 1) {
                        sb.append(pinYinChar[0].charAt(0));
                    }
                    if (pinYinChar != null && pinYinChar.length == 1) {
//						if (pinYinChar[0].matches("[a-zA-Z]+"))
                        sb.append(pinYinChar[0].charAt(0));
                    }

                }
                ret.acronym = sb.toString();
            }
            return ret;
        }
        return null;
    }

    public static Set<String> getPinYinSet(String chineseCharString) {
        if (chineseCharString != null
                && !"".equalsIgnoreCase(chineseCharString.trim())) {
            char[] srcChar;
            srcChar = chineseCharString.toCharArray();
            // 汉语拼音格式输出类
            HanyuPinyinOutputFormat hanYuPinOutputFormat = new HanyuPinyinOutputFormat();

            // 输出设置，大小写，音标方式等
            hanYuPinOutputFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
            hanYuPinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
            hanYuPinOutputFormat.setVCharType(HanyuPinyinVCharType.WITH_V);

            String[][] temp = new String[chineseCharString.length()][];
            for (int i = 0; i < srcChar.length; i++) {
                char c = srcChar[i];

                if (String.valueOf(c).matches("[\\u4E00-\\u9FA5]+")) {
					/* 如果是中文将转转成拼音 */
                    try {
                        temp[i] = PinyinHelper.toHanyuPinyinStringArray(
                                srcChar[i], hanYuPinOutputFormat);
                    } catch (BadHanyuPinyinOutputFormatCombination e) {
                        LOGGER.error("PinYinUtil Word is ->{}",chineseCharString);
                        LOGGER.error("PinYinUtil is error ->{}",e);
                    }
                } else {
					/* 如果是其他字符将保留 */
                    temp[i] = new String[]{String.valueOf(srcChar[i])};
                }

				/*
				 * 是中文或者a-z或者A-Z转换拼音(我的需求，是保留中文或者a-z或者A-Z)
				 *
				 * else if (((int) c >= 65 && (int) c <= 90) || ((int) c >= 97
				 * && (int) c <= 122)) { temp[i] = new String[] {
				 * String.valueOf(srcChar[i]) }; } else { temp[i] = new String[]
				 * { String.valueOf(srcChar[i]) }; }
				 */

            }
            String[] pinyinArray = exchange(temp);
            Set<String> pinyinSet = new HashSet<String>();
            for (int i = 0; i < pinyinArray.length; i++) {
                pinyinSet.add(pinyinArray[i]);
            }
            return pinyinSet;
        }
        return null;
    }


    /**
     * 获取字符串内的所有汉字的汉语拼音并大写每个字的首字母
     *
     * @param chinese
     * @return
     */
    public static String spellAllFirstUpper(String chinese) {
        if (chinese == null) {
            return null;
        }
        String pinyin = null;
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不标声调
        format.setVCharType(HanyuPinyinVCharType.WITH_V);// u:的声母替换为v
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chinese.length(); i++) {
                String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), format);
                if (array == null || array.length == 0) {
                    pinyin = String.valueOf(chinese.charAt(i)).toUpperCase();
                } else {
                    String s = array[0];// 不管多音字,只取第一个
                    char c = s.charAt(0);// 大写第一个字母
                    pinyin = String.valueOf(c).toUpperCase().concat(s.substring(1));
                }
                sb.append(pinyin);
            }
            return sb.toString();
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取字符串内的所有汉字的汉语拼音大写的首字母
     *
     * @param chinese
     * @return
     */
    public static String spellFirstUpper(String chinese) {
        if (chinese == null) {
            return null;
        }
        String pinyin = null;
        HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
        format.setCaseType(HanyuPinyinCaseType.LOWERCASE);// 小写
        format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);// 不标声调
        format.setVCharType(HanyuPinyinVCharType.WITH_V);// u:的声母替换为v
        try {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < chinese.length(); i++) {
                String[] array = PinyinHelper.toHanyuPinyinStringArray(chinese.charAt(i), format);
                if (array == null || array.length == 0) {
                    pinyin = String.valueOf(chinese.charAt(i)).toUpperCase();
                } else {
                    String s = array[0];// 不管多音字,只取第一个
                    char c = s.charAt(0);// 大写第一个字母
                    pinyin = String.valueOf(c).toUpperCase();
                }
                sb.append(pinyin);
            }
            return sb.toString();
        } catch (BadHanyuPinyinOutputFormatCombination e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 递归
     *
     * @param strJaggedArray
     * @return
     * @author
     */
    private static String[] exchange(String[][] strJaggedArray) {
        String[][] temp = doExchange(strJaggedArray);
        return temp[0];
    }

    /**
     * 递归
     *
     * @param strJaggedArray
     * @return
     * @author
     */
    private static String[][] doExchange(String[][] strJaggedArray) {
        int len = strJaggedArray.length;
        if (len >= 2) {
            int len1 = strJaggedArray[0].length;
            int len2 = strJaggedArray[1].length;
            int newlen = len1 * len2;
            String[] temp = new String[newlen];
            int count = 0;
            for (int index = 0; index < len1; index++) {
                for (int j = 0; j < len2; j++) {
                    temp[count] = strJaggedArray[0][index]
                            + strJaggedArray[1][j];
                    count++;
                }
            }
            String[][] newArray = new String[len - 1][];
            for (int index = 2; index < len; index++) {
                newArray[index - 1] = strJaggedArray[index];
            }
            newArray[0] = temp;
            return doExchange(newArray);
        } else {
            return strJaggedArray;
        }
    }
}
