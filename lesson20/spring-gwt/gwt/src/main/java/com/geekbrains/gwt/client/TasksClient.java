package com.geekbrains.gwt.client;

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
    void getAllTasks(@HeaderParam("Authorization") String token
            ,@QueryParam("statusId") Integer statusId
            ,@QueryParam("executer_id") Long executer_id
            ,@QueryParam("author_id") Long author_id
            ,MethodCallback<List<TaskDto>> tasks
    );

    @GET
    @Path("/{id}")
    void getTask(@HeaderParam("Authorization") String token, @PathParam("id") String id, MethodCallback<TaskDto> result);

    @GET
    @Path("/statuses")
    void getStatuses(@HeaderParam("Authorization") String token
            ,MethodCallback<List<TaskDto.StatusDto>> statuses
    );

    @DELETE
    @Path("/remove/{id}")
    void removeTask(@HeaderParam("Authorization") String token
            ,@PathParam("id") String id, MethodCallback<Void> result
    );

    @POST
    @Path("/add")
    void createTask(@HeaderParam("Authorization") String token, @BeanParam() TaskAddDto taskAddDto, MethodCallback<Void> result);

    @POST
    @Path("/update")
    void updateTask(@HeaderParam("Authorization") String token, @BeanParam() TaskAddDto taskAddDto, MethodCallback<Void> result);
}
