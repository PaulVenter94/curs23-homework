package org.fasttractit.curs23homework.services;

import org.fasttractit.curs23homework.domain.Vacation;
import org.fasttractit.curs23homework.repositories.VacationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacationService {
    VacationRepository vacationRepository;

    public VacationService(VacationRepository vacationRepository) {
        this.vacationRepository = vacationRepository;
    }

    public List<Vacation> getAll(String location, Integer price) {
        if (location == null && price == null) {
            return vacationRepository.findAll();
        } else if (location != null && price == null) {
            return vacationRepository.findByLocationIgnoreCase(location);
        } else if (price != null && location == null) {
            return vacationRepository.findByPriceLessThan(price);
        } else {
            return vacationRepository.findByLocationIgnoreCaseAndPriceLessThanEqual(location, price);
        }
    }

    public Vacation getById(int id) {
        return vacationRepository.findById(id);
    }

    public List<Vacation> getLowerPrice(int price) {
        return vacationRepository.findByPriceLessThan(price);
    }

    public Vacation add(Vacation vacation) {
        System.out.println("saved" + vacation);
        return vacationRepository.save(vacation);
    }

    public void deleteById(int id) {
        vacationRepository.deleteById(id);
    }

    public void delete(Vacation vacation) {
        vacationRepository.delete(vacation);
    }

    public Vacation replace(int id, Vacation vacation) {
        vacation.setId(id);
        return vacationRepository.save(vacation);
    }
}
