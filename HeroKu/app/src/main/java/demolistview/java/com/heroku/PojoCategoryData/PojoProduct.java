package demolistview.java.com.heroku.PojoCategoryData;

import java.io.Serializable;

public class PojoProduct implements Serializable
{
    private Rankings[] rankings;

    private Categories[] categories;

    public Rankings[] getRankings ()
    {
        return rankings;
    }

    public void setRankings (Rankings[] rankings)
    {
        this.rankings = rankings;
    }

    public Categories[] getCategories ()
    {
        return categories;
    }

    public void setCategories (Categories[] categories)
    {
        this.categories = categories;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [rankings = "+rankings+", categories = "+categories+"]";
    }
}
