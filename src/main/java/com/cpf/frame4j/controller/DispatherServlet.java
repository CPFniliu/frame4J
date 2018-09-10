package com.cpf.frame4j.controller;

import com.alibaba.fastjson.JSON;
import com.cpf.frame4j.util.common.ReflectionUtil;
import com.cpf.frame4j.util.config.ERequestType;
import com.cpf.frame4j.util.config.FConfigHelper;

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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebServlet(urlPatterns = "/*", loadOnStartup = 0)
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
        // 获取请求方法和请求路径
        String requestMethod = req.getMethod().toLowerCase();
        ERequestType requestType = ERequestType.valueOf(requestMethod);
        String requestPath = req.getPathInfo();
        // 获取 Action 处理器
        Handler handler = ControllerHolder.getHandler(requestType, requestPath);
        if (handler != null) {
            // 获取 Controller 类及其 Bean 实例
            Class<?> controllerClass = handler.getControllerClass();
            Object controllerBean = BeanHolder.getBean(controllerClass);
            // 创建请求对象
            Param param = new Param(req);
            // 调用 Action 方法
            Method actionMethod = handler.getActionMethod();
            Object result = ReflectionUtil.invokeMethod(controllerBean, actionMethod, param);
            // 处理 Action 方法返回值
            if (result instanceof View) {
                // 返回 JSP 页面
                View view = (View) result;
                String path = view.getPath();

                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String dateStr = dateFormat.format(new Date());
                req.setAttribute("currectTime", dateStr);
                req.getRequestDispatcher("/jsp/hello.jsp").forward(req, resp);
//                if (StringUtils.isNotEmpty(path)) {
//                    if (path.startsWith("/")) {
//                        resp.sendRedirect(req.getContextPath() + path);
//                    } else {
//                        Map<String, Object> model = view.getModel();
//                        for (Map.Entry<String, Object> entry : model.entrySet()) {
//                            req.setAttribute(entry.getKey(), entry.getValue());
//                            req.getRequestDispatcher(FConfigHelper.getAppBaseJspPath() + path).forward(req, resp);
//                        }
//                    }
//                }
            } else if (result instanceof Data) {
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
    }
}
