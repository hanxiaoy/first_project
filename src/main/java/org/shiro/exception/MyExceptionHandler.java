package org.shiro.exception;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hanxy on 2016/7/26.
 * projectName : first_project
 * description :
 */
public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {

        System.out.println("================================= resolveException() ==============================================");
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("ex", ex);
        ex.printStackTrace();
        return  null;

    }


}