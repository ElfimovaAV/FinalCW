package presenter;

import model.DBconnection;
import model.animals.Animal;
import model.animals.packanimals.Camel;
import model.animals.packanimals.Donkey;
import model.animals.packanimals.Horse;
import model.animals.pets.Cat;
import model.animals.pets.Dog;
import model.animals.pets.Hamster;
import view.View;

import java.io.IOException;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Presenter {
    private View view;
    private DBconnection dbConnection;


    public Presenter(View view, DBconnection dbConnection) {
        this.view = view;
        this.dbConnection = dbConnection;
    }


    public List<Animal> showAnimals() throws SQLException, IOException {
        ResultSet rs = dbConnection.getResultSet("SELECT * FROM animals_all;");
        List<Animal> animals = new ArrayList<Animal>();
        while (rs.next()) {
            Animal animal = new Animal(
                    rs.getInt("id"),
                    rs.getInt("animal_type_id"),
                    rs.getInt("kinds_of_animals_id"),
                    rs.getString("name"),
                    rs.getDate("birthday"),
                    rs.getString("command"));
            animals.add(animal);
        }

        return animals;
    }


    public Map<Integer, String> getMap(String tableName, String fieldName) throws SQLException, IOException {
        ResultSet rs = dbConnection.getResultSet("SELECT * FROM " + tableName + ";");
        Map<Integer, String> map = new HashMap<Integer, String>();
        while (rs.next()) {
            map.put(rs.getInt("id"), rs.getString(fieldName));
        }
        return map;
    }


    public boolean checkAnimalNumber(String tableName, int id) throws SQLException, IOException {
        boolean idExist = false;
        ResultSet rs = dbConnection.getResultSet("SELECT * from " + tableName + " WHERE id = " + id + ";");
        while (rs.next()) {
            idExist = true;
            break;
        }
        return idExist;
    }

    public void updateComand(Animal animal, String command) throws SQLException, IOException {
        String oldCommand = animal.getComand();
        if (oldCommand == null){
            oldCommand = "";
        }
        if (!oldCommand.isEmpty()) {
            command = oldCommand + ", " + command;
        }
        animal.setComand(command);
        updateAnimal(animal);

    }

    public String showCommands(int id) throws SQLException, IOException {
        Animal animal = takeAnimal(id);
        return animal.getComand();
    }


    public void updateClass(Animal animal, int animal_kind_id) throws SQLException, IOException {
        ResultSet getAnimalsTypeID = dbConnection.getResultSet("SELECT animal_type_id FROM kinds_of_animals WHERE id = " + animal_kind_id + ";");
        int animal_type_id = 0;
        while (getAnimalsTypeID.next()) {
            animal_type_id = getAnimalsTypeID.getInt(1);
        }
        animal.setKindId(animal_kind_id);
        animal.setAnimalTypeId(animal_type_id);
        updateAnimal(animal);
    }

    public Animal takeAnimal(int id) throws SQLException, IOException {
        ResultSet rs = dbConnection.getResultSet("SELECT * FROM animals_all WHERE id = " + id + ";");
        Animal animal = new Animal();
        while (rs.next()) {
            animal = new Animal(
                    rs.getInt("id"),
                    rs.getInt("animal_type_id"),
                    rs.getInt("kinds_of_animals_id"),
                    rs.getString("name"),
                    rs.getDate("birthday"),
                    rs.getString("command"));
        }
        return animal;
    }

    public void updateAnimal(Animal animal) throws SQLException, IOException {
        int id = animal.getId();
        int anymal_type_id = animal.getAnimalTypeId();
        int type_id = animal.getKindId();
        String name = animal.getName();
        Date birthday = animal.getBirthDate();
        String command = animal.getComand();

        dbConnection.executeQuery("UPDATE animals_all SET animal_type_id = ?, kinds_of_animals_id = ?, name = ?, birthday = ?, command = ? WHERE id = ?;",
                anymal_type_id, type_id, name, birthday, command, id);

    }


    public void addAnimal(String name, Date birthday, int selectClass) throws SQLException, IOException {
        Animal animal = null;
        int id = maxAnimalsId() + 1;
        switch (selectClass) {
            case 1:
                animal = new Dog(id,  name, birthday, null);
                break;
            case 2:
                animal = new Cat(id, name, birthday, null);
                break;
            case 3:
                animal = new Hamster(id, name, birthday, null);
                break;
            case 4:
                animal = new Horse(id, name, birthday, null);
                break;
            case 5:
                animal = new Camel(id, name, birthday, null);
                break;
            case 6:
                animal = new Donkey(id, name, birthday, null);
                break;
        }
        dbConnection.executeQuery("INSERT INTO animals_all(animal_type_id, kinds_of_animals_id, name, birthday, command) " +
                                        "VALUES (?, ?, ?, ?, ?);",
                                            animal.getAnimalTypeId(),
                                            animal.getKindId(),
                                            animal.getName(),
                                            animal.getBirthDate(),
                                            animal.getComand());
    }

    private int maxAnimalsId() throws SQLException, IOException {
        ResultSet rs = dbConnection.getResultSet("SELECT MAX(id) FROM animals_all;" );
        int maxID = 0;
        while(rs.next()){
            maxID = rs.getInt(1);
        }
        return maxID;
    }
}
