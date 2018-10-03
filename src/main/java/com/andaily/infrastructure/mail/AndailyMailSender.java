package com.andaily.infrastructure.mail;

/**
 * @author Shengzhao Li
 */
public interface AndailyMailSender {

    //Logo inline resource name
    //demo:  <img src="cid:andaily_logo" alt='Andaily'/>
    public static final String LOGO_INLINE_RESOURCE_NAME = "andaily_logo";

    boolean sendSimpleMail(String subject, String content, String... to);
}
