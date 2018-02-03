package Utility;

import android.app.Notification;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.Settings;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

import com.example.jigsaw.cookbook.R;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Created by jigsaw on 3/2/18.
 */

public class PDFGenerator {

    //write method takes two parameter pdf name and content
    //return true if pdf successfully created
    public static void write(String fname,List<String> array,Context context) {
        try {
            //Create file path for Pdf
            String fpath = Environment.getExternalStorageDirectory().getPath() + "/" + fname + ".pdf";
            File file = new File(fpath);
            if (!file.exists()) {
                file.createNewFile();
            }
            // To customise the text of the pdf
            // we can use FontFamily
            Font titleFont = new Font(Font.FontFamily.TIMES_ROMAN, 22, Font.BOLD
                    | Font.UNDERLINE, BaseColor.BLUE);
            Font bf12 = new Font(Font.FontFamily.TIMES_ROMAN,
                    16);
            // create an instance of itext document
            Document document = new Document();
            PdfWriter.getInstance(document,
                    new FileOutputStream(file.getAbsoluteFile()));
            document.open();
            //using add method in document to insert a paragraph
            Paragraph paragraph = new Paragraph(fname);
            paragraph.setFont(titleFont);
            document.add(paragraph);
            for(String content : array)
                document.add(new Paragraph(content));
            // close document
            document.close();
            sendNotification(fpath,context,fname);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    private static void sendNotification(String fpath, Context context,String fname){

        Intent i = new Intent();
        i.setAction(android.content.Intent.ACTION_VIEW);
        File file = new File(fpath);
        i.setDataAndType(Uri.fromFile(file), "application/pdf");
        PendingIntent pi = PendingIntent.getActivity(context, 0, i, 0);

        Notification notification = new NotificationCompat.Builder(context)
                .setTicker("CookBook Recipe Ingredients")
                .setSmallIcon(R.drawable.ic_pdf_icon)
                .setBadgeIconType(R.drawable.ic_pdf_icon)
                .setContentTitle(fname)
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setSound(Settings.System.DEFAULT_NOTIFICATION_URI)
                .setLights(0xff00ff00, 1500, 1500)
                .build();

        NotificationManagerCompat notificationManagerCompat =
                NotificationManagerCompat.from(context);
        notificationManagerCompat.notify(0, notification);





    }


}
