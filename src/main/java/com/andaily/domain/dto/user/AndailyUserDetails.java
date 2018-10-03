package com.andaily.domain.dto.user;

import com.andaily.domain.team.Team;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.Language;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.domain.user.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

/**
 * @author Shengzhao Li
 */
public class AndailyUserDetails implements UserDetails {

    protected static final String ROLE_PREFIX = "ROLE_";
    protected static final GrantedAuthority DEFAULT_USER_ROLE = new SimpleGrantedAuthority(ROLE_PREFIX + "USER");

    protected User user;
    //Developer fields
    protected ScrumTerm scrumTerm;
    protected Team team;
    protected Language language = Language.ENGLISH;

    public AndailyUserDetails(User user) {
        updateUserDetails(user);
    }

    /**
     * Update user details information if necessary
     *
     * @param user User
     */
    public void updateUserDetails(User user) {
        this.user = user;
        if (user.isDeveloper()) {
            Developer developer = (Developer) user;
            this.scrumTerm = developer.scrumTerm();
            this.team = developer.team();
            this.language = developer.language();
        }
    }

    @Override
    public Collection<GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (scrumTerm != null) {
            authorities.add(new SimpleGrantedAuthority(ROLE_PREFIX + scrumTerm.name()));
        }
        authorities.add(DEFAULT_USER_ROLE);
        return authorities;
    }

    public Team getTeam() {
        return this.team;
    }

    public User getUser() {
        return this.user;
    }

    @Override
    public String getPassword() {
        return user.password();
    }

    @Override
    public String getUsername() {
        return user.username();
    }

    public Language getLanguage() {
        return language;
    }

    /**
     * Call by dashboard page
     *
     * @return User nick name
     */
    public String getNickName() {
        if (StringUtils.isNotBlank(user.nickName())) {
            return user.nickName();
        }
        return user.username();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String toString() {
        return "AndailyUserDetails{" +
                "user=" + user +
                '}';
    }
}
