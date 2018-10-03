package com.andaily.infrastructure.mail;

import com.andaily.infrastructure.support.LogHelper;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.util.StringUtils;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Arrays;
import java.util.Date;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class SendMailHandler extends Thread {

    private static LogHelper log = LogHelper.create(SendMailHandler.class);

    private static JavaMailSender mailSender;

    private static String defaultAddress;
    private static boolean developEnvironment;
    private static String developEmailAddress;

    //mail fields
    private String from;
    private String to[];
    private String subject;
    private String content;
    private Date sendDate;
    private Map<String, Resource> attachments;
    private Map<String, Resource> inlineResources;

    public SendMailHandler() {
    }

    public SendMailHandler(String subject, String content, String... to) {
        this.to = to;
        this.subject = subject;
        this.content = content;
        //default value
        this.from = defaultAddress;
        this.sendDate = new Date();
    }

    public SendMailHandler addFrom(String from) {
        this.from = from;
        return this;
    }

    public SendMailHandler addSendDate(Date sendDate) {
        this.sendDate = sendDate;
        return this;
    }

    public SendMailHandler addAttachments(Map<String, Resource> attachments) {
        this.attachments = attachments;
        return this;
    }

    public SendMailHandler addInlineResources(Map<String, Resource> inlineResources) {
        this.inlineResources = inlineResources;
        return this;
    }


    public void handle(boolean sendMailUseThread) {
        if (sendMailUseThread) {
            this.start();
        } else {
            this.run();
        }
    }

    public void run() {
        try {
            String toAsText = Arrays.toString(to);
            log.debug("Start to send email to [" + toAsText + "]");
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            if (!StringUtils.hasText(from)) {
                from = defaultAddress;
            }
            helper.setFrom(from);
            //Check develop environment or not.
            if (developEnvironment) {
                log.warn("NOTE: Current environment is Test, The email will be send to developer [" + developEmailAddress + "]");
                InternetAddress[] addresses = InternetAddress.parse(developEmailAddress);
                helper.setTo(addresses);
            } else {
                helper.setTo(to);
            }
            helper.setSentDate(sendDate);
            helper.setSubject(subject);
            helper.setText(content, true);
            //attachments
            addAttachments(helper);
            //inline resource.
            addInlineResources(helper);
            mailSender.send(message);
            log.debug(">>>>> Send email finish.");
        } catch (Exception e) {
            log.error("Send Email error", e);
        }
    }

    private void addAttachments(MimeMessageHelper helper) throws MessagingException {
        if (attachments != null && attachments.size() > 0) {
            for (String name : attachments.keySet()) {
                helper.addAttachment(name, attachments.get(name));
            }
        }
    }

    private void addInlineResources(MimeMessageHelper helper) throws MessagingException {
        if (inlineResources != null && inlineResources.size() > 0) {
            for (String name : inlineResources.keySet()) {
                helper.addInline(name, inlineResources.get(name));
            }
        }
    }


    public void setDefaultAddress(String defaultAddress) {
        SendMailHandler.defaultAddress = defaultAddress;
    }

    public void setMailSender(JavaMailSender mailSender) {
        SendMailHandler.mailSender = mailSender;
    }

    public void setDevelopEnvironment(boolean developEnvironment) {
        SendMailHandler.developEnvironment = developEnvironment;
    }

    public void setDevelopEmailAddress(String developEmailAddress) {
        SendMailHandler.developEmailAddress = developEmailAddress;
    }

}
