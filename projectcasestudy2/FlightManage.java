package projectcasestudy2;

import java.util.ArrayList;
import java.util.Scanner;

public class FlightManage implements Manage<Flight> {
    private final ArrayList<Flight> flights;
    Scanner scanner;

    public FlightManage() {
        scanner = new Scanner(System.in);
        flights = new ArrayList<>();
    }

    @Override
    public Flight creat() {
        System.out.println("Enter flight code: ");
        String flightCode = scanner.nextLine();

        System.out.println("Enter departure: ");
        String departure = scanner.nextLine();

        System.out.println("Enter destination: ");
        String destination = scanner.nextLine();

        System.out.println("Enter departure time: ");
        String departureTime = scanner.nextLine();

        System.out.println("Enter destination time: ");
        String destinationTime = scanner.nextLine();

        System.out.println("Enter quantity tickets: ");
        int quantityTickets = Integer.parseInt(scanner.nextLine());

        System.out.println("Enter price of ticket: ");
        double priceTicket = Double.parseDouble(scanner.nextLine());

        Flight flight = new Flight(flightCode,departure,destination,departureTime,
                destinationTime,quantityTickets,priceTicket);
        flights.add(flight);
        System.out.println("Your flight has been created");
        return null;
    }

    @Override
    public Flight update() {
        displayAll();
        System.out.println("Enter flight code you want to update: ");
        String flightCode = scanner.nextLine();

        boolean foundFlight = false;
        for (Flight flight: flights) {
            if (flight.getFlightCode().equals(flightCode)){
                System.out.println("Enter new departure: ");
                String departure = scanner.nextLine();
                flight.setDeparture(departure);

                System.out.println("Enter new destination: ");
                String destination = scanner.nextLine();
                flight.setDestination(destination);

                System.out.println("Enter new departure time: ");
                String departureTime = scanner.nextLine();
                flight.setDepartureTime(departureTime);

                System.out.println("Enter new destination time: ");
                String destinationTime = scanner.nextLine();
                flight.setDestinationTime(destinationTime);

                System.out.println("Enter new quantity tickets: ");
                int quantityTickets = Integer.parseInt(scanner.nextLine());
                flight.setQuantityTickets(quantityTickets);

                System.out.println("Enter new price ticket: ");
                double priceTicket = Double.parseDouble(scanner.nextLine());
                flight.setPriceTicket(priceTicket);

                System.out.println("The flight has been updated successfully.");
                foundFlight = true;
                break;
                }
            }
            if(!foundFlight){
                System.out.println("No flight with code found " + flightCode);
        }
        return null;
    }

    @Override
    public Flight delete() {
        displayAll();
        System.out.println("Enter the flight code to delete: ");
        String flightCode = scanner.nextLine();
        boolean foundFlight = false;
        for (Flight flight: flights) {
            if(flight.getFlightCode().equals(flightCode)){
                flights.remove(flight);
                System.out.println("Flight has been deleted successfully.");
                foundFlight = true;
                break;
            }
        }if(!foundFlight){
            System.out.println("No flight with code found " + flightCode);
        }

        return null;
    }

    @Override
    public Flight getById() {
        displayAll();
        System.out.println("Enter the flight code to get information: ");
        String flightCode = scanner.nextLine();
        boolean foundFlight = false;
        for (Flight flight: flights){
            if(flight.getFlightCode().equals(flightCode)){
                System.out.println("Flight information:");
                System.out.println("FLIGHT CODE: " + flight.getFlightCode());
                System.out.println("DEPARTURE: " + flight.getDeparture());
                System.out.println("DESTINATION: " + flight.getDestination());
                System.out.println("DEPARTURE TIME: " + flight.getDepartureTime());
                System.out.println("DESTINATION TIME: " + flight.getDestinationTime());
                System.out.println("QUANTITY TICKETS: " + flight.getQuantityTickets());
                System.out.println("PRICE TICKET: " + flight.getPriceTicket());
                foundFlight = true;
                break;
            }
        }
        if(!foundFlight){
            System.out.println("No flight with code found " + flightCode);
        }
        return null;
    }

    @Override
    public void displayAll() {
        for (Flight flight: flights) {
            System.out.println(flight);
        }
    }
    public void checkQuantityTickets(){
        displayAll();
        System.out.println("Enter the flight code to check the remaining number of tickets: ");
        String flightCode = scanner.nextLine();

        boolean foundFlight = false;
        for (Flight flight: flights) {
            if(flight.getFlightCode().equals(flightCode)){
                System.out.println("Number of remaining tickets of the flight with code " + flightCode + " is：" + flight.getQuantityTickets());
                foundFlight = true;
                break;
            }
        }if(!foundFlight){
            System.out.println("No flight with code found " + flightCode);
        }

    }

    public void bookTicket(){
        displayAll();
        System.out.println("Enter flight code: ");
        String flightCode = scanner.nextLine();

        boolean foundFlight = true;
        for (Flight flight:flights) {
            if(flight.getFlightCode().equals(flightCode)){
                System.out.println("flight code is: " + flightCode);
            }
            System.out.println("Ticket class (1: Economy, 2: Business class):");
            int seatClass = Integer.parseInt(scanner.nextLine());
            if(seatClass != 1 && seatClass != 2){
                System.out.println("Invalid ticket class.");
                return;
            }
            System.out.println("Number of tickets to book: ");
            int numTickets = Integer.parseInt(scanner.nextLine());


            double price;
            if(seatClass == 2){
                price = flight.getPriceTicket() * 2;
            } else {
                price = flight.getPriceTicket();
            }
            if (numTickets > flight.getQuantityTickets()){
                System.out.println("There are not enough tickets for your request.");
                return;
            }

            double totalCost = price * numTickets;
            System.out.println("Total cost is: " + totalCost);

            System.out.println("Booking confirmation? (Y/N)!");
            String confirmation = scanner.nextLine();
            if(confirmation.equalsIgnoreCase("Y")){
                System.out.println("Thank you for booking!");
            }
            else if(confirmation.equalsIgnoreCase("N")){
                System.out.println("You just canceled booking!");
            }
        }
        if(!foundFlight){
            System.out.println("No flight found with code " + flightCode);
        }
    }

}
