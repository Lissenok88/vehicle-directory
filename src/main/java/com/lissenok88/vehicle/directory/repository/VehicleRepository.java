package com.lissenok88.vehicle.directory.repository;

import com.lissenok88.vehicle.directory.error.NotFoundException;
import com.lissenok88.vehicle.directory.model.Vehicle;
import com.lissenok88.vehicle.directory.util.Util;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Hidden
@Repository
@Transactional(readOnly = true)
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM #{#entityName} e WHERE e.id=:id")
    int delete(long id);

    @Query("SELECT v FROM Vehicle v WHERE v.category =:category")
    List<Vehicle> findAllByCategory(String category);

    @Query("SELECT v FROM Vehicle v WHERE v.model =:model")
    List<Vehicle> findAllByModel(String model);

    @Query("SELECT v FROM Vehicle v WHERE v.stateNumber =:stateNumber")
    List<Vehicle> findSameStateNumber(String stateNumber);


    @Query("SELECT v FROM Vehicle v WHERE v.make =:make OR v.model =:model OR v.category =:category OR v.stateNumber =:stateNumber OR v.year =:year")
    List<Vehicle> findByFilter(String make, String model, String category, String stateNumber, Integer year);

    //  https://stackoverflow.com/a/60695301/548473 (existed delete code 204, not existed: 404)
    default void deleteExisted(long id) {
        if (delete(id) == 0) {
            throw new NotFoundException("Entity with id=" + id + " not found");
        }
    }

    default Vehicle getExisted(long id) {
        return Util.checkExist(id, findById(id));
    }
}
