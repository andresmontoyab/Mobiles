package co.edu.udea.compumovil.contentprovider;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.database.Cursor;
import android.net.Uri;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
ListView listView;

    ContentResolver cr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView)findViewById(R.id.listView);
        cr=getContentResolver();
        String records[] = getRecords();

        listView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, records));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = (listView.getItemAtPosition(position)).toString();
                Intent intent = new Intent(MainActivity.this, Eliminar.class);
                intent.putExtra("details", text);
                startActivity(intent);
            }
        });

    }


    public String[] getRecords(){

        String phoneNumber = null;
        // String email = null;

        Uri CONTENT_URI = ContactsContract.Contacts.CONTENT_URI;
        Log.d("oee",CONTENT_URI+" ");
        String _ID = ContactsContract.Contacts._ID;
        Log.d("oee",_ID+" ");
        String DISPLAY_NAME = ContactsContract.Contacts.DISPLAY_NAME;
        Log.d("oee",DISPLAY_NAME+" ");
        String HAS_PHONE_NUMBER = ContactsContract.Contacts.HAS_PHONE_NUMBER;
        Log.d("oee",HAS_PHONE_NUMBER+" ");

        Uri PhoneCONTENT_URI = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Log.d("oee",PhoneCONTENT_URI+"");
        String Phone_CONTACT_ID = ContactsContract.CommonDataKinds.Phone.CONTACT_ID;
        Log.d("oee",Phone_CONTACT_ID+"");
        String NUMBER = ContactsContract.CommonDataKinds.Phone.NUMBER;
        Log.d("oee",NUMBER+"");
/*
        Uri EmailCONTENT_URI = ContactsContract.CommonDataKinds.Email.CONTENT_URI;
        String EmailCONTACT_ID = ContactsContract.CommonDataKinds.Email.CONTACT_ID;
        String DATA = ContactsContract.CommonDataKinds.Email.DATA;
*/
        StringBuffer output = new StringBuffer();

        ContentResolver contentResolver = getContentResolver();

        Cursor cursor = contentResolver.query(CONTENT_URI, null, null, null, ContactsContract.Contacts.DISPLAY_NAME);

        String records[] = new String[cursor.getCount()];
        int i=0;

        if (cursor.getCount() > 0) {
            // Loop for every contact in the phone
            while (cursor.moveToNext()) {


                String contact_id = cursor.getString(cursor.getColumnIndex(_ID));
                Log.d("oee",contact_id+"  "+i);
                String name = cursor.getString(cursor.getColumnIndex(DISPLAY_NAME));
                Log.d("oee",name+" "+i);
                int hasPhoneNumber = Integer.parseInt(cursor.getString(cursor.getColumnIndex(HAS_PHONE_NUMBER)));
                Log.d("oee",hasPhoneNumber+" "+i);
                if (hasPhoneNumber > 0) {

                    output.append("\n First Name:" + name);

                    // Query and loop for every phone number of the contact
                    Cursor phoneCursor = contentResolver.query(PhoneCONTENT_URI, null, Phone_CONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (phoneCursor.moveToNext()) {

                        phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(NUMBER));
                        output.append("\n Phone number:" + phoneNumber);

                    }
                    records[i++] =String.format("%-15.15s %s", name, phoneNumber);
                    phoneCursor.close();

                }
                 /*
                    // Query and loop for every email of the contact
                    Cursor emailCursor = contentResolver.query(EmailCONTENT_URI, null, EmailCONTACT_ID + " = ?", new String[]{contact_id}, null);

                    while (emailCursor.moveToNext()) {

                        email = emailCursor.getString(emailCursor.getColumnIndex(DATA));

                        output.append("\nEmail:" + email);

                    }

                    emailCursor.close();
                    */

                //output.append("\n");

            }

            //outputText.setText(output);
            Log.d("ContentProvider", "text: " + output);
        }else{
            Log.d("ContentProvider", "Cursor:" + cursor.getCount());

        }
        return records;
    }

    public void Insertar(View v){
        Intent i=new Intent(this, Insertar.class);
        startActivity(i);


    }


}
