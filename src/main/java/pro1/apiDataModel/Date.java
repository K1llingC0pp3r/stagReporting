package pro1.apiDataModel;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Date {
    public String value;

    public boolean isValid() {
        return value != null && !value.isEmpty();
    }

    public LocalDate toLocalDate() {
        return LocalDate.parse(value, DateTimeFormatter.ofPattern("d.M.yyyy"));
    }
}