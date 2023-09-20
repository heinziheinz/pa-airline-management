package service;

import Model.*;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AirlineManagementTest {

    @org.junit.jupiter.api.Test
    void readyOrNot() {
        LocalDate birthdayPilot = LocalDate.of(1990, 2, 2);
        LocalDate birthdayCoPilot = LocalDate.of(1999, 3, 2);
        LocalDate birthdayFlightAttendantOne = LocalDate.of(1999, 3, 2);
        LocalDate birthdayFlightAttendantTwo = LocalDate.of(1999, 3, 2);
        LocalDate birthdayFlightAttendantThree = LocalDate.of(1999, 3, 2);
        Employee pilot = new Pilot(1, "Steve", birthdayPilot, PilotStatus.PILOT,true);
        Employee copilot = new Pilot(2, "Frank", birthdayCoPilot, PilotStatus.COPILOT,true);
        Employee flightAttendantOne = new FlightAttendant(3,"Anne", birthdayFlightAttendantOne, Language.GERMAN);
        Employee flightAttendantTwo = new FlightAttendant(3,"Anne", birthdayFlightAttendantTwo, Language.GERMAN);
        Employee flightAttendantThree = new FlightAttendant(3,"Anne", birthdayFlightAttendantThree, Language.GERMAN);
        List<Employee> flightCrew = List.of(pilot, copilot, flightAttendantOne,flightAttendantTwo,flightAttendantThree);
        Flight flight = new Flight(1, Language.GERMAN);
        AirlineManagement airlineManagement = new AirlineManagement();

        boolean hasPermissonToTakeOff = airlineManagement.readyOrNot(flight, flightCrew);

        System.out.println(hasPermissonToTakeOff);

        assertTrue(hasPermissonToTakeOff);

        Flight flight1 = new Flight(2, Language.RUSSIAN);

        boolean hasPermissonToTakeOffTwo = airlineManagement.readyOrNot(flight1 , flightCrew);

        assertTrue(hasPermissonToTakeOffTwo);



    }
}

//   Airline Management:
//
//        You received a task to model an Airline's internal structure.
//        A Flight has two Pilots, three Flight Attendants. Design the model based on the following requirements:
//        Every Airline Employee has a name and birth date
//        You have to store which languages flight attendants speak from the relevant ones (English, German, Spanish, Russian).
//        Every flight has a unique identifier and a language from the list above.
//        Pilots can be either Captain or Co-pilot on a flight. A Co-pilot on a flight can be captain on another one.
//        Pilots get premium quality Analog Compass but only once, sometime after they got employed
//        We should be able to check if a flight is ready to take off (with checking every Employee whether they are ready or not);
//        the conditions of the clearance are:
//        The Captain and the Co-pilot have received their Compasses already.
//        All the Attendants speak the language of the flight.
//        Log the steps of the clearance check (passed / failed, and why)!