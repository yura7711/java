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
import java.util.ArrayList;
import java.util.List;

@Repository
public class OracleDBRepository implements TaskRepository {
    private SessionFactory sessionFactory;
    private Session session;

    public OracleDBRepository() {
    }

    @Autowired
    public void setSessionFactory(SessionFactory factory){
        this.sessionFactory = factory;
    }

    @PostConstruct
    public void prepare(){
        sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        session = sessionFactory.getCurrentSession();
    }

    @PreDestroy
    public void shutdown(){
        sessionFactory.close();
        if (session != null){
            session.close();
        }
    }

    //Проверка наличия задачи с указанным ID
    @Override
    public boolean checkTaskById(Long id) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Task task = session.get(Task.class, id);
        session.getTransaction().commit();

        return task != null;
    }

    @Override
    public void addTask(Long id, String name, Long author_id, Long executor_id, String description) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Task task = session.get(Task.class, id);

        if (task != null){
            session.getTransaction().rollback();
            throw new TaskIsExistsException(id);
        }
        User author = session.get(User.class, author_id);
        User executor = session.get(User.class, executor_id);

        session.save(new Task(id, name, author, executor, description));
        session.getTransaction().commit();
    }

    @Override
    public void addTask(Task task) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(task);
        session.getTransaction().commit();
    }

    @Override
    public List<Task> getTaskArray() {
        List<Task> listTasks;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        listTasks = session.createQuery("SELECT tt FROM Task tt ORDER BY tt.id", Task.class).getResultList();
        session.getTransaction().commit();
        return listTasks;
    }

    @Override
    public List<User> getUserArray() {
        List<User> listUsers;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        listUsers = session.createQuery("SELECT usr FROM User usr ORDER BY usr.userName", User.class).getResultList();
        session.getTransaction().commit();
        return listUsers;
    }

    @Override
    public void updateTaskStatus(Long idTask, Task.Status newStatus) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Task task = session.get(Task.class, idTask);
        if (task == null){
            session.getTransaction().rollback();
            throw new TaskNotUpdatedException(idTask);
        }
        task.setStatus(newStatus);
        session.getTransaction().commit();
    }

    @Override
    public void deleteTask(Long idTask) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Task task = session.get(Task.class, idTask);

        if (task == null){
            session.getTransaction().rollback();
            throw new TaskNotDeletedException(idTask);
        }
        session.delete(task);
        session.getTransaction().commit();
    }

    @Override
    public void deleteTask(String nameTask) {
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Task> listTasks = session.createQuery("SELECT tt FROM Task tt WHERE tt.name = :nameTask", Task.class).setParameter("nameTask", nameTask).getResultList();

        if (listTasks.size() == 0){
            session.getTransaction().rollback();
            throw new TaskNotDeletedException(nameTask);
        }

        for (Task t: listTasks) {
            session.delete(t);
        }
        session.getTransaction().commit();
    }

    //Получить задачи назначенные на Пользователя
    public List<Task> getTasksForMe(Long userId){
        List<Task> tasks = new ArrayList<>();
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        User user = session.get(User.class, userId);

        for (Task t: user.getTasksForMe()) {
            tasks.add(t);
        }

        session.getTransaction().commit();
        return tasks;
    }

    public int getCountTaskByStatus(Task.Status status){
        int count;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        count = session.createQuery("SELECT tt FROM Task tt WHERE tt.status = :status", Task.class).setParameter("status", status).getResultList().size();
        session.getTransaction().commit();
        return count;
    }

    public List<Task> getTaskByStatus(Task.Status status) {
        List<Task> listTasks;
        session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        listTasks = session.createQuery("SELECT tt FROM Task tt where tt.status = :status", Task.class).setParameter("status", status).getResultList();
        session.getTransaction().commit();
        return listTasks;
    }
}
