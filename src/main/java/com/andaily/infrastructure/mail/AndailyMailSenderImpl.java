package com.andaily.infrastructure.mail;

import com.andaily.infrastructure.support.LogHelper;
import org.springframework.core.io.Resource;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public class AndailyMailSenderImpl implements AndailyMailSender {

    /**
     * Is or not use new thread send mail.
     */
    public static final boolean SEND_MAIL_USE_THREAD = true;

    private static LogHelper log = LogHelper.create(AndailyMailSenderImpl.class);

    private boolean sendMailUseThread = SEND_MAIL_USE_THREAD;
    private Resource logoInlineResource;

    public AndailyMailSenderImpl() {
        log.info("Initialize AndailyMailSender ");
    }

    //We always add default logo inline resource if you call the method
    @Override
    public boolean sendSimpleMail(String subject, String content, String... to) {
        log.debug("Call sendSimpleMail [subject= " + subject + ", content= " + content + ", to= " + Arrays.toString(to) + "]");
        Map<String, Resource> inlineResources = defaultInlineResources();
        SendMailHandler sendMailHandler = new SendMailHandler(subject, content, to).addInlineResources(inlineResources);
        sendMailHandler.handle(this.sendMailUseThread);
        return true;
    }

    //default inline resources: logo image
    private Map<String, Resource> defaultInlineResources() {
        Map<String, Resource> resourceMap = new HashMap<String, Resource>();
        resourceMap.put(LOGO_INLINE_RESOURCE_NAME, this.logoInlineResource);
        return resourceMap;
    }


    public void setSendMailUseThread(boolean sendMailUseThread) {
        this.sendMailUseThread = sendMailUseThread;
    }

    public void setLogoInlineResource(Resource logoInlineResource) {
        this.logoInlineResource = logoInlineResource;
    }
}
