package com.nimbus.onepiece.devilfruits.service;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.nimbus.onepiece.devilfruits.domain.DevilFruit;
import com.nimbus.onepiece.devilfruits.persistence.InMemoryDevilFruitRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DevilFruitService {


    private final InMemoryDevilFruitRepository repository;

    public Optional<DevilFruit> getDevilFruit(@NonNull String code){
        return repository.find(code);
    }

    public Collection<DevilFruit> getAllDevilFruits(){
        return repository.findAll();
    }
}
