package br.com.fatec.jade;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import br.com.fatec.jade.modelos.Marca;

public class ExibicaoMarca extends AppCompatActivity {

    String chave;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private String consumidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_false_marca);
        String nome = getIntent().getStringExtra("nome");
        String imagem = getIntent().getStringExtra("imagem");
        chave = getIntent().getStringExtra("chave");
        float nota = getIntent().getFloatExtra("nota", 0);
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        consumidor = preferences.getString("usuario", "FALHOU DE NOVO");

        TextView nomeText = (TextView) findViewById(R.id.marcaNomeMFalse);
        RatingBar notaBar = (RatingBar) findViewById(R.id.barraAvaliacaoMarca);
        ImageView imagemView = (ImageView) findViewById(R.id.imgMarca);
        nomeText.setText(nome);
        notaBar.setRating(nota);
        Resources res = getResources();
        int resourceId = res.getIdentifier(imagem, "drawable", getPackageName());
        imagemView.setImageResource(resourceId);
        //impede que o foco não vá para o campo de texto e inicie o teclado
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

    }

    public void listarComentarioMarca(View view) {
        Intent it = new Intent(this, Comentario.class);
        it.putExtra("chave", chave);
        it.putExtra("categoria", "marca");
        startActivity(it);
    }

    public void novoComentarioMarca(View view) {
        EditText texto = (EditText) findViewById(R.id.campoComentario);
        RatingBar notaBar = (RatingBar) findViewById(R.id.barraAvaliacaoMarca);
        String texto_avaliacao = texto.getText().toString();
        texto.setText("");
        Toast.makeText(getApplicationContext(), "Enviado", Toast.LENGTH_SHORT).show();
    }
}
