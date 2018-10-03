package com.andaily.infrastructure.mail;

import com.andaily.ContextTest;
import com.andaily.domain.shared.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

/**
 * @author Shengzhao Li
 */
public class AndailyMailSenderImplTest extends ContextTest {

    @Autowired
    private AndailyMailSender mailSender;

    @Test
    public void sendSimpleMail() throws Exception {
        String to = "baby@andaily.com";
        String subject = "I'm andaily-developer";
        String content = "<p><img src=\"cid:andaily_logo\" alt='Andaily'/></p><p>Now is <strong>" + DateUtils.now() + "</strong>.</p>";

        boolean result = mailSender.sendSimpleMail(subject, content, to);
        assertTrue(result);
    }
}
