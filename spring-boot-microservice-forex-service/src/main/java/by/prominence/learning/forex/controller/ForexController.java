package by.prominence.learning.forex.controller;

import by.prominence.learning.forex.model.ExchangeValue;
import by.prominence.learning.forex.repository.ExchangeValueRepository;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ForexController {

    private final Environment environment;

    private final ExchangeValueRepository repository;

    public ForexController(Environment environment, ExchangeValueRepository repository) {
        this.environment = environment;
        this.repository = repository;
    }

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = repository.findByFromAndTo(from, to);

        // need to run application with -Dserver.port=<port> VM option
        exchangeValue.setPort(Integer.parseInt(environment.getProperty("local.server.port")));

        return exchangeValue;
    }
}