package com.andaily.service.impl;

import com.andaily.domain.user.User;
import com.andaily.infrastructure.mail.AndailyMailSender;
import com.andaily.infrastructure.support.LogHelper;
import com.andaily.infrastructure.support.st.STRender;
import com.andaily.service.MailService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class MailServiceImpl implements MailService {

    private static LogHelper log = LogHelper.create(MailServiceImpl.class);

    private AndailyMailSender mailSender;


    @Override
    public void sendRegisterUserMail(User registerUser) {
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public void sendForgotPasswordMail(User user, String newPass) {
        String email = user.email();
        String subject = "忘记密码 --Andaily";

        Map<String, Object> model = new HashMap<String, Object>();
        model.put("title", subject);
        model.put("username", email);
        model.put("newPassword", newPass);
        STRender stRender = new STRender("template/forgot_password.html", model);
        String content = stRender.render();

        mailSender.sendSimpleMail(subject, content, email);
    }

    @Override
    public boolean sendSimpleMail(String subject, String to, String content) {
        return mailSender.sendSimpleMail(subject, content, to);
    }

    public void setMailSender(AndailyMailSender mailSender) {
        this.mailSender = mailSender;
    }
}
