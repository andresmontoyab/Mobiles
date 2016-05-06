package co.edu.udea.compumovil.contentprovider;

import android.content.ContentProviderOperation;
import android.content.ContentResolver;
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
import android.widget.EditText;

import java.util.ArrayList;

public class Eliminar extends AppCompatActivity {
    ContentResolver cr;
    String detail;
    private EditText name;
    private EditText phone;
    String[] st;
    String numero;
    String nombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar);

        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        cr=getContentResolver();

        Bundle bundle = getIntent().getExtras();

        if(bundle.getString("details") != null) {
            detail = bundle.getString("details");
            Log.d("ae",detail.toString());
        }
        st = detail.split(" ");

       nombre = "";
        for(int i = 0; i < st.length-1; i++) {
            if(st[i] == null || st[i].equals("")) {
                break;
            }
            nombre += st[i] + " ";
        }
        name.setText(nombre);
        numero=st[st.length-1];
        phone.setText(st[st.length-1]);
    }
    public static void deleteContact(ContentResolver cr,String number) {


        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        String[] args = new String[] { String.valueOf(getContactID(
                cr, number)) };
        ops.add(ContentProviderOperation.newDelete(ContactsContract.RawContacts.CONTENT_URI)
                .withSelection(ContactsContract.RawContacts.CONTACT_ID+ "=?", args).build());
        try {
            cr.applyBatch(ContactsContract.AUTHORITY, ops);
        } catch (RemoteException e) {
            e.printStackTrace();
        } catch (OperationApplicationException e) {
            e.printStackTrace();
        }
    }
    public void Borrar(View v){

        this.deleteContact(cr,numero);
        Intent i=new Intent(this,MainActivity.class);
        startActivity(i);


    }

    private static long getContactID(ContentResolver contactHelper,
                                     String number) {
        Uri contactUri = Uri.withAppendedPath(ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] projection = { ContactsContract.PhoneLookup._ID };
        Cursor cursor = null;
        try {
            cursor = contactHelper.query(contactUri, projection, null, null,
                    null);
            if (cursor.moveToFirst()) {
                int personID = cursor.getColumnIndex(ContactsContract.PhoneLookup._ID);
                return cursor.getLong(personID);
            }
            return -1;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (cursor != null) {
                cursor.close();
                cursor = null;
            }
        }
        return -1;
    }
    public void update(View v){
        this.deleteContact(cr,numero);
        name=(EditText)findViewById(R.id.name);
        phone=(EditText)findViewById(R.id.phone);
        String number = phone.getText().toString().replaceAll(" ", "");


        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        int rawContactInsertIndex = ops.size();

        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null).build());

        //Phone Number
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                        rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, "1").build());

        //Display name/Contact name
        ops.add(ContentProviderOperation
                .newInsert(ContactsContract.Data.CONTENT_URI)
                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID,
                        rawContactInsertIndex)
                .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name.getText().toString())
                .build());

        try{

            cr.applyBatch(ContactsContract.AUTHORITY,ops);
            Intent i=new Intent(this, MainActivity.class);
            startActivity(i);

        }catch (RemoteException e){

            e.printStackTrace();

        }catch (OperationApplicationException e){

            e.printStackTrace();

        }





    }
}
