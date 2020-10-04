package in.inator.dbmsproject;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.preference.PreferenceManager;


public class MainActivity extends AppCompatActivity {
private boolean network_status = false,maintaining=false;
    String restoredState = "notwelcomed";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        network_check();
    }
    public void network_check() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE).getState() == NetworkInfo.State.CONNECTED ||
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI).getState() == NetworkInfo.State.CONNECTED) {
            //we are connected to a network true
            network_status = true;
            maintenanceCheck();
        } else {
            network_status = false;
            MainActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
                    alertDialog.setTitle("Connection Error");
                    alertDialog.setMessage("Unable to connect with the server.\nPlease check your internet connection and try again.");
                    alertDialog.setCancelable(false);
                    alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Exit",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    finish();
                                    System.exit(0);
                                }
                            });
                    alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Retry",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.dismiss();
                                    network_check();
                                }
                            });
                    alertDialog.show();
                }
            });


        }
        //return network_status;
    }
    public void openNext(){
        restoredState = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("Load_State", "welcomed");
        //Todo: Change to notwelcomed
        if (restoredState.equals("notwelcomed")) {
            startActivity(new Intent(MainActivity.this, Welcome.class));
        }else  if (restoredState.equals("welcomed")) {
            String authState = PreferenceManager.getDefaultSharedPreferences(getBaseContext()).getString("authstate", "0");
            if(authState.equals("0")){
                startActivity(new Intent(MainActivity.this, Register.class));

            }else {
                startActivity(new Intent(MainActivity.this, Dashboard.class));
            }
        }
        //restoredState = "welcomed";

    }
    public void maintenanceCheck(){
        if(maintaining) {
            AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
            alertDialog.setTitle("Maintenance break");
            alertDialog.setMessage("The servers are under maintenance please try again in some time!");
            alertDialog.setCancelable(false);
            alertDialog.setButton(AlertDialog.BUTTON_POSITIVE, "Retry",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();

                        }
                    });
            alertDialog.setButton(AlertDialog.BUTTON_NEGATIVE, "Exit",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                            System.exit(0);
                        }
                    });
            alertDialog.show();
        }else{
            openNext();
        }
    }

}