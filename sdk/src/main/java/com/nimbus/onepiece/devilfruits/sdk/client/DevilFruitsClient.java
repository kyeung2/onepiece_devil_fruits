package com.nimbus.onepiece.devilfruits.sdk.client;

import com.nimbus.onepiece.devilfruits.interfaces.dto.DevilFruitDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DevilFruitsClient {

    Mono<DevilFruitDto> getDevilFruit(String code);

    Flux<DevilFruitDto> getAllDevilFruits();
}
