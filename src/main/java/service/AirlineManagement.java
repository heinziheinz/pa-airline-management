package service;

import Model.Employee;
import Model.Flight;
import Model.FlightAttendant;
import Model.Pilot;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//import static sun.jvm.hotspot.runtime.BasicObjectLock.size;

public class AirlineManagement {

    public boolean readyOrNot(Flight flight, List<Employee> employeeList) {

        if (employeeList.size() != 5) {
            System.out.println("Not enough employees. Is " + employeeList.size() + " should be 5 ");
            return false;
        }

        Map<Class<?>, List<Employee>> flightCrew = employeeList.stream().collect(Collectors.groupingBy(
                Employee::getClass,
                Collectors.toList()
        ));
        System.out.println(flightCrew.get(Pilot.class).size());
        if (flightCrew.get(Pilot.class).size() != 2) {
            System.out.println("Not enough Pilots " + flightCrew.get(Pilot.class).size());
            return false;
        }

        if (flightCrew.get(FlightAttendant.class).size() != 3) {
            System.out.println("Not enough light Attendants");
            return false;
        }

        // casting
        List<Pilot> pilotList = flightCrew.get(Pilot.class).stream().map(p -> (Pilot) p).toList();
        List<FlightAttendant> flightAttandantList = flightCrew.get(FlightAttendant.class).stream().map(p -> (FlightAttendant) p).toList();

        boolean allAttandantsSpeakLanguage = flightAttandantList.stream().allMatch(flightAttendant -> flightAttendant.language().equals(flight.language()));
        if (!allAttandantsSpeakLanguage) {
            System.out.println("Not all FlightAttendants speak language");
            return false;
        }

        if (pilotList.get(0).pilotStatus() == pilotList.get(1).pilotStatus()){
            System.out.println("No pilot and copilot are aboard");
            return false;
        }

        boolean haveAnalogCompass = pilotList.stream().allMatch(Pilot::hasAnalogCompass);
        if(!haveAnalogCompass){
            System.out.println("No all pilots have Analog Compass");
            return false;
        }


        return true;
    }
}

//.collect(Collectors.groupingBy(
//        Appointment::patientId,//könnte man auch mit patients machen, wenn zuvor Patient an Appointment übergeben würde
//        Collectors.counting()
//        ));

//    Airline Management:
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
