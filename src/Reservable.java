public interface Reservable {
    void makeReservation(Passenger passenger);
    void cancelReservation(Passenger passenger);
}
