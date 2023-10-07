package model.animals.packanimals;

import java.sql.Date;

import model.animals.Animal;

public class PackAnimals extends Animal {

    public PackAnimals(int id, int kindId, String name, Date birthDate, String comand) {
        super(id, 2, kindId, name, birthDate, comand);
    }

    
}
