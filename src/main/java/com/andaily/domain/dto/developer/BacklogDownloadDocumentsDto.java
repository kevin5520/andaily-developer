package com.andaily.domain.dto.developer;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.dto.AbstractDTO;

/**
 * @author Shengzhao Li
 */
public class BacklogDownloadDocumentsDto extends AbstractDTO {

    private byte[] data;

    public BacklogDownloadDocumentsDto() {
    }

    public BacklogDownloadDocumentsDto(Backlog backlog) {
        super(backlog.guid());

    }

    public String getDownloadFilename() {
        return "documents-" + this.guid + ".zip";
    }

    public byte[] getData() {
        return data;
    }

    public BacklogDownloadDocumentsDto setData(byte[] data) {
        this.data = data;
        return this;
    }
}