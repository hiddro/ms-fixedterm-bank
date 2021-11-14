package ms.fixedterm.bank.repository;

import ms.fixedterm.bank.documents.entities.FixedTermAccount;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface FixedTermAccountRepository extends ReactiveMongoRepository<FixedTermAccount, String> {

    Mono<FixedTermAccount> findByCustomerIdentityNumber(String customerIdentityNumber);

    Mono<FixedTermAccount> findByAccountNumber(String accountNumber);
}
