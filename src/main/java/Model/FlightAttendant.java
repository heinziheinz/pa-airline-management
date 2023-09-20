package Model;

import java.time.LocalDate;

public record FlightAttendant(int id, String name, LocalDate birthdate, Language language)implements Employee{
}
