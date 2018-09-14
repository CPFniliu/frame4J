package com.cpf.frame4j.controller;

import com.alibaba.fastjson.JSON;
import com.cpf.frame4j.util.common.ReflectionUtil;
import com.cpf.frame4j.util.config.ERequestType;
import com.cpf.frame4j.util.config.FConfigHelper;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.util.Objects;

@WebServlet(urlPatterns = {"/"}, loadOnStartup = 1)
public class DispatherServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        // 初始化相关类
        FrameIniter.init();
        // 获取 ServletContext 对象（用于注册Servlet）
        ServletContext servletContext = config.getServletContext();
        // TODO 注册处理 JSP 的 Servlet
        ServletRegistration jspServlet = servletContext.getServletRegistration("jsp");
        jspServlet.addMapping(FConfigHelper.getAppBaseJspPath());
        // 注册处理静态资源的默认 Servlet
        ServletRegistration defaultServlet = servletContext.getServletRegistration("default");
        defaultServlet.addMapping();
    }

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String servletPath = req.getServletPath();
        String params = req.getParameterMap().keySet().toString();
        System.out.println(params);
        if (StringUtils.isBlank(servletPath)) {
            System.out.println("error!!!!--------------------------------");
            return;
        }
        if (servletPath.endsWith(".do")){
            // 获取请求方法和请求路径
            String requestMethod = req.getMethod().toUpperCase();
            ERequestType requestType = ERequestType.valueOf(requestMethod);
            Objects.requireNonNull(requestType);
            // 获取 Action 处理器
            Handler handler = ControllerHolder.getHandler(requestType, servletPath);
            if (handler != null) {
                // 获取 Controller 类及其 Bean 实例
                Class<?> controllerClass = handler.getControllerClass();
                Object controllerBean = BeanHolder.getBean(controllerClass);
                // 调用 Action 方法
                Method actionMethod = handler.getActionMethod();
                // 创建请求对象
                Param param = new Param(req);
                Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
                // 处理 Action 方法返回值
                if (result instanceof Data) {
                    // 返回 json 数据
                    Data data = (Data) result;
                    Object model = data.getModel();
                    if (model != null) {
                        resp.setContentType("application/json");
                        resp.setCharacterEncoding("UTF-8");
                        PrintWriter writer = resp.getWriter();
                        String json = JSON.toJSONString(model);
                        writer.write(json);
                        writer.flush();
                        writer.close();
                    }
                }
            }
        } else {
            if (servletPath.startsWith("/jsp/")){
                servletPath += ".jsp";
                req.getRequestDispatcher(servletPath).forward(req, resp);
            } else if (servletPath.startsWith("/html/")) {
                servletPath += ".html";
                req.getRequestDispatcher(servletPath).forward(req, resp);
            }
        }

    }


}
