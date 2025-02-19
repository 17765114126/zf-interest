package com.example.application.utils.regex;

import com.alibaba.druid.util.StringUtils;

/**
 * @ClassName CardsUtil
 * @Description 证件信息校验
 * @Author halvie
 * @Date 2021/6/22 1:13 下午
 */
public class CardsUtil {

    /**
     * 正则表达式：验证身份证
     */
    public static final String REGEX_ID_CARD = "(^[1-9]\\d{5}(18|19|20)\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|" +
            "(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}$)";

    /**
     * 正则表达式：验证护照 数字+字母，共9位
     */
    public static final String REGEX_PASSPORT_CARD = "^([a-zA-z]|[0-9]){9}$";

    /**
     * 正则表达式：验证军官证 汉字+8位数字
     */
    public static final String REGEX_OFFICER_CARD = "^[\\u4E00-\\u9FA5](字第)([0-9a-zA-Z]{4,8})(号?)$";

    /**
     * 正则表达式：验证港澳居民通行证 H/M + 10位或8位数字
     */
    public static final String REGEX_HK_CARD = "^[HMhm]{1}([0-9]{10}|[0-9]{8})$";

    /**
     * 正则表达式：验证台湾居民通行证 新版8位或18位数字,旧版10位数字 + 英文字母
     */
    public static final String REGEX_TW_CARD = "^\\d{8}|^[a-zA-Z0-9]{10}|^\\d{18}$";

    /**
     * 正则表达式：验证香港居民身份证 一个英文+6个数字+（一个校验码0~9或A）
     */
    public static final String REGEX_HK_ID_CARD = "^[A-Z]{1,2}[0-9]{6}\\(?[0-9A-Z]\\)?$";

    /**
     * 正则表达式：验证澳门居民身份证 第一位1、5、7，后面7个数字，最后带括号的一位校验码
     */
    private static final String REGEX_MC_ID_CARD = "^[1|5|7][0-9]{6}\\(?[0-9A-Z]\\)?$";

    /**
     * 正则表达式：验证台湾居民身份证 1个英文+9个数字
     */
    private static final String REGEX_TW_ID_CARD = "^[a-zA-Z][0-9]{9}$";

    /**
     * 正则表达式：验证统一社会信息代码:15位纯数字或 18位纯数字 或 18数字+英文字母组合
     */
    public static final String REGEX_ENTERPRISE_CODE = "^[0-9]{15}|^[a-zA-Z0-9]{18}|^\\d{18}$";

    /** 统一社会信息代码:15位纯数字或 18位纯数字 或 18数字+英文字母组合 */
    public static final String REGEX_CREDIT_CODE = "^[0-9]{15}|^[a-zA-Z0-9]{18}|^\\d{18}$";

    /**
     * 证件号最大长度
     */
    private static final Integer CERT_NO_MAX_LENGTH = 18;

    /**
     * 证件号最小长度
     */
    private static final Integer CERT_NO_MIN_LENGTH = 6;

    /**
     * 校验身份证
     *
     * @param idCardNo 身份证号
     * @return 校验通过返回true，否则返回false
     * @by https://blog.csdn.net/u011106915/article/details/76066985
     */
    public static boolean isIDCard(String idCardNo) {
        //校验非空
        if (StringUtils.isEmpty(idCardNo)) {
            return false;
        }
        //校验长度
        int idCardLength = idCardNo.length();
        if (idCardLength != 18 && idCardLength != 15) {
            return false;
        }
        // 定义判别用户身份证号的正则表达式（15位或者18位，最后一位可以为字母）
        //假设18位身份证号码:41000119910101123X  410001 19910101 123X
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //(18|19|20)                19（现阶段可能取值范围18xx-20xx年）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十七位奇数代表男，偶数代表女）
        //[0-9Xx] 0123456789Xx其中的一个 X（第十八位为校验值）
        //$结尾

        //假设15位身份证号码:410001910101123  410001 910101 123
        //^开头
        //[1-9] 第一位1-9中的一个      4
        //\\d{5} 五位数字           10001（前六位省市县地区）
        //\\d{2}                    91（年份）
        //((0[1-9])|(10|11|12))     01（月份）
        //(([0-2][1-9])|10|20|30|31)01（日期）
        //\\d{3} 三位数字            123（第十五位奇数代表男，偶数代表女），15位身份证不含X
        //$结尾

        boolean matches = idCardNo.matches(REGEX_ID_CARD);

        //判断第18位校验值
        if (matches) {

            //如是15位身份证，不做更多校验，直接返回合法
            if (idCardLength == 15) {
                return true;
            }

            if (idCardLength == 18) {
                try {
                    char[] charArray = idCardNo.toCharArray();
                    //前十七位加权因子
                    int[] idCardWi = {7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2};
                    //这是除以11后，可能产生的11位余数对应的验证码
                    String[] idCardY = {"1", "0", "X", "9", "8", "7", "6", "5", "4", "3", "2"};
                    int sum = 0;
                    for (int i = 0; i < idCardWi.length; i++) {
                        int current = Integer.parseInt(String.valueOf(charArray[i]));
                        int count = current * idCardWi[i];
                        sum += count;
                    }
                    char idCardLast = charArray[17];
                    int idCardMod = sum % 11;
                    return idCardY[idCardMod].toUpperCase().equals(String.valueOf(idCardLast).toUpperCase());

                } catch (Exception e) {
                    e.printStackTrace();
                    return false;
                }
            }

        }
        return matches;
    }

