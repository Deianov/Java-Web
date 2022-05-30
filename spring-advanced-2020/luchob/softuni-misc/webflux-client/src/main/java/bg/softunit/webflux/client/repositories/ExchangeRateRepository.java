package bg.softunit.webflux.client.repositories;

import bg.softunit.webflux.client.domain.ExchangeRate;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface ExchangeRateRepository extends ReactiveMongoRepository<ExchangeRate, String> {

}
