package Ferramentas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class EventosDB extends SQLiteOpenHelper{

    private Context contexto;

    public EventosDB(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public EventosDB(Context cont){
        super(cont, "evento", null, 1);
        contexto = cont;
    }


    //criar e config tabelas
    @Override
    public void onCreate(SQLiteDatabase db) {
        final String criaTabela = "CREATE TABLE IF NOT EXISTS evento (id INTEGER PRIMARY KEY AUTOINCREMENT, nome TEXT," +
                " valor REAL, imagem TEXT, dataocorreu DATE, datacadastro DATE, datavalidade DATE)";

        db.execSQL(criaTabela);
    }

    public void insereEvento(){

        try(SQLiteDatabase db = this.getWritableDatabase()){

            String sql = "INSERT into evento(nome, valor) VALUES ('evento1', 89)";

            db.execSQL(sql);

        } catch (SQLiteException ex){
            ex.printStackTrace();
        }

    }

    public void atualizaEvento(){

    }

    public void buscaEvento(){

    }






    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //fica parado até a att da Activity de Update
    }
}
