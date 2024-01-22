package com.example.demo.controllers;

import com.example.demo.model.User;
import com.example.demo.service.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@Log
@AllArgsConstructor
public class UserController {
    private final UserService userService;


    /**
     * Endpoint for HTTP GET requests "/users".
     * <p>
     * This method is responsible for processing GET requests to retrieve a list of users.
     * It logs information about handling the "/users" request and fetches the list of users
     * from the UserService. The retrieved user list is then added to the Model for rendering
     * in the user-list view. Finally, it returns the view name "user-list" to be displayed.
     *
     * @param model The Spring Model object for passing data to the view.
     * @return The name of the view template ("user-list") to be rendered.
     */
    @GetMapping("/users")
    public String findAll(Model model) {
        log.info("Get /users request");
        List<User> users = userService.findAll();
        model.addAttribute("users", users);
        return "user-list";
    }


    /**
     * Endpoint for HTTP GET requests "/user-create".
     * <p>
     * This method is responsible for processing GET requests to display the user creation form.
     * It logs information about handling the "/user-create" request and returns the view name
     * "user-create" where the user creation form is rendered.
     *
     * @param user An optional User object that may be provided as a model attribute.
     * @return The name of the view template ("user-create") to be rendered.
     */
    @GetMapping("/user-create")
    public String createUserForm(User user) {
        log.info("Get /users create");
        return "user-create";
    }


    /**
     * Endpoint for HTTP POST requests "/user-create".
     * <p>
     * This method is responsible for processing POST requests to create a new user.
     * It logs information about handling the POST request for user creation, saves the
     * provided User object using the UserService, and then redirects the client to the
     * "/users" endpoint to view the updated list of users.
     *
     * @param user The User object containing information for the new user.
     * @return A redirect instruction to navigate to the "/users" endpoint.
     */
    @PostMapping("/user-create")
    public String createUser(User user) {
        log.info("Post /users create");
        userService.saveUser(user);
        return "redirect:/users";
    }


    /**
     * Endpoint for HTTP GET requests "/user-delete/{id}".
     * <p>
     * This method is responsible for processing GET requests to delete a user with the
     * specified ID. It logs information about handling the "/user-delete/{id}" request,
     * deletes the user using the UserService, and redirects the client to the "/users"
     * endpoint to view the updated list of users.
     *
     * @param id The ID of the user to be deleted, extracted from the path variable.
     * @return A redirect instruction to navigate to the "/users" endpoint.
     */
    @GetMapping("user-delete/{id}")
    public String deleteUser(@PathVariable("id") int id) {
        log.info("Get /user-delete/{id}");
        userService.deleteById(id);
        return "redirect:/users";
    }


    /**
     * Endpoint for HTTP GET requests "/user-update/{id}".
     * <p>
     * This method is responsible for processing GET requests to display the user update form
     * for a user with the specified ID. It logs information about handling the "/user-update/{id}"
     * request, retrieves the user details using the UserService, adds the user information to
     * the Model for rendering in the user-update view, and returns the view name "user-update".
     *
     * @param id    The ID of the user to be updated, extracted from the path variable.
     * @param model The Spring Model object for passing data to the view.
     * @return The name of the view template ("user-update") to be rendered.
     */
    @GetMapping("/user-update/{id}")
    public String updateUserForm(@PathVariable("id") int id, Model model) {
        log.info("Get /user-update/{id}");
        User user = userService.getOne(id);
        model.addAttribute("user", user);
        return "user-update";
    }


    /**
     * Endpoint for HTTP POST requests "/user-update".
     * <p>
     * This method is responsible for processing POST requests to update an existing user.
     * It logs information about handling the POST request for user update, updates the user
     * information using the UserService, and redirects the client to the "/users" endpoint to
     * view the updated list of users.
     *
     * @param user The User object containing updated information for the existing user.
     * @return A redirect instruction to navigate to the "/users" endpoint.
     */
    @PostMapping("/user-update")
    public String updateUser(@ModelAttribute("user") User user) {
        log.info("Post /user-update");
        userService.update(user);
        return "redirect:/users";
    }

}
