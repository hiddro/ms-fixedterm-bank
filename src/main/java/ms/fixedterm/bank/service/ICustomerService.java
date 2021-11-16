package ms.fixedterm.bank.service;

import ms.fixedterm.bank.documents.dto.Customer;
import reactor.core.publisher.Mono;

public interface ICustomerService {

    Mono<Customer> getCustomer(String customerIdentityNumber);
}
