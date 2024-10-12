import java.util.ArrayList;

public class Flight {
    private final String flightNumber;
    private final String origin;
    private final String destination;
    private final String departureTime;
    private final ArrayList<Passenger> passengers;

    public Flight(String flightNumber, String origin, String destination, String departureTime) {
        this.flightNumber = flightNumber;
        this.origin = origin;
        this.destination = destination;
        this.departureTime = departureTime;
        this.passengers = new ArrayList<>();
    }

    public String getFlightNumber() {
        return flightNumber;
    }

    public String getOrigin() {
        return origin;
    }

    public String getDestination() {
        return destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public ArrayList<Passenger> getPassengers() {
        return passengers;
    }

    public void makeReservation(Passenger passenger) {
        passengers.add(passenger);
        System.out.println("Reservation made for " + passenger.getName() + " on flight " + flightNumber);
    }

    public void cancelReservation(Passenger passenger) {
        passengers.remove(passenger);
        System.out.println("Reservation cancelled for " + passenger.getName() + " on flight " + flightNumber);
    }

    public void exchangeFlight(Passenger passenger, Flight newFlight) {
        this.cancelReservation(passenger);
        newFlight.makeReservation(passenger);
        System.out.println("Reservation exchanged for " + passenger.getName() + " to flight " + newFlight.getFlightNumber());
    }

    public boolean matchesRoute(String origin, String destination) {
        return this.origin.equalsIgnoreCase(origin) && this.destination.equalsIgnoreCase(destination);
    }
}
