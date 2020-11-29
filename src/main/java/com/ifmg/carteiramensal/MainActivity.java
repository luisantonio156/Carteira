package com.ifmg.carteiramensal;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;


import java.util.Calendar;




public class MainActivity extends AppCompatActivity {

    private TextView titulo;
    private TextView entrada;
    private TextView saida;
    private TextView saldo;
    private ImageButton entraBtn;
    private ImageButton saidaBtn;
    private Button anteriorBtn;
    private Button proxBtn;
    private Button novoBtn;
    private Calendar hoje;
    private Calendar dataApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //criando link entre componentes java - xml

        titulo = (TextView) findViewById(R.id.tituloMain);
        entrada = (TextView) findViewById(R.id.entradaTxt);
        saida = (TextView) findViewById(R.id.saidaTxt);
        saldo = (TextView) findViewById(R.id.saldoTxt);

        entraBtn = (ImageButton) findViewById(R.id.entradaBtn);
        saidaBtn = (ImageButton) findViewById(R.id.saidaBtn);

        anteriorBtn = (Button) findViewById(R.id.anteriorBtn);
        proxBtn = (Button) findViewById(R.id.proximoBtn);
        novoBtn = (Button) findViewById(R.id.novoBtn);

        //implementa todos os eventos de botões
        cadastroEventos();

        //data e hora virtual
        dataApp = Calendar.getInstance();
        hoje = Calendar.getInstance();


        mostraDataApp();
    }

    private void mostraDataApp(){
        //0 - janeiro | 1 - fevereiro, ...
        String nomeMes[] = {"Janeiro", "Fevereiro", "Março", "Abril", "Maio", "Junho",
                "Julho", "Agosto", "Setembro", "Outubro", "Novembro", "Dezembro"};

        int mes = dataApp.get(Calendar.MONTH);
        int ano = dataApp.get(Calendar.YEAR);

        titulo.setText(nomeMes[mes] + "/" + ano);
    }

    //Aqui ainda prefiro definir direto no botão kkk
    private void cadastroEventos(){
        anteriorBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizaMes(-1);
            }
        });

        proxBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                atualizaMes(1);
            }
        });

        novoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventosDB db = new EventosDB(MainActivity.this);
                db.insereEvento();

                Toast.makeText(MainActivity.this, db.getDatabaseName(), Toast.LENGTH_LONG).show();
            }
        });
    }
    private void atualizaMes(int ajuste){
        dataApp.add(Calendar.MONTH, ajuste);

        //n se pode passar do mes atual
        if(ajuste > 0){
            if(dataApp.after(hoje)){
                dataApp.add(Calendar.MONTH, -1);
            }
        }else{

        }

        //avaliar se meses anteriores estão cadastrdos
        mostraDataApp();
    }
}