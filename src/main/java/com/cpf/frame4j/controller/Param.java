package com.cpf.frame4j.controller;

import com.cpf.frame4j.util.cast.CastUtil;
import com.cpf.frame4j.util.common.CodecUtil;
import com.cpf.frame4j.util.io.StreamUtil;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class Param {

    private Map<String, Object> paramMap;

    private HttpServletRequest request;

    public Param(HttpServletRequest req) throws IOException {
        this.request = req;
        initParam();

        System.out.println(123254365);
        System.out.println(request.getPathInfo());
        System.out.println(request.getContextPath());
        System.out.println(request.getRequestURL());
        System.out.println(request.getPathTranslated());
    }

    public void initParam() throws IOException {// 创建请求对象
        paramMap = new HashMap<>();
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String parameterName = parameterNames.nextElement();
            String parameterVal = request.getParameter(parameterName);
            paramMap.put(parameterName, parameterVal);
        }
        String body = CodecUtil.decodeUrlOnUtf8(StreamUtil.getString(request.getInputStream()));
        if (StringUtils.isNotEmpty(body)) {
            String[] params = StringUtils.split(body, "&");
            if (ArrayUtils.isNotEmpty(params)) {
                for (String param : params) {
                    String[] array = StringUtils.split(param, ":");
                    if (ArrayUtils.isNotEmpty(array) && array.length == 2) {
                        paramMap.put(array[0], array[1]);
                    }
                }
            }
        }
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public long getLong(String name) {
        return CastUtil.parseLong(paramMap.get(name));
    }
}
