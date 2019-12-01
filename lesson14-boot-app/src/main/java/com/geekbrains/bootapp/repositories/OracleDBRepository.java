package com.geekbrains.bootapp.repositories;

import com.geekbrains.bootapp.entities.Task;
import com.geekbrains.bootapp.entities.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Repository
@Transactional
public class OracleDBRepository implements TaskRepository {
    @PersistenceContext
    private EntityManager entityManager;

    //Проверка наличия задачи с указанным ID
    @Override
    public boolean checkTaskById(Long id) {
        Task task = entityManager.find(Task.class, id);

        entityManager.find(Task.class, id);
        return task != null;
    }

    @Override
    public void addTask(Long id, String name, Long author_id, Long executor_id, String description) {
        Task task = entityManager.find(Task.class, id);

        if (task != null){
            throw new TaskIsExistsException(id);
        }
        User author = entityManager.find(User.class, author_id);
        User executor = entityManager.find(User.class, executor_id);

        entityManager.persist(new Task(id, name, author, executor, description));
    }

    @Override
    public void addTask(Task task) {
        entityManager.persist(task);
    }

    @Override
    public List<Task> getTaskArray(Task.Status status, Long executer_id, Long author_id) {
        List<Task> listTasks;
        String statusName = null;
        if (status != null) {
            statusName = status.name();
        }
        listTasks = entityManager.createNativeQuery("select tt.* " +
                "from tasks tt " +
                "where (tt.status=:status or :status is null) " +
                "  and (tt.executor_id=nvl(to_number(:executer_id),tt.executor_id)) " +
                "  and (tt.author_id=nvl(to_number(:author_id),tt.author_id)) " +
                "order by tt.task_id", Task.class)
                .setParameter("status", statusName)
                .setParameter("executer_id", executer_id)
                .setParameter("author_id", author_id)
                .getResultList();
        return listTasks;
    }

    @Override
    public List<User> getUserArray() {
        List<User> listUsers;
        listUsers = entityManager.createQuery("SELECT usr FROM User usr ORDER BY usr.userName", User.class).getResultList();
        return listUsers;
    }

    @Override
    public void updateTaskStatus(Long idTask, Task.Status newStatus) {
        Task task = entityManager.find(Task.class, idTask);
        if (task == null){
            throw new TaskNotUpdatedException(idTask);
        }
        task.setStatus(newStatus);
    }

    @Override
    public void deleteTask(Long idTask) {
        Task task = entityManager.find(Task.class, idTask);

        if (task == null){
            throw new TaskNotDeletedException(idTask);
        }
        entityManager.remove(task);
    }

    @Override
    public void deleteTask(String nameTask) {
        List<Task> listTasks = entityManager.createQuery("SELECT tt FROM Task tt WHERE tt.name = :nameTask", Task.class).setParameter("nameTask", nameTask).getResultList();

        if (listTasks.size() == 0){
            throw new TaskNotDeletedException(nameTask);
        }

        for (Task t: listTasks) {
            entityManager.remove(t);
        }
    }

    //Получить задачи назначенные на Пользователя
    public List<Task> getTasksForMe(Long userId){
        List<Task> tasks = new ArrayList<>();
        User user = entityManager.find(User.class, userId);

        for (Task t: user.getTasksForMe()) {
            tasks.add(t);
        }
        return tasks;
    }

    public int getCountTaskByStatus(Task.Status status){
        int count;
        count = entityManager.createQuery("SELECT tt FROM Task tt WHERE tt.status = :status", Task.class).setParameter("status", status).getResultList().size();
        return count;
    }

    public Task getTaskById(Long taskId){
        Task task = entityManager.createQuery("SELECT tt FROM Task tt WHERE tt.id = :taskId", Task.class).setParameter("taskId", taskId).getSingleResult();
        return task;
    }

    public List<Task> getTaskByStatus(Task.Status status) {
        List<Task> listTasks;
        listTasks = entityManager.createQuery("SELECT tt FROM Task tt where tt.status = :status", Task.class).setParameter("status", status).getResultList();
        return listTasks;
    }
}
