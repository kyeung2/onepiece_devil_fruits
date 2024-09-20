package nimbus.onepiece.devilfruits.presentation;


import lombok.RequiredArgsConstructor;
import nimbus.onepiece.devilfruits.domain.DevilFruit;
import nimbus.onepiece.devilfruits.interfaces.dto.DevilFruitDto;
import nimbus.onepiece.devilfruits.interfaces.dto.DevilFruitTypeDto;
import nimbus.onepiece.devilfruits.service.DevilFruitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@RestController
@RequestMapping("/devilfruits")
@RequiredArgsConstructor
public class DevilFruitController {

    private final DevilFruitService service;

    @GetMapping(params = "code")
    public Mono<DevilFruitDto> getDevilFruit(@RequestParam String code) {
        return service.getDevilFruit(code)
                .map(mapDto());
    }

    @GetMapping
    public Flux<DevilFruitDto> getAllDevilFruits() {
        return service.getAllDevilFruits()
                .map(mapDto());
    }

    private static Function<DevilFruit, DevilFruitDto> mapDto() {
        return domain -> DevilFruitDto.builder()
                .code(domain.code())
                .name(domain.name())
                .type(DevilFruitTypeDto.valueOf(domain.type().name()))
                .ability(domain.ability())
                .build();
    }


}
