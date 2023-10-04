import android.app.VoiceInteractor;
import android.app.assist.AssistContent;
import android.app.assist.AssistStructure;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.voice.VoiceInteractionService;
import android.service.voice.VoiceInteractionSession;
import android.view.View;

public class MyVoiceInteractionService extends VoiceInteractionService {
    @Override
    public void onReady() {
        super.onReady();

        // Create a new voice interaction session
        MyVoiceInteractionSession session = new MyVoiceInteractionSession(this);

        // Show the voice interaction prompt
        session.setShowWhenLocked(true);
        session.setShowWithInsecureKeyguard(true);
        startSession(session);
    }

}

