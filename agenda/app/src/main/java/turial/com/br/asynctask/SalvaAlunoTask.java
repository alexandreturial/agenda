package turial.com.br.asynctask;

import turial.com.br.database.dao.AlunoDAO;
import turial.com.br.database.dao.TelefoneDAO;
import turial.com.br.model.Aluno;
import turial.com.br.model.Telefone;

public class SalvaAlunoTask  extends BaseAlunoComTelefoneTask {
    private final AlunoDAO alunoDao;
    private final Aluno aluno;
    private final Telefone telefoneFixo;
    private final Telefone telefoneCelular;
    private final TelefoneDAO telefoneDAO;

    public SalvaAlunoTask(AlunoDAO alunoDao,
                          Aluno aluno, Telefone telefoneFixo, Telefone telefoneCelular,
                          TelefoneDAO telefoneDAO, FinalizadaListener listener) {
        super(listener);
        this.alunoDao = alunoDao;
        this.aluno = aluno;
        this.telefoneFixo = telefoneFixo;
        this.telefoneCelular = telefoneCelular;
        this.telefoneDAO = telefoneDAO;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        try {
            Thread.sleep(8000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int idAluno =  alunoDao.salva(aluno).intValue();
        vinculaAlunoComTelefone(idAluno, telefoneFixo, telefoneCelular);
        telefoneDAO.salva(telefoneFixo, telefoneCelular);

        return null;
    }


}
