package com.andaily.web.context;

import com.andaily.domain.shared.Application;
import com.andaily.infrastructure.support.LogHelper;
import org.springframework.web.context.ContextLoaderListener;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

/**
 * @author Shengzhao Li
 */
public class AndailyContextLoaderListener extends ContextLoaderListener {

    private static LogHelper log = LogHelper.create(AndailyContextLoaderListener.class);

    @Override
    public void contextInitialized(ServletContextEvent event) {
        super.contextInitialized(event);
        final ServletContext servletContext = event.getServletContext();
        WebApplicationContext applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContext);
        BeanProvider.initialize(applicationContext);

        log.info("Initial  Andaily-Developer successful, Version: " + Application.VERSION);
        servletContext.setAttribute("adVersion", Application.VERSION);
    }
}