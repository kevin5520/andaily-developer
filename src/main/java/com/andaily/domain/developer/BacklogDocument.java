package com.andaily.domain.developer;

import com.andaily.domain.developer.commons.Document;
import com.andaily.domain.developer.commons.GeckoFile;

/**
 * @author Shengzhao Li
 */
public class BacklogDocument extends Document {

    private Backlog backlog;

    public BacklogDocument() {
    }

    public BacklogDocument(Backlog backlog, GeckoFile file) {
        super(file);
        this.backlog = backlog;
    }

    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            file.saveOrUpdate();
            file = commonsRepository.findGeckoFileByGuid(file.guid());
            commonsRepository.saveBacklogDocument(this);
        } else {
            throw new UnsupportedOperationException("Update BacklogDocument is un-supported");
        }
    }

    public Backlog backlog() {
        return backlog;
    }
}