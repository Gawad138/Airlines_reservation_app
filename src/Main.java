import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Create airline
        Airline airline = new Airline("Airway");

        // Scanner for user input
        Scanner scanner = new Scanner(System.in);

        // Display menu
        while (true) {
            System.out.println("Thanks for booking with us, OTA");
            System.out.println("1. Make Reservation");
            System.out.println("2. Cancel Reservation");
            System.out.println("3. Name Correction");
            System.out.println("4. Flight Exchange");
            System.out.println("5. Display Airline Details");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // Make reservation
                    System.out.print("Enter passenger name: ");
                    String name = scanner.nextLine();
                    System.out.print("Enter passport number: ");
                    String passport = scanner.nextLine();

                    System.out.print("Enter departure city: ");
                    String departureCity = scanner.nextLine();
                    System.out.print("Enter arrival city: ");
                    String arrivalCity = scanner.nextLine();

                    ArrayList<Flight> availableFlights = airline.generateFlights(departureCity, arrivalCity);
                    if (availableFlights.isEmpty()) {
                        System.out.println("No flights available for this route.");
                        break;
                    }

                    System.out.println("Available flights:");
                    for (int i = 0; i < availableFlights.size(); i++) {
                        Flight flight = availableFlights.get(i);
                        System.out.println((i + 1) + ". Flight " + flight.getFlightNumber() + " at " + flight.getDepartureTime());
                    }

                    System.out.print("Choose a flight: ");
                    int flightChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (flightChoice >= 1 && flightChoice <= availableFlights.size()) {
                        Flight chosenFlight = availableFlights.get(flightChoice - 1);
                        Passenger newPassenger = new Passenger(name, passport);
                        chosenFlight.makeReservation(newPassenger);
                    } else {
                        System.out.println("Invalid choice");
                    }
                    break;

                case 2:
                    // Cancel reservation
                    System.out.print("Enter passenger name to cancel: ");
                    String cancelName = scanner.nextLine();
                    Passenger cancelPassenger = findPassenger(cancelName, airline);
                    if (cancelPassenger != null) {
                        System.out.print("Enter flight number: ");
                        String flightNumber = scanner.nextLine();
                        Flight cancelFlight = airline.findFlight(flightNumber);
                        if (cancelFlight != null) {
                            cancelFlight.cancelReservation(cancelPassenger);
                        } else {
                            System.out.println("Invalid flight number");
                        }
                    } else {
                        System.out.println("Passenger not found");
                    }
                    break;

                case 3:
                    // Name correction
                    System.out.print("Enter old name: ");
                    String oldName = scanner.nextLine();
                    Passenger passenger = findPassenger(oldName, airline);
                    if (passenger != null) {
                        System.out.print("Enter new name: ");
                        String newName = scanner.nextLine();
                        passenger.setName(newName);
                        System.out.println("Name corrected to " + newName);
                    } else {
                        System.out.println("Passenger not found");
                    }
                    break;

                case 4:
                    // Flight exchange
                    System.out.print("Enter passenger name for flight exchange: ");
                    String exchangeName = scanner.nextLine();
                    Passenger exchangePassenger = findPassenger(exchangeName, airline);
                    if (exchangePassenger != null) {
                        System.out.print("Enter current flight number: ");
                        String currentFlightNumber = scanner.nextLine();
                        Flight currentFlight = airline.findFlight(currentFlightNumber);
                        if (currentFlight != null) {
                            System.out.print("Enter new departure city: ");
                            String newDepartureCity = scanner.nextLine();
                            System.out.print("Enter new arrival city: ");
                            String newArrivalCity = scanner.nextLine();

                            ArrayList<Flight> newAvailableFlights = airline.generateFlights(newDepartureCity, newArrivalCity);
                            if (newAvailableFlights.isEmpty()) {
                                System.out.println("No flights available for this route.");
                                break;
                            }

                            System.out.println("Available flights:");
                            for (int i = 0; i < newAvailableFlights.size(); i++) {
                                Flight flight = newAvailableFlights.get(i);
                                System.out.println((i + 1) + ". Flight " + flight.getFlightNumber() + " at " + flight.getDepartureTime());
                            }

                            System.out.print("Choose a new flight: ");
                            int newFlightChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (newFlightChoice >= 1 && newFlightChoice <= newAvailableFlights.size()) {
                                Flight newChosenFlight = newAvailableFlights.get(newFlightChoice - 1);
                                currentFlight.exchangeFlight(exchangePassenger, newChosenFlight);
                            } else {
                                System.out.println("Invalid choice");
                            }
                        } else {
                            System.out.println("Invalid current flight number");
                        }
                    } else {
                        System.out.println("Passenger not found");
                    }
                    break;

                case 5:
                    // Display airline details
                    airline.display();
                    break;

                case 6:
                    // Exit
                    System.out.println("Exiting...");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private static Passenger findPassenger(String name, Airline airline) {
        for (Flight flight : airline.getFlights()) {
            for (Passenger p : flight.getPassengers()) {
                if (p.getName().equalsIgnoreCase(name)) {
                    return p;
                }
            }
        }
        return null;
    }
}
