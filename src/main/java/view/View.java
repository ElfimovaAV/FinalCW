package view;


import presenter.Presenter;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;


public interface View {
    void setPresenter(Presenter presenter);

    void message(String message);

    void addAnimal() throws ParseException, SQLException, IOException;

    void start() throws IOException, SQLException, ParseException;

    void showCommands() throws SQLException, IOException;

    void teachAnimal() throws SQLException, IOException;

    void showAnimals() throws SQLException, IOException;

    void exit();


    void setClass() throws SQLException, IOException;
}
