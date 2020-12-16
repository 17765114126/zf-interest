package dorago.yiqiancms.biz.exception;

import com.alibaba.fastjson.support.spring.FastJsonJsonView;
import dorago.yiqiancms.biz.common.Result;
import dorago.yiqiancms.biz.common.enums.ResultCodeEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MyExceptionHandler implements HandlerExceptionResolver {

    @Override
    public ModelAndView resolveException(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception ex) {

        ModelAndView mv = new ModelAndView();
        FastJsonJsonView view = new FastJsonJsonView();
        Result<Void> result;
        if (ex instanceof UnauthenticatedException) {
            log.error("", ex);
            result = Result.with(ResultCodeEnum.NOT_LOGIN);
        } else if (ex instanceof UnauthorizedException) {
            log.error("", ex);
            result = Result.with(ResultCodeEnum.NOT_AUTH);
        } else {
            log.error("", ex);
            result = Result.with(ResultCodeEnum.INNER_ERROR);
        }
        Map<String, Object> map = new HashMap<>(16);
        map.put("code", result.getCode());
        map.put("msg", result.getMsg());
        view.setAttributesMap(map);
        mv.setView(view);
        return mv;
    }
}