    /**
     * 校验护照
     *
     * @param passPortNo 护照号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isPassPortCard(String passPortNo) {
        //校验非空
        if (StringUtils.isEmpty(passPortNo)) {
            return false;
        }
        return passPortNo.matches(REGEX_PASSPORT_CARD);
    }

    /**
     * 校验军官证
     * 规则： 军/兵/士/文/职/广/（其他中文） + "字第" + 4到8位字母或数字 + "号"
     * 样本： 军字第2001988号, 士字第P011816X号
     *
     * @param officerNo 军官证号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isOfficerCard(String officerNo) {
        //校验非空
        if (StringUtils.isEmpty(officerNo)) {
            return false;
        }
        return officerNo.matches(REGEX_OFFICER_CARD);
    }

    /**
     * 校验港澳通行证
     *
     * @param HMNo 港澳通行证号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isHMCard(String HMNo) {
        //校验非空
        if (StringUtils.isEmpty(HMNo)) {
            return false;
        }
        return HMNo.matches(REGEX_HK_CARD);
    }

    /**
     * 校验台湾通行证
     * 规则 新版8位或18位数字,旧版10位数字 + 英文字母
     *
     * @param TWNo 台湾通行证号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isTWCard(String TWNo) {
        //校验非空
        if (StringUtils.isEmpty(TWNo)) {
            return false;
        }
        return TWNo.matches(REGEX_TW_CARD);
    }

    /**
     * 验证香港居民身份证 一个英文+6个数字+（一个校验码0~9或A）
     *
     * @param HKIdNo 香港身份证号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isHKIDCard(String HKIdNo) {
        //校验非空
        if (StringUtils.isEmpty(HKIdNo)) {
            return false;
        }
        return HKIdNo.matches(REGEX_HK_ID_CARD);
    }

    /**
     * 验证澳门居民身份证 第一位1、5、7，后面7个数字，最后带括号的一位校验码
     *
     * @param MCIdNo 澳门身份证号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isMCIDCard(String MCIdNo) {
        //校验非空
        if (StringUtils.isEmpty(MCIdNo)) {
            return false;
        }
        return MCIdNo.matches(REGEX_MC_ID_CARD);
    }

    /**
     * 验证台湾居民身份证 1个英文+9个数字
     *
     * @param TWIdNo 台湾身份证号
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isTWIDCard(String TWIdNo) {
        //校验非空
        if (StringUtils.isEmpty(TWIdNo)) {
            return false;
        }
        return TWIdNo.matches(REGEX_TW_ID_CARD);
    }

    /**
     * 验证企业统一社会信用代码，15位纯数字或18位数字+字母或纯数字组合
     *
     * @param enterpriseCode 企业统一社会信用代码
     * @return 校验通过返回true，否则返回false
     */
    public static boolean isEnterPriseCode(String enterpriseCode) {
        //校验非空
        if (StringUtils.isEmpty(enterpriseCode)) {
            return false;
        }
        return enterpriseCode.matches(REGEX_ENTERPRISE_CODE);
    }

    /**
     * 验证统一社会信息代码
     *
     * @return
     */
    public static boolean isCreditCode(String creditCode){
        if(org.apache.commons.lang3.StringUtils.isBlank(creditCode)){
            return false;
        }
        return creditCode.matches(REGEX_CREDIT_CODE);
    }

    /**
     * 证件号码通用验证，只验证证件号码的长度符不符合规则
     *
     * @return boolean
     */
    public static boolean commonCheck(String certNo) {
        if (org.apache.commons.lang3.StringUtils.isBlank(certNo) || certNo.length() > CERT_NO_MAX_LENGTH
                || certNo.length() < CERT_NO_MIN_LENGTH) {
            return false;
        } else {
            return true;
        }
    }
}
