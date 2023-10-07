package model.animals.pets;

import java.sql.Date;

public class Dog extends Pets {

    public Dog(int id, String name, Date birthDate, String comand) {
        super(id, 1, name, birthDate, comand);
    }
    
}
