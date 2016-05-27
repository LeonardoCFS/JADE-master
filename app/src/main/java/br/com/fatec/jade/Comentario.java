package br.com.fatec.jade;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import java.util.ArrayList;

import br.com.fatec.jade.CustomAdapters.AvaliacaoAdapter;
import br.com.fatec.jade.modelos.Avaliacao;

public class Comentario extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comentario);
        ListView lv = (ListView) findViewById(R.id.listAvProduto);
        String chave = getIntent().getStringExtra("chave");
        String categoria = getIntent().getStringExtra("categoria");
        // Construct the data source
        ArrayList<Avaliacao> avaliacoes;
        //Log.d("QUANTIDADE DE AVALIACAO", String.valueOf(avaliacoes.size()));
        // Create the adapter to convert the array to views
        //AvaliacaoAdapter adapter = new AvaliacaoAdapter(this, avaliacoes);
        // Attach the adapter to a ListView
        //lv.setAdapter(adapter);
    }
}
