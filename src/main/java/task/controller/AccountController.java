package task.controller;

import task.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import task.service.AccountService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AccountController {
    @Autowired
    AccountService accountService;

    @GetMapping("/accounts")
    public List<Account> accounts(){
        return accountService.readAll();
    }

    @GetMapping("/accounts/{id}")
    public Account getAccount(@PathVariable String id){
        return accountService.findAccountByID(id);
    }

    @PostMapping("/accounts")
    public Account createAccount(@RequestBody Account account){
        return accountService.saveNewAccount(account);
    }

    @PutMapping("/accounts/{id}")
    public Account updateAccount(@PathVariable String id,
                           @RequestBody Account account){
        return accountService.updateAccount(id, account);
    }

    @DeleteMapping("/accounts/{id}")
    public ResponseEntity deleteAccount(@PathVariable String id){
        boolean result = accountService.delete(id);
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
