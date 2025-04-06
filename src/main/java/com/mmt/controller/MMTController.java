package com.mmt.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mmt.binding.Passenger;
import com.mmt.binding.TicketInformation;
import com.mmt.service.MMTService;

@RestController
@RequestMapping("/mmp")
public class MMTController {

    private final MMTService mmtService;

    public MMTController(MMTService mmtService) {
        this.mmtService = mmtService;
    }

    @PostMapping("/bookTickets")
	public ResponseEntity<TicketInformation> bookTicketFromController(@RequestBody Passenger passenger) {
		TicketInformation ticketInfo = mmtService.bookTicket(passenger);
		return new ResponseEntity<TicketInformation>(ticketInfo, HttpStatus.CREATED);
	}
	
	@GetMapping("/getTicket/{pnr}")
	public ResponseEntity<TicketInformation> getTicketDataFromRestController(@PathVariable String pnr){
		TicketInformation trainInformation = mmtService.getTicket(pnr);
		return new ResponseEntity<TicketInformation>(trainInformation, HttpStatus.OK);
		
	}
	
	@PutMapping("/updateTicket/{pnr}")
	public ResponseEntity<TicketInformation> updateTicket(@PathVariable String pnr, @RequestBody Passenger passenger) {
		TicketInformation updatedTicket = mmtService.updateTicket(pnr, passenger);
		return new ResponseEntity<>(updatedTicket, HttpStatus.OK);

//	@PutMapping("/updateTicket/{pnr}")
//	public ResponseEntity<TicketInformation> updateTicket(@PathVariable String pnr, @RequestBody String journyDate) {
//		TicketInformation updatedTicket = mmtService.updateTicket(pnr, journyDate);
//		if(updatedTicket == null) {
//			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//		
//		}
//		else {
//			return new ResponseEntity<>(updatedTicket,HttpStatus.OK);
//		}
	}

	@DeleteMapping("/deleteTicket/{pnr}")
	public ResponseEntity<String> deleteTicket(@PathVariable String pnr) {
		boolean isDeleted = mmtService.deleteTicket(pnr);
		if (isDeleted) {
			return new ResponseEntity<>("Ticket with PNR " + pnr + " has been cancelled.", HttpStatus.OK);
		} else {
			return new ResponseEntity<>("Ticket not found with PNR " + pnr, HttpStatus.NOT_FOUND);
		}
	}

   
}
