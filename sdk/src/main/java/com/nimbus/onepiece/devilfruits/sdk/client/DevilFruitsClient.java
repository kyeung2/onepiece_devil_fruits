package com.nimbus.onepiece.devilfruits.sdk.client;

import com.nimbus.onepiece.devilfruits.interfaces.dto.DevilFruitDto;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface DevilFruitsClient {

    /// **Retrieves a `DevilFruitDto` for the given code.**
    ///
    /// Performs a non-blocking HTTP GET request to fetch the devil fruit data associated with the specified code.
    ///
    /// #### Parameters
    /// - `code`: *(String)* The unique code representing the devil fruit. e.g. `DF0001`
    ///
    /// #### Returns
    /// - A `Mono<DevilFruitDto>` emitting the devil fruit data when available.
    ///
    /// #### Exceptions
    /// - `DevilFruitsClientException`: Emitted when a client error occurs (HTTP status 4xx).
    /// - `DevilFruitsServerException`: Emitted when a server error occurs (HTTP status 5xx).
    /// - `DevilFruitsGenericException`: Emitted for other errors such as network issues or unexpected status codes.
    ///
    /// #### Example Usage
    /// ```java
     /// client.getDevilFruit("DF0001")
     ///     .subscribe(
     ///         devilFruit -> {},
     ///         error -> {}
     ///);
     ///```
    Mono<DevilFruitDto> getDevilFruit(String code);


    /// **Retrieves all available `DevilFruitDto` objects.**
    ///
    /// Performs a non-blocking HTTP GET request to fetch the list of all devil fruits.
    ///
    /// #### Returns
    /// - A `Flux<DevilFruitDto>` emitting each devil fruit data item when available.
    ///
    /// #### Exceptions
    /// - `DevilFruitsClientException`: Emitted when a client error occurs (HTTP status 4xx).
    /// - `DevilFruitsServerException`: Emitted when a server error occurs (HTTP status 5xx).
    /// - `DevilFruitsGenericException`: Emitted for other errors such as network issues or unexpected status codes.
    ///
    /// #### Example Usage
    /// ```java
     /// client.getAllFruits()
     ///     .subscribe(
     ///         devilFruit -> {},
     ///         error -> {}
     ///);
     ///```
    Flux<DevilFruitDto> getAllDevilFruits();
}
