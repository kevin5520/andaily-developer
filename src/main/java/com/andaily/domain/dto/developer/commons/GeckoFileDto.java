package com.andaily.domain.dto.developer.commons;

import com.andaily.domain.developer.commons.GeckoFile;
import com.andaily.domain.dto.AbstractDTO;
import org.apache.commons.io.FilenameUtils;
import org.springframework.util.StringUtils;

import java.io.UnsupportedEncodingException;

/**
 * @author Shengzhao Li
 */
public class GeckoFileDto extends AbstractDTO {

    protected String name;
    protected byte[] data;
    protected long size;

    public GeckoFileDto() {
    }

    public GeckoFileDto(GeckoFile file) {
        this(file, false);
    }

    public GeckoFileDto(GeckoFile file, boolean includeData) {
        super(file.guid());
        this.name = file.name();
        this.size = file.size();

        if (includeData) {
            this.data = file.data();
        }
    }

    public String downloadFileName() throws UnsupportedEncodingException {
        String fileName = StringUtils.trimAllWhitespace(this.name);
        return new String(fileName.getBytes(), "ISO8859-1");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte[] getData() {
        return data;
    }

    public void setData(byte[] data) {
        this.data = data;
    }

    public long getSize() {
        return size;
    }

    public String getSizeAsText() {
        if (size < 1024) {
            return size + "B";
        } else if (size >= 1024 && size < 1024 * 1024) {
            return (size / 1024) + "KB";
        } else {
            return (size / 1024 / 1024) + "MB";
        }
    }

    public void setSize(long size) {
        this.size = size;
    }

    public String getContextTypeExtension() {
        final String extension = FilenameUtils.getExtension(this.name).toLowerCase();
        if ("png".equals(extension) || "gif".equals(extension)) {
            return extension;
        }
        return "jpeg";
    }
}