package ms.fixedterm.bank.handler;

import ms.fixedterm.bank.documents.entities.FixedTermAccount;
import ms.fixedterm.bank.service.IFixedTermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class FixedTermAccountHandler {

    @Autowired
    private IFixedTermService fixedTermService;

    public Mono<ServerResponse> findAll(ServerRequest request){
        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fixedTermService.getAllFixedTerm(), FixedTermAccount.class)
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> findByCustomerIdentityNumber(ServerRequest request){

        String id = request.pathVariable("customerIdentityNumber");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fixedTermService.getByIdCustomer(id), FixedTermAccount.class)
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> findByAccountNumber(ServerRequest request){

        String id = request.pathVariable("accountNumber");

        return ServerResponse.ok().contentType(MediaType.APPLICATION_JSON)
                .body(fixedTermService.getByIdNumber(id), FixedTermAccount.class)
                .switchIfEmpty(ServerResponse.badRequest().build());
    }

    public Mono<ServerResponse> newFixedTermAccount(ServerRequest request){

        Mono<FixedTermAccount> fixedTermAccountMono = request.bodyToMono(FixedTermAccount.class);

        return fixedTermAccountMono.flatMap(fixedterm -> fixedTermService.saveFixedTermAccount(fixedterm))
                .flatMap(c -> ServerResponse
                        .ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(BodyInserters.fromValue(c))
                        .switchIfEmpty(ServerResponse.badRequest().build()));
    }

}
