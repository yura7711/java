package com.geekbrains.gwt.common;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskDto {
    public enum StatusDto {
        created(1,"Создана", 1)
        , inWork(2,"В работе", 2)
        , closed(3,"Закрыта", 3)
        , rejected(4,"Отклонена", 4);

        private Integer statusId;
        private String rusTitle;
        private int priority;

        StatusDto(int statusId, String rusTitle, int priority) {
            this.statusId = statusId;
            this.rusTitle = rusTitle;
            this.priority = priority;
        }

        StatusDto(Integer statusId, String rusTitle) {
            this.statusId = statusId;
            this.rusTitle = rusTitle;
        }

        public String getRusTitle() {
            return rusTitle;
        }

        public void setRusTitle(String rusTitle) {
            this.rusTitle = rusTitle;
        }

        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }

        public static StatusDto getStatusById(Integer statusId){
            for (StatusDto o: StatusDto.values()) {
                if (o.getStatusId() == statusId){
                    return o;
                }
            }
            return null;
        }


    }

    private Long id;
    private String name;
    private UserDto authorDto;
    private UserDto executorDto;
    private String description;
    private StatusDto statusDto;

    public TaskDto() {
    }

    public TaskDto(Long id, String name, UserDto authorDto, UserDto executorDto, String description, StatusDto statusDto) {
        this.id = id;
        this.name = name;
        this.authorDto = authorDto;
        this.executorDto = executorDto;
        this.description = description;
        this.statusDto = statusDto;
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

    public UserDto getAuthorDto() {
        return authorDto;
    }

    public void setAuthorDto(UserDto authorDto) {
        this.authorDto = authorDto;
    }

    public UserDto getExecutorDto() {
        return executorDto;
    }

    public void setExecutorDto(UserDto executorDto) {
        this.executorDto = executorDto;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public StatusDto getStatusDto() {
        return statusDto;
    }

    public void setStatusDto(StatusDto statusDto) {
        this.statusDto = statusDto;
    }
}
