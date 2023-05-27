package turial.com.br.asynctask;

import android.os.AsyncTask;

import java.util.List;

import turial.com.br.database.dao.TelefoneDAO;
import turial.com.br.model.Aluno;
import turial.com.br.model.Telefone;

public class BuscaTodosOsTelfonesAluno extends AsyncTask<Void, Void, List<Telefone>> {
    private final TelefoneDAO telefoneDAO;
    private final Aluno aluno;
    private final TelefonesDoAlunoListener listener;

    public BuscaTodosOsTelfonesAluno(TelefoneDAO telefoneDAO, Aluno aluno, TelefonesDoAlunoListener listener) {
        this.telefoneDAO = telefoneDAO;
        this.aluno = aluno;
        this.listener = listener;
    }


    @Override
    protected List<Telefone> doInBackground(Void... voids) {
        return telefoneDAO.buscaTodosOsTelefonesDoAluno(this.aluno.getId());
    }

    @Override
    protected void onPostExecute(List<Telefone> telefones) {
        super.onPostExecute(telefones);
        listener.quandoEncotrados(telefones);
    }

    public interface TelefonesDoAlunoListener{
        void quandoEncotrados(List<Telefone> telefones);
    }
}
