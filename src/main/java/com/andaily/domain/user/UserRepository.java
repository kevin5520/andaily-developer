package com.andaily.domain.user;

import com.andaily.domain.shared.Repository;
import com.andaily.infrastructure.mybatis.data.ScrumTermData;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * @author Shengzhao Li
 */
public interface UserRepository extends Repository {

    User findByGuid(String guid);

    void saveUser(User user);

    String findUserGuidByEmail(String email);

    /**
     * When active user,  will set verifyCode field value to null
     *
     * @param guid User guid
     */
    void activeUser(String guid);

    User findArchivedUser(String code);

    User findByEmail(String email);

    void updatePassword(User user);

    String findMyPassword(String guid);

    void updateUser(User user);

    String findUserGuidByCellphone(String phone);

    List<User> findUserByGuids(@Param("guids") List<String> guids);

    List<User> findOverviewUsers(Map<String, Object> map);

    int totalOverviewUsers(Map<String, Object> map);

    List<ScrumTermData> findScrumTermDatas();

    User findByEmailIncludeArchived(String email);
}
