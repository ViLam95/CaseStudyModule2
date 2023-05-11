package projectcasestudy2.service;


import projectcasestudy2.model.Flight;

public class BookingTickets extends FlightManage {
    public static int numTickets = 0;
    static double totalCost = 0;

    public void bookTicket() {
        super.displayAll();
        System.out.println("Enter flight code: ");
        String flightCode = scanner.nextLine();

        boolean foundFlight = false;
        for (Flight flight : super.getFlights()) {
            if (flight.getFlightCode().equals(flightCode)) {
                System.out.println("flight code is: " + flightCode);
                foundFlight = true;
                System.out.println("Ticket class (1: Economy, 2: Business class):");
                int seatClass = inputNum();
                String seat = "";
                if (seatClass == 1) {
                    seat = "ECONOMY";
                } else if (seatClass == 2) {
                    seat = "BUSINESS CLASS";
                }
                System.out.println(seat);

                if (seatClass != 1 && seatClass != 2) {
                    System.out.println("Invalid ticket class.");
                    return;
                }
                System.out.println("Number of tickets to book: ");
                numTickets = Integer.parseInt(scanner.nextLine());


                double price;
                if (seatClass == 2) {
                    price = flight.getPriceTicket() * 2;
                } else {
                    price = flight.getPriceTicket();
                }
                if (numTickets > flight.getQuantityTickets()) {
                    System.out.println("There are not enough tickets for your request!");
                    return;
                }

                totalCost = price * numTickets;
                System.out.println("========== BOOKING INFORMATION ==========");
                System.out.println("FLIGHT CODE: " + flight.getFlightCode());
                System.out.println("DEPARTURE: " + flight.getDeparture());
                System.out.println("DESTINATION: " + flight.getDestination());
                System.out.println("DEPARTURE TIME: " + flight.getDepartureTime());
                System.out.println("DESTINATION TIME: " + flight.getDestinationTime());
                System.out.println("SEAT CLASS: " + seat);
                System.out.println("NUMBER TICKETS: " + numTickets);
                System.out.println("TOTAL COST IS: " + totalCost + "$");

                System.out.println("Booking confirmation? (Y/N)!");
                String confirmation = scanner.nextLine();
                if (confirmation.equalsIgnoreCase("Y")) {
                    System.out.println("Thank you for booking!");
                    updateQuantityTicket(flightCode, numTickets);
                } else if (confirmation.equalsIgnoreCase("N")) {
                    System.out.println("You just canceled booking!");
                }
            }

        }
        if (!foundFlight) {
            System.out.println("No flight found with code " + flightCode);
        }
        super.writeBinary(super.getFlights(), super.PATH_FILE);
    }

    public void updateQuantityTicket(String flightCode, int numTickets) {
        for (Flight flight : super.getFlights()) {
            if (flight.getFlightCode().equals(flightCode)) {
                flight.setQuantityTickets(flight.getQuantityTickets() - numTickets);
            }
        }
        super.writeBinary(super.getFlights(), super.PATH_FILE);
    }

    public void cancellationBooking() {
        super.displayAll();
        System.out.println("Enter flight code: ");
        String flightCode = scanner.nextLine();

        Flight flight = null;
        for (Flight fl : super.getFlights()) {
            if (fl.getFlightCode().equals(flightCode)) {
                flight = fl;
                break;
            }
        }
        if (flight == null) {
            System.out.println("No flight found with code " + flightCode);
        }
        System.out.println("Enter the number of tickets to cancel: ");
        int cancelNumTickets = inputNum();
        if (cancelNumTickets > numTickets) {
            System.out.println("The number of tickets you want to cancel is greater than the number of tickets booked!");
        } else {
            assert flight != null;
            double refundAmount = flight.getPriceTicket() * cancelNumTickets;
            updateQuantityTicket(flightCode, (-cancelNumTickets));
            System.out.println("Successfully canceled ticket " + cancelNumTickets + "tickets!");
            System.out.println("Refund amount is: " + refundAmount + "$");

        }
        super.writeBinary(super.getFlights(), super.PATH_FILE);
    }

//    public void payment() {
//        System.out.println("Enter flight code to pay: ");
//        String flightCode = scanner.nextLine();
//
//        Flight flight = null;
//        for (Flight fl : super.getFlights()) {
//            if (fl.getFlightCode().equals(flightCode)) {
//                flight = fl;
//                break;
//            }
//        }
//        if (flight == null) {
//            System.out.println("No flight found with code " + flightCode);
//        }
//        System.out.println("Total cost is: " + totalCost);
//        System.out.println("Are you sure you want to pay? (Y/N)!");
//        String confirmation = scanner.nextLine();
//        if (confirmation.equalsIgnoreCase("Y")) {
//            System.out.println("Thank you for paying!");
//        }
//        else if(confirmation.equalsIgnoreCase("N")) {
//            System.out.println("Cancel payment. Thank you for visiting our store!");
//        }
//        super.writeBinary(super.getFlights(),super.PATH_FILE);
//    }

}
