package rs.softuni;


import java.util.Random;
import java.util.Scanner;

public class Main {

    private static int shieldEnergy = 100;
    private static int rockets = 3;

    private static final Random RANDOM = new Random();
    private static final Scanner SCANNER = new Scanner(System.in);
    private static int enemiesDestroyed = 0;

    public static void main(String[] args) {

        for (int sector = 1; sector <= 5 ; sector++) {
            if(shieldEnergy <= 0){
                System.out.println("Game over!");
                break;
            }

            System.out.printf("Sector: %d/5 \n", sector);
            System.out.printf("Shield energy: %d \n", shieldEnergy);
            System.out.printf("Rockets: %d \n", rockets);


            if(RANDOM.nextInt(100) < 80){
                int enemyHP = 50;

                System.out.println("An enemy ship has appeared!");
                System.out.printf("Enemy HP: %d\n", enemyHP);

                boolean enemyDefeated = false;
                boolean enemyEscaped = false;

                while (!enemyDefeated && !enemyEscaped){

                    System.out.println("Choose you action:");
                    System.out.println("1. Laser attack");
                    System.out.println("2. Rocket attack");
                    System.out.println("3. Attampt to Escape");

                    int choice = SCANNER.nextInt();

                    switch (choice){
                        case 1:
                            int laserDamage = 10 + RANDOM.nextInt(11); //10-20 damage
                            boolean laserHit = RANDOM.nextInt(100) < 80; // 80% chance

                            if(laserHit){
                                enemyHP = enemyHP - laserDamage;
                                System.out.printf("Laser attack hit! You deal %d damage \n", laserDamage);

                                if(enemyHP <= 0){
                                    System.out.println("Enemy destroyed");
                                    enemyDefeated = true;
                                    break;
                                }
                            }else {
                                System.out.println("Laser attack missed!");
                            }

                            enemyDefeated = false;
                            break;
                        case 2:
                            if(rockets <= 0){
                                System.out.println("No rockets left!");
                                break;
                            }

                            rockets--;

                            int rocketDamage = 30 + RANDOM.nextInt(11); //30-40 damage
                            boolean rocketHit = RANDOM.nextInt(100) < 90; //90% chance

                            if(rocketHit){
                                enemyHP -= rocketDamage;
                                System.out.printf("Rocket attack hit! You deal %d damage \n", rocketDamage);

                                if(enemyHP <= 0){
                                    System.out.println("Enemy destroyed.");
                                    enemyDefeated = true;
                                    break;
                                }
                            }else {
                                System.out.println("Rocket attack missed!");
                            }

                            enemyDefeated = false;
                            break;
                        case 3:
                            enemyEscaped = RANDOM.nextInt(100) < 50; // 50% chance to escape
                            if(enemyEscaped){
                                System.out.println("Escaped successfull!");
                            }else{
                                System.out.println("Escaped failed!");
                            }
                    }

                    if(!enemyDefeated && !enemyEscaped){
                        boolean enemyHit = RANDOM.nextInt(100) < 70; //70% chance for enemy
                        if (enemyHit){
                            int enemyDamage = 10 + RANDOM.nextInt(6); //10-15 damage
                            shieldEnergy = shieldEnergy - enemyDamage;
                            System.out.printf("Your shield energy is depleted. You take %d damage. \n", enemyDamage);
                            if(shieldEnergy <= 0){
                                System.out.println("Enemy won. Game over!");
                            }
                        }else{
                            System.out.println("Enemy missed! You are not taking any damage.");
                        }
                    }
                }

                if(enemyDefeated){
                    enemiesDestroyed++;
                }
            }else{
                System.out.println("The sector is clear. You pass through safely.");
            }
        }

        if(shieldEnergy > 0){
            System.out.println("Congratulations! You survived all sectors.");
            System.out.printf("Enemies destroyed: %d\n", enemiesDestroyed);
        }
    }
}