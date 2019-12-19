package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.StatusDto;
import com.geekbrains.gwt.common.TaskAddDto;
import com.geekbrains.gwt.common.TaskDto;
import com.geekbrains.gwt.common.UserDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/api/v1/tasks")
public interface TasksClient extends RestService {
    @GET
    void getAllTasks(@QueryParam("statusId") Integer statusId
            ,@QueryParam("executer_id") Long executer_id
            ,@QueryParam("author_id") Long author_id
            ,MethodCallback<List<TaskDto>> tasks
    );

    @GET
    @Path("/statuses")
    void getStatuses(MethodCallback<List<StatusDto>> statuses);

    @GET
    @Path("/users")
    void getUsers(MethodCallback<List<UserDto>> statuses);

    @DELETE
    @Path("/remove/{id}")
    void removeTask(@PathParam("id") String id, MethodCallback<Void> result);

    @POST
    @Path("/add")
    void createTask(@BeanParam() TaskAddDto taskAddDto, MethodCallback<Void> result);
}
