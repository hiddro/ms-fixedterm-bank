package ms.fixedterm.bank.service;

import ms.fixedterm.bank.documents.entities.FixedTermAccount;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface IFixedTermService extends ICrudService<FixedTermAccount, String>{

    Mono<FixedTermAccount> saveFixedTermAccount(FixedTermAccount fixedTermAccount);

    Flux<FixedTermAccount> getAllFixedTerm();

    Mono<FixedTermAccount> getByIdCustomer(String id);

    Mono<FixedTermAccount> getByIdNumber(String id);
}
