package org.fasttractit.curs23homework.bootstrap;

import org.fasttractit.curs23homework.domain.Vacation;
import org.fasttractit.curs23homework.services.VacationService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {
    VacationService vacationService;

    public DataLoader(VacationService vacationService) {
        this.vacationService = vacationService;
    }

    @Override
    public void run(String... args) throws Exception {
        loadVacations();
        new Menu().run();
    }

    private void loadVacations() {
        vacationService.add(new Vacation("Maldive", 2000, 10));
        vacationService.add(new Vacation("Bora Bora", 7000, 14));
        vacationService.add(new Vacation("Mamaia", 8000, 7));
        vacationService.add(new Vacation("Maldive", 3000, 14));
        vacationService.add(new Vacation("Mamaia", 2000, 7));
        vacationService.add(new Vacation("Lefkada", 1000, 11));
        vacationService.add(new Vacation("Costinesti", 1000, 7));
        vacationService.add(new Vacation("Maldive", 5000, 10));
    }
}
