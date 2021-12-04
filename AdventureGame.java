import java.util.Random;
import java.util.Scanner;

public class AdventureGame {
    public static void main(String[] args) {
        Scanner scnr = new Scanner(System.in);
        Random rand = new Random(); //Scanner and randoms in

        String[] enemies = {"Ogre", "Troll", "Witch", "Dark King"}; //Dark King is boss and appears after 5 loops of other enemies
        int maxEHealth = 75;
        int EAttackDmg = 25;
        int numLoops = 0;

        int health = 100;  //Setting all variables to amounts
        int attackDamage = 35;
        int numHealthPots = 1;
        int healthPotionHeal = 35;
        int healthPotionDrop = 20;
        boolean superAbility = true;

        boolean running = true;

        System.out.println("Welcome to the Castle!!!!!");
        GAME:  //easy reference to break/continue later
        while(running){
            System.out.println("----------------------------------------------------------"); //formatting

            int EHealth = rand.nextInt(maxEHealth);
            EHealth += 20;
            String enemy = enemies[rand.nextInt(enemies.length - 1)]; //Everything exept for the boss, Dark King
            while(EHealth > 0 ) {
                if(numLoops == 5){ //Dark King's entry if loops are 5
                    enemy = enemies[3];
                    EHealth = 110;
                    EAttackDmg = 41;
                    numLoops++;
                }
                System.out.println("\t# " + "The " + enemy + " appeared! Brace yourself! #\n");
                System.out.println("\tYourHP: " + health);
                System.out.println("\t" + enemy + "'s HP: " + EHealth);
                System.out.println("\n\tMake a decision. What do you want to do?");
                System.out.println("\t1. Attack");
                System.out.println("\t2. Drink health potion");    //Choices of what to do
                System.out.println("\t3. Run");
                System.out.println("\t4. Super Ability (Once per game. Use wisely!)");

                String input = scnr.nextLine();
                if(input.equals("1")) {

                    int damageDealt = attackDamage;
                    int damageTaken = EAttackDmg;

                    EHealth -= damageDealt;

                    System.out.println("\t> You strike the " + enemy + " for " + damageDealt + " damage."); //If kill enemy, don't receive any damage back.
                    if (EHealth <= 0) {
                        numLoops++;
                        continue;
                    }
                    System.out.println("\t> You receive " + damageTaken + " in retaliation!");
                    health -= damageTaken;
                    if (health < 1) {
                        System.out.println("\t> You are too weak! Better luck next time!"); //If health<1, break loop and game ends.
                        break;
                    }
                }
                else if(input.equals("2")) {
                    if(numHealthPots > 0) {
                        health += healthPotionHeal;
                        if (health > 100){
                            health = 100;
                        }
                        numHealthPots--;
                        System.out.println("\t> You drink a health potion, healing yourself for " + healthPotionHeal + "." + "\n\t>You now have " + health + "HP." + "\n\t> You have " + numHealthPots + " health potions left.\n");
                    }//Heal if you have a potion, if not there is no heal and it tells the player they can get one by killing an enemy.
                    else {
                        System.out.println("\t> You have no health potions left! Defeat enemies for a 20% chance to drop one.");

                    }

                }
                else if(input.equals("3")) {
                    if(enemy == "Dark King"){
                        System.out.println("How dare you try to run from the dark king. He stabs you in the back and you die coward. The End.");
                        break GAME; //You can't run from the final dark king.
                    }
                    System.out.println("\tYou run away from the " + enemy + "! Coward!");
                    continue GAME; //Restart with a new mob

                }
                else if(input.equals("4")) {
                    if (superAbility){
                        System.out.println("\t> You channel your magic powers and cast a spell to hurt the "+enemy+" for "+ (EHealth - 1) +" damage.");
                        EHealth = 1;
                        superAbility = false;
                        continue; //Set the enemy's health to one without taking a hit(Best on Dark King) Only works once, boolean becomes false
                    }
                    else{
                        System.out.println("You don't have that ability anymore!");
                    }

                }
                else{
                    System.out.println("\tInvalid command!");


                }
                }
            if (health <= 0){
                System.out.println("You limp away from the castle, defeated."); // If health 0, you lose. If not, continue
                break;
            }
            System.out.println("----------------------------------------------------------");
            System.out.println(" # " + enemy + " was defeated! # ");
            System.out.println(" # You have " + health + "HP left. #");
            if(rand.nextInt(100) < healthPotionDrop) {
                numHealthPots++;
                System.out.println(" # The " + enemy + " dropped a health potion! # ");
                System.out.println(" # You now have " + numHealthPots + " health potion(s). # "); // If random chance hits, drops a health potion
            }
            if(numLoops > 5){
                System.out.println("You have beaten the Dark King and claim the King's treasure as reward. Congratulations!");//Win game
                break;

            }
            System.out.println("----------------------------------------------------------");
            System.out.println("1. Continue fighting");
            System.out.println("2. Leave the castle");

            String input = scnr.nextLine();

            while(!input.equals("1") && !input.equals("2")) {
                System.out.println("Invalid command!");
                input = scnr.nextLine();
            }
            if(input.equals("1")) {
                System.out.println("You walk deeper into the castle"); //continue
            }
            else if(input.equals("2")){
                System.out.println("You walk away, successful but not having found the secret treasure of the Dark King.");
                break GAME;

        }



        }
        System.out.println("##################");
        System.out.println("# THANKS FOR PLAYING!!! #");
        System.out.println("##################");


    }
}
