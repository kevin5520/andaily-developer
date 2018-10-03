package com.andaily.domain.user;

import com.andaily.domain.team.Team;
import com.andaily.web.context.BeanProvider;

/**
 * Date: 13-9-22
 *
 * @author Shengzhao Li
 */
public class Developer extends User {


    private transient DeveloperRepository developerRepository = BeanProvider.getBean(DeveloperRepository.class);

    private ScrumTerm scrumTerm;

    private Team team;

    private Language language = Language.CHINESE;

    public Developer() {
        super();
    }

    public Developer(String email, String password, String nickName, String cellPhone, ScrumTerm scrumTerm) {
        super(email, password, nickName);
        this.cellPhone = cellPhone;
        this.scrumTerm = scrumTerm;
    }

    public ScrumTerm scrumTerm() {
        return scrumTerm;
    }

    public boolean isDeveloper() {
        return true;
    }

    public Developer updateScrumTerm(ScrumTerm scrumTerm) {
        this.scrumTerm = scrumTerm;
        return this;
    }

    public Team team() {
        return team;
    }

    public Developer team(Team team) {
        this.team = team;
        return this;
    }

    public void saveOrUpdate() {
        if (isNewly()) {
            developerRepository.saveDeveloper(this);
        } else {
            developerRepository.updateDeveloper(this);
        }
    }

    public Language language() {
        return language;
    }

    public Developer language(Language language) {
        this.language = language;
        return this;
    }
}
