package Assignment1Part2;
// Author: Olivia Cole
// Date: 11/4/2023
// Assignment: Assignment 1 Part 2
// Milestone 1

import java.util.*;

public class PetDatabase {
    public static Scanner s = new Scanner(System.in);
    public static ArrayList<Pet> petList = new ArrayList<>();
    public static void main(String[] args) {
        System.out.println("Welcome to the pet database!");
        System.out.println("What would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        //System.out.println("3) Update an existing pet");
        //System.out.println("4) Remove an existing pet");
        System.out.println("5) Search pets by name");
        System.out.println("6) Search pets by age");
        System.out.println("7) Exit");
        System.out.print("Your choice: ");
        int choice = s.nextInt();

        while(choice != 7) {
            switch(choice) {
                case 1: displayPets(); break;
                case 2: addPet(); break;
                case 5: searchByName(); break;
                case 6: searchByAge(); break;
                case 7: break;
                default: System.out.println("Invalid choice.");
            }

            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            //System.out.println("3) Update an existing pet");
            //System.out.println("4) Remove an existing pet");
            System.out.println("5) Search pets by name");
            System.out.println("6) Search pets by age");
            System.out.println("7) Exit");
            System.out.print("Your choice: ");
            choice = s.nextInt();
        }
        
        System.out.println("Goodbye!");
    }

    public static void displayPets() {
        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        for(int i = 0; i < petList.size(); i++) {
            System.out.printf("|%3d | %-10s|%4d |", i, petList.get(i).getName(), petList.get(i).getAge()); 
            System.out.println();
        }
        System.out.println("+----------------------+");
        System.out.println(Pet.petCount + " rows in set.");
    }

    public static void addPet() {
        String input = s.nextLine();
        while(!input.equals("done")) {
            System.out.print("Add pet(name age): ");
            input = s.nextLine();

            if(input.equals("done")) {
                break;
            }

            String[] inputSplit = input.split(" ");
            String name = inputSplit[0];
            int age = Integer.parseInt(inputSplit[1]);

            Pet myPet = new Pet(name, age);
            petList.add(myPet);
        }
        
    }

    public static void searchByName() {
        System.out.print("What name would you like to search for?: ");
        String name = s.next();

        ArrayList<Pet> foundPets = new ArrayList<>();

        for(int i = 0; i < Pet.petCount; i++) {
            if(petList.get(i).getName().equals(name)) {
                foundPets.add(petList.get(i));
            }
        }

        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        for(int i = 0; i < foundPets.size(); i++) {
            System.out.printf("|%3d | %-10s|%4d |", i, foundPets.get(i).getName(), foundPets.get(i).getAge()); 
            System.out.println();
        }
        System.out.println("+----------------------+");
        System.out.println(foundPets.size() + " rows in set.");
    }

    public static void searchByAge() {
        System.out.print("What age would you like to search for?: ");
        int age = s.nextInt();

        ArrayList<Pet> foundPets = new ArrayList<>();

        for(int i = 0; i < Pet.petCount; i++) {
            if(petList.get(i).getAge() == age) {
                foundPets.add(petList.get(i));
            }
        }

        System.out.println("+----------------------+");
        System.out.println("| ID | NAME      | AGE |");
        System.out.println("+----------------------+");

        for(int i = 0; i < foundPets.size(); i++) {
            System.out.printf("|%3d | %-10s|%4d |", i, foundPets.get(i).getName(), foundPets.get(i).getAge()); 
            System.out.println();
        }
        System.out.println("+----------------------+");
        System.out.println(foundPets.size() + " rows in set.");
    }
}

class Pet {
    private String name;
    private int age;
    public static int petCount = 0;

    Pet(String name, int age) {
        this.name = name;
        this.age = age;
        petCount++;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }
}