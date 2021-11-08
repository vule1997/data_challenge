package com.example.demo.league;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "league")
public class League {

    @Id
    private int id_league;

    public League() {
    }

    public League(int id_league) {
        this.id_league = id_league;
    }

    public int getId_league() {
        return id_league;
    }

    public void setId_league(int id_league) {
        this.id_league = id_league;
    }

    @Override
    public String toString() {
        return "League{" +
                "id_league=" + id_league +
                '}';
    }
}
