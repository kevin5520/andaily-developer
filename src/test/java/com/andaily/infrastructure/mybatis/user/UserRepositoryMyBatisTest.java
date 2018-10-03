package com.andaily.infrastructure.mybatis.user;

import com.andaily.domain.shared.GuidGenerator;
import com.andaily.domain.shared.security.SecurityUtils;
import com.andaily.domain.user.User;
import com.andaily.domain.user.UserRepository;
import com.andaily.infrastructure.AbstractRepositoryTest;
import com.andaily.infrastructure.mybatis.data.ScrumTermData;
import org.springframework.beans.factory.annotation.Autowired;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.testng.Assert.*;

/**
 * @author Shengzhao Li
 */
public class UserRepositoryMyBatisTest extends AbstractRepositoryTest {

    @Autowired
    private UserRepository userRepository;


    @Test
    public void findScrumTermDatas() {
        final List<ScrumTermData> list = userRepository.findScrumTermDatas();
        assertTrue(list.isEmpty());
    }

    @Test
    public void findOverviewUsers() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("email", "%es%");
        map.put("archived", false);
        map.put("teamGuid", null);

        List<User> list = userRepository.findOverviewUsers(map);
        assertTrue(list.isEmpty());

        User user = new User("es@ed.com", "ad", "cd");
        userRepository.saveUser(user);

        list = userRepository.findOverviewUsers(map);
        assertTrue(list.size() == 1);

    }

    @Test
    public void totalOverviewUsers() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userGuid", SecurityUtils.currentUserGuid());
        map.put("perPageSize", 20);
        map.put("startIndex", 0);
        map.put("email", "%es%");
        map.put("archived", false);
        map.put("teamGuid", null);

        int count = userRepository.totalOverviewUsers(map);
        assertTrue(count == 0);

        User user = new User("es@ed.com", "ad", "cd");
        userRepository.saveUser(user);

        count = userRepository.totalOverviewUsers(map);
        assertTrue(count == 1);

    }

    @Test
    public void findUserByGuids() {
        User user = new User("es@ed.com", "ad", "cd");
        userRepository.saveUser(user);

        List<String> guids = Arrays.asList(user.guid());
        List<User> list = userRepository.findUserByGuids(guids);
        assertTrue(list.size() == 1);
    }

    @Test
    public void findArchivedUser() {
        String code = GuidGenerator.generate();
        User user = new User("es@ed.com", "ad", "cd").verifyCode(code);
        user.archived(true);
        userRepository.saveUser(user);

        User result = userRepository.findArchivedUser(code);
        assertNotNull(result);
        assertTrue(user.archived());
    }

    @Test
    public void findUserGuidByEmail() {

        String email = "test@andaily.com";
        String guid = userRepository.findUserGuidByEmail(email);
        assertNull(guid);

        User user = new User(email, "123sdsooe", null);
        userRepository.saveUser(user);
        guid = userRepository.findUserGuidByEmail(email);
        assertTrue(guid.equals(user.guid()));

    }

    @Test
    public void findMyPassword() {
        String guid = "0230sddf";
        String myPassword = userRepository.findMyPassword(guid);
        assertNull(myPassword);

    }

    @Test
    public void saveUser() {
        String phone = "32332";

        User user = new User("abd@gmil.com", "wee", "cd");
        user.update("abc", phone);
        user.archived(true);

        userRepository.saveUser(user);
        User result = userRepository.findByGuid(user.guid());
        assertNotNull(result);
        assertTrue(user.archived());

    }

    @Test
    public void findByEmailIncludeArchived() {
        String phone = "32332";

        User user = new User("abd@gmil.com", "wee", "cd");
        user.update("abc", phone);

        userRepository.saveUser(user);
        User result = userRepository.findByEmailIncludeArchived(user.email());
        assertNotNull(result);

    }

    @Test
    public void findUserGuidByCellphone() {
        String phone = "123456";

        String userGuid = userRepository.findUserGuidByCellphone(phone);
        assertNull(userGuid);

        User user = new User("abd@gmil.com", "wee", "cd");
        user.update("ad", phone);
        userRepository.saveUser(user);

        userGuid = userRepository.findUserGuidByCellphone(phone);
        assertNotNull(userGuid);
        assertEquals(userGuid, user.guid());
    }

    @Test
    public void updateUser() {
        User user = new User("abd@gmil.com", "wee", "cd");
        user.update("ad", "phone");
        userRepository.saveUser(user);

        userRepository.updateUser(user);
    }

    @Test
    public void activeUser() {
        String code = GuidGenerator.generate();
        User user = new User("abd@tad.com", "asd", "cd").verifyCode(code);
        user.archived(true);
        userRepository.saveUser(user);
        assertNotNull(user.verifyCode());

        User result = userRepository.findByGuid(user.guid());
        assertNotNull(result.verifyCode());

        userRepository.activeUser(user.guid());

        User queryUser = userRepository.findByGuid(user.guid());
        assertFalse(queryUser.archived());
        assertNull(queryUser.verifyCode());
    }
}
