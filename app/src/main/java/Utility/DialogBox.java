package Utility;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.jigsaw.cookbook.R;


/**
 * Created by ss-51 on 1/4/2018.
 */

public class DialogBox extends Activity {
    public static void showDialog(final Context context, String title, String message) {
        if (context != null) {
            LayoutInflater layoutInflater = LayoutInflater.from(context);
            View view = layoutInflater.inflate(R.layout.custom_dialog, null);
            TextView tvTitle = (TextView) view.findViewById(R.id.dialogtitle);
            TextView tvMessage = (TextView) view.findViewById(R.id.dialog_message);
            tvTitle.setText(title);
            tvMessage.setText(message);
            final AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setView(view);
            builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {

                    dialogInterface.dismiss();
                }
            });
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }
}