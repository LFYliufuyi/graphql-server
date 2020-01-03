package net.likeyun.user.util;

import net.likeyun.base.error_result.GraphQLErrorResult;
import net.likeyun.base.exception.GraphQLCustomException;

import java.security.MessageDigest;

/**
 * @Description: 密码加密工具类
 * @Author: lfy
 * @Date: 2019/12/6 16:18
 */
public final class EncryptUtil {
    private EncryptUtil() {
    }


    public static String encryptPassword(String username, String password, Integer roleId) {
        byte[] salt = (roleId.toString() + username).getBytes();
        byte[] passwords = password.getBytes();
        String generatedPassword;
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(salt);
            byte[] bytes = md.digest(passwords);
            StringBuilder sb = new StringBuilder();
            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }

            generatedPassword = sb.toString();
        } catch (Exception e) {
            throw new GraphQLCustomException(GraphQLErrorResult.SYSTEM_INNER_ERROR);
        }
        return generatedPassword;
    }
}
