package com.andaily.domain.shared;

/**
 * @author Shengzhao Li
 */
public abstract class MatchUtils {

    /**
     * BigDecimal regex.
     */
    public static final String BIG_DECIMAL_REGEX = "^(\\d+)||(\\d+\\.\\d+)$";

    /**
     * Positive Number regex.
     */
    public static final String POSITIVE_NUMBER_REGEX = "^\\d+$";

    /**
     * Email regex.
     */
    public static final String EMAIL_REGEX = ".+@.+\\.[a-z]+";


    /**
     * private constructor
     */
    private MatchUtils() {
    }

    public static boolean isBigDecimal(String text) {
        return text.matches(BIG_DECIMAL_REGEX);
    }

    public static boolean isEmail(String email) {
        return email.matches(EMAIL_REGEX);
    }

    public static boolean isPositiveNumber(String numberText) {
        return numberText.matches(POSITIVE_NUMBER_REGEX);
    }
}
