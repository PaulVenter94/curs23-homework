package org.fasttractit.curs23homework.bootstrap;

import org.fasttractit.curs23homework.domain.Vacation;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.SecureCacheResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

@Component
public class Menu {
    private final RestTemplate rest;

    public Menu() {
        this.rest = new RestTemplate();
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        int input;
        do {
            printMenu();
            System.out.println("Please select input: ");
            input = scanner.nextInt();
            doAction(input);
        } while (input != 7);
    }

    private void doAction(int input) {
        switch (input) {
            case 1:
                System.out.println(getAllVacations());
                break;
            case 2:
                System.out.println(getVacationsFromLocations());
                break;
            case 3:
                System.out.println(getVacationsForMaxPrice());
                break;
            case 4:
                System.out.println(addVacation());
                break;
            case 5:
                deleteVacation();
                break;
            case 6:
                System.out.println(updateVacation());
                break;
            default:
                break;
        }
    }

    private Vacation updateVacation() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input id to update: ");
        int idToUpdate = scanner.nextInt();
        Vacation vacation = getVacationToAdd();
        UriComponents uri = getUriComponents(idToUpdate);
        return rest.exchange(
                uri.toUriString(),
                HttpMethod.PUT,
                new HttpEntity<>(vacation),
                new ParameterizedTypeReference<Vacation>() {
                }
        ).getBody();
    }

    private UriComponents getUriComponents(int idToUpdate) {
        String url = "http://localhost:8080/vacations/{id}";
        Map<String, Integer> urlParam = new HashMap<>();
        urlParam.put("id", idToUpdate);
        return UriComponentsBuilder.fromHttpUrl(url)
                .buildAndExpand(urlParam);
    }

    private void deleteVacation() {
        Scanner scanner = new Scanner(System.in);
        String url = "http://localhost:8080/vacations";
        System.out.println("Please input id to delete: ");
        int idToDelete = scanner.nextInt();
        rest.exchange(
                url,
                HttpMethod.DELETE,
                new HttpEntity<>(idToDelete),
                new ParameterizedTypeReference<Object>() {
                }
        );
    }

    private Vacation addVacation() {
        Vacation vacation = getVacationToAdd();
        String url = "http://localhost:8080/vacations";
        return rest.exchange(
                url,
                HttpMethod.POST,
                new HttpEntity<>(vacation),
                new ParameterizedTypeReference<Vacation>() {
                }
        ).getBody();
    }


    private List<Vacation> getVacationsForMaxPrice() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input max price: ");
        int maxPrice = scanner.nextInt();
        String url = "http://localhost:8080/vacations";
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("price", maxPrice);
        return rest.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<Vacation>>() {
                }
        ).getBody();
    }

    private List<Vacation> getVacationsFromLocations() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input location: ");
        String location = scanner.next();
        String url = "http://localhost:8080/vacations";
        UriComponentsBuilder uri = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("location", location);
        return rest.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<Vacation>>() {
                }).getBody();
    }

    private List<Vacation> getAllVacations() {
        return rest.exchange(
                "http://localhost:8080/vacations",
                HttpMethod.GET,
                new HttpEntity<>(null),
                new ParameterizedTypeReference<List<Vacation>>() {
                }).getBody();
    }

    private void printMenu() {
        System.out.println("1)Get all vacations");
        System.out.println("2)Get vacations for location");
        System.out.println("3)Get vacations for max price");
        System.out.println("4) Add vacation ");
        System.out.println("5) Delete a vacation by id");
        System.out.println("6) Update a vacation");
        System.out.println("7)Exit");
    }

    private Vacation getVacationToAdd() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Input location: ");
        String location = scanner.next();
        System.out.println("Input price: ");
        int price = scanner.nextInt();
        System.out.println("Input duration: ");
        int duration = scanner.nextInt();
        return new Vacation(location, price, duration);
    }
}
