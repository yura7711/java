package com.geekbrains.gwt.common;

public class TaskAddDto {
    private Long id;
    private String name;
    private Long author_id;
    private Long executor_id;
    private String description;

    public TaskAddDto() {
    }

    public TaskAddDto(Long id, String name, Long author_id, Long executor_id, String description) {
        this.id = id;
        this.name = name;
        this.author_id = author_id;
        this.executor_id = executor_id;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAuthor_id() {
        return author_id;
    }

    public void setAuthor_id(Long author_id) {
        this.author_id = author_id;
    }

    public Long getExecutor_id() {
        return executor_id;
    }

    public void setExecutor_id(Long executor_id) {
        this.executor_id = executor_id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
