package model;

import java.sql.Date;

public class Counter implements AutoCloseable {
    private int value = 0;
    private String name;
    private Date birthday;
    private int kindId;
    public boolean isUsed = false;

    public Counter(String name, Date birthday, int kindId) {
        this.name = name;
        this.birthday = birthday;
        this.kindId = kindId;
    }

    public void add() {
        value++;
    }

    public void checkAllFieldsFilled() throws Exception {
        if (!isUsed || name.isEmpty() || (birthday == null)) {
            throw new Exception("Error: Working with the Counter object was not in the try resource block or the resource remained open. \nOr all fields are not filled in");
        }
    }

    @Override
    public void close() throws Exception {
        checkAllFieldsFilled();
    }
}
