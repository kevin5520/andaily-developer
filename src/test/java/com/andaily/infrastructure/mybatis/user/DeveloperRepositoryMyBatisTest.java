package com.andaily.infrastructure.mybatis.user;

import com.andaily.domain.team.Team;
import com.andaily.domain.team.TeamRepository;
import com.andaily.domain.user.*;
import com.andaily.infrastructure.AbstractRepositoryTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.*;

/**
 * @author Shengzhao Li
 */
public class DeveloperRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private DeveloperRepository developerRepository;
    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void saveDeveloper() {
        Developer developer = new Developer("me", "123", "CD", "OK", ScrumTerm.MEMBER).description("testing");
        developerRepository.saveDeveloper(developer);

        Developer result = developerRepository.findByGuid(developer.guid());
        assertNotNull(result);
        assertNotNull(result.description());
        assertTrue(result.id() > 0);

        User user = userRepository.findByGuid(developer.guid());
        assertNotNull(user);
        assertTrue(user.isDeveloper());
    }

    @Test
    public void findAllProductOwners() {
        final List<Developer> list = developerRepository.findAllProductOwners();
        assertTrue(list.isEmpty());
    }

    @Test
    public void cleanDevelopersTeam() {
        Team team = new Team("test", null);
        team.saveOrUpdate();
        team = teamRepository.findByGuid(team.guid());

        Developer developer = new Developer("me", "123", "CD", "OK", ScrumTerm.MEMBER);
        developer.team(team);
        developerRepository.saveDeveloper(developer);

        Developer result = developerRepository.findByGuid(developer.guid());
        assertNotNull(result.team());

        developerRepository.cleanDevelopersTeam(Arrays.asList(result));

        result = developerRepository.findByGuid(developer.guid());
        assertNull(result.team());
    }

    @Test
    public void updateDevelopersTeam() {
        Team team = new Team("test", null);
        team.saveOrUpdate();
        team = teamRepository.findByGuid(team.guid());

        Developer developer = new Developer("me", "123", "CD", "OK", ScrumTerm.MEMBER);
        developerRepository.saveDeveloper(developer);

        Developer result = developerRepository.findByGuid(developer.guid());
        assertNull(result.team());

        developerRepository.updateDevelopersTeam(Arrays.asList(result.guid()), team);

        result = developerRepository.findByGuid(developer.guid());
        assertNotNull(result.team());
    }

    @Test
    public void findEmptyDevelopers() {
        Developer developer = new Developer("me", "123", "CD", "OK", ScrumTerm.MEMBER);
        developerRepository.saveDeveloper(developer);

        final List<Developer> list = developerRepository.findEmptyDevelopers(ScrumTerm.MEMBER);
        assertEquals(list.size(), 1);
    }

    @Test
    public void updateDeveloper() {
        Developer developer = new Developer("me", "123", "CD", "OK", ScrumTerm.MEMBER);
        developerRepository.saveDeveloper(developer);

        Developer result = developerRepository.findByGuid(developer.guid());
        assertNotNull(result);
        assertTrue(result.id() > 0);

        result.updateScrumTerm(ScrumTerm.MASTER);
        developerRepository.updateDeveloper(result);

        Developer result2 = developerRepository.findByGuid(result.guid());
        assertNotNull(result2);
        assertTrue(result2.id() > 0);
        assertEquals(result2.scrumTerm(), ScrumTerm.MASTER);
    }
}
