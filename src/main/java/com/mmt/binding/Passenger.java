package com.mmt.binding;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Passenger {
	private String passengerName;
	private String fromLoc;
	private String toLoc;
	private String journeyDate;
	private String email;
	private Long trainNum;
	private String trainName;
}
