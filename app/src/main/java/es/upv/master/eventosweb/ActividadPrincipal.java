package es.upv.master.eventosweb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ActividadPrincipal extends AppCompatActivity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = Uri.parse( "https://eventos-d4f6a.firebaseapp.com//index.html");
        intent.setData(uri);
        startActivity(intent);
    }
}
