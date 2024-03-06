package org.example;

public class Sword extends Weapon {

    public Sword( int damage, int range) {
        super( damage, range);
    }


    @Override
    public void attack() {
        System.out.println("Урон мечом с уроном " + this.damage);
    }
}
