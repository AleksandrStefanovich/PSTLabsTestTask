package task.controller;

import task.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import task.service.RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/roles")
    public List<Role> roles(){
        return roleService.readAll();
    }

    @GetMapping("/roles/{id}")
    public Role getRole(@PathVariable String id){
        return roleService.findRoleByID(id);
    }

    @PostMapping("/roles")
    public Role createRole(@RequestBody Role role){
        return roleService.saveNewRole(role);
    }

    @PutMapping("/roles/{id}")
    public Role updateRole(@PathVariable String id,
                           @RequestBody Role role){
        return roleService.updateRole(id, role);
    }

    @DeleteMapping("/roles/{id}")
    public ResponseEntity deleteRole(@PathVariable String id){
        boolean result = roleService.delete(id);
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
