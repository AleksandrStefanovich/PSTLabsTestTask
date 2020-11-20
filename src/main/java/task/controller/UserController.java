package task.controller;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import task.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import task.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/users")
    public List<User> users(){
        return userService.readAll();
    }

    @GetMapping("/users/page")
    public Page<User> getPagedUsers(
            @PageableDefault(page = 0, size = 5)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "name", direction = Sort.Direction.DESC),
                    @SortDefault(sort = "id", direction = Sort.Direction.ASC)
            })
                    Pageable pageable) {
        return userService.findAllPage(pageable);
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id){
        return userService.findUserByID(id);
    }

    @PostMapping("/users")
    public User createUser(@RequestBody User user){
        return userService.saveNewUser(user);
    }

    @PutMapping("/users/{id}")
    public User updateUser(@PathVariable String id,
                                 @RequestBody User user){
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        boolean result = userService.delete(id);
        return result ? new ResponseEntity(HttpStatus.OK) : new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> validation(MethodArgumentNotValidException e){
        final Map<String,String> messages = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(objectError ->
                messages.put(((FieldError)objectError).getField(), objectError.getDefaultMessage())
        );
        return messages;
    }
}
