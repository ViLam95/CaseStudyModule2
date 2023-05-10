package projectcasestudy2;


public class BookingTickets extends FlightManage {
    static int  numTickets = 0;
    public void bookTicket(){
        super.displayAll();
        System.out.println("Enter flight code: ");
        String flightCode = scanner.nextLine();

        boolean foundFlight = false;
        for (Flight flight: super.getFlights()) {
            if(flight.getFlightCode().equals(flightCode)){
                System.out.println("flight code is: " + flightCode);
                foundFlight = true;
                System.out.println("Ticket class (1: Economy, 2: Business class):");
                int seatClass = Integer.parseInt(scanner.nextLine());
                String seat = "";
                if(seatClass == 1){
                    seat = "ECONOMY";
                } else if(seatClass == 2){
                    seat = "BUSINESS CLASS";
                }
                System.out.println(seat);

                if(seatClass != 1 && seatClass != 2){
                    System.out.println("Invalid ticket class.");
                    return;
                }
                System.out.println("Number of tickets to book: ");
                numTickets = Integer.parseInt(scanner.nextLine());


                double price;
                if(seatClass == 2){
                    price = flight.getPriceTicket() * 2;
                } else {
                    price = flight.getPriceTicket();
                }
                if (numTickets > flight.getQuantityTickets()){
                    System.out.println("There are not enough tickets for your request!");
                    return ;
                }

                double totalCost = price * numTickets;
                System.out.println("========== BOOKING INFORMATION ==========");
                System.out.println("FLIGHT CODE: " + flight.getFlightCode());
                System.out.println("DEPARTURE: " + flight.getDeparture());
                System.out.println("DESTINATION: " + flight.getDestination());
                System.out.println("DEPARTURE TIME: " + flight.getDepartureTime());
                System.out.println("DESTINATION TIME: " + flight.getDestinationTime());
                System.out.println("SEAT CLASS: " + seat);
                System.out.println("NUMBER TICKETS: " + numTickets);
                System.out.println("TOTAL COST IS: " + totalCost);

                System.out.println("Booking confirmation? (Y/N)!");
                String confirmation = scanner.nextLine();
                if(confirmation.equalsIgnoreCase("Y")){
                    System.out.println("Thank you for booking!");
                    updateQuantityTicket(flightCode,numTickets);
                }
                else if(confirmation.equalsIgnoreCase("N")){
                    System.out.println("You just canceled booking!");
                }
            }

        }
        super.writeBinary(super.getFlights(),super.PATH_FILE);
        if(!foundFlight){
            System.out.println("No flight found with code " + flightCode);
        }
    }

    public void updateQuantityTicket(String flightCode, int numTickets){
        for (Flight flight:super.getFlights()){
            if(flight.getFlightCode().equals(flightCode)){
                flight.setQuantityTickets(flight.getQuantityTickets() - numTickets);
            }
        }super.writeBinary(super.getFlights(),super.PATH_FILE);
    }

    public void cancellationBooking(){
        super.displayAll();
        System.out.println("Enter flight code: ");
        String flightCode = scanner.nextLine();

        Flight flight = null;
        for (Flight fl :super.getFlights()) {
            if(fl.getFlightCode().equals(flightCode)){
                flight = fl;
                break;
            }
        }
        if (flight == null){
            System.out.println("No flight found with code " + flightCode);
        }
        System.out.println("Enter the number of tickets to cancel: ");
        int cancelNumTickets = Integer.parseInt(scanner.nextLine());
        if(cancelNumTickets > numTickets){
            System.out.println("The number of tickets you want to cancel is greater than the number of tickets booked!");
        }else {
            updateQuantityTicket(flightCode, (-cancelNumTickets));
            System.out.println("Successfully canceled ticket!");
        }
        super.writeBinary(super.getFlights(),super.PATH_FILE);
    }
}
