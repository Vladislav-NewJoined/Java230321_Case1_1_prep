import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.util.Date;

public class Case_1_1_1_Draft_3_AnotherDateEntering {
    // Задание 1. Кейс «Анализатор курса валют».
    // инфо здесь: Урок6 Модуль1 мин 44.30  ( https://lms.synergy.ru/learning/view/68488/?groupPeriodId=1045153&disciplineVersionId=25858&start=1 )
    // https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235
    public static void main(String[] args) throws IOException, ParseException {
//        String page1 = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235");
// Инициализация объекта date
        Date date = new Date();

        // Вывод текущей даты и времени с использованием toString()
        System.out.println(date.toString());    }

    private static String downloadWebPage(String url) throws IOException {
        StringBuilder result = new StringBuilder();
        String line;
        URLConnection urlConnection = new URL(url).openConnection();
        try (InputStream is = urlConnection.getInputStream();
             BufferedReader br = new BufferedReader(new InputStreamReader(is))) {
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
        }
        return result.toString();
    }
}
