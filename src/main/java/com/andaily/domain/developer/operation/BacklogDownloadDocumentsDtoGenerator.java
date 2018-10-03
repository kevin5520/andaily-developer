package com.andaily.domain.developer.operation;

import com.andaily.domain.developer.Backlog;
import com.andaily.domain.developer.BacklogDocument;
import com.andaily.domain.developer.BacklogRepository;
import com.andaily.domain.developer.commons.GeckoFile;
import com.andaily.domain.dto.developer.BacklogDownloadDocumentsDto;
import com.andaily.domain.shared.Application;
import com.andaily.web.context.BeanProvider;
import org.apache.tools.zip.ZipEntry;
import org.apache.tools.zip.ZipOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Shengzhao Li
 */
public class BacklogDownloadDocumentsDtoGenerator {

    private transient BacklogRepository backlogRepository = BeanProvider.getBean(BacklogRepository.class);
    private String guid;

    public BacklogDownloadDocumentsDtoGenerator(String guid) {
        this.guid = guid;
    }

    public BacklogDownloadDocumentsDto generate() {
        Backlog backlog = backlogRepository.findByGuid(guid);
        final List<BacklogDocument> documents = backlog.documents();
        byte[] data;
        try {
            data = generateDate(documents);
        } catch (IOException e) {
            throw new IllegalStateException("Generate Zip data failed", e);
        }
        //more codes in here
        return new BacklogDownloadDocumentsDto(backlog).setData(data);
    }

    private byte[] generateDate(List<BacklogDocument> documents) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ZipOutputStream zipOutputStream = new ZipOutputStream(baos);
        zipOutputStream.setEncoding(Application.ENCODING);

        for (BacklogDocument document : documents) {
            final GeckoFile file = document.file();
            ZipEntry entry = new ZipEntry(file.name());
            zipOutputStream.putNextEntry(entry);

            zipOutputStream.write(file.data());
            zipOutputStream.closeEntry();
        }

        zipOutputStream.close();
        return baos.toByteArray();
    }
}