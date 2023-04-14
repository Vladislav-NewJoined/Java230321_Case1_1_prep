import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Case_2_1_1_Draft_6_DATES_DONE {
    // Кейс «Анализатор курса валют».
    // Задание 2. - Пользователь вводит месяц и год. Вывести курс рубля за этот месяц, найти наибольший и наименьшие значения
    public static void main(String[] args) {
        // April 2017
        YearMonth ym = YearMonth.of(2020, 4);
// get the last day of month
        int lastDay = ym.lengthOfMonth();
// loop through the days
        for(int day = 1; day <= lastDay; day++) {
            // create the day
            LocalDate dt = ym.atDay(day);
            // set to midnight at JVM default timezone
            System.out.println(dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            
//            do {
//                System.out.println(dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
//                dt = dt.plusDays(1);

//            }
//        while (dt.getDayOfMonth() > 1);
        }
    }
}
