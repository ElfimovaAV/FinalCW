package view.consoleUI.menu;

import view.View;

import java.io.IOException;
import java.sql.SQLException;



public class ShowAnimals extends MenuMethod {

    public ShowAnimals(View view) {
        super(view);
    }

    @Override
    public String description() {
        return "Show all animals";
    }

    @Override
    public void run() throws IOException, SQLException {
        getView().showAnimals();
    }

    
}
