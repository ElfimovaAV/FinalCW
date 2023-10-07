
import model.DBconnection;
import presenter.Presenter;
import view.View;
import view.consoleUI.ConsoleUI;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException, SQLException, ParseException {
        View view = new ConsoleUI();
        DBconnection dBconnection = new DBconnection();
        Presenter presenter = new Presenter(view, dBconnection);
        view.setPresenter(presenter);
        view.start();
    }
}
