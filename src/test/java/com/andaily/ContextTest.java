package com.andaily;

import com.andaily.domain.dto.user.AndailyUserDetails;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.web.context.BeanProvider;
import com.andaily.web.context.SpringSecurityHolder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTransactionalTestNGSpringContextTests;
import org.springframework.test.context.transaction.BeforeTransaction;

/**
 * Init test context
 *
 * @author Shengzhao Li
 */
@ContextConfiguration(locations = {"classpath:testApplicationContext.xml"})
public abstract class ContextTest extends AbstractTransactionalTestNGSpringContextTests {

    @BeforeTransaction
    public void beforeTest() {
        BeanProvider.initialize(applicationContext);
        SecurityUtils securityUtils = new SecurityUtils();
        securityUtils.setSecurityHolder(new SpringSecurityHolder() {
            @Override
            public AndailyUserDetails currentUserDetails() {
                return null;
            }
        });
    }
}