package org.example;

public class Gun extends Weapon{

    @Override
    public void attack() {
        System.out.println("Огонь пистолетом с уроном " + this.damage);
    }

    public Gun( int damage, int range) {
        super( damage, range);
    }
}
