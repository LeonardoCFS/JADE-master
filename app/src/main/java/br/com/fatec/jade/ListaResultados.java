package br.com.fatec.jade;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import java.util.ArrayList;
import br.com.fatec.jade.modelos.Resultado;

public class ListaResultados extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_resultados);
        String busca = getIntent().getStringExtra("busca");
        ListView lv = (ListView) findViewById(R.id.lista);

        lv.setOnItemClickListener(new ListClickHandler());

        // Construct the data source
        ArrayList<Resultado> resultados;
        // Create the adapter to convert the array to views
        //ResultadoAdapter adapter = new ResultadoAdapter(this, resultados);
        // Attach the adapter to a ListView
        //lv.setAdapter(adapter);
    }

    private class ListClickHandler implements AdapterView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> adapter, View view, int position, long arg3) {
            TextView textCategoria = (TextView) view.findViewById(R.id.resultadoCategoria);
            String categoria = textCategoria.getText().toString();
            TextView textChave = (TextView) view.findViewById(R.id.resultadoChave);
            String chave = textChave.getText().toString();
        }
    }
}
