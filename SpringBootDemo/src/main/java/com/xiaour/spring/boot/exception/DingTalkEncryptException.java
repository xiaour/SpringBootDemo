package com.xiaour.spring.boot.exception;

import java.util.HashMap;
import java.util.Map;

/**
 * 钉钉开放平台加解密异常类
 */
public class DingTalkEncryptException extends Exception {
    /**成功**/
    public static final int SUCCESS = 0;
    /**加密明文文本非法**/
    public final static int  ENCRYPTION_PLAINTEXT_ILLEGAL = 900001;
    /**加密时间戳参数非法**/
    public final static int  ENCRYPTION_TIMESTAMP_ILLEGAL = 900002;
    /**加密随机字符串参数非法**/
    public final static int  ENCRYPTION_NONCE_ILLEGAL = 900003;
    /**不合法的aeskey**/
    public final static int AES_KEY_ILLEGAL = 900004;
    /**签名不匹配**/
    public final static int SIGNATURE_NOT_MATCH = 900005;
    /**计算签名错误**/
    public final static int COMPUTE_SIGNATURE_ERROR = 900006;
    /**计算加密文字错误**/
    public final static int COMPUTE_ENCRYPT_TEXT_ERROR  = 900007;
    /**计算解密文字错误**/
    public final static int COMPUTE_DECRYPT_TEXT_ERROR  = 900008;
    /**计算解密文字长度不匹配**/
    public final static int COMPUTE_DECRYPT_TEXT_LENGTH_ERROR  = 900009;
    /**计算解密文字corpid不匹配**/
    public final static int COMPUTE_DECRYPT_TEXT_CORPID_ERROR  = 900010;

    private static Map<Integer,String> msgMap = new HashMap<Integer,String>();
    static{
        msgMap.put(SUCCESS,"成功");
        msgMap.put(ENCRYPTION_PLAINTEXT_ILLEGAL,"加密明文文本非法");
        msgMap.put(ENCRYPTION_TIMESTAMP_ILLEGAL,"加密时间戳参数非法");
        msgMap.put(ENCRYPTION_NONCE_ILLEGAL,"加密随机字符串参数非法");
        msgMap.put(SIGNATURE_NOT_MATCH,"签名不匹配");
        msgMap.put(COMPUTE_SIGNATURE_ERROR,"签名计算失败");
        msgMap.put(AES_KEY_ILLEGAL,"不合法的aes key");
        msgMap.put(COMPUTE_ENCRYPT_TEXT_ERROR,"计算加密文字错误");
        msgMap.put(COMPUTE_DECRYPT_TEXT_ERROR,"计算解密文字错误");
        msgMap.put(COMPUTE_DECRYPT_TEXT_LENGTH_ERROR,"计算解密文字长度不匹配");
        msgMap.put(COMPUTE_DECRYPT_TEXT_CORPID_ERROR,"计算解密文字corpid或者suiteKey不匹配");
    }

    public Integer  code;
    public DingTalkEncryptException(Integer exceptionCode){
        super(msgMap.get(exceptionCode));
        this.code = exceptionCode;
    }
}
