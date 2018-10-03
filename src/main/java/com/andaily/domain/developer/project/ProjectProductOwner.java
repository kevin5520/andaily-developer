package com.andaily.domain.developer.project;

import com.andaily.domain.AbstractDomain;
import com.andaily.domain.user.Developer;
import com.andaily.domain.user.ScrumTerm;
import com.andaily.web.context.BeanProvider;

/**
 * @author Shengzhao Li
 */
public class ProjectProductOwner extends AbstractDomain {

    private transient ProjectRepository projectRepository = BeanProvider.getBean(ProjectRepository.class);

    private Project project;
    private Developer productOwner;

    public ProjectProductOwner() {
    }

    public ProjectProductOwner(Project project, Developer productOwner) {
        this.project = project;
        this.productOwner = productOwner;
        if (!productOwner.scrumTerm().equals(ScrumTerm.PRODUCT_OWNER)) {
            throw new IllegalStateException("The developer must be [PRODUCT_OWNER]");
        }
    }

    @Override
    public void saveOrUpdate() {
        if (isNewly()) {
            projectRepository.saveProjectProductOwner(this);
        } else {
            throw new UnsupportedOperationException("Update ProjectProductOwner is unsupported");
        }
    }

    public Project project() {
        return project;
    }

    public Developer productOwner() {
        return productOwner;
    }
}