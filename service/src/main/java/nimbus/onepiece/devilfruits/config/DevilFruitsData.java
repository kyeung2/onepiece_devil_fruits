package nimbus.onepiece.devilfruits.config;

import lombok.Getter;
import lombok.Setter;
import nimbus.onepiece.devilfruits.domain.DevilFruitType;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

@ConfigurationProperties(prefix = "onepiece")
@Getter
@Setter
public class DevilFruitsData {

    private List<DevilFruitProperties> devilFruits;

    @Getter
    @Setter
    public static class DevilFruitProperties {
        private String code;
        private String name;
        private DevilFruitType type;
        private String ability;
    }
}
