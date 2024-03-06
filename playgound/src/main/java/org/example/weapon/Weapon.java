package org.example;

public class Weapon implements Attacker {


    int damage;
    int range;

    public Weapon( int damage, int range) {

        this.damage = damage;
        this.range = range;
    }

    public void attack() {
    }
}