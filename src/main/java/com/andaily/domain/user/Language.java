package com.andaily.domain.user;

import java.util.Locale;

/**
 * @author Shengzhao Li
 */

public enum Language {

    CHINESE("中文") {
        @Override
        public Locale resolveLocale() {
            return Locale.SIMPLIFIED_CHINESE;
        }
    },
    ENGLISH("English") {
        @Override
        public Locale resolveLocale() {
            return Locale.US;
        }
    };

    private Language(String label) {
        this.label = label;
    }

    public abstract Locale resolveLocale();

    public String getValue() {
        return name();
    }

    public String getLabel() {
        return label;
    }

    private String label;


    public boolean isChinese() {
        return CHINESE.equals(this);
    }
}