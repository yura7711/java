package com.geekbrains.bootapp.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name="tasks")
@Data
@NoArgsConstructor
public class Task {
    private static final long serialVersionUID = -277821547227621214L;

    public enum Status {
        created(1,"Создана", 1)
        , inWork(2,"В работе", 2)
        , closed(3,"Закрыта", 3)
        , rejected(4,"Отклонена", 4);

        private int statusId;
        private String rusTitle;
        private int priority;

        Status(int statusId, String rusTitle, int priority) {
            this.statusId = statusId;
            this.rusTitle = rusTitle;
            this.priority = priority;
        }

        public String getRusTitle() {
            return rusTitle;
        }

        public int getPriority() {
            return priority;
        }

        public int getStatusId() { return statusId; }

        public static Status getStatusByRusName(String rusName){
            for (Status status: Status.values()) {
                if (status.getRusTitle().equals(rusName)){
                    return status;
                }
            }
            return null;
        }

        public static Status getStatusById(int statusId){
            for (Status status: Status.values()) {
                if (status.getStatusId() == statusId){
                    return status;
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
    private String name;

    @ManyToOne
    @JoinColumn(name="author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name="executor_id")
    private User executor;

    @Column(name="description")
    private String description;

    @Enumerated(EnumType.STRING)
    private Status status;

    public Task(String name, User author, User executor, String description) {
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.description = description;
        this.status = Status.created;
    }

    public Task(Long id, String name, User author, User executor, String description) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.description = description;
        this.status = Status.created;
    }

    public Task(Long id, String name, User author, User executor, String description, Status status) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.executor = executor;
        this.description = description;
        this.status = status;
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