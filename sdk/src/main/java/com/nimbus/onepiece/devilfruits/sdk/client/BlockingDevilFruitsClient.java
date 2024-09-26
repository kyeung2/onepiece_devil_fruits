package com.nimbus.onepiece.devilfruits.sdk.client;

import com.nimbus.onepiece.devilfruits.sdk.dto.DevilFruitDto;

import java.util.Collection;
import java.util.Optional;

public interface BlockingDevilFruitsClient {

    Optional<DevilFruitDto> getDevilFruit(String code);

    Collection<DevilFruitDto> getAllDevilFruits();
}
