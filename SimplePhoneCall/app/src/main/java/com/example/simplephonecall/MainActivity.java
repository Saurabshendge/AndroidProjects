package com.example.simplephonecall;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private SpeechRecognizer speechRecognizer;
    private Intent speechRecognizerIntent;
    private static final int REQUEST_CODE = 0;
    private String contactName1;
    private String contactName;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        voiceautomation();
    }

    private void voiceautomation() {
        Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voice.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say the name of the contact you want to call");
        startActivityForResult(voice, REQUEST_CODE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> results = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            // Do something with spokenText.
           // String musicplay=spokenText.replace("play", "").trim();
            String contactName1 = spokenText.replace("call", "").trim();
           callContact(contactName1);
          // showAllMusic(musicplay)

        }

    }

    @SuppressLint("Range")
    private void callContact(String contactName1) {
        // Split the spoken text into words
        String[] words = contactName1.split(" ");
        contactName = "";

        // Build the contact name from the spoken words
        for (String word : words) {
            contactName += word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase() + " ";
        }
        contactName = contactName.trim();

        // Retrieve the contact's phone number
        String phoneNumber = "";
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " LIKE ?", new String[]{"%" + contactName + "%"}, null);

        // Check if there are multiple contacts with the same name
        if (cursor.getCount() > 1) {
            // Show a list of matching contacts to the user
            ArrayList<String> contactList = new ArrayList<String>();
            while (cursor.moveToNext()) {
                String displayName = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                String phoneNumberTemp = "";
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor phoneCursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);

                if (phoneCursor.moveToFirst()) {
                    phoneNumberTemp = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }

                phoneCursor.close();

                // Add the contact name and phone number to the list
                contactList.add(displayName + " - " + phoneNumberTemp);
            }

            // Show the list of contacts to the user
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Multiple contacts found");
            builder.setItems(contactList.toArray(new String[0]), new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    // Extract the selected phone number from the contact list
                    String selectedContact = contactList.get(which);
                    String[] parts = selectedContact.split(" - ");
                    String selectedPhoneNumber = parts[1];

                    // Call the selected phone number
                    if (!selectedPhoneNumber.isEmpty()) {
                        Intent callIntent = new Intent(Intent.ACTION_DIAL);
                        callIntent.setData(Uri.parse("tel:" + selectedPhoneNumber));
                        //if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) == PackageManager.PERMISSION_GRANTED) {
                            startActivity(callIntent);
                        //}
                    } else {
                        // Handle case where selected contact does not have a phone number
                        Toast.makeText(MainActivity.this, "Selected contact does not have a phone number", Toast.LENGTH_SHORT).show();
                    }
                }
            });
            builder.show();
        } else if (cursor.getCount() == 1) {
            // Retrieve the contact's phone number
            if (cursor.moveToNext()) {
                String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                Cursor phoneCursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                        ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);

                if (phoneCursor.moveToFirst()) {
                    phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                }

                phoneCursor.close();
                if (phoneNumber == null) {
                    // Handle case where contact name was not found or contact does not have a phone number
                } else {
                    // Call the retrieved phone number
                    Intent callIntent = new Intent(Intent.ACTION_DIAL);
                    callIntent.setData(Uri.parse("tel:" + phoneNumber));
                    startActivity(callIntent);
                }
            }
        }
    }
}