package view.consoleUI.menu;

import view.View;

import java.io.IOException;



public class Exit extends MenuMethod{

    public Exit(View view) {
        super(view);
    }

    @Override
    public String description() {
        return "Exit";
    }

    @Override
    public void run() throws IOException {
        getView().exit();
    }

}
