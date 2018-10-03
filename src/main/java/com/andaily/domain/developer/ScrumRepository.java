package com.andaily.domain.developer;

import com.andaily.domain.shared.Repository;

/**
 * Date: 13-9-23
 *
 * @author Shengzhao Li
 */
public interface ScrumRepository extends Repository {

    void saveComment(ScrumComment comment);

    ScrumComment findCommentByGuid(String guid);
}
