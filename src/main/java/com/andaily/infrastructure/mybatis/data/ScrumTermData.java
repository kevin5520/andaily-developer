package com.andaily.infrastructure.mybatis.data;

import com.andaily.domain.user.ScrumTerm;

/**
 * Date: 13-11-26
 *
 * @author Shengzhao Li
 */
public class ScrumTermData {

    private ScrumTerm term;
    private int count;

    public ScrumTermData() {
    }

    public ScrumTermData(ScrumTerm term) {
        this.term = term;
    }

    public ScrumTerm getTerm() {
        return term;
    }

    public void setTerm(ScrumTerm term) {
        this.term = term;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ScrumTermData that = (ScrumTermData) o;

        if (term != that.term) {
            return false;
        }

        return true;
    }

    @Override
    public int hashCode() {
        return term != null ? term.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "ScrumTermData{" +
                "term=" + term +
                ", count=" + count +
                '}';
    }
}
