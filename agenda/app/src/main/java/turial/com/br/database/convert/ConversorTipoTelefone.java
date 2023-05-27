package turial.com.br.database.convert;

import androidx.room.TypeConverter;

import turial.com.br.model.TipoTelefone;

public class ConversorTipoTelefone {

    @TypeConverter
    public String paraTipoTelefoneString(TipoTelefone telefone) {
        if (telefone != null) {
            return telefone.name();
        }
        return null;
    }

    @TypeConverter
    public TipoTelefone paraTipoTelefone(String valor) {
        if (valor != null) {
            return TipoTelefone.valueOf(valor);
        }
        return TipoTelefone.FIXO;
    }
}
