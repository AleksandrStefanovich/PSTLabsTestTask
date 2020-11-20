package task.service;

import task.model.Wallet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.repository.WalletRepository;

import java.util.List;

@Service
public class WalletService {
    @Autowired
    WalletRepository repository;

    public List<Wallet> readAll() {
        return repository.findAll();
    }

    public Wallet findWalletByID(String id) {
        return repository.findById(id).orElse(null);
    }

    public Wallet saveNewWallet(Wallet wallet) {
        return repository.save(wallet);
    }

    public Wallet updateWallet(String id, Wallet wallet) {
        Wallet savedWallet = repository.findById(id).orElseThrow();
        if(savedWallet.equals(wallet)){
            return savedWallet;
        }
        if (savedWallet.getBalance() != wallet.getBalance()){
            savedWallet.setBalance(wallet.getBalance());
            repository.save(savedWallet);
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
