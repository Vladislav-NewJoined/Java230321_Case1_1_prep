import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class case_1_1 {
    // Задание 1. Кейс «Анализатор курса валют».
    // инфо здесь: Урок6 Модуль1 мин 44.30  ( https://lms.synergy.ru/learning/view/68488/?groupPeriodId=1045153&disciplineVersionId=25858&start=1 )
    // https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235
    public static void main(String[] args) throws IOException {
        String page1 = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235");
        int startIndex1 = page1.lastIndexOf("<Value>");
        int endIndex1 = page1.lastIndexOf("</Value>");
        String courseStr1 = page1.substring(startIndex1 + 7, endIndex1);

        String page2 = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2020&date_req2=13/11/2020&VAL_NM_RQ=R01235");
        int startIndex2 = page2.lastIndexOf("<Value>");
        int endIndex2 = page2.lastIndexOf("</Value>");
        String courseStr2 = page2.substring(startIndex2 + 7, endIndex2);

        System.out.println(courseStr1);
        System.out.println(courseStr2);

        double course1 = Double.parseDouble(courseStr1.replace(",", "."));
        double course2 = Double.parseDouble(courseStr2.replace(",", "."));

        if (course1 > course2) {
            System.out.print("Курс подрос на ");
            System.out.print(course1 - course2);
        } else {
            System.out.print("Год назад курс был больше на ");
            System.out.print(course2 - course1);
        }
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
