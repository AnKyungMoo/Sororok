package com.nexters.sororok.util;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.nexters.sororok.item.ContactItem;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;

public class ContactsUtil {

    public static boolean savePhoneNumber(Context context, String name, String number) {
        ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();
        ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                .build());

//------------------------------------------------------ Names
        if (name != null) {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, name).build());
        }
//------------------------------------------------------ Mobile Number
        if (number != null) {
            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                    .withValue(ContactsContract.Data.MIMETYPE,
                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, number)
                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                    .build());
        }


// Asking the Contact provider to create a new contact
        try {
            context.getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
//            Toast.makeText(context, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            return false;
        }
    }

    public static ArrayList<ContactItem> getContactList(Context context) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{
            ContactsContract.CommonDataKinds.Phone.NUMBER,
            ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        };
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" COLLATE LOCALIZED ASC";
        Cursor cursor = context.getContentResolver().query(uri,projection,null,selectionArgs,sortOrder);
        LinkedHashSet<ContactItem> hashlist = new LinkedHashSet<>();
        if(cursor.moveToFirst()){
            do{
                ContactItem contactItem  = new ContactItem();
                contactItem.setUser_phNumber(cursor.getString(0));
                contactItem.setUser_Name(cursor.getString(1));

                hashlist.add(contactItem);
            } while (cursor.moveToNext());
        }
        ArrayList<ContactItem> contactItems = new ArrayList<>(hashlist);
        for(int i=0;i<contactItems.size();i++){
            contactItems.get(i).setId(i);
        }
        return contactItems;
    }

    public static ArrayList<String> getNumberList(Context context) {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        String[] projection = new String[]{
                ContactsContract.CommonDataKinds.Phone.NUMBER,
        };
        String[] selectionArgs = null;
        String sortOrder = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME+" COLLATE LOCALIZED ASC";
        Cursor cursor = context.getContentResolver().query(uri,projection,null,selectionArgs,sortOrder);
        LinkedHashSet<String> hashlist = new LinkedHashSet<>();
        if(cursor.moveToFirst()){
            do{
                String number = cursor.getString(0);
                number.replace("-","");
                number.replace("(","");
                number.replace(")","");

                hashlist.add(number);
            } while (cursor.moveToNext());
        }
        ArrayList<String> numbers = new ArrayList<>(hashlist);

        return numbers;
    }
}
