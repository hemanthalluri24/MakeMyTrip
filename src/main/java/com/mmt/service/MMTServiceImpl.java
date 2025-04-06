package com.mmt.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mmt.binding.Passenger;
import com.mmt.binding.TicketInformation;
import com.mmt.exception.TicketNotFoundException;

@Service
@Component
public class MMTServiceImpl implements MMTService {

	private RestTemplate restTemplate;

	public MMTServiceImpl(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	@Override
	public TicketInformation bookTicket(Passenger passenger) {
		String bookUrl = "http://localhost:6061/ticket/bookTicket";
		HttpEntity<Passenger> requestEntity = new HttpEntity<>(passenger);
		ResponseEntity<TicketInformation> responseEntity = restTemplate.exchange(bookUrl, HttpMethod.POST,
				requestEntity, TicketInformation.class);
		TicketInformation ticket = responseEntity.getBody();
		if (ticket == null) {
			throw new TicketNotFoundException("Booking failed for passenger: " + passenger.getPassengerName());
		}
		return ticket;
	}

	@Override
	public TicketInformation getTicket(String pnr) {
		String getUrl = "http://localhost:6061/ticket/getTicket/{pnr}";
		Map<String, String> param = new HashMap<>();
		param.put("pnr", pnr);
		ResponseEntity<TicketInformation> responseEntity = restTemplate.getForEntity("http://localhost:6061/ticket/getTicket/"+pnr, TicketInformation.class);
		TicketInformation ticket = responseEntity.getBody();
		if (ticket == null) {
			throw new TicketNotFoundException("Ticket not found with PNR: " + pnr);
		}
		return ticket;
	}

	@Override
	public TicketInformation updateTicket(String pnr, Passenger passenger) {
		String updateUrl = "http://localhost:6061/ticket/updateTicket/" + pnr;
		HttpEntity<Passenger> requestEntity = new HttpEntity<>(passenger);
		ResponseEntity<TicketInformation> responseEntity = restTemplate.exchange(updateUrl, HttpMethod.PUT,
				requestEntity, TicketInformation.class);
		TicketInformation updatedTicket = responseEntity.getBody();
		if (updatedTicket == null) {
			throw new TicketNotFoundException("Update failed for ticket ID: " + pnr);
		}
		return updatedTicket;
	}

	@Override
	public boolean deleteTicket(String pnr) {
		String deleteUrl = "http://localhost:6061/ticket/deleteTicket/{pnr}";
		Map<String, String> param = new HashMap<>();
		param.put("pnr", pnr);
		ResponseEntity<String> responseEntity = restTemplate.exchange(deleteUrl, HttpMethod.DELETE, null, String.class,
				param);
		if (responseEntity.getBody() == null || "Ticket not found".equals(responseEntity.getBody())) {
			throw new TicketNotFoundException("Ticket not found with ID: " + pnr);
		}
		return responseEntity.getBody() != null;
	}

}
