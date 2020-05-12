package org.fasttractit.curs23homework.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Vacation {
    @Id
    @GeneratedValue
    private int id;
    private String location;
    private int price;
    private int duration;

    public Vacation() {
    }

    public Vacation(String location, int price, int duration) {
        this.location = location;
        this.price = price;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Vacation vacation = (Vacation) o;

        if (id != vacation.id) return false;
        if (price != vacation.price) return false;
        if (duration != vacation.duration) return false;
        return location != null ? location.equals(vacation.location) : vacation.location == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + duration;
        return result;
    }

    @Override
    public String toString() {
        return "Vacation{" +
                "id=" + id +
                ", location='" + location + '\'' +
                ", price=" + price +
                ", duration=" + duration +
                '}';
    }
}
