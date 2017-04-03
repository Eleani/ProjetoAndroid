package com.example.nany_.projetoviagem;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private SpeechRecognizer sr;
    private static final String TAG = "My1Activity";
    private final Activity activity = this;
    private Button speakButton;
    private boolean isSpeechRecognizerAlive;
    public int teste = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        speakButton = (Button) findViewById(R.id.btLoginVoz);
        speakButton.setOnClickListener(this);

        requestPermissions();

        sr = SpeechRecognizer.createSpeechRecognizer(this);
        sr.setRecognitionListener(new Listener());
        Toast.makeText(getApplicationContext(), "onCreate", Toast.LENGTH_LONG).show();

    }

    private void requestPermissions(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},1);
        }
    }

    public void loginFacebook(View view){
        Intent gastoActivity = new Intent(this, MainActivity.class);
        startActivity(gastoActivity);
    }

    public void loginGoogle(View view){
        Intent gastoActivity = new Intent(this, MainActivity.class);
        startActivity(gastoActivity);
    }

    @Override
    public void onClick(View v) {

        Log.d(TAG, "onClick " + v.getId());

        Toast.makeText(getApplicationContext(), "onClick", Toast.LENGTH_LONG).show();

        teste = 0;
        if (v.getId() == R.id.btLoginVoz){
            Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
            intent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, "com.example.nany_.projetoviagem");
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "pt-BR");
            Log.d(TAG, "onClick 2 " + v.getId());
            sr.startListening(intent);

        }
    }

    public void abrirMain() {

        if (teste == 1){
            Intent mainActivity = new Intent(this, MainActivity.class);
            startActivity(mainActivity);
        }

    }

    class Listener implements RecognitionListener{

        @Override
        public void onReadyForSpeech(Bundle params) {

        }

        @Override
        public void onBeginningOfSpeech() {

        }

        @Override
        public void onRmsChanged(float rmsdB) {

        }

        @Override
        public void onBufferReceived(byte[] buffer) {

        }

        @Override
        public void onEndOfSpeech() {

        }

        @Override
        public void onError(int error) {

        }

        @Override
        public void onResults(Bundle results) {

            String str = new String();

            Log.d(TAG, "results 1");

            ArrayList data = results.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
            int cont = 0;
            for (int i = 0; i < 3; i++){
                str += data.get(i);
                String textoFalado = data.get(i).toString();
                if ((textoFalado.equals("login"))||(textoFalado.equals("logim"))||(textoFalado.equals("lugin"))){
                    cont = 1;
                }
                if ((textoFalado.equals("lugim"))||(textoFalado.equals("loguim"))||(textoFalado.equals("luguin"))){
                    cont = 1;
                }
                if ((textoFalado.equals("lugin in"))||(textoFalado.equals("login in"))||(textoFalado.equals("loguin"))){
                    cont = 1;
                }
            }

            if (cont == 1){
                teste = 1;
                abrirMain();
            }

        }

        @Override
        public void onPartialResults(Bundle partialResults) {

        }

        @Override
        public void onEvent(int eventType, Bundle params) {

        }
    }
}
