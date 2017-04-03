package com.example.nany_.projetoviagem;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

/**
 * Created by nany_ on 26/03/2017.
 */

public class Camera extends Activity {

    private static final int CAPTURAR_IMAGEM = 1;
    private Uri uri;

    public void capturarImagem(View v){
        boolean temCamera = getPackageManager()
                .hasSystemFeature(PackageManager.FEATURE_CAMERA);
        if(temCamera){

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
        if (requestCode == CAPTURAR_IMAGEM) {
            if (resultCode == RESULT_OK) {
                mostrarMensagem("Imagem capturada!");
                adicionarNaViagem();
            } else {
                mostrarMensagem("Imagem não capturada!");
            }
        }
    }

    private void mostrarMensagem(String msg){
        Toast.makeText(this, msg,
                Toast.LENGTH_LONG)
                .show();
    }

    private void adicionarNaViagem(){



    }

}
