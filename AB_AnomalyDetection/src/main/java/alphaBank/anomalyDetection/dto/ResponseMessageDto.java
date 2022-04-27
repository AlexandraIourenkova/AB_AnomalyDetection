package alphaBank.anomalyDetection.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ResponseMessageDto {
	String approvementId;
	String transferId;
	String senderId;
	String receiverId;
	int sum;
	boolean transactionApproved;
	

}
