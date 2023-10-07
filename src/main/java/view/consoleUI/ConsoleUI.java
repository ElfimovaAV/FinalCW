package view.consoleUI;

import model.Counter;
import model.animals.Animal;
import presenter.Presenter;
import view.View;
import view.consoleUI.menu.*;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import static java.lang.System.in;

public class ConsoleUI implements View {
    private Presenter presenter;
    private Scanner scanner;
    private boolean mainFlag = true;

    public ConsoleUI() {
        scanner = new Scanner(in);
    }

    @Override
    public void setPresenter(Presenter presenter) {
        this.presenter = presenter;
    }

    @Override
    public void message(String message) {
        System.out.println(message);
    }

    @Override
    public void addAnimal() throws SQLException, IOException {
        String name = scan("Input animal's name");
        Date birthday;
        while (true) {
            String sDate = scan("Input animal's date of birth (in format 'yyyy-MM-dd')");
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                birthday = Date.valueOf(sDate);
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Wrong date format. Please try again.");
            }
        }
        int selectClass = selectClass();
        try (Counter counter = new Counter(name, birthday, selectClass)) {
            counter.add();
            counter.isUsed = true;
            presenter.addAnimal(name, birthday, selectClass);

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void start() throws IOException, SQLException, ParseException {
        Menu mainMenu = new Menu(this);
        mainMenu.addItem(new AddAnimal(this));
        mainMenu.addItem(new SetKind(this));
        mainMenu.addItem(new ShowCommands(this));
        mainMenu.addItem(new TeachAnimal(this));
        mainMenu.addItem(new ShowAnimals(this));
        mainMenu.addItem(new Exit(this));
        while (mainFlag == true) {
            System.out.println("Main menu:");
            mainMenu.printMenu();
            int choice = scanInt("Please choose a number from 1 to 6: ");
            mainMenu.getItem(choice).run();
        }
    }

    public String scan(String message) {
        message(message);
        String result = scanner.nextLine();
        return result;
    }

    public int scanInt(String message) {
        boolean scanFlag = false;
        int id = 0;
        while (!scanFlag) {
            message(message);
            try {
                id = scanner.nextInt();
                if (id < 1 || id > 6) {
                    message("The wrong number");
                } else {
                    scanFlag = true;
                }
            } catch (InputMismatchException ex) {
                message("The number must be a positive integer other than 0");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return id;
    }

    public int scanKind(String message) {
        boolean scanFlag = false;
        int id = 0;
        while (!scanFlag) {
            message(message);
            try {
                id = scanner.nextInt();
                if (id < 1) {
                    message("The wrong number");
                } else {
                    scanFlag = true;
                }
            } catch (InputMismatchException ex) {
                message("The number must be a positive integer other than 0");
                scanner.nextLine();
            }
        }
        scanner.nextLine();
        return id;
    }

    @Override
    public void showCommands() throws SQLException, IOException {
        int id = scanKind("Input animal number: ");
        String tableName = "Animals_all";
        boolean isAnimalPresent = presenter.checkAnimalNumber(tableName, id);
        if (isAnimalPresent) {
            String command = presenter.showCommands(id);
            message("The animal can execute the following commands: " + command);
        }
    }

    @Override
    public void teachAnimal() throws SQLException, IOException {
        int id = scanKind("Input animal number: ");
        String tableName = "Animals_all";
        boolean isAnimalPresent = presenter.checkAnimalNumber(tableName, id);
        if (isAnimalPresent) {
            message("The animal was found in the database ");
            Animal animal = presenter.takeAnimal(id);
            String newComand = scan("Input new command");
            try {
                presenter.updateComand(animal, newComand);
            } catch (IOException ex) {
                message("I/O error");
                message(ex.getMessage());
            } catch (SQLException ex) {
                message("Error working with the database");
                message(ex.getMessage());
                scanner.nextLine();
            }
        } else {
            message("There is no such animal in the database");
        }
    }

    @Override
    public void showAnimals() throws SQLException, IOException {
        List<Animal> animals = presenter.showAnimals();
        Map<Integer, String> animalsMap = presenter.getMap("animals", "animal_type");
        Map<Integer, String> kindAnimals = presenter.getMap("kinds_of_animals", "name_of_kind");
        String[] headers = {"№", "Тип животного", "Вид животного", "Имя", "Дата рождения", "Команды"};
        System.out.format("%5s %15s %15s %15s %14s %50s\n", "№", "Тип животного", "Вид животного", "Имя", "Дата рождения", "Команды");
        System.out.println();
        for (Animal animal : animals) {
            System.out.format("%5s %15s %15s %15s %14s %50s\n",
                    animal.getId(),
                    animalsMap.get(animal.getAnimalTypeId()),
                    kindAnimals.get(animal.getKindId()),
                    animal.getName(),
                    (animal.getBirthDate()).toString(),
                    animal.getComand());
        }
    }

    @Override
    public void exit() {
        System.out.println("The app is stopped");
        mainFlag = false;
    }

    @Override
    public void setClass() throws SQLException, IOException {
        int id = scanKind("Input animal number: ");
        String tableName = "Animals_all";
        boolean isAnimalPresent = presenter.checkAnimalNumber(tableName, id);
        if (isAnimalPresent) {

            message("The animal was found in the database ");
            Animal animal = presenter.takeAnimal(id);
            int newClass = selectClass();
            try {
                presenter.updateClass(animal, newClass);
            } catch (IOException ex) {
                message("I/O error");
                message(ex.getMessage());
            } catch (SQLException ex) {
                message("Error working with the database");
                message(ex.getMessage());
            }
        } else {
            message("here is no such animal in the database ");
        }

    }

    public int selectClass() throws SQLException, IOException {
        Map<Integer, String> animalsKind = presenter.getMap("kinds_of_animals", "name_of_kind");
        for (Map.Entry<Integer, String> entry : animalsKind.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue());
        }
        int newClass = 0;
        boolean correctClass = false;
        while (!correctClass) {
            newClass = scanInt("Input new kind of animal:");
            if (newClass > 0 || newClass <= animalsKind.size()) {
                correctClass = true;
            } else {
                message("Invalid class " + newClass);
                message("Try again");
            }
        }
        return newClass;
    }

}
