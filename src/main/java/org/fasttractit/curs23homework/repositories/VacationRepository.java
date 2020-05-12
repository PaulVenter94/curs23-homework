package org.fasttractit.curs23homework.repositories;

import org.fasttractit.curs23homework.domain.Vacation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface VacationRepository extends JpaRepository<Vacation, Integer> {
    List<Vacation> findByLocationIgnoreCase(String location);

    List<Vacation> findByPriceLessThan(int price);

    void deleteById(int id);

    Vacation findById(int id);

    List<Vacation> findByLocationIgnoreCaseAndPriceLessThanEqual(String location, Integer price);


}
