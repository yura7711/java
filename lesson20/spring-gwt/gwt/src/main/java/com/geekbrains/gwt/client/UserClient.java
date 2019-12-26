package com.geekbrains.gwt.client;

import com.geekbrains.gwt.common.UserDto;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.RestService;

import javax.ws.rs.*;
import java.util.List;

@Path("/api/v1/users")
public interface UserClient extends RestService {
    @GET
    void getUsers(@HeaderParam("Authorization") String token
            , MethodCallback<List<UserDto>> statuses
    );
}
