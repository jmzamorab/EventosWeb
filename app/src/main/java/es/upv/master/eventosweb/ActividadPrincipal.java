package es.upv.master.eventosweb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

public class ActividadPrincipal extends AppCompatActivity{
    WebView navegador;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        navegador = (WebView) findViewById(R.id.webkit);
        //navegador.loadUrl("https://eventos-d4f6a.firebaseapp.com/index.html");

        navegador.getSettings().setJavaScriptEnabled(true);
        navegador.loadUrl("file:///android_asset/index.html");
        //Intent intent = new Intent(Intent.ACTION_VIEW);
        //Uri uri = Uri.parse( "https://eventos-d4f6a.firebaseapp.com/index.html");
        //intent.setData(uri);
        //startActivity(intent);
    }
}
