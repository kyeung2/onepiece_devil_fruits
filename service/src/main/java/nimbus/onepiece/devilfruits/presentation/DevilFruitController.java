package nimbus.onepiece.devilfruits.presentation;


import nimbus.onepiece.devilfruits.interfaces.DevilFruit;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class DevilFruitController {

    @GetMapping
    public Mono<DevilFruit> getDevilFruit(){
        return Mono.just(new DevilFruit());
    }
}
