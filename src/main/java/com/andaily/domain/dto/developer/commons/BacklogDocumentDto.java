package com.andaily.domain.dto.developer.commons;

import com.andaily.domain.developer.BacklogDocument;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class BacklogDocumentDto extends DocumentDto {


    public BacklogDocumentDto() {
        super();
    }

    public BacklogDocumentDto(BacklogDocument document) {
        super(document);
    }


    public static List<BacklogDocumentDto> toBacklogDtos(List<BacklogDocument> documents) {
        List<BacklogDocumentDto> dtos = new ArrayList<>(documents.size());
        for (BacklogDocument document : documents) {
            dtos.add(new BacklogDocumentDto(document));
        }
        return dtos;
    }
}