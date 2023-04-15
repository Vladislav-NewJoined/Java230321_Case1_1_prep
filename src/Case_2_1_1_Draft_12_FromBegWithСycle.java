import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

public class Case_2_1_1_Draft_12_FromBegWithСycle {
    // Кейс «Анализатор курса валют».
    // Задание 2. - Пользователь вводит месяц и год. Вывести курс рубля за этот месяц, найти наибольший и наименьшие значения
    // Инфо здесь: Как найти анализ курса валют за определенную дату. Урок 6 Видео мин 0.44.56
    // https://lms.synergy.ru/student/updiscipline/4474947/1045153/1/1
    public static void main(String[] args) throws IOException, ParseException {
        BufferedReader buffered = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите исходные месяц и год с разделителем '/': пример: 02/2020");
        String origMonth = buffered.readLine();  // Start month
//        System.out.println(origMonth);

        // Делаем парсинг введённой строки методом Split.
        String[] items = origMonth.split("/");
        String mon = items[0];
        String yea = items[1];
//        System.out.println(mon);
//        System.out.println(yea);

//        Или можно так.
//        for (String item : items) {
//            System.out.println(item);
//        }

        int monI = Integer.parseInt (mon);
        int yeaI = Integer.parseInt (yea);

        // Преобразовываем ввод через переменную YearMonth.
        YearMonth ym = YearMonth.of(yeaI, monI);
        //System.out.println(ym);

        // Скачиваем исходный код веб-страницы Центробанка.
        String originalPage = downloadWebPage("https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235");
        // Задаём адрес исходной веб-страницы Центробанка в текстовом формате.
        String originalPageText = "https://cbr.ru/scripts/XML_dynamic.asp?date_req1=12/11/2021&date_req2=12/11/2021&VAL_NM_RQ=R01235";

// get the last day of month
        int lastDay = ym.lengthOfMonth();
// loop through the days
        for(int day = 1; day <= lastDay; day++) {
            // create the day
            LocalDate dt = ym.atDay(day);
            // set to midnight at JVM default timezone
            System.out.println(dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));  // Это надо
            int startIndex = originalPage.lastIndexOf("<Value>") + 7;
            int endIndex = originalPage.lastIndexOf("</Value>");
            // System.out.println("Введите исходную дату с разделителем '/': пример: 14/02/2020"); // Это не надо
            // String originDate = buffered.readLine();  // Start date  // Это не надо
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Calendar c = Calendar.getInstance();
            c.setTime(sdf.parse(String.valueOf(dt)));
            c.add(Calendar.DATE, 1);  // number of days to add
            String nextDate;
            nextDate = sdf.format(c.getTime());  // entering nextDate

            // System.out.println(originDate);  // Это не надо
            // System.out.println(nextDate);  // Это не надо

            // Меняем в адресе исходной страницы дату на следующую.
            String urlWithNextDate = originalPageText.replaceAll("12/11/2021", nextDate);

            System.out.println("Страница после перемены даты:"); // Это не надо
            System.out.println(urlWithNextDate);  // Это не надо

            String nextPage = downloadWebPage(urlWithNextDate);
            System.out.println("Исходный код после перемены даты:");
            System.out.println(nextPage);
            System.out.println("Курс после перемены даты в типе переменной String:");
            String courseNextPage = nextPage.substring(startIndex, endIndex);
            System.out.println(courseNextPage);

            // Задаём курс в виде переменной Double.
            double courseNextDoble = Double.parseDouble(courseNextPage.replace(",", "."));
            System.out.println("Курс в типе переменной Double:");
            System.out.println(courseNextDoble);

            // Выводим на экран дату и соответствующий курс.
            System.out.println("Курс на " + nextDate + "    " + courseNextDoble);
        }





        //System.out.println("Курс на исходную дату " + originalPage.substring(startIndex, endIndex));
        // Переменная курс на исходную дату
        //String origCourseStr = originalPage.substring(startIndex, endIndex);
        //System.out.println("Курс на исходную дату " + origCourseStr);














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