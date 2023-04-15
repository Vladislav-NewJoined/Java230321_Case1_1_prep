import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Case_2_1_1_Draft_11_FromBegin {
    // Кейс «Анализатор курса валют».
    // Задание 2. - Пользователь вводит месяц и год. Вывести курс рубля за этот месяц, найти наибольший и наименьшие значения
    // Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
    // https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));

        String originalPage = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235");
        // Задаём адрес исходной веб-страницы Центробанка в текстовом формате.
        String originalPageText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";


        int startIndex = originalPage.lastIndexOf("<Value>") + 7;
        int endIndex = originalPage.lastIndexOf("</Value>");
        //System.out.println("Курс на исходную дату " + originalPage.substring(startIndex, endIndex));
        // Переменная курс на исходную дату
        String origCourseStr = originalPage.substring(startIndex, endIndex);
        System.out.println("Курс на исходную дату " + origCourseStr);

        System.out.println("Введите исходную дату с разделителем '/': пример: 14/02/2020");
        String originDate = buffered.readLine();  // Start date
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Calendar c = Calendar.getInstance();
        c.setTime(sdf.parse(originDate));
        c.add(Calendar.DATE, 1);  // number of days to add
        String nextDate;
        nextDate = sdf.format(c.getTime());  // entering nextDate

        System.out.println(originDate);
        System.out.println(nextDate);

        // Меняем в адресе исходной страницы дату на следующую.
        String urlWithNextDate = originalPageText.replaceAll("12/11/2021", nextDate);

        System.out.println("Страница после перемены даты:");
        System.out.println(urlWithNextDate);

        String nextPage = downloadWebPage(urlWithNextDate);
        System.out.println("Исходный код после перемены даты:");
        System.out.println(nextPage);
        System.out.println("Курс после перемены даты в типе String:");
        String courseNextPage = nextPage.substring(startIndex, endIndex);
        System.out.println(courseNextPage);


        // Делаем курс в виде переменной.
        // Задаём курсы с типом переменной double.
        double courseNextDoble = Double.parseDouble(courseNextPage.replace(",", "."));
        System.out.println("Курс Double:");
        System.out.println(courseNextDoble);


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