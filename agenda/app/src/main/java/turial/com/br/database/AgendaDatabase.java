package turial.com.br.database;

import static turial.com.br.database.AgendaMigrations.TODAS_MIGRATIONS;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import turial.com.br.database.convert.ConversorCalendar;
import turial.com.br.database.convert.ConversorTipoTelefone;
import turial.com.br.database.dao.AlunoDAO;
import turial.com.br.database.dao.TelefoneDAO;
import turial.com.br.model.Aluno;
import turial.com.br.model.Telefone;

@Database(entities = {Aluno.class, Telefone.class}, version = 6, exportSchema = false)
@TypeConverters({ ConversorCalendar.class, ConversorTipoTelefone.class })
public abstract class AgendaDatabase extends RoomDatabase {

    private static final String NOME_BANCO_DADOS = "agenda.db";

    public abstract AlunoDAO getRoomAlunoDao();

    public abstract TelefoneDAO getTefoneDAO();

    public static AgendaDatabase getInstance(Context context){
        return  Room.databaseBuilder(context, AgendaDatabase.class, NOME_BANCO_DADOS)
                .addMigrations(TODAS_MIGRATIONS)
                .build();
    }

}
