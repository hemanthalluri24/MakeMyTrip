package com.mmt.service;

import com.mmt.binding.Passenger;
import com.mmt.binding.TicketInformation;

public interface MMTService {
	
	public TicketInformation bookTicket(Passenger passenger);

	public TicketInformation getTicket(String pnr);

	public TicketInformation updateTicket(String pnr,  Passenger passenger);

	public boolean deleteTicket(String pnr);


}
