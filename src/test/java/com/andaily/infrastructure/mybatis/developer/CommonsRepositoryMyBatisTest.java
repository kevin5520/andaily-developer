package com.andaily.infrastructure.mybatis.developer;

import com.andaily.domain.developer.BacklogDocument;
import com.andaily.domain.developer.commons.CommonsRepository;
import com.andaily.domain.developer.commons.Document;
import com.andaily.domain.developer.commons.GeckoFile;
import com.andaily.domain.shared.GuidGenerator;
import com.andaily.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * @author Shengzhao Li
 */
public class CommonsRepositoryMyBatisTest extends AbstractRepositoryTest {


    @Autowired
    private CommonsRepository commonsRepository;


    @Test
    public void saveBacklogDocument() {
        BacklogDocument backlogDocument = new BacklogDocument();
        commonsRepository.saveBacklogDocument(backlogDocument);

        Document document = commonsRepository.findDocumentByGuid(backlogDocument.guid());
        assertNotNull(document);

        commonsRepository.deleteDocument(document);

        document = commonsRepository.findDocumentByGuid(backlogDocument.guid());
        assertNull(document);
    }

    @Test
    public void findGeckoFileByGuid() {
        String guid = GuidGenerator.generate();
        GeckoFile geckoFile = commonsRepository.findGeckoFileByGuid(guid);
        assertNull(geckoFile);

        GeckoFile file = new GeckoFile("abc.txt", "abc".getBytes());
        commonsRepository.saveGeckoFile(file);

        geckoFile = commonsRepository.findGeckoFileByGuid(file.guid());
        assertNotNull(geckoFile);
        assertNotNull(geckoFile.name());

    }

    @Test
    public void deleteGeckoFile() {
        GeckoFile geckoFile;

        GeckoFile file = new GeckoFile("abc.txt", "abc".getBytes());
        commonsRepository.saveGeckoFile(file);

        geckoFile = commonsRepository.findGeckoFileByGuid(file.guid());
        assertNotNull(geckoFile);
        assertNotNull(geckoFile.name());

        commonsRepository.deleteGeckoFile(geckoFile);

        geckoFile = commonsRepository.findGeckoFileByGuid(file.guid());
        assertNull(geckoFile);
    }

}