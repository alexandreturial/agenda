package turial.com.br.database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import turial.com.br.model.Aluno;
import turial.com.br.model.Telefone;
@Dao
public interface TelefoneDAO {
    @Query("SELECT * FROM Telefone WHERE alunoId = :alunoId LIMIT 1")
    Telefone buscaPrimeiroTelefone(int alunoId);


    @Insert
    void salva(Telefone... telefones);

    @Query("SELECT * FROM Telefone WHERE alunoId == :alunoId")
    List<Telefone> buscaTodosOsTelefonesDoAluno(int alunoId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void atualiza(Telefone...telefones);
}
