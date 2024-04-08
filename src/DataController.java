import java.time.LocalDate;
import java.time.Month;
import java.time.Period;
import java.time.Year;
import java.util.Arrays;
import java.util.List;

public class DataController {
    private int GetMaxDayFromMonth(Month month, int year){
        if (month == Month.FEBRUARY){
            if (Year.isLeap(year))
                return 29;
            return 28;
        }
        if (month == Month.JANUARY || month == Month.MARCH || month == Month.MAY ||
                month == Month.JULY || month == Month.SEPTEMBER || month == Month.NOVEMBER)
            return 31;
        return 30;
    }
    protected Sex DefineSex(String patronymic){
        if (patronymic.endsWith("а")) return Sex.FEMALE;
        else if (patronymic.endsWith("ч")) return Sex.MALE;
        return Sex.UNKNOWN;
    }
    protected int CalculateAge(LocalDate birthDate) throws UndefinedDateException {
        if (birthDate == null)
            throw new UndefinedDateException("Возраст неизвестен");
        return(Period.between(birthDate, LocalDate.now()).getYears());
    }

    protected void CheckCorrectDate(String date) throws IllegalDateException, NumberFormatException {
        final int lowLimitYear = 1920;
        final int lowLimitMonth = 1;
        final int highLimitMonth = 12;
        final int lowLimitDay = 1;

        String[] splittedDate = date.split("-");
        if (Integer.parseInt(splittedDate[0]) < lowLimitYear || Integer.parseInt(splittedDate[0]) > LocalDate.now().getYear())
            throw new IllegalDateException("Impossible year! Please, enter correct year!");
        if (Integer.parseInt(splittedDate[1]) < lowLimitMonth || Integer.parseInt(splittedDate[1]) > highLimitMonth)
            throw new IllegalDateException("Impossible month! Please, enter correct month!");

        Month month = Month.of(Integer.parseInt(splittedDate[1]));
        int maxDay = GetMaxDayFromMonth(month, Integer.parseInt(splittedDate[0]));
        if (Integer.parseInt(splittedDate[2]) < lowLimitDay || Integer.parseInt(splittedDate[2]) > maxDay)
            throw new IllegalDateException("Impossible day! Please, enter correct day!");
    }
    protected void CheckCorrectDate(LocalDate date) throws IllegalDateException, NumberFormatException {
        if (date == null)
            throw new NullPointerException("Date is null!");
        CheckCorrectDate(date.toString());
    }
    protected void CheckCorrectDate(Integer year, Integer month, Integer day) throws IllegalDateException, NumberFormatException {
        CheckCorrectDate(year.toString() + "-" + month.toString() + "-" + day.toString());
    }
    protected String GetDeclination(Integer age) throws UndefinedAgeException {
        if (age == -1){
            throw new UndefinedAgeException("Возраст неизвестен");
        }

        String result;
        List<Integer> units = Arrays.asList(2, 3, 4);
        List<Integer> dozens = Arrays.asList(11, 12, 13, 14);

        if(age != 11 && age % 10 == 1) {
            result = "год";
        } else if (units.contains(age % 10) && !dozens.contains(age % 100)) {
            result = "года";
        } else {
            result = "лет";
        }
        return result;
    }
}
