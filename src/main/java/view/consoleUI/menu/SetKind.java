package view.consoleUI.menu;

import view.View;

import java.io.IOException;
import java.sql.SQLException;

public class SetKind extends MenuMethod{
    public SetKind(View view) {
        super(view);
    }

    @Override
    public String description() {
        return "Set animal kind";
    }

    @Override
    public void run() throws IOException, SQLException {
        getView().setClass();
    }
}
