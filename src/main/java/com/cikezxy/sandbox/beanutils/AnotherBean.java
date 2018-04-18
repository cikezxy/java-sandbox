package com.cikezxy.sandbox.beanutils;

public class AnotherBean {

    private String id;
    private String title;

    private String unique;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUnique() {
        return unique;
    }

    public void setUnique(String unique) {
        this.unique = unique;
    }

    @Override
    public String toString() {
        return "AnotherBean{" + "id='" + id + '\'' + ", title='" + title + '\'' + ", unique='" + unique + '\'' + '}';
    }
}
