package com.stackroute.emailnotificationservice.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancellationEmail {
    private String _id;
    private String refundId;
    private int eventId;
    private int theatreId;
    private String eventName;
    private String eventType;
    private String eventDate;
    private String eventTime;
    private List<String> seatNumbers;
    private String userEmailId;
    private String cancelledDate;
    private String cancelledTime;
    private String refundStatus;
    private int refundedAmount;
    private String theatreName;
    private String street;
    private String district;
    private String state;
    private String pinCode;
}
