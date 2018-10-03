package com.andaily.web.resolver;

import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.User;
import org.springframework.web.servlet.i18n.AcceptHeaderLocaleResolver;

import javax.servlet.http.HttpServletRequest;
import java.util.Locale;

/**
 * @author Shengzhao Li
 */
public class AndailyDeveloperLocaleResolver extends AcceptHeaderLocaleResolver {


    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        final User user = SecurityUtils.currUser();
        if (user != null && user instanceof Developer) {
            return ((Developer) user).language().resolveLocale();
        }
        return super.resolveLocale(request);
    }
}