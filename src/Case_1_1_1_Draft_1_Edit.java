import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Case_1_1_1_Draft_1_Edit {
    // Задание 1. Кейс «Анализатор курса валют».
    // инфо здесь: Урок6 Модуль1 мин 44.30  ( https://lms.synergy.ru/learning/view/68488/?groupPeriodId=1045153&disciplineVersionId=25858&start=1 )
    // https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235
    public static void main(String[] args) throws IOException {
//        String page1 = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235");
        String originalString = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235");


        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Введите первую дату с разделителем '/': пример: 12/11/2021");
        String date1 = buffered.readLine();
        System.out.println("Введите вторую дату с разделителем '/': пример: 12/11/2021");
        String date2 = buffered.readLine();
        System.out.println("Введите третью дату с разделителем '/': пример: 12/11/2021");
        String date3 = buffered.readLine();

        // Задаём адрес исходной веб-страницы Центробанка в текстовом формате.
        String originalStrText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";
        System.out.println(originalStrText + System.lineSeparator());

        // Меняем в адресе исходной страницы даты на введённые (в трех строках).
        String urlWithDate1 = originalStrText.replaceAll("12/11/2021", date1);
        System.out.println(urlWithDate1);
        String urlWithDate2 = originalStrText.replaceAll("12/11/2021", date2);
        System.out.println(urlWithDate2);
        String urlWithDate3 = originalStrText.replaceAll("12/11/2021", date3);
        System.out.println(urlWithDate3);

        String page1 = downloadWebPage(urlWithDate1);
        int startIndex1 = page1.lastIndexOf("<Value>");
        int endIndex1 = page1.lastIndexOf("</Value>");
        String courseStr1 = page1.substring(startIndex1 + 7, endIndex1);
        System.out.println(courseStr1);



//        String page1 = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235");
//        int startIndex1 = page1.lastIndexOf("<Value>");
//        int endIndex1 = page1.lastIndexOf("</Value>");
//        String courseStr1 = page1.substring(startIndex1 + 7, endIndex1);
//
//        String page2 = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2020&date_req2=13/11/2020&VAL_NM_RQ=R01235");
//        int startIndex2 = page2.lastIndexOf("<Value>");
//        int endIndex2 = page2.lastIndexOf("</Value>");
//        String courseStr2 = page2.substring(startIndex2 + 7, endIndex2);
//
//        System.out.println(courseStr1);
//        System.out.println(courseStr2);
//
//        double course1 = Double.parseDouble(courseStr1.replace(",", "."));
//        double course2 = Double.parseDouble(courseStr2.replace(",", "."));
//
//        if (course1 > course2) {
//            System.out.print("Курс подрос на ");
//            System.out.print(course1 - course2);
//        } else {
//            System.out.print("Год назад курс был больше на ");
//            System.out.print(course2 - course1);
//        }
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
