package com.andaily.domain.shared.system;

import com.andaily.domain.AbstractDomain;
import com.andaily.web.context.BeanProvider;

/**
 * @author Shengzhao Li
 */
public class SystemConfiguration extends AbstractDomain {


    private transient SystemRepository systemRepository = BeanProvider.getBean(SystemRepository.class);

    private PerPageSize perPageSize = PerPageSize.TWENTY;

    public SystemConfiguration() {
    }

    public PerPageSize perPageSize() {
        return perPageSize;
    }

    public SystemConfiguration perPageSize(PerPageSize perPageSize) {
        this.perPageSize = perPageSize;
        return this;
    }


    public void saveOrUpdate() {
        if (isNewly()) {
            throw new UnsupportedOperationException("Unsupport create SystemConfiguration");
        } else {
            systemRepository.updateSystemConfiguration(this);
        }
    }


    public static enum PerPageSize {
        TEN(10),
        TWENTY(20),
        THIRTY(30),
        FIFTY(50);


        private int size;

        private PerPageSize(int size) {
            this.size = size;
        }

        public int getSize() {
            return size;
        }

        public String getValue() {
            return name();
        }
    }
}