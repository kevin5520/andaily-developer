package com.andaily.service;

import com.andaily.domain.user.User;

/**
 * @author Shengzhao Li
 */
public interface MailService {

    void sendRegisterUserMail(User registerUser);

    void sendForgotPasswordMail(User user, String newPass);

    boolean sendSimpleMail(String subject, String to, String content);
}
