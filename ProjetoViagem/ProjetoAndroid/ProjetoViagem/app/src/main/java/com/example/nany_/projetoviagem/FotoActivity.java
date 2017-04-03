package com.example.nany_.projetoviagem;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class FotoActivity extends Activity {

    private static final int CAPTURAR_IMAGEM = 1;
    private Uri uri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_foto);
    }

    public void capturarFoto(View v) {
        boolean temCamera = getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA);
        if (temCamera) {

            File diretorio = Environment
                    .getExternalStoragePublicDirectory(
                            Environment.DIRECTORY_PICTURES);

            String nomeImagem = diretorio.getPath() + "/" +
                    System.currentTimeMillis() +
                    ".jpg";

            uri = Uri.fromFile(new File(nomeImagem));
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            intent.putExtra(MediaStore.EXTRA_OUTPUT, uri);

            startActivityForResult(intent, CAPTURAR_IMAGEM);

        }
    }

    @Override
    protected void onActivityResult(int requestCode,
                                    int resultCode, Intent data) {
        if (resultCode == RESULT_OK) {
            mostrarMensagem("Imagem capturada!");
            adicionarNaGaleria();
        } else {
            mostrarMensagem("Imagem não capturada!");
        }
    }

    private void mostrarMensagem(String msg) {
        Toast.makeText(this, msg,
                Toast.LENGTH_LONG)
                .show();
    }

    public void visualizarFoto(View v) {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setDataAndType(uri, "image/jpeg");
        startActivity(intent);
    }

    private void adicionarNaGaleria() {
        Intent intent = new Intent(
                Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
        intent.setData(uri);
        this.sendBroadcast(intent);
    }


}
