import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Case_2_1_1_Draft_7_DATES_DONE_IF {
    // Кейс «Анализатор курса валют».
    // Задание 2. - Пользователь вводит месяц и год. Вывести курс рубля за этот месяц, найти наибольший и наименьшие значения
    public static void main(String[] args) {
// April 2017
        YearMonth ym = YearMonth.of(2017, 4);
// get the last day of month
        int lastDay = ym.lengthOfMonth();
// loop through the days
        for(int day = 1; day <= lastDay; day++) {
            // create the day
            LocalDate dt = ym.atDay(day);
            // set to midnight at JVM default timezone
            if(dt.getDayOfMonth() >= 1){
                System.out.println(dt.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                dt = dt.plusDays(1);

            }
        }
    }
}

