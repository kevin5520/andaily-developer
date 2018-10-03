package com.andaily.domain.user;

import com.andaily.domain.shared.Repository;
import com.andaily.domain.team.Team;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Date: 13-9-23
 *
 * @author Shengzhao Li
 * @see UserRepository
 */
public interface DeveloperRepository extends Repository {

    Developer findByGuid(String guid);

    List<Developer> findDeveloperByGuids(@Param("guids") List<String> guids);

    void saveDeveloper(Developer developer);

    /**
     * Please call {@link UserRepository#updateUser(User user)} before call this.
     * For update <code>User</code>  fields
     *
     * @param developer Exist developer
     */
    void updateDeveloper(Developer developer);

    /**
     * Empty developer, it's mean  the developer not relate any team.
     *
     * @return Developer list
     */
    List<Developer> findEmptyDevelopers(@Param("terms") ScrumTerm... terms);

    //set developer team is null
    void cleanDevelopersTeam(@Param("developers") List<Developer> developers);

    void updateDevelopersTeam(@Param("guids") List<String> guids, @Param("team") Team team);

    List<Developer> findAllProductOwners();
}
