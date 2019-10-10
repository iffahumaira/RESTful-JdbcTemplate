package com.petStore.PetStore.repository;

import com.petStore.PetStore.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PetRepositoryImpl implements  PetRepository{

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int register(Pet pet) {
        return jdbcTemplate.update(
                "insert into pets (name, type, gender, price, color) values(?,?,?,?,?)",
                pet.getName(), pet.getType(), pet.getGender(), pet.getPrice(), pet.getColor());
    }

    @Override
    public int update(Pet pet) {
        return jdbcTemplate.update(
                "update pets set name = ?, type = ?, gender = ?, price = ?, color = ? where id = ?",
                pet.getName(), pet.getType(), pet.getGender(), pet.getPrice(), pet.getColor(), pet.getId());
    }

    @Override
    public int deleteById(Integer id) {
        return jdbcTemplate.update(
                "delete from pets where id = ?", id);
    }

    @Override
    public List<Pet> findAll() {
        return jdbcTemplate.query(
                "select * from pets",
                (rs, rowNum) ->
                        new Pet(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("type"),
                                rs.getString("gender"),
                                rs.getDouble("price"),
                                rs.getString("color")
                        )
        );
    }

    @Override
    public Optional<Pet> findById(Integer id) {
        return jdbcTemplate.queryForObject(
                "select * from pets where id = ?",
                new Object[]{id},
                (rs, rowNum) ->
                        Optional.of(new Pet(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getString("type"),
                                rs.getString("gender"),
                                rs.getDouble("price"),
                                rs.getString("color")
                        ))
        );
    }
}
