package com.example.voicecmd1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.ContentResolver;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Set up the speech recognizer and intent
//        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
//        speechRecognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
//        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
//                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
//        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 1);
//        speechRecognizerIntent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say the name of the contact you want to call");
//        startActivityForResult(speechRecognizerIntent, 1);
//        speechRecognizer.startListening(speechRecognizerIntent);

        // Start listening for voice commands
        voiceautomation();
    }

    private void voiceautomation() {
        Intent voice = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        voice.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());
        voice.putExtra(RecognizerIntent.EXTRA_PROMPT, "Say the name of the contact you want to call");
        startActivityForResult(voice, REQUEST_CODE);

    }

    //    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
//            ArrayList<String> matches = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
//            String spokenText = matches.get(0);
//            // Extract the name of the contact to call
//            String contactName = spokenText.replace("call", "").trim();
//
//            // Get the phone number of the contact from the device's contact list
//            String phoneNumber = getContactPhoneNumber(contactName);
//
//            if (phoneNumber != null) {
//                // Initiate the call
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:" + phoneNumber));
//                startActivity(callIntent);
//            } else {
//                Toast.makeText(this, "Could not find contact", Toast.LENGTH_SHORT).show();
//            }
//    }
//}
    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    Intent data) {
        if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
            ArrayList<String> results = data.getStringArrayListExtra(
                    RecognizerIntent.EXTRA_RESULTS);
            String spokenText = results.get(0);
            // Do something with spokenText.
            String contactName = spokenText.replace("call", "").trim();
            String phoneNumber = getContactPhoneNumber(contactName);
            if (phoneNumber != null) {
                // Initiate the call
//                String phone_number=phoneNumber.toString();
//                Intent callIntent = new Intent(Intent.ACTION_CALL);
//                callIntent.setData(Uri.parse("tel:"+phone_number));
//                startActivity(callIntent);

                String phone_number=phoneNumber.toString();

                Uri phoneUri = Uri.parse("tel:" + phone_number);
                Intent callIntent = new Intent(Intent.ACTION_DIAL, phoneUri);
                startActivity(callIntent);
            } else {
                Toast.makeText(this, "Could not find contact", Toast.LENGTH_SHORT).show();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @SuppressLint("Range")
    private String getContactPhoneNumber(String contactName) {
        String phoneNumber = "";
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS}, 0);
        }

        // Define the contact search criteria
//        String[] projection = new String[]{ContactsContract.CommonDataKinds.Phone.NUMBER};
//        String selection = ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = ?";
//        String[] selectionArgs = new String[]{contactName};
//
//        // Search the contact list for a matching contact
//        Cursor cursor = getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
//                projection, selection, selectionArgs, null);
        ContentResolver cr = getContentResolver();
        Cursor cursor = cr.query(ContactsContract.Contacts.CONTENT_URI, null,
                ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME + " = ?", new String[]{contactName}, null);


//        if (cursor != null && cursor.moveToFirst()) {
//            // Extract the phone number of the matching contact
//            phoneNumber = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
//            cursor.close();
//        }
//
//        return phoneNumber;
        if (cursor.getCount() >0 && cursor.moveToNext()) {
            // Retrieve the contact's phone number
            String contactId = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
            Cursor phoneCursor = cr.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI, null,
                    ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?", new String[]{contactId}, null);

            if (phoneCursor.moveToFirst()) {
                phoneNumber = phoneCursor.getString(phoneCursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
            }

            phoneCursor.close();
        }
        cursor.close();

//        if (phoneNumber.isEmpty()) {
//            // Provide feedback to the user that the contact was not found or does not have a phone number
//            Toast.makeText(this, "Could not find a phone number for " + contactName, Toast.LENGTH_SHORT).show();
//        } else {
//            // Call the retrieved phone number
//            Intent callIntent = new Intent(Intent.ACTION_CALL, Uri.parse("tel:" + phoneNumber));
//            startActivity(callIntent);
//        }

        return phoneNumber;
    }
}