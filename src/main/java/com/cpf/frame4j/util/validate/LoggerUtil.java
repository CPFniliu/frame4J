package com.cpf.frame4j.util.validate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggerUtil {

    public static final Logger LOGGER = LoggerFactory.getLogger(LoggerUtil.class);


    /**
     * 打印错误日志，并抛出运行时异常RuntimeException，一般用于 catch 中
     *
     * @param errMsg 错误信息
     */
    public static void loggerAndThrow(String errMsg){
        LOGGER.error(errMsg);
        throw new RuntimeException(errMsg);
    }

    /**
     * 打印错误日志，并抛出运行时异常RuntimeException，一般用于 catch 中
     *
     * @param errMsg 错误信息
     * @param e
     */
    public static void loggerAndThrow(String errMsg, Throwable e){
        loggerAndThrow(LOGGER, errMsg, e);
    }


    /**
     * 打印错误日志，并抛出运行时异常RuntimeException，一般用于 catch 中
     *
     * @param errMsg 错误信息
     * @param e
     */
    public static void loggerAndThrow(final Logger logger, String errMsg, Throwable e){
        logger.error(errMsg, e);
        throw new RuntimeException(e);
    }


}
