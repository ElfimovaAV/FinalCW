package model.animals.pets;

import java.sql.Date;

public class Hamster extends Pets {

    public Hamster(int id, String name, Date birthDate, String comand) {
        super(id, 3, name, birthDate, comand);
    }
    
}
