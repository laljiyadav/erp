

package main.erp.root.erp.main.erp.root.erp.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;


public class CheckInternetUtil {

	public static boolean isInternetAvailable(Context context) {

		try{
			ConnectivityManager connectivity = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null)
			{
				NetworkInfo[] info = connectivity.getAllNetworkInfo();
				if (info != null)
					for (int i = 0; i < info.length; i++)
						if (info[i].getState() == NetworkInfo.State.CONNECTED)
						{
							return true;
						}

			}
		}catch(Exception ex){}

		return false;

	} //isInternetAvailable

}//CheckInternet
