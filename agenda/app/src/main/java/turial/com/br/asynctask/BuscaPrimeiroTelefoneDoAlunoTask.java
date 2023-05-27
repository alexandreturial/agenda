package turial.com.br.asynctask;

import android.os.AsyncTask;

import turial.com.br.database.dao.TelefoneDAO;
import turial.com.br.model.Telefone;


public class BuscaPrimeiroTelefoneDoAlunoTask extends AsyncTask<Void, Void, Telefone> {
    private final TelefoneDAO telefoneDAO;
    private final int alunoId;
    private final PrimeiroTelefoneEncontradoListener listener;

    public BuscaPrimeiroTelefoneDoAlunoTask(
            TelefoneDAO dao,
            int alunoId, PrimeiroTelefoneEncontradoListener listener
    ) {
        this.telefoneDAO = dao;
        this.alunoId = alunoId;
        this.listener = listener;
    }

    @Override
    protected Telefone doInBackground(Void... voids) {
        return telefoneDAO.buscaPrimeiroTelefone(alunoId);
    }

    @Override
    protected void onPostExecute(Telefone primeiroTelefone) {
        super.onPostExecute(primeiroTelefone);
        listener.quandoEncontrado(primeiroTelefone);

    }

    public interface PrimeiroTelefoneEncontradoListener{
        void quandoEncontrado(Telefone telefoneEncontrado);
    }
}
