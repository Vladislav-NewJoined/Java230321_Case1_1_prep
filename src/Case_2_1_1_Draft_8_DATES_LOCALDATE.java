import java.time.LocalDate;
import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

public class Case_2_1_1_Draft_8_DATES_LOCALDATE {
    // Кейс «Анализатор курса валют».
    // Задание 2. - Пользователь вводит месяц и год. Вывести курс рубля за этот месяц, найти наибольший и наименьшие значения
    public static void printMonthDays(YearMonth ym) {
        DateTimeFormatter ddMMyyyy = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        for (LocalDate dt = ym.atDay(1), to = dt.plusMonths(1); dt.isBefore(to); dt = dt.plusDays(1)) {
            System.out.println(dt.format(ddMMyyyy));
        }
    }

    public static void main(String[] args) {
        printMonthDays(YearMonth.of(2017, 4));
    }
}

