package com.geekbrains.server.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.geekbrains.gwt.common.TaskAddDto;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="tasks")
@Data
@NoArgsConstructor
public class Task {

    @JsonFormat(shape=JsonFormat.Shape.OBJECT)
    public enum Status {
        created(1,"Создана", 1)
        , inWork(2,"В работе", 2)
        , closed(3,"Закрыта", 3)
        , rejected(4,"Отклонена", 4);

        private Integer statusId;
        private String rusTitle;
        private int priority;

        Status(int statusId, String rusTitle, int priority) {
            this.statusId = statusId;
            this.rusTitle = rusTitle;
            this.priority = priority;
        }

        Status(Integer statusId, String rusTitle) {
            this.statusId = statusId;
            this.rusTitle = rusTitle;
        }

        public String getRusTitle() {
            return rusTitle;
        }

        public Integer getStatusId() {
            return statusId;
        }

        public void setStatusId(Integer statusId) {
            this.statusId = statusId;
        }

        public static Status getStatusById(Integer statusId){
            for (Status o: Status.values()) {
                if (o.getStatusId() == statusId){
                    return o;
                }
            }
            return null;
        }
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "s_tasks_id")
    @SequenceGenerator(name = "s_tasks_id", sequenceName = "s_tasks_id", allocationSize = 1)
    @Column(name="task_id")
    private Long id;

    @Column(name="task_name")
    @Size(min = 3, message = "Название должно содержать минимум 3 символа")
    @NotNull
    private String name;

    @ManyToOne
    @JoinColumn(name="author_id")
    @NotNull
    private User author;

    @ManyToOne
    @JoinColumn(name="executor_id")
    @NotNull
    private User executor;

    @Column(name="description")
    @Size(max = 4000, message = "Превышена максимальная длина поля")
    @Size(min = 10, message = "Описание должно содержать минимум 10 символов")
    @NotNull
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Task(TaskAddDto taskAddDto, User author, User executer) {
        this.id = taskAddDto.getId();
        this.name = taskAddDto.getName();
        this.author = author;
        this.executor = executer;
        this.description = taskAddDto.getDescription();
        this.status = Status.created;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)){
            return false;
        }
        return this.id.equals(((Task)obj).getId());
    }

    @Override
    public String toString() {
        return "***\nid=" + id + " " + name + "\nСтатус: " + status.getRusTitle() + "\nИсполнитель: " + executor.getUserName() + "\nАвтор: " + author.getUserName() + "\nОписание: " + description;
    }
}