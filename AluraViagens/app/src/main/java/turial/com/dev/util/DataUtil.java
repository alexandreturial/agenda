package turial.com.dev.util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataUtil {

    public static final String DIA_E_MES = "dd/MM";

    public static String periodoTexto(int  dias) {
        final Calendar dataIda = Calendar.getInstance();
        final Calendar dataVolta = Calendar.getInstance();
        dataVolta.add(Calendar.DATE, dias);

        final SimpleDateFormat formatoBr = new SimpleDateFormat(DIA_E_MES);
        final String dataIdaFormat = formatoBr.format(dataIda.getTime());
        final String dataVoltaFormat = formatoBr.format(dataVolta.getTime());
        return (dataIdaFormat + " - " + dataVoltaFormat + " de " + dataVolta.get(Calendar.YEAR));
    }
}
