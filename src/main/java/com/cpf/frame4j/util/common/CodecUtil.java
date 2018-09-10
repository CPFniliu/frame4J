package com.cpf.frame4j.util.common;

import com.cpf.frame4j.util.validate.LoggerUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public final class CodecUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(CodecUtil.class);

    /**
     * 将 URL 以 UTF-8 的形式编码
     * URLEncoder.encode(source, "UTF-8"); 的形式处理
     *
     * @param source
     * @return
     */
    public static String encodeUrlOnUtf8(String source){
        String target = null;
        try {
            target = URLEncoder.encode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LoggerUtil.loggerAndThrow(LOGGER, "encode url failure on utf-8", e);
        }
        return target;
    }


    /**
     * 将 URL 以 UTF-8 的形式解码
     * URLDecoder.decode(source, "UTF-8"); 的形式处理
     *
     * @param source
     * @return
     */
    public static String decodeUrlOnUtf8(String source){
        String target = null;
        try {
            target = URLDecoder.decode(source, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            LoggerUtil.loggerAndThrow(LOGGER, "decode url failure on utf-8", e);
        }
        return target;
    }


}
