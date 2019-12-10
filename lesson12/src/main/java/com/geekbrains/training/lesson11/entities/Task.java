package com.geekbrains.training.lesson11.entities;

import javax.persistence.*;

@Entity
@Table(name="tasks")
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

    public Task() {
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public User getAuthor() {
        return author;
    }

    public User getExecutor() {
        return executor;
    }

    public String getDescription() {
        return description;
    }

    public String getStatus() {
        return status.getRusTitle();
    }

    public Status getEnumStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setExecutor(User executor) {
        this.executor = executor;
    }

    public void setDescription(String description) {
        this.description = description;
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