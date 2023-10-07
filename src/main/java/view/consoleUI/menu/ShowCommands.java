package view.consoleUI.menu;

import view.View;

import java.io.IOException;
import java.sql.SQLException;


public class ShowCommands extends MenuMethod {

    public ShowCommands(View view) {
        super(view);        
    }

    @Override
    public String description() {
        return "Show commands of this animal";
    }

    @Override
    public void run() throws IOException, SQLException {
        getView().showCommands();
    }

}
