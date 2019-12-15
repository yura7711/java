package com.geekbrains.server.repositories.specifications;

import com.geekbrains.server.entities.Task;
import org.springframework.data.jpa.domain.Specification;

public class TaskSpecifications {
    public static Specification<Task> executerEquals(Long executer_id) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("executor").get("userId"), executer_id);
    }

    public static Specification<Task> authorEquals(Long author_id) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("author").get("userId"), author_id);
    }

    public static Specification<Task> statusEquals(Integer statusId) {
        return (Specification<Task>) (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get("status"), Task.Status.getStatusById( statusId));
    }
}
