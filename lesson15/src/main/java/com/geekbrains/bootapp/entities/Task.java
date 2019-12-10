package com.geekbrains.bootapp.entities;

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