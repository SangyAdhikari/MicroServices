package com.stackroute.bookingticketservice.RabbitMq;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class ProducerDataForCancellationTicekets {
    private String _id;
    private String refundId;
    private int eventId;
    private int theatreId;
    private String eventDate;
    private String eventTime;
    private List<String> seatNumbers;
    private String userEmailId;
    private String cancelledDate;
    private String cancelledTime;
    private String refundStatus;
    private int refundedAmount;
}
