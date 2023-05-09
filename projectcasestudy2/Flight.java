package projectcasestudy2;

import java.io.Serializable;

public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;
    private String flightCode;
    private String departure;
    private String destination;
    private String departureTime;
    private String destinationTime;
    private int quantityTickets;
    private double priceTicket;

    public Flight(String flightCode, String departure, String destination,
                  String departureTime, String destinationTime, int quantityTickets, double priceTicket) {
        this.flightCode = flightCode;
        this.departure = departure;
        this.destination = destination;
        this.departureTime = departureTime;
        this.destinationTime = destinationTime;
        this.quantityTickets = quantityTickets;
        this.priceTicket = priceTicket;
    }

    public String getFlightCode() {
        return flightCode;
    }

    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }

    public String getDeparture() {
        return departure;
    }

    public void setDeparture(String departure) {
        this.departure = departure;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDepartureTime() {
        return departureTime;
    }

    public void setDepartureTime(String departureTime) {
        this.departureTime = departureTime;
    }

    public String getDestinationTime() {
        return destinationTime;
    }

    public void setDestinationTime(String destinationTime) {
        this.destinationTime = destinationTime;
    }

    public int getQuantityTickets() {
        return quantityTickets;
    }

    public void setQuantityTickets(int quantityTickets) {
        this.quantityTickets = quantityTickets;
    }

    public double getPriceTicket() {
        return priceTicket;
    }

    public void setPriceTicket(double priceTicket) {
        this.priceTicket = priceTicket;
    }

    @Override
    public String toString() {
        return "Flight{ " +
                "flightCode : '" + flightCode + '\'' +
                ", departure : '" + departure + '\'' +
                ", destination : '" + destination + '\'' +
                ", departureTime : '" + departureTime + '\'' +
                ", destinationTime : '" + destinationTime + '\'' +
                ", quantityTickets : '" + quantityTickets + '\'' +
                ", priceTicket : " + priceTicket +
                '}';
    }


}
