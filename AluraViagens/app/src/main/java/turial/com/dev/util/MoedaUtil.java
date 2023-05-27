package turial.com.dev;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Locale;

public class MoedaUtil {

    public static final String IDIOMA = "pt";
    public static final String PAIS = "br";

    public static String formataMoedaParaBr(BigDecimal precoPacote) {
        NumberFormat formatoBrasileiro = DecimalFormat.getCurrencyInstance(new Locale(IDIOMA, PAIS));
        return formatoBrasileiro.format(precoPacote);

    }
}
