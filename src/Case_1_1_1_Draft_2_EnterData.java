import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Case_1_1_1_Draft_2_EnterData {
    // Задание 1. Кейс «Анализатор курса валют».
    // инфо здесь: Урок6 Модуль1 мин 44.30  ( https://lms.synergy.ru/learning/view/68488/?groupPeriodId=1045153&disciplineVersionId=25858&start=1 )
    // https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235
    public static void main(String[] args) throws IOException, ParseException {
//        String page1 = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235");
        String originalString = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235");


        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("Введите первую дату с разделителем '/': пример: 01/11/2021");
//        String date1 = buffered.readLine();

        System.out.println("Введите исходную дату с разделителем '/': пример: 03/03/2020");
        String originalDate = buffered.readLine();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(originalDate));
        c.add(Calendar.DATE, -1);  // number of days to add
        String oneDayBeforeDate;
        oneDayBeforeDate = sdf.format(c.getTime());  // entering oneDayBeforeDate
        c.add(Calendar.DATE, 2);  // number of days to add
        String oneDayAfterDate;
        oneDayAfterDate = sdf.format(c.getTime());  // entering oneDayAfterDate

       // Я дописал:
        System.out.println(originalDate);
        System.out.println(oneDayBeforeDate);
        System.out.println(oneDayAfterDate);

    }

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
