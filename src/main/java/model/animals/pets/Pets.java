package model.animals.pets;

import java.sql.Date;

import model.animals.Animal;

public class Pets extends Animal {
    
    public Pets(int id, int kindId, String name, Date birthDate, String comand) {
        super(id, 1, kindId, name, birthDate, comand);
        
    }    
    
    
}
