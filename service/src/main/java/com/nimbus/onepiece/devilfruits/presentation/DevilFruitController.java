package com.nimbus.onepiece.devilfruits.presentation;


import com.nimbus.onepiece.devilfruits.domain.DevilFruit;
import com.nimbus.onepiece.devilfruits.interfaces.dto.DevilFruitDto;
import com.nimbus.onepiece.devilfruits.interfaces.dto.DevilFruitTypeDto;
import com.nimbus.onepiece.devilfruits.service.DevilFruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.function.Function;

@RestController
@RequestMapping("/devilfruits")
@RequiredArgsConstructor
public class DevilFruitController {

    private final DevilFruitService service;

    @GetMapping(path = "/{code}")
    public DevilFruitDto getDevilFruit(@PathVariable String code) {
        return service.getDevilFruit(code)
                .map(mapToDto())
                .orElse(null);
    }

    @GetMapping
    public Collection<DevilFruitDto> getAllDevilFruits() {
        return service.getAllDevilFruits()
                .stream()
                .map(mapToDto())
                .toList();
    }

    private static Function<DevilFruit, DevilFruitDto> mapToDto() {
        return domain -> DevilFruitDto.builder()
                .code(domain.code())
                .name(domain.name())
                .type(DevilFruitTypeDto.valueOf(domain.type().name()))
                .ability(domain.ability())
                .build();
    }


}
