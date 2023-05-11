package projectcasestudy2.service;

import projectcasestudy2.io.IOFile;
import projectcasestudy2.io.Manage;
import projectcasestudy2.model.Flight;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FlightManage implements Manage<Flight>, IOFile<Flight> {
    private ArrayList<Flight> flights;
    public Scanner scanner;
    String PATH_FILE = "/Users/viquoclam/Documents/CaseStudyM2/projectcasestudy2/data/flightmanage.txt";

    public ArrayList<Flight> getFlights() {
        return flights;
    }


    public FlightManage() {
        scanner = new Scanner(System.in);
        flights = new ArrayList<>();
        flights = readBinary(PATH_FILE);
    }

    @Override
    public Flight creat() {
        flights = readBinary(PATH_FILE);
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
        writeBinary(flights, PATH_FILE);
        System.out.println("Your flight has been created");
        return flight;
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
            writeBinary(flights, PATH_FILE);
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
        writeBinary(flights,PATH_FILE);
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
                System.out.println("========== FLIGHT INFORMATION ==========:");
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
        flights = readBinary(PATH_FILE);
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
    @Override
    public void writeBinary(List<Flight> e, String path) {
        File file = new File(path);
        try (ObjectOutputStream obs = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            obs.writeObject(e);
        }catch (IOException ioException){
            System.out.println(ioException.getMessage());
        }
    }

    @Override
    public ArrayList<Flight> readBinary(String path) {
        File file = new File(path);
        ArrayList<Flight> flights = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()))){
            flights = (ArrayList<Flight>) ois.readObject();
        }catch (IOException | ClassNotFoundException ioException){
            System.out.println(ioException.getMessage());
        }
        return flights;
    }
}
