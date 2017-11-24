package com.cikezxy.sandbox.beanutils;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MessageBeanAlias implements Serializable{
    @JsonProperty(value = "msg_id",access = JsonProperty.Access.READ_WRITE)
    private String id;

    private String title;

    @JsonProperty(value = "body",access = JsonProperty.Access.READ_WRITE)
    private String content;

    public MessageBeanAlias() {
    }

    public MessageBeanAlias(String id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

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
    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "MessageBeanAlias{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                '}';
    }
}
