import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Case_1_1_1_Draft_6_EditCorrected {
    // Задание 1. Кейс «Анализатор курса валют».
    // инфо здесь: Урок6 Модуль1 мин 44.30  ( https://lms.synergy.ru/learning/view/68488/?groupPeriodId=1045153&disciplineVersionId=25858&start=1 )
    // https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235
    public static void main(String[] args) throws IOException, ParseException {
        String originalString = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235");

        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите исходную дату с разделителем '/': пример: 12/02/2020");
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

        System.out.println(originalDate);
        System.out.println(oneDayBeforeDate);
        System.out.println(oneDayAfterDate);

        // Задаём адрес исходной веб-страницы Центробанка в текстовом формате.
        System.out.println("Исходная страница:");
        String originalStrText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";
        System.out.println(originalStrText + System.lineSeparator());

        // Меняем в адресе исходной страницы даты на введённые (в трех строках).
        System.out.println("Страницы после ввода даты:");
        String urlWithDate1 = originalStrText.replaceAll("12/11/2021", originalDate);
        System.out.println(urlWithDate1);
        String urlWithDate2 = originalStrText.replaceAll("12/11/2021", oneDayBeforeDate);
        System.out.println(urlWithDate2);
        String urlWithDate3 = originalStrText.replaceAll("12/11/2021", oneDayAfterDate);
        System.out.println(urlWithDate3);
        System.out.println();

        String page1 = downloadWebPage(urlWithDate1);
        if (page1.contains("<Value>")) {
            int startIndex1 = page1.lastIndexOf("<Value>");
            int endIndex1 = page1.lastIndexOf("</Value>");
            String courseStr1 = page1.substring(startIndex1 + 7, endIndex1);
            System.out.println("Курс на " + originalDate + ": " + courseStr1);
        } else {
            String courseStr1 = "На указанную дату Курс отсутствует";
            System.out.println("Курс на " + originalDate + ": " + courseStr1); // как например на дату 02/01/2020
        }

        String page2 = downloadWebPage(urlWithDate2);
        if (page2.contains("<Value>")) {
            int startIndex2 = page2.lastIndexOf("<Value>");
            int endIndex2 = page2.lastIndexOf("</Value>");
            String courseStr2 = page2.substring(startIndex2 + 7, endIndex2);
            System.out.println("Курс на " + oneDayBeforeDate + ": " + courseStr2);
        } else {
            String courseStr2 = "На указанную дату Курс отсутствует";
            System.out.println("Курс на " + oneDayBeforeDate + ": " + courseStr2); // как например на дату 02/01/2020
        }


        String page3 = downloadWebPage(urlWithDate3);
        if (page3.contains("<Value>")) {
            int startIndex3 = page3.lastIndexOf("<Value>");
            int endIndex3 = page3.lastIndexOf("</Value>");
            String courseStr3 = page3.substring(startIndex3 + 7, endIndex3);
            System.out.println("Курс на " + oneDayAfterDate + ": " + courseStr3);
        } else {
            String courseStr3 = "На указанную дату курс отсутствует";
            System.out.println("Курс на " + oneDayAfterDate + ": " + courseStr3); // как например на дату 02/01/2020
        }

        // Задаём курсы с типом переменной double.
        double courseDoble1 = 0;
        double courseDoble2 = 0;
        double courseDoble3 = 0;

        if (page1.contains("<Value>")) {
            int startIndex1 = page1.lastIndexOf("<Value>");
            int endIndex1 = page1.lastIndexOf("</Value>");
            String courseStr1 = page1.substring(startIndex1 + 7, endIndex1);
            double course1 = Double.parseDouble(courseStr1.replace(",", "."));
            courseDoble1 = course1;
        }
        if (page2.contains("<Value>")) {
            int startIndex2 = page2.lastIndexOf("<Value>");
            int endIndex2 = page2.lastIndexOf("</Value>");
            String courseStr2 = page2.substring(startIndex2 + 7, endIndex2);
            double course2 = Double.parseDouble(courseStr2.replace(",", "."));
            courseDoble2 = course2;
        }
        if (page3.contains("<Value>")) {
            int startIndex3 = page3.lastIndexOf("<Value>");
            int endIndex3 = page3.lastIndexOf("</Value>");
            String courseStr3 = page3.substring(startIndex3 + 7, endIndex3);
            double course3 = Double.parseDouble(courseStr3.replace(",", "."));
            courseDoble3 = course3;
        }

        System.out.println();
        System.out.println("Курс на " + originalDate + ": " + courseDoble1);
        System.out.println("Курс на " + oneDayBeforeDate + ": " + courseDoble2);
        System.out.println("Курс на " + oneDayAfterDate + ": " + courseDoble3);

        if (courseDoble1 == 0 || courseDoble2 == 0 || courseDoble3 == 0) {
            System.out.println("Имеются даты с отсутствующим курсом. Повторите программу и введите другую дату.");
        } else {
            double max;
            boolean courseDoble1Max = courseDoble1 > courseDoble2 && courseDoble1 > courseDoble3;
            boolean courseDoble2Max = courseDoble2 > courseDoble1 && courseDoble2 > courseDoble3;
            max = courseDoble1Max ? courseDoble1 : (courseDoble2Max ? courseDoble2 : courseDoble3);

            System.out.println("Максимальный курс: " +  max);


        }

//        String courseStr1 = page3.substring(startIndex1 + 7, endIndex3);
//        String courseStr3 = page3.substring(startIndex3 + 7, endIndex3);
//        String courseStr3 = page3.substring(startIndex3 + 7, endIndex3);

//        if (courseStr3 > courseStr2) {
//            System.out.print("Курс подрос на ");
//            System.out.print(course1 - course2);
//        } else {
//            System.out.print("Год назад курс был больше на ");
//            System.out.print(course2 - course1);
//        }


// код от Сильнова В.        String page1 = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=13/11/2021&VAL_NM_RQ=R01235");
// код от Сильнова В.        int startIndex1 = page1.lastIndexOf("<Value>");
// код от Сильнова В.        int endIndex1 = page1.lastIndexOf("</Value>");
// код от Сильнова В.        String courseStr1 = page1.substring(startIndex1 + 7, endIndex1);
// код от Сильнова В.
// код от Сильнова В.        String page2 = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2020&date_req2=13/11/2020&VAL_NM_RQ=R01235");
// код от Сильнова В.        int startIndex2 = page2.lastIndexOf("<Value>");
// код от Сильнова В.        int endIndex2 = page2.lastIndexOf("</Value>");
// код от Сильнова В.        String courseStr2 = page2.substring(startIndex2 + 7, endIndex2);
// код от Сильнова В.
// код от Сильнова В.        System.out.println(courseStr1);
// код от Сильнова В.        System.out.println(courseStr2);
// код от Сильнова В.
// код от Сильнова В.        double course1 = Double.parseDouble(courseStr1.replace(",", "."));
// код от Сильнова В.        double course2 = Double.parseDouble(courseStr2.replace(",", "."));
// код от Сильнова В.
// код от Сильнова В.        if (course1 > course2) {
// код от Сильнова В.            System.out.print("Курс подрос на ");
// код от Сильнова В.            System.out.print(course1 - course2);
// код от Сильнова В.        } else {
// код от Сильнова В.            System.out.print("Год назад курс был больше на ");
// код от Сильнова В.            System.out.print(course2 - course1);
// код от Сильнова В.        }
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
