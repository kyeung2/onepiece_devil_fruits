package nimbus.onepiece.devilfruits.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import nimbus.onepiece.devilfruits.domain.DevilFruit;
import nimbus.onepiece.devilfruits.persistence.InMemoryDevilFruitRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DevilFruitService {


    private final InMemoryDevilFruitRepository repository;

    public Mono<DevilFruit> getDevilFruit(@NonNull String code){
        return repository.find(code);
    }

    public Flux<DevilFruit> getAllDevilFruits(){
        return repository.findAll();
    }
}
