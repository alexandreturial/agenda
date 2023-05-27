package turial.com.br.asynctask;

import android.os.AsyncTask;

import turial.com.br.model.Telefone;

abstract class BaseAlunoComTelefoneTask extends AsyncTask<Void,Void,Void> {
    private final FinalizadaListener listener;

    BaseAlunoComTelefoneTask(FinalizadaListener listener) {
        this.listener = listener;
    }

    void vinculaAlunoComTelefone(int idAluno, Telefone...  telefones) {
        for (Telefone telefone :
                telefones) {
            telefone.setAlunoId(idAluno);
        }
    }

    @Override
    protected void onPostExecute(Void unused) {
        super.onPostExecute(unused);
        listener.quandoFinalizada();
    }

    public interface FinalizadaListener{
        void quandoFinalizada();
    }

}
