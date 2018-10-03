package com.andaily.domain.dto.developer.commons;

import com.andaily.domain.developer.commons.Document;
import com.andaily.domain.dto.AbstractDTO;

/**
 * @author Shengzhao Li
 */
public class DocumentDto extends AbstractDTO {

    protected GeckoFileDto fileDto;

    public DocumentDto() {
    }

    public DocumentDto(Document document) {
        super(document.guid());
        this.fileDto = new GeckoFileDto(document.file());
    }


    public GeckoFileDto getFileDto() {
        return fileDto;
    }

    public void setFileDto(GeckoFileDto fileDto) {
        this.fileDto = fileDto;
    }
}