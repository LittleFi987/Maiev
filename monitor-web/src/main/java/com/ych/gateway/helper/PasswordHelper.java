package com.ych.gateway.helper;

import com.ych.core.dto.UserDto;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.stereotype.Component;

/**
 * Created by chenhaoye on 2017/9/17.
 */
@Component
public class PasswordHelper {
    private RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
    private String algorithmName = "md5";
    private final int hashIterations = 1;

    public void encryptPassword(UserDto userDTO) {
        // User对象包含最基本的字段Username和Password
        userDTO.setToken(randomNumberGenerator.nextBytes().toHex());
        // 将用户的注册密码经过散列算法替换成一个不可逆的新密码保存进数据，散列过程使用了盐
        String newPassword = new SimpleHash(algorithmName, userDTO.getPwd(),
                ByteSource.Util.bytes(userDTO.getCredentialsSalt()), hashIterations).toHex();
        userDTO.setPwd(newPassword);
    }
}
