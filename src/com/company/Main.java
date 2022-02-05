package com.company;

import java.util.Random;

public class Main {

    public static int bossHealth = 800;
    public static int bossDamage = 50;
    public static String bossDefenceType;
    public static int[] heroesHealth = {300, 250, 270, 300, 400, 250, 220, 250};
    public static int[] heroesDamage = {20, 15, 10, 0, 5, 20, 15, 20};
    public static String[] heroesAttackType = {"Physical", "Magical", "Kinetic", "Medic", "Golem", "Lucky", "Berserk",
            "Thor"};
    public static int roundNumber;

    public static void main(String[] args) {
        printStatistics();

        while (!isGameFinished()) {
            round();
        }
    }

    public static void chooseBossDefence() {
        Random random = new Random();
        int randomIndex = random.nextInt(heroesAttackType.length); // 0,1,2
        bossDefenceType = heroesAttackType[randomIndex];
        System.out.println("Boss chose: " + bossDefenceType);
    }

    public static void round() {
        roundNumber++;
        chooseBossDefence();
        bossHits();
        heroesHit();
        Medic();
        Golem();
        Lucky();
        Berserk();
        Thor();
        printStatistics();

    }

    public static void bossHits() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (heroesHealth[i] - bossDamage < 0) {
                    heroesHealth[i] = 0;
                } else {
                    heroesHealth[i] = heroesHealth[i] - bossDamage;
                }
            }
        }
    }

    public static void heroesHit() {
        for (int i = 0; i < heroesDamage.length; i++) {
            if (bossHealth > 0 && heroesHealth[i] > 0) {
                if (bossDefenceType == heroesAttackType[i]) {
                    Random random = new Random();
                    int coeff = random.nextInt(9) + 2; // 2,3,4,5,6,7,8,9,10
                    if (bossHealth - heroesDamage[i] * coeff < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i] * coeff;
                    }
                    System.out.println("Critical damage: " + heroesDamage[i] * coeff);
                } else {
                    if (bossHealth - heroesDamage[i] < 0) {
                        bossHealth = 0;
                    } else {
                        bossHealth = bossHealth - heroesDamage[i]; //  bossHealth -= heroesDamage[i];
                    }
                }
            }
        }
    }

    public static boolean isGameFinished() {
        if (bossHealth <= 0) {
            System.out.println("Heroes won!!!");
            return true;
        }
        /*if (heroesHealth[0] <= 0 && heroesHealth[1] <= 0 && heroesHealth[2] <= 0) {
            System.out.println("Boss won!!!");
            return true;
        }
        return false;*/
        boolean allHeroesDead = true;
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0) {
                allHeroesDead = false;
                break;
            }
        }
        if (allHeroesDead) {
            System.out.println("Boss won!!!");
        }
        return allHeroesDead;
    }

    public static void Medic() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && heroesHealth[i] < 100 && heroesHealth[3] != heroesHealth[i]) {
                heroesHealth[i] += 50;
                System.out.println("Medic healths: " + heroesAttackType[i]);
                break;

            }
        }
    }

    public static void Golem() {
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[i] > 0 && heroesHealth[4] > 0 && heroesHealth[4] != heroesHealth[i]) {
                int heroDamage = bossDamage / 5;
                heroesHealth[4] -= heroDamage;
                heroesHealth[i] += heroDamage;

            }

        }
    }

    public static void Lucky() {
        Random random = new Random();
        boolean lucker = random.nextBoolean();
        if (heroesHealth[5] > 0 && lucker == true) {
            heroesHealth[5] += 50;

        }

    }

    public static void Berserk(){
        for (int i = 0; i < heroesHealth.length; i++) {
            if (heroesHealth[6] > 0 ){
                int berserkDamage = bossDamage / 5;
                heroesDamage[6] += berserkDamage;
                break;
            }

        }
    }
    public static void Thor(){
        Random random = new Random();
        boolean pass = random.nextBoolean();
            if (heroesHealth[7] > 0 && pass == true) {
                bossDamage = 0;
                System.out.println("Stunned");
            }else bossDamage = 50;{
            }


    }




    public static void printStatistics() {
        System.out.println(roundNumber + " ROUND -------------");
        System.out.println("Boss health: " + bossHealth + " (" + bossDamage + ")");
        for (int i = 0; i < heroesHealth.length; i++) {
            System.out.println(heroesAttackType[i] + " health: "
                    + heroesHealth[i] + " (" + heroesDamage[i] + ")");
        }
    }
}
