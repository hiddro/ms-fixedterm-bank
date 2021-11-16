package ms.fixedterm.bank.service.impl;

import ms.fixedterm.bank.documents.dto.Customer;
import ms.fixedterm.bank.documents.dto.CustomerDTO;
import ms.fixedterm.bank.documents.entities.FixedTermAccount;
import ms.fixedterm.bank.repository.FixedTermAccountRepository;
import ms.fixedterm.bank.service.ICustomerService;
import ms.fixedterm.bank.service.IFixedTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Service
public class FixedTermServiceImpl implements IFixedTermService {

    @Autowired
    private FixedTermAccountRepository fixedTermAccountRepository;

    @Autowired
    private ICustomerService customerService;

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
//        Mono<Customer> customer =

        return customerService.getCustomer(fixedTermAccount.getCustomerIdentityNumber()).flatMap(c -> {
            if(c.getId() == null){
                return Mono.empty();
            }
            fixedTermAccount.setTypeOfAccount("FIXEDTERM_ACCOUNT");
            fixedTermAccount.setAmount(0.0);
            fixedTermAccount.setCustomer(CustomerDTO.builder().name(c.getName())
                            .code(c.getCustomerType().getCode())
                            .customerIdentityNumber(c.getCustomerIdentityNumber()).build());

            return fixedTermAccountRepository.save(fixedTermAccount);
        });
//        return getCustomer(fixedTermAccount.getCustomerIdentityNumber())
//                .flatMap(customerd -> {
//                    fixedTermAccount.setTypeOfAccount("FIXEDTERM_ACCOUNT");
//                    fixedTermAccount.setAmount(0.0);
//                    fixedTermAccount.setCustomer(CustomerDTO.builder().name(customerd.getName())
//                            .code(customerd.getCustomerType().getCode())
//                            .customerIdentityNumber(customerd.getCustomerIdentityNumber()).build());
//
//                    return fixedTermAccountRepository.save(fixedTermAccount);
//                });
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

    @Override
    public Mono<FixedTermAccount> updateFixedTerm(String id, FixedTermAccount fixedTermAccount) {
        Mono<FixedTermAccount> fixedTermExist = fixedTermAccountRepository.findById(id);

        return fixedTermExist.flatMap(existFixedTerm -> {
            existFixedTerm.setTypeOfAccount(fixedTermAccount.getTypeOfAccount());
            existFixedTerm.setCustomerIdentityNumber(fixedTermAccount.getCustomerIdentityNumber());
            existFixedTerm.setAccountNumber(fixedTermAccount.getAccountNumber());
            existFixedTerm.setAmount(fixedTermAccount.getAmount());
            return fixedTermAccountRepository.save(existFixedTerm);
        }).switchIfEmpty(Mono.just(FixedTermAccount.builder().build()));
    }

    @Override
    public Mono<Void> deleteFixedTerm(String id) {
        return fixedTermAccountRepository.deleteById(id);
    }

}
