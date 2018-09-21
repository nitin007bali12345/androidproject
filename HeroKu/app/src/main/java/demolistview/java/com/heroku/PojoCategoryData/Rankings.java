package demolistview.java.com.heroku.PojoCategoryData;

import java.io.Serializable;

public class Rankings implements Serializable
{
    private Products[] products;
    private String ranking;
    
    public Products[] getProducts ()
    {
        return products;
    }

    public void setProducts (Products[] products)
    {
        this.products = products;
    }

    public String getRanking ()
    {
        return ranking;
    }

    public void setRanking (String ranking)
    {
        this.ranking = ranking;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [products = "+products+", ranking = "+ranking+"]";
    }
}

