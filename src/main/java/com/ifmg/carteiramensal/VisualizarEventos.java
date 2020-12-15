package com.ifmg.carteiramensal;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class VisualizarEventos extends AppCompatActivity {


    private TextView tituloTxt;
    private ListView listaEventos;
    private TextView totalTxt;
    private Button novoBtn;
    private Button cancelarBtn;

    //operacao = 0 _ entrada | operacao = 1 _ saída
    private int operacao = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visualizar_eventos);

        tituloTxt = (TextView) findViewById(R.id.tituloTxt);
        listaEventos = (ListView) findViewById(R.id.listaEventos);
        totalTxt = (TextView) findViewById(R.id.valorTotalTxt);
        novoBtn = (Button) findViewById(R.id.novoBtn);
        cancelarBtn = (Button) findViewById(R.id.cancelarBtn);

        Intent intencao = getIntent();
        operacao = intencao.getIntExtra("acao", -1);

        ajusteOperacao();
        cadastrarEventos();
    }


    private void cadastrarEventos(){
        novoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(operacao != -1) {
                    Intent trocaAct = new Intent(VisualizarEventos.this, CadastroEdicaoEventos.class);

                    //para saber de onde veio a operação no botao, entrada ou saída, ambas serão de cadastro
                    if(operacao == 0){
                        trocaAct.putExtra("acao", 0);
                    }else{
                        trocaAct.putExtra("acao", 1);
                    }

                    startActivity(trocaAct);
                }
            }
        });
    }

    // metodo q auxilia a reutilização da activit alterando valores que serao utilizados
    private void ajusteOperacao(){
        //necessidade de se fazer uma busca no banco em relação ao eventos
        if(operacao == 0){
            tituloTxt.setText("Entradas");
        }else{
            if(operacao == 1){
                tituloTxt.setText("Saídas");
            }else{
                //erro ao configurar-se intents
                Toast.makeText(VisualizarEventos.this,"erro no parâmetro", Toast.LENGTH_LONG).show();
            }
        }

    }
}