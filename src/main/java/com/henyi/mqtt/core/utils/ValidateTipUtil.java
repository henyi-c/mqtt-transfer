package com.henyi.mqtt.core.utils;

/**
 * Validate常见提示
 *
 * @author henyi
 */
public class ValidateTipUtil {

    /**
     * 校验自定义统一处理
     *
     * @param typeName 类型
     * @param message  定义的字段名称
     * @param params   参数
     * @return 自定义消息
     */
    public static String validate(String typeName, String message, Object[] params) {
        String res = null;
        switch (typeName) {
            case "Size":
                params[0] = message;
                res = size(params);
                break;
            case "NotNull":
                res = notNull(message);
                break;
            case "NotBlank":
                res = notBlank(message);
                break;
            default:
        }
        return res;
    }


    private static String notBlank(String message) {
        return message + "不能为空，且长度必须大于0!";
    }

    private static String notNull(String message) {
        return message + "不能为空!";
    }

    private static String size(Object[] params) {
        long max = 200000L;
        int index0 = 0;
        int index1 = 1;
        int index2 = 2;
        if (Long.parseLong(params[index1].toString()) > max) {
            return params[index0] + "最小长度为" + params[index2] + "个字符!";
        }
        if (params[index2].equals(0)) {
            return params[index0] + "最大长度为" + params[index1] + "个字符!";
        }
        if (params[index1].equals(params[index2])) {
            return params[index0] + "长度为" + params[index1] + "个字符!";
        }
        return params[index0] + "最小长度为" + params[index2] + ",最大长度为" + params[index1] + "个字符!";
    }
}
