package es.upv.master.eventosweb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class ActividadPrincipal extends AppCompatActivity{
    private ProgressBar barraProgreso;
    ProgressDialog dialogo;
    WebView navegador;
    Button btnDetener, btnAnterior, btnSiguiente;
    Button btnGo;
    EditText edtHttp;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.actividad_principal);
        btnDetener = (Button) findViewById(R.id.btnDetener);
        btnAnterior = (Button) findViewById(R.id.btnAnterior);
        btnSiguiente = (Button) findViewById(R.id.btnSiguiente);
        btnGo = (Button) findViewById(R.id.btnIr);
        edtHttp = (EditText) findViewById(R.id.edtPagina);
        navegador = (WebView) findViewById(R.id.webkit);
        navegador.getSettings().setJavaScriptEnabled(true);
        navegador.getSettings().setBuiltInZoomControls(false);
        navegador.loadUrl("https://eventos-d4f6a.firebaseapp.com/index.html");

        btnGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edtHttp.getText() != null)
                {
                    navegador.loadUrl(edtHttp.getText().toString());
                }
            }
        });

        //navegador.loadUrl("file:///android_asset/index.html");
        //Intent intent = new Intent(Intent.ACTION_VIEW);
        //Uri uri = Uri.parse( "https://eventos-d4f6a.firebaseapp.com/index.html");
        //intent.setData(uri);
        //startActivity(intent);

        navegador.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request)
            {
                return false;
            }
        });
        barraProgreso = (ProgressBar) findViewById(R.id.barraProgreso);

        navegador.setWebChromeClient(new WebChromeClient() {
            @Override
            public void onProgressChanged(WebView view, int progreso) {
                barraProgreso.setProgress(0);
                barraProgreso.setVisibility(View.VISIBLE);
                ActividadPrincipal.this.setProgress(progreso * 1000);
                barraProgreso.incrementProgressBy(progreso);
                if (progreso == 100) {
                    barraProgreso.setVisibility(View.GONE);
                }
            }
        });

        navegador.setWebViewClient(new WebViewClient(){
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                dialogo = new ProgressDialog(ActividadPrincipal.this);
                dialogo.setMessage("Cargando...");
                dialogo.setCancelable(true);
                dialogo.show();
                btnDetener.setEnabled(true);
            }
            @Override
            public void onPageFinished(WebView view, String url) {
                dialogo.dismiss();
                btnDetener.setEnabled(false);
                if (view.canGoBack()) {
                    btnAnterior.setEnabled(true);
                } else {
                    btnAnterior.setEnabled(false);
                }
                if (view.canGoForward()) {
                    btnSiguiente.setEnabled(true);
                } else {
                    btnSiguiente.setEnabled(false);
                }
            }
        });

    }

    public void detenerCarga(View v) {
        navegador.stopLoading();
    }
    public void irPaginaAnterior(View v) {
        navegador.goBack();
    }
    public void irPaginaSiguiente(View v) {
        navegador.goForward();
    }
}
