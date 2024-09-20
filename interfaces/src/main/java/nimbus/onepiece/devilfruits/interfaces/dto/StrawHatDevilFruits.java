package nimbus.onepiece.devilfruits.interfaces.dto;

import lombok.experimental.UtilityClass;

import java.util.Set;

@UtilityClass
public class StrawHatDevilFruits {

    public static final DevilFruitDto GOMU_GOMU_NO_MI = DevilFruitDto.builder()
            .code("DF0001")
            .name("Gomu Gomu no Mi")
            .type(DevilFruitTypeDto.PARAMECIA)
            .ability("Grants the user's body rubber-like properties")
            // this would be a database entry. How would this ensure integrity?
            .build();

    public static final DevilFruitDto HITO_HITO_NO_MI = DevilFruitDto.builder()
            .code("DF0011")
            .name("Hito Hito no Mi")
            .type(DevilFruitTypeDto.ZOAN)
            .ability("Allows the user to transform into a human or human hybrid")
            .build();

    public static final DevilFruitDto HANA_HANA_NO_MI = DevilFruitDto.builder()
            .code("DF0020")
            .name("Hana Hana no Mi")
            .type(DevilFruitTypeDto.PARAMECIA)
            .ability("Allows the user to sprout replicas of their body parts on any surface")
            .build();

    public static final DevilFruitDto YOMI_YOMI_NO_MI = DevilFruitDto.builder()
            .code("DF0021")
            .name("Yomi Yomi no Mi")
            .type(DevilFruitTypeDto.PARAMECIA)
            .ability("Grants the user resurrection and soul-based abilities")
            .build();
}
