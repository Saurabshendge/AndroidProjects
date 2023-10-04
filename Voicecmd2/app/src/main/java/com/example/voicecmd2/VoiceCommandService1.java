package com.example.voicecmd2;

import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class VoiceCommandService1 extends Service {
    private SpeechRecognizer speechRecognizer;

    @Override
    public void onCreate() {
        super.onCreate();

        // Initialize the SpeechRecognizer
        speechRecognizer = SpeechRecognizer.createSpeechRecognizer(this);
        speechRecognizer.setRecognitionListener(new RecognitionListener() {
            @Override
            public void onReadyForSpeech(Bundle bundle) {

            }

            @Override
            public void onBeginningOfSpeech() {

            }

            @Override
            public void onRmsChanged(float v) {

            }

            @Override
            public void onBufferReceived(byte[] bytes) {

            }

            @Override
            public void onEndOfSpeech() {

            }

            @Override
            public void onError(int i) {

            }

            @Override
            public void onResults(Bundle results) {
                // Process the recognized speech
                ArrayList<String> matches = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
                if (matches != null && !matches.isEmpty()) {
                    String voiceCommand = matches.get(0);
                    processVoiceCommand(voiceCommand);
                }
            }

            @Override
            public void onPartialResults(Bundle bundle) {

            }

            @Override
            public void onEvent(int i, Bundle bundle) {

            }

        });
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        // Start listening for voice input
        startListening();

        return super.onStartCommand(intent, flags, startId);
    }
    private void startListening() {
        Intent speechIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        speechIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);

        // Start the speech recognition
        speechRecognizer.startListening(speechIntent);
    }
    private void processVoiceCommand(String command) {
        // Check if the command matches the activation phrase
        if (command.equalsIgnoreCase("OK App")) {
            // Launch your app or trigger the desired action
            Intent launchIntent = getPackageManager().getLaunchIntentForPackage("com.your.package");
            if (launchIntent != null) {
                startActivity(launchIntent);
            }
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();

        // Release resources
        if (speechRecognizer != null) {
            speechRecognizer.destroy();
        }
    }




    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
