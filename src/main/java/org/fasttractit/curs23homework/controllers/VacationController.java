package org.fasttractit.curs23homework.controllers;

import org.fasttractit.curs23homework.domain.Vacation;
import org.fasttractit.curs23homework.repositories.VacationRepository;
import org.fasttractit.curs23homework.services.VacationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("vacations")
public class VacationController {
    private final VacationService vacationService;

    public VacationController(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @GetMapping
    public List<Vacation> getVacations(@RequestParam(required = false) String location,
                                       @RequestParam(required = false) Integer price) {
        return vacationService.getAll(location, price);
    }

    @GetMapping("/{id}")
    public Vacation getById(@PathVariable int id) {
        return vacationService.getById(id);
    }

    @PostMapping
    public Vacation add(@RequestBody Vacation vacation) {
        return vacationService.add(vacation);
    }

    @DeleteMapping
    public void delete(@RequestBody Integer id) {
        vacationService.deleteById(id);
    }

    @PutMapping("/{id}")
    public Vacation patchVacation(@PathVariable int id, @RequestBody Vacation vacation) {
        return vacationService.replace(id, vacation);
    }
}
