package com.andaily.web.resolver;

import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.shared.security.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.DefaultHandlerExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Date: 13-7-3 下午10:13
 * <p/>
 * Record all throw exception to log files
 *
 * @author Shengzhao Li
 */
public class AndailyHandlerExceptionResolver extends DefaultHandlerExceptionResolver {

    private static Logger logger = LoggerFactory.getLogger(AndailyHandlerExceptionResolver.class);

    public AndailyHandlerExceptionResolver() {
    }

    @Override
    protected ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        recordLog(request, handler, ex);
        return super.doResolveException(request, response, handler, ex);
    }

    private void recordLog(HttpServletRequest request, Object handler, Exception ex) {
        StringBuilder sb = new StringBuilder();
        sb.append("requestUri = ").append(request.getRequestURI()).append("|Current user guid = ");
        sb.append(SecurityUtils.currentUserGuid()).append("|handler = ");
        sb.append(handler).append("|time = ").append(DateUtils.toDateTime(DateUtils.now()));

        if (logger.isDebugEnabled()) {
            logger.debug(sb.toString(), ex);
        }
    }
}
