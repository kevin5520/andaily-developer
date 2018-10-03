package com.andaily.domain.shared;

import com.andaily.domain.shared.system.SystemConfiguration;
import com.andaily.domain.shared.system.SystemRepository;
import com.andaily.web.context.BeanProvider;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.util.Assert;

/**
 * @author Shengzhao Li
 */
public class Application implements InitializingBean {

    //Default encoding
    public static final String ENCODING = "UTF-8";

    /**
     * 当前的版本号
     */
    public static final String VERSION = "1.0";


    //Application host
    private static String host;

    private static SystemConfiguration systemConfiguration;

    //default constructor
    public Application() {
    }


    public static String host() {
        return host;
    }

    public void setHost(String host) {
        Application.host = host;
    }


    public static int perPageSize() {
        return systemConfiguration().perPageSize().getSize();
    }


    public static SystemConfiguration systemConfiguration() {
        checkingSystemConfiguration();
        return systemConfiguration;
    }


    private static void checkingSystemConfiguration() {
        if (systemConfiguration == null) {
            final SystemRepository systemRepository = BeanProvider.getBean(SystemRepository.class);
            Application.systemConfiguration = systemRepository.findSystemConfiguration();
            Assert.notNull(systemConfiguration, "systemConfiguration is null");
        }
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(host, "host is null");
    }
}
