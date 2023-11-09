package Assignment1Part2;
// Author: Olivia Cole
// Date: 11/4/2023
// Assignment: Assignment 1 Part 2
// Milestone 3

import java.util.*;
import java.io.FileWriter;
import java.lang.*;
import java.io.*;

public class PetDatabase {
    public static Scanner s = new Scanner(System.in);
    public static ArrayList<Pet> petList = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        load();
        System.out.println("Welcome to the pet database!");
        System.out.println("What would you like to do?");
        System.out.println("1) View all pets");
        System.out.println("2) Add more pets");
        //System.out.println("3) Update an existing pet");
        System.out.println("3) Remove an existing pet");
        //System.out.println("5) Search pets by name");
        //System.out.println("6) Search pets by age");
        System.out.println("4) Exit");
        System.out.print("Your choice: ");
        int choice = s.nextInt();

        while(choice != 4) {
            switch(choice) {
                case 1: displayPets(); break;
                case 2: addPet(); break;
                //case 3: updatePet(); break;
                case 3: removePet(); break;
                //case 5: searchByName(); break;
                //case 6: searchByAge(); break;
                case 4: break;
                default: System.out.println("Invalid choice.");
            }

            System.out.println("What would you like to do?");
            System.out.println("1) View all pets");
            System.out.println("2) Add more pets");
            //System.out.println("3) Update an existing pet");
            System.out.println("3) Remove an existing pet");
            //System.out.println("5) Search pets by name");
            //System.out.println("6) Search pets by age");
            System.out.println("4) Exit");
            System.out.print("Your choice: ");
            choice = s.nextInt();
        }
        
        save();
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

    public static void addPet() throws Exception {
        String input = s.nextLine();
        int age;
        while(!input.equals("done")) {
            System.out.print("Add pet(name age): ");
            input = s.nextLine();

            if(input.equals("done")) {
                break;
            }

            String[] inputSplit = input.split(" ");
            String name = inputSplit[0];
            try {
                age = Integer.parseInt(inputSplit[1]);
            } catch (IndexOutOfBoundsException ex) {
                System.out.println("Input must have both a name and an age.");
                break;
            }

            if((age < 1) || (age > 20)) {
                System.out.println("Pet age needs to be between 1-20.");
            } else {
                Pet myPet = new Pet(name, age);
                petList.add(myPet);
            }
        }
        
    }

    public static void load() throws Exception {
        String line;
        String[] vals;
        try {
            Scanner input = new Scanner(new File("Pets.txt"));

            while(input.hasNextLine()) {
                line = input.nextLine();
                vals = line.split(" ");
                Pet myPet = new Pet(vals[0], Integer.parseInt(vals[1]));
                petList.add(myPet);
            }

            input.close();
        } catch (Exception ex) {
            System.out.println("No existing pet database.");
        }
    }

    public static void save() {
        try {
            FileWriter file = new FileWriter("Pets.txt");
            PrintWriter output = new PrintWriter(file);

            for(int i = 0; i < petList.size(); i++) {
                output.print(petList.get(i).getName() + " " + petList.get(i).getAge() + "\n");
            }

            output.close();
        } catch (Exception ex) {
            System.out.println("Save failed.");
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

    public static void updatePet() {
        displayPets();

        System.out.print("Enter the ID of the pet you would like to update: ");
        int id = s.nextInt();

        String oldName = petList.get(id).getName();

        System.out.print("Enter the new name and age(name age): ");
        String hold = s.nextLine();
        String input = s.nextLine();
        String[] inputSplit = input.split(" ");
        petList.get(id).setName(inputSplit[0]);
        petList.get(id).setAge(Integer.parseInt(inputSplit[1]));

        System.out.println(oldName + " changed to " + input);
    }

    public static void removePet() {
        displayPets();
        System.out.print("Enter the ID of the pet you would like to remove: ");
        int id = s.nextInt();

        String name = petList.get(id).getName();
        petList.remove(id);

        Pet.petCount--;
        System.out.println(name + " has been removed.");
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