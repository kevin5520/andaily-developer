package com.andaily.web.tag;

import org.apache.commons.lang.StringEscapeUtils;
import org.springframework.util.StringUtils;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Date: 13-6-23 下午11:46
 *
 * @author Shengzhao Li
 */
public class SubstringTag extends TagSupport {

    private String value;
    private int length = 10;
    private String suffix = "...";
    private String style;
    private boolean showTitle = true;

    private boolean escapeHtml = true;

    private int maxLineBreaks = 0;

    private int maxLineLength;


    @Override
    public int doStartTag() throws JspException {
        String subString = generateSubString();
        try {
            writeMessage(subString);
        } catch (IOException e) {
            throw new JspException(e);
        }
        return EVAL_BODY_INCLUDE;

    }

    protected String generateSubString() {
        boolean needSuffixAsBoolean = needSuffix();
        String title = escapeHtml(value);
        String tempSuffix = needSuffixAsBoolean ? this.suffix : "";
        String tempValue = needSuffixAsBoolean ? this.value.substring(0, length) : this.value;
        if (escapeHtml) {
            tempValue = escapeHtml(tempValue);
        }
        tempValue = limitLines(tempValue);
        return generateSimpleTag(title, tempValue, style, tempSuffix);
    }

    private boolean needSuffix() {
        return value.length() > length;
    }

    private String generateSimpleTag(String title, String value, String style, String suffix) {
        if (!StringUtils.hasText(value)) {
            return "";
        }
        String label = value + suffix;
        String styleAsString = StringUtils.hasText(style) ? "style=\"" + style + "\"" : "";
        if (showTitle) {
            return "<span  title=\"" + title + "\" " + styleAsString + ">" + label + "</span>";
        } else {
            return "<span style=\"\" " + styleAsString + ">" + label + "</span>";
        }
    }

    protected String limitLines(String value) {
        String lineSeparator = System.getProperty("line.separator");
        value = StringUtils.replace(value, lineSeparator + lineSeparator, lineSeparator + " " + lineSeparator);
        String[] tokens = StringUtils.tokenizeToStringArray(value, lineSeparator, false, false);
        List<String> allowedLines = new ArrayList<String>();
        for (String token : tokens) {
            if (maxLineLength > 0) {
                splitTokenIfNecessary(token, allowedLines);
            } else {
                allowedLines.add(token);
            }
        }

        String limitedValue = "";
        int lineCount = 0;
        for (String allowedLine : allowedLines) {
            if (lineCount <= maxLineBreaks) {
                limitedValue += allowedLine;
                if (lineCount < maxLineBreaks) {
                    limitedValue += "<br/>";
                }

                lineCount++;
            }
        }
        return limitedValue;
    }

    protected String escapeHtml(String value) {
        return StringEscapeUtils.escapeHtml(value);
    }

    private void splitTokenIfNecessary(String token, List<String> allowedLines) {
        if (token.length() > maxLineLength) {
            String maxedToken = token.substring(0, maxLineLength);
            String remainingToken = token.substring(maxLineLength);
            allowedLines.add(maxedToken);
            splitTokenIfNecessary(remainingToken, allowedLines);
        } else {
            allowedLines.add(token);
        }
    }

    protected void writeMessage(String urlInfo) throws IOException {
        pageContext.getOut().write(urlInfo);
    }

    public void setLength(int length) {
        this.length = length;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public void setShowTitle(Boolean showTitle) {
        this.showTitle = showTitle;
    }

    public void setMaxLineBreaks(int maxLineBreaks) {
        this.maxLineBreaks = maxLineBreaks;
    }

    public void setMaxLineLength(int maxLineLength) {
        this.maxLineLength = maxLineLength;
    }

    public boolean isEscapeHtml() {
        return escapeHtml;
    }

    public void setEscapeHtml(boolean escapeHtml) {
        this.escapeHtml = escapeHtml;
    }
}
