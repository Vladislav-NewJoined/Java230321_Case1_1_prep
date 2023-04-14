
import java.util.Calendar;

public class Case_2_1_1_Draft_1_ListOfDates {
    // Кейс «Анализатор курса валют».
    // Задание 2. - Пользователь вводит месяц и год. Вывести курс рубля за этот месяц, найти наибольший и наименьшие значения
    public static void main(String[] args) {
        Calendar calendar = Calendar.getInstance();

        if (args.length == 2) {
            int year = Integer.parseInt(args[0]);
            int month = Integer.parseInt(args[1]);
            calendar.set(year, month, 1);
        }

        int month = calendar.get(Calendar.MONTH);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        while (calendar.get(Calendar.MONTH) == month) {
            System.out.println(calendar.getTime());
            calendar.add(Calendar.DATE, 1);
        }
    }
}
