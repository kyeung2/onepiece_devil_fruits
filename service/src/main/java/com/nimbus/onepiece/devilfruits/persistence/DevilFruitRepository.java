package com.nimbus.onepiece.devilfruits.persistence;

import com.nimbus.onepiece.devilfruits.persistence.records.DevilFruitRecord;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class DevilFruitRepository {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    public Optional<DevilFruitRecord> findByCode(@NonNull String code) {

        MapSqlParameterSource params = new MapSqlParameterSource();
        params.addValue("code", code);
        DevilFruitRecord result = jdbcTemplate.queryForObject(
                "SELECT * FROM DEVIL_FRUITS WHERE code = :code",
                params,
                mapToRecord());

        return Optional.ofNullable(result);
    }

    public Collection<DevilFruitRecord> findAll() {
        MapSqlParameterSource params = new MapSqlParameterSource();
        return jdbcTemplate.query("SELECT * FROM DEVIL_FRUITS", params, mapToRecord());
    }

    private static RowMapper<DevilFruitRecord> mapToRecord() {
        return (rs, _) -> DevilFruitRecord.builder()
                .id(UUID.fromString(rs.getString("id")))
                .code(rs.getString("code"))
                .name(rs.getString("name"))
                .type(rs.getString("type"))
                .ability(rs.getString("ability"))
                .build();
    }
}
