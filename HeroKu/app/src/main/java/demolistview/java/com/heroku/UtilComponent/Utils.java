package demolistview.java.com.heroku.UtilComponent;

import android.app.ProgressDialog;
import android.content.Context;

import demolistview.java.com.heroku.ApplicationClassComponent.ApplicationClass;

public class Utils
{
    public ProgressDialog progressDialog;
    Context con = ApplicationClass.getContext();
    public Utils(Context con)
    {
        this.con = con;
    }


}
