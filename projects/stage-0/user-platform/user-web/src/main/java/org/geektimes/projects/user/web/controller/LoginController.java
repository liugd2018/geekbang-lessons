package org.geektimes.projects.user.web.controller;

import org.geektimes.projects.user.service.UserService;
import org.geektimes.projects.user.service.impl.UserServiceImpl;
import org.geektimes.web.mvc.controller.PageController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * TODO
 *
 * @author <a href="mailto:liugd2020@gmail.com">liuguodong</a>
 * @since 1.0
 */
@Path("/user")
public class LoginController implements PageController {

    @POST
    @Path("/login")
    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws Throwable {

        UserService userService = new UserServiceImpl();

        userService.queryUserByNameAndPassword(request.getParameter("name"),request.getParameter("password"));

//        if (userService.register(user)){
//            return "success.jsp";
//        } else {
//            return "fail.jsp";
//        }

        return null;

    }


}
