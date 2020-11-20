package task.service;

import task.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.repository.AccountRepository;

import java.util.List;

@Service
public class AccountService {
    @Autowired
    AccountRepository repository;

    public List<Account> readAll() {
        return repository.findAll();
    }

    public Account findAccountByID(String id) {
        return repository.findById(id).orElse(null);
    }

    public Account saveNewAccount(Account account) {
        return repository.save(account);
    }

    public Account updateAccount(String id, Account account) {
        Account savedAccount = repository.findById(id).orElseThrow();
        if(savedAccount.equals(account)){
            return savedAccount;
        }
        if (savedAccount.getStatus().equals(account.getStatus())){
            savedAccount.setStatus(account.getStatus());
            repository.save(savedAccount);
        }
        return  repository.findById(id).orElseThrow();
    }

    public boolean delete(String id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }else{
            return false;
        }
    }
}
