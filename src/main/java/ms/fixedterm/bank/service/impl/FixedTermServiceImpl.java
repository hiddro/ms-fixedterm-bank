package ms.fixedterm.bank.service.impl;

import ms.fixedterm.bank.documents.entities.FixedTermAccount;
import ms.fixedterm.bank.repository.FixedTermAccountRepository;
import ms.fixedterm.bank.service.IFixedTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class FixedTermServiceImpl implements IFixedTermService {

    @Autowired
    private FixedTermAccountRepository fixedTermAccountRepository;

    @Override
    public Mono<FixedTermAccount> create(FixedTermAccount o) {
        return fixedTermAccountRepository.save(o);
    }

    @Override
    public Flux<FixedTermAccount> findAll() {
        return fixedTermAccountRepository.findAll();
    }

    @Override
    public Mono<FixedTermAccount> findById(String s) {
        return fixedTermAccountRepository.findById(s);
    }

    @Override
    public Mono<FixedTermAccount> update(FixedTermAccount o) {
        return fixedTermAccountRepository.save(o);
    }

    @Override
    public Mono<Void> delete(FixedTermAccount o) {
        return fixedTermAccountRepository.delete(o);
    }

    @Override
    public Mono<FixedTermAccount> saveFixedTermAccount(FixedTermAccount fixedTermAccount) {
        return null;
    }

    @Override
    public Flux<FixedTermAccount> getAllFixedTerm() {
        return fixedTermAccountRepository.findAll();
    }

    @Override
    public Mono<FixedTermAccount> getByIdCustomer(String id) {
        return fixedTermAccountRepository.findByCustomerIdentityNumber(id);
    }

    @Override
    public Mono<FixedTermAccount> getByIdNumber(String id) {
        return fixedTermAccountRepository.findByAccountNumber(id);
    }
}
