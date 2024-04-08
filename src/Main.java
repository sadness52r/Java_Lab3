import java.time.format.DateTimeParseException;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("Введите фамилию пользователя или напишите STOP, чтобы закончить программу: ");
            String surname = scanner.next();
            if (surname.equals("STOP")) break;
            System.out.print("Введите имя пользователя или напишите STOP, чтобы закончить программу: ");
            String name = scanner.next();
            if (name.equals("STOP")) break;
            System.out.print("Введите отчество пользователя или напишите STOP, чтобы закончить программу: ");
            String patronymic = scanner.next();
            if (patronymic.equals("STOP")) break;
            System.out.print("Введите дату рождения пользователя в формате {YYYY-MM-DD} или напишите STOP, чтобы закончить программу: ");
            String birthDate = scanner.next();
            if (birthDate.equals("STOP")) break;
            System.out.println();

            User user;
            try{
                user = new User(name, surname, patronymic, birthDate);
            }
            catch (NumberFormatException | IllegalDateException | DateTimeParseException | UndefinedDateException e) {
                System.out.println(e.getMessage());
                continue;
            }

            System.out.println("Отформатированная информация о пользователе:\n " + user + "\n");
        }
    }
}