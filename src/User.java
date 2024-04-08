import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class User extends DataController{
    private String name;
    private String surname;
    private String patronymic;
    private LocalDate birthDate;
    private Sex sex;
    private int age;

    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getSurname(){
        return surname;
    }
    public void setSurname(String surname){
        this.surname = surname;
    }
    public String getPatronymic(){
        return patronymic;
    }
    public void setPatronymic(String patronymic){
        this.patronymic = patronymic;
    }
    public LocalDate getBirthDate() throws UndefinedDateException {
        if (birthDate == null)
            throw new UndefinedDateException("Дата рождения не может быть получена у пользователя "
                    + surname + " " + name + " " + patronymic + ". Задайте корректную дату и повторите снова!");
        return birthDate;
    }
    public void setBirthDate(int day, int month, int year) throws IllegalDateException, UndefinedDateException {
        this.birthDate = LocalDate.of(year, month, day);
        CheckCorrectDate(this.birthDate);
        age = CalculateAge(this.birthDate);
    }
    public Sex getSex(){
        return sex;
    }
    public int getAge() throws UndefinedDateException, UndefinedAgeException {
        if (age == -1)
            throw new UndefinedAgeException("Возраст не может быть получен у пользователя "
                    + name + " " + surname + " " + patronymic + ". Задайте корректную дату рождения и повторите снова!");
        age = CalculateAge(this.birthDate);
        return age;
    }

    public User(String name, String surname, String patronymic, LocalDate birthDate) throws IllegalDateException, NumberFormatException, DateTimeParseException, UndefinedDateException {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = birthDate;
        CheckCorrectDate(this.birthDate);
        this.sex = DefineSex(this.patronymic);
        this.age = CalculateAge(this.birthDate);
    }
    public User(String name, String surname, String patronymic, int day, int month, int year) throws IllegalDateException, NumberFormatException, DateTimeParseException, UndefinedDateException {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = LocalDate.of(day, month, year);
        CheckCorrectDate(this.birthDate);
        this.sex = DefineSex(this.patronymic);
        this.age = CalculateAge(this.birthDate);
    }
    public User(String name, String surname, String patronymic, String birthDate) throws IllegalDateException, NumberFormatException, DateTimeParseException, UndefinedDateException {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.birthDate = LocalDate.parse(birthDate);
        CheckCorrectDate(this.birthDate);
        this.sex = DefineSex(this.patronymic);
        this.age = CalculateAge(this.birthDate);
    }

    @Override
    public String toString(){
        try {
            String declination = GetDeclination(age);
            return this.surname + " " + this.name.charAt(0) + ". " + this.patronymic.charAt(0) + ". " + this.sex.name() + " " + getAge() + " " + declination;
        } catch (UndefinedDateException e) {
            return this.surname + " " + this.name.charAt(0) + ". " + this.patronymic.charAt(0) + ". " + this.sex.name() + " UNKNOWN_BIRTHDATE";
        } catch (UndefinedAgeException e) {
            return this.surname + " " + this.name.charAt(0) + ". " + this.patronymic.charAt(0) + ". " + this.sex.name() + " UNKNOWN_AGE";
        }
    }

}
