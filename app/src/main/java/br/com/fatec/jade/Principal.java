package br.com.fatec.jade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.util.JsonReader;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.vision.barcode.Barcode;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import br.com.fatec.jade.barcode.BarcodeCaptureActivity;
import br.com.fatec.jade.opcoes.SettingsActivity;

public class Principal extends AppCompatActivity implements View.OnClickListener {

    private static final int RC_BARCODE_CAPTURE = 9001;
    private static final String TAG = "BarcodeMain";
    private JSONObject produto;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pricipal);
        findViewById(R.id.fab).setOnClickListener(this);
        findViewById(R.id.botaoBusca).setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.sobre) {
            Intent it = new Intent(this, Sobre.class);
            startActivity(it);
        } else if (id == R.id.login) {
            Intent it = new Intent(this, Login.class);
            it.putExtra("abrindo", false);
            startActivity(it);
        } else if (id == R.id.opcoes) {
            Intent it = new Intent(this, SettingsActivity.class);
            startActivity(it);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.fab) {
            Intent intent = new Intent(this, BarcodeCaptureActivity.class);
            SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
            Boolean focus = preferences.getBoolean("foco", false);
            Boolean flash = preferences.getBoolean("flash", false);
            intent.putExtra(BarcodeCaptureActivity.AutoFocus, focus);
            intent.putExtra(BarcodeCaptureActivity.UseFlash, flash);
            startActivityForResult(intent, RC_BARCODE_CAPTURE);
        }
        if (v.getId() == R.id.botaoBusca) {
            this.buscar(v);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == RC_BARCODE_CAPTURE) {
            if (resultCode == CommonStatusCodes.SUCCESS) {
                if (data != null) {
                    Barcode barcode = data.getParcelableExtra(BarcodeCaptureActivity.BarcodeObject);
                    Log.d(TAG, "Barcode read: " + barcode.displayValue);
                        Intent it = new Intent(Principal.this, ExibicaoProduto.class);
                        it.putExtra("chave", barcode.displayValue);
                        startActivity(it);
                } else {
                    Log.d(TAG, "No barcode captured, intent data is null");
                }
            } else {
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }


    private void buscar(View v) {
        //EditText textoBusca = (EditText) findViewById(R.id.campoBusca);
        //String busca = textoBusca.getText().toString();
        //Intent it = new Intent(this, ListaResultados.class);
        //it.putExtra("busca", busca);
        //startActivity(it);
        GetData getData = new GetData();
        getData.execute();

    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
        this.finish();
    }

    public class GetData extends AsyncTask<Void,Void,Boolean> {



        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            //Sua tarefa a ser executada em background
            try {
                // Classe definida no ultimo post, citado no texto anterior
                JsonClass json = new JsonClass();
                produto = json.getJSONFromUrl("http://jade.sa-east-1.elasticbeanstalk.com/Funcoes");
                try {
                    String test = produto.getString("nome");
                    Log.i("JRF", test);
                } catch (JSONException e) {
                    Log.i("JRF", "Falhou !!!");
                    e.printStackTrace();
                }
                return true;
            } catch (Exception e) {
                return false;
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected void onPostExecute(Boolean result) {
            super.onPostExecute(result);
            Intent it = new Intent(Principal.this, ExibicaoProduto.class);
            try {
                it.putExtra("nome", produto.getString("nome"));
                it.putExtra("status", produto.getString("status"));
                it.putExtra("categoria", produto.getString("categoria"));
                it.putExtra("nota", produto.getDouble("nota"));
                it.putExtra("imagem",produto.getString("imagem"));
            }catch(JSONException e){
                Log.i("Intent", "Deu ruim");
                e.printStackTrace();
            }
            startActivity(it);
        }
    }
}
