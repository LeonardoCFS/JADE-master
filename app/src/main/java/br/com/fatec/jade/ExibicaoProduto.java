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

import com.squareup.picasso.Picasso;

import br.com.fatec.jade.modelos.Produto;

public class ExibicaoProduto extends AppCompatActivity {

    String chave;
    public static final String MY_PREFS_NAME = "MyPrefsFile";
    private String consumidor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String nome = getIntent().getStringExtra("nome");
        String categoria = getIntent().getStringExtra("categoria");
        String status = getIntent().getStringExtra("status");
        String imagem = getIntent().getStringExtra("imagem");
        Float nota = getIntent().getFloatExtra("nota",5);
        if(status.equals("S")){
            setContentView(R.layout.activity_true_produto);
        }else{
            setContentView(R.layout.activity_false_produto);
        }


        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        consumidor = preferences.getString("usuario", "FALHOU DE NOVO");

        TextView nomeText = (TextView) findViewById(R.id.prodNomeFalse);
        ImageView imagemView = (ImageView) findViewById(R.id.imagemProdutoFalse);
        Picasso.with(this).load("http://static.wixstatic.com/media/a37a6e_5c7f14d8271d465381bd035eea79e2e3.png_256").into(imagemView);
        RatingBar notaBar = (RatingBar) findViewById(R.id.barraAvaliacaoProduto);
        nomeText.setText(nome);
        notaBar.setRating(nota);

        //impede que o foco não vá para o campo de texto e inicie o teclado
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
    }

    public void listarComentarioProduto(View view) {
        Intent it = new Intent(this, Comentario.class);
        it.putExtra("chave", chave);
        it.putExtra("categoria", "produto");
        startActivity(it);
    }

    public void novoComentarioProduto(View view) {
        EditText texto = (EditText) findViewById(R.id.campoComentario);
        RatingBar notaBar = (RatingBar) findViewById(R.id.barraAvaliacaoProduto);
        String texto_avaliacao = texto.getText().toString();
        texto.setText("");
        Toast.makeText(getApplicationContext(), "Enviado", Toast.LENGTH_SHORT).show();
    }
}
