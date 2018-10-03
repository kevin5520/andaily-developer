package com.andaily.infrastructure.mybatis.developer;

import com.andaily.domain.developer.ScrumComment;
import com.andaily.domain.developer.ScrumRepository;
import com.andaily.domain.shared.DateUtils;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.DeveloperRepository;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

/**
 * Date: 13-9-23
 *
 * @author Shengzhao Li
 */
public class ScrumRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private ScrumRepository scrumRepository;
    @Autowired
    private DeveloperRepository developerRepository;

    @Test
    public void findCommentByGuid() {
        ScrumComment result = scrumRepository.findCommentByGuid("ddd");
        assertNull(result);

        Developer developer = new Developer("asdoo@ddo.com", "123", "cd", "OK", ScrumTerm.MASTER);
        developerRepository.saveDeveloper(developer);

        Developer developer1 = developerRepository.findByGuid(developer.guid());

        ScrumComment scrumComment = new ScrumComment(developer1, DateUtils.now(), "test");
        scrumRepository.saveComment(scrumComment);

        ScrumComment result2 = scrumRepository.findCommentByGuid(scrumComment.guid());
        assertNotNull(result2);
        assertNotNull(result2.developer());
        assertNotNull(result2.commentTime());
    }

}
