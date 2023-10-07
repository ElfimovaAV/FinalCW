package model.animals.packanimals;

import java.sql.Date;

public class Donkey extends PackAnimals{

    public Donkey(int id, String name, Date birthDate, String comand) {
        super(id, 6, name, birthDate, comand);
    }
    
}
