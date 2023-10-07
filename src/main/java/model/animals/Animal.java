package model.animals;

import java.sql.Date;

public class Animal {
    private int id;
    private int animalTypeId;
    private int kindId;
    private String name;
    private Date birthDate;
    private String comand;

    public Animal(){}

    public Animal(int id, int animalTypeId, int kindId, String name, Date birthDate, String comand) {
        this.id = id;
        this.animalTypeId = animalTypeId;
        this.kindId = kindId;
        this.name = name;
        this.birthDate = birthDate;
        this.comand = comand;
    }

    public int getId() {
        return id;
    }

    public int getAnimalTypeId() {
        return animalTypeId;
    }

    public int getKindId() {
        return kindId;
    }

    public String getName() {
        return name;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getComand() {
        return comand;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setAnimalTypeId(int animalTypeId) {
        this.animalTypeId = animalTypeId;
    }

    public void setKindId(int kindId) {
        this.kindId = kindId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setComand(String comand) {
        this.comand = comand;
    }

    @Override
    public String toString() {
        return "Animal{" + "id=" + id + ", animalTypeId=" + animalTypeId + ", kindId=" + kindId + ", name=" + name
                + ", birthDate=" + birthDate + ", comand=" + comand + '}';
    }
}
