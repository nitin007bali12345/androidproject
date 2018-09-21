package demolistview.java.com.heroku.PojoCategoryData;

import java.io.Serializable;

public class Item implements Serializable
{
    private String name;

    public Item(String n) {
        name = n;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
