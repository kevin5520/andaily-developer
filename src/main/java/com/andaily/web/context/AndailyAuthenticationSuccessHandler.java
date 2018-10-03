package com.andaily.web.context;

import com.andaily.domain.dto.user.AndailyUserDetails;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.domain.user.User;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.util.WebUtils;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author Shengzhao Li
 */
public class AndailyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private static LogHelper log = LogHelper.create(AndailyAuthenticationSuccessHandler.class);

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        recordLog(request, authentication);

        final AndailyUserDetails principal = (AndailyUserDetails) authentication.getPrincipal();
        final User user = principal.getUser();
        if (user.isDeveloper()) {
            Developer developer = (Developer) user;
            //Super man redirect to user overview after sign in successful
            if (ScrumTerm.SUPER_MAN.equals(developer.scrumTerm())) {
                response.sendRedirect("developer/user_/overview");
                return;
            }
        }
        super.onAuthenticationSuccess(request, response, authentication);
    }

    private void recordLog(HttpServletRequest request, Authentication authentication) {
        String ip = WebUtils.clientIp(request);
        String userAgent = request.getHeader("User-Agent");
        log.debug(authentication.getPrincipal() + LogHelper.LOG_DELIMITER
                + DateUtils.toDateTime(DateUtils.now()) + LogHelper.LOG_DELIMITER
                + ip + LogHelper.LOG_DELIMITER
                + userAgent);
    }


}
