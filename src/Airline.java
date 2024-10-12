import java.util.ArrayList;

public class Airline {
    private final String name;
    private final ArrayList<Flight> flights;

    public Airline(String name) {
        this.name = name;
        this.flights = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public ArrayList<Flight> getFlights() {
        return flights;
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public Flight findFlight(String flightNumber) {
        for (Flight flight : flights) {
            if (flight.getFlightNumber().equalsIgnoreCase(flightNumber)) {
                return flight;
            }
        }
        return null;
    }

    public void display() {
        System.out.println("Airline: " + name);
        for (Flight flight : flights) {
            System.out.println("Flight: " + flight.getFlightNumber() + " from " + flight.getOrigin() + " to " + flight.getDestination() + " at " + flight.getDepartureTime());
        }
    }

    public ArrayList<Flight> generateFlights(String origin, String destination) {
        ArrayList<Flight> generatedFlights = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            Flight flight = new Flight("FL" + i, origin, destination, (9 + i) + ":00 AM");
            generatedFlights.add(flight);
            addFlight(flight);  // Add generated flight to the airline's flight list
        }
        return generatedFlights;
    }
}
