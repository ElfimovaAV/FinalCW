package view.consoleUI.menu;

import view.View;

import java.io.IOException;
import java.sql.SQLException;


public class TeachAnimal extends MenuMethod{

    public TeachAnimal(View view) {
        super(view);        
    }

    @Override
    public String description() {
        return "Teach animal";
    }

    @Override
    public void run() throws IOException, SQLException {
        getView().teachAnimal();
    }

}  

