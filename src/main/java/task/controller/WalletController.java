package task.controller;

import task.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import task.service.WalletService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class WalletController {
    @Autowired
    WalletService walletService;

    @GetMapping("/wallets")
    public List<Wallet> wallets(){
        return walletService.readAll();
    }

    @GetMapping("/wallets/{id}")
    public Wallet getWallet(@PathVariable String id){
        return walletService.findWalletByID(id);
    }

    @PostMapping("/wallets")
    public Wallet createWallet(@RequestBody Wallet wallet){
        return walletService.saveNewWallet(wallet);
    }

    @PutMapping("/wallets/{id}")
    public Wallet updateWallet(@PathVariable String id,
                           @RequestBody Wallet wallet){
        return walletService.updateWallet(id, wallet);
    }

    @DeleteMapping("/wallets/{id}")
    public ResponseEntity deleteWallet(@PathVariable String id){
        boolean result = walletService.delete(id);
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
