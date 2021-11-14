package ms.fixedterm.bank.config;

import ms.fixedterm.bank.handler.FixedTermAccountHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class RouterConfig {

    @Bean
    public RouterFunction<ServerResponse> routes(FixedTermAccountHandler fixedTermAccountHandler){

        return route(GET("/getAll"), fixedTermAccountHandler::findAll)
                .andRoute(GET("/getByIdCustomer/{customerIdentityNumber}"), fixedTermAccountHandler::findByCustomerIdentityNumber)
                .andRoute(GET("/getByIdNumber/{accountNumber}"), fixedTermAccountHandler::findByAccountNumber)
                .andRoute(POST("/create"), fixedTermAccountHandler::newFixedTermAccount);
//                .andRoute(PUT("/update/{id}"), fixedTermAccountHandler::updateFixedTermAccound)
//                .andRoute(DELETE("/delete/{id}"), fixedTermAccountHandler::deleteFixedTermAccound);

    }
}
