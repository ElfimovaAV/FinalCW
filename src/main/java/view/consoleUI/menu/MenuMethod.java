package view.consoleUI.menu;

import view.View;

public abstract class MenuMethod implements MenuItem {
    private View view;
    public MenuMethod(View view) {
        this.view = view;
    }

    public View getView() {
        return this.view;
    };
}
