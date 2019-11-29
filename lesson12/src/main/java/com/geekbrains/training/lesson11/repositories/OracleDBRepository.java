package com.geekbrains.training.lesson11.repositories;

import com.geekbrains.training.lesson11.entities.Task;
import com.geekbrains.training.lesson11.entities.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class OracleDBRepository implements TaskRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public OracleDBRepository() {
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory){
        this.sessionFactory = factory;
    }

    //Проверка наличия задачи с указанным ID
    @Override
    public boolean checkTaskById(Long id) {
        session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);

        return task != null;
    }

    @Override
    public void addTask(Long id, String name, Long author_id, Long executor_id, String description) {
        session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, id);

        if (task != null){
            throw new TaskIsExistsException(id);
        }
        User author = session.get(User.class, author_id);
        User executor = session.get(User.class, executor_id);

        session.save(new Task(id, name, author, executor, description));
    }

    @Override
    public void addTask(Task task) {
        session = sessionFactory.getCurrentSession();
        session.save(task);
    }

    @Override
    public List<Task> getTaskArray() {
        List<Task> listTasks;
        session = sessionFactory.getCurrentSession();
        listTasks = session.createQuery("SELECT tt FROM Task tt ORDER BY tt.id", Task.class).getResultList();
        return listTasks;
    }

    @Override
    public List<User> getUserArray() {
        List<User> listUsers;
        session = sessionFactory.getCurrentSession();
        listUsers = session.createQuery("SELECT usr FROM User usr ORDER BY usr.userName", User.class).getResultList();
        return listUsers;
    }

    @Override
    public void updateTaskStatus(Long idTask, Task.Status newStatus) {
        session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, idTask);
        if (task == null){
            throw new TaskNotUpdatedException(idTask);
        }
        task.setStatus(newStatus);
    }

    @Override
    public void deleteTask(Long idTask) {
        session = sessionFactory.getCurrentSession();
        Task task = session.get(Task.class, idTask);

        if (task == null){
            throw new TaskNotDeletedException(idTask);
        }
        session.delete(task);
    }

    @Override
    public void deleteTask(String nameTask) {
        session = sessionFactory.getCurrentSession();
        List<Task> listTasks = session.createQuery("SELECT tt FROM Task tt WHERE tt.name = :nameTask", Task.class).setParameter("nameTask", nameTask).getResultList();

        if (listTasks.size() == 0){
            throw new TaskNotDeletedException(nameTask);
        }

        for (Task t: listTasks) {
            session.delete(t);
        }
    }

    //Получить задачи назначенные на Пользователя
    public List<Task> getTasksForMe(Long userId){
        List<Task> tasks = new ArrayList<>();
        session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, userId);

        for (Task t: user.getTasksForMe()) {
            tasks.add(t);
        }
        return tasks;
    }

    public int getCountTaskByStatus(Task.Status status){
        int count;
        session = sessionFactory.getCurrentSession();
        count = session.createQuery("SELECT tt FROM Task tt WHERE tt.status = :status", Task.class).setParameter("status", status).getResultList().size();
        return count;
    }

    public List<Task> getTaskByStatus(Task.Status status) {
        List<Task> listTasks;
        session = sessionFactory.getCurrentSession();
        listTasks = session.createQuery("SELECT tt FROM Task tt where tt.status = :status", Task.class).setParameter("status", status).getResultList();
        return listTasks;
    }
}
