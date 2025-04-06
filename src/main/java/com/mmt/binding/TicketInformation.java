package com.mmt.binding;
import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TicketInformation {
	private String fromLoc;
	private String toLoc;
	private String passengerName;
	private String journeyDate;
	private Long trainNum;
	private String pnr;
	private String email;
	private String trainName;
	private String ticketStatus;
	private Double ticketPrice;
}
