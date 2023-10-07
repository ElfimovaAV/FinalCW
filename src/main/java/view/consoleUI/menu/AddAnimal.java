package view.consoleUI.menu;


import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import view.View;

public class AddAnimal extends MenuMethod{
    private View view;

    public AddAnimal(View view) {
        super(view);
    }
    
    @Override
        public void run() throws ParseException, SQLException, IOException {
            getView().addAnimal();
        }

    @Override
    public String description() {
        return "Add an animal";
    }
}
