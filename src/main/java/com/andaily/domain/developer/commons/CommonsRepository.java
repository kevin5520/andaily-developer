package com.andaily.domain.developer.commons;

import com.andaily.domain.developer.BacklogDocument;
import com.andaily.domain.shared.Repository;

/**
 * @author Shengzhao Li
 */

public interface CommonsRepository extends Repository {


    void saveGeckoFile(GeckoFile geckoFile);

    void updateGeckoFile(GeckoFile geckoFile);

    GeckoFile findGeckoFileByGuid(String guid);

    void deleteGeckoFile(GeckoFile geckoFile);

    void saveBacklogDocument(BacklogDocument backlogDocument);

    void deleteDocument(Document document);

    Document findDocumentByGuid(String guid);
}