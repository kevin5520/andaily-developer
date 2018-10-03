package com.andaily.web.filter;

import com.andaily.infrastructure.support.LogHelper;
import com.andaily.web.util.CookieUserAssistant;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.ServletRequestUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.springframework.security.web.authentication.rememberme.AbstractRememberMeServices.DEFAULT_PARAMETER;

/**
 * @author Shengzhao Li
 */
public class AndailyRememberMeFilter extends UsernamePasswordAuthenticationFilter {

    private static LogHelper log = LogHelper.create(AndailyRememberMeFilter.class);

    public AndailyRememberMeFilter() {
        //The uri value from security.xml  #form-login > login-processing-url
        setFilterProcessesUrl("/signin");
        log.info("Initial AndailyRememberMeFilter success.");
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;

        if (requiresAuthentication(request, response)) {
            String username = obtainUsername(request);
            String password = obtainPassword(request);

            if (StringUtils.hasText(username) && StringUtils.hasText(password)) {
                log.debug("Save username [" + username + "] and password to cookie");
                CookieUserAssistant cookieUserAssistant = new CookieUserAssistant(request);
                cookieUserAssistant.save(response, username, password);
            }

        }
        chain.doFilter(req, res);
    }

    @Override
    protected boolean requiresAuthentication(HttpServletRequest request, HttpServletResponse response) {
        boolean result = super.requiresAuthentication(request, response);
        if (result) {
            // check remember-me parameter
            return ServletRequestUtils.getBooleanParameter(request, DEFAULT_PARAMETER, false);
        }
        return result;
    }

    @Override
    public void afterPropertiesSet() {
        //Ignore
    }
}
