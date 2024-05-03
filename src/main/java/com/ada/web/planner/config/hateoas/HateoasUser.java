package com.ada.web.planner.config.hateoas;

import com.ada.web.planner.controller.PlannerController;
import com.ada.web.planner.controller.UserController;
import com.ada.web.planner.core.model.User;
import com.ada.web.planner.dto.user.LoginRequestDto;
import com.ada.web.planner.dto.user.UpdateUserRequestDTO;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HateoasUser {

    public User hrefUser(User user) {
        return user.add(relSelfReadLink(), relSelfDeleteLink(), relUpdateLink());
    }

    public Link relSelfReadLink() {
        return linkTo(methodOn(UserController.class).readUser())
                .withRel("self")
                .withType(RequestMethod.GET.name());
    }

    public Link relSelfDeleteLink() {
        return linkTo(methodOn(UserController.class).deleteUser())
                .withRel("self")
                .withType(RequestMethod.DELETE.name());
    }

    public Link relUpdateLink() {
        return linkTo(methodOn(UserController.class).updateUser(new UpdateUserRequestDTO("New password in http body")))
                .withRel("self")
                .withType(RequestMethod.PUT.name());
    }

    public Link relSelfLoginLink(){
        return linkTo(methodOn(PlannerController.class).authorizeLogin(new LoginRequestDto("Email in http body", "Password in http body")))
                .withRel("self")
                .withType(RequestMethod.POST.name());
    }
}
