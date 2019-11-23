package com.geekbrains.training.lesson11;

import com.geekbrains.training.lesson11.entities.Task;
import com.geekbrains.training.lesson11.services.TaskService;

public class MainApp {
    public static void main(String[] args) {
        TaskService myTaskService = new TaskService();
        myTaskService.prepare();
        myTaskService.addTask(1L, "Выполнить обзвон Клиентов часть 1", 1L, 1L, "Выполнить обзвон Клиентов по списку (первая часть)");
        myTaskService.addTask(1L, "Выполнить", 1L, 1L, "Выполнить");
        myTaskService.updateTaskStatus(1L, Task.Status.inWork);
        myTaskService.addTask(2L, "Выполнить обзвон Клиентов часть 1", 1L, 1L, "Выполнить обзвон Клиентов по списку (первая часть)");
        myTaskService.addTask(3L, "Выполнить обзвон Клиентов часть 2", 1L, 2L, "Выполнить обзвон Клиентов по списку (вторая часть)");
        myTaskService.addTask(4L, "Отправить письмо", 3L, 3L, "Предложить КП по интернету Клиенту 1");
        myTaskService.addTask(5L, "Сходить на встречу с Клиентом", 3L, 3L, "Обсудить КП по интернету");
        myTaskService.addTask(6L, "Выполнить обзвон Клиентов часть 3", 1L, 3L, "Выполнить обзвон Клиентов по списку (третья часть)");
        myTaskService.addTask(7L, "Покормить кота", 4L, 4L, "Покормить Мурзика");
        myTaskService.addTask(16L, "Оформить командировку", 5L, 5L, "Оформить командировку в Пермь");
        myTaskService.updateTaskStatus(16L, Task.Status.inWork);
        myTaskService.addTask(17L, "Создать домен", 5L, 1L, "Создать новый домен для Клиента 2");
        myTaskService.addTask(18L, "Заключить договор", 4L, 1L, "Заключить договор с Клиентом 3");
        myTaskService.addTask(19L, "Расторгнуть договор", 5L, 1L, "Расторгнуть договор с Клиентом 4");
        myTaskService.updateTaskStatus(19L, Task.Status.closed);
        myTaskService.addTask(20L, "Реализовать приостановление договоров", 5L, 1L, "Реализовать визард приостановления договора");
        myTaskService.updateTaskStatus(20L, Task.Status.rejected);
        myTaskService.addTask(21L, "Оформить командировку", 5L, 3L, "Оформить командировку в СПБ");
        myTaskService.addTask(22L, "Покормить кота", 5L, 5L, "Покормить Мурзика");
        myTaskService.addTask(23L, "Отправить письмо", 3L, 3L, "Предложить КП по интернету Клиенту 1");
        myTaskService.deleteTask(23L);
        myTaskService.deleteTask("Отправить письмо");

        System.out.println(">>>Получение списка задач по выбранному статусу");
        for (Task o : myTaskService.getTaskByStatus(Task.Status.inWork)) {
            System.out.println(o);
        }

        System.out.println(">>>Проверка наличия задачи с указанным ID");
        System.out.println(myTaskService.checkTaskById(3L));

        //Получение списка задач в отсортированном по статусу виде: открыта, в работе, закрыта
        // (можете выбирать любой статус и любой порядок, главное чтобы было 3 разных статуса);
        System.out.println(">>>Получение списка задач в отсортированном по статусу виде");
        for (Task o : myTaskService.getSortTaskByStatus()) {
            System.out.println(o);
        }

        System.out.println(">>>Подсчет количества задач по определенному статусу");
        System.out.println(myTaskService.getCountTaskByStatus(Task.Status.created));

        System.out.println(">>>Список задач назначенных на пользователя");
        for (Task t: myTaskService.getTasksForMe(5L)) {
            System.out.println(t);
        }

        myTaskService.shutdown();
    }
}
