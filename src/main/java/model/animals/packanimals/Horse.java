package model.animals.packanimals;

import java.sql.Date;

public class Horse extends PackAnimals {

    public Horse(int id,  String name, Date birthDate, String comand) {
        super(id, 4, name, birthDate, comand);
    }
    
}
