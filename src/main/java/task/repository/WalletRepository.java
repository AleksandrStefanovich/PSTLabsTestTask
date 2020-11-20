package task.repository;

import task.model.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalletRepository extends JpaRepository<Wallet, String> {
}
