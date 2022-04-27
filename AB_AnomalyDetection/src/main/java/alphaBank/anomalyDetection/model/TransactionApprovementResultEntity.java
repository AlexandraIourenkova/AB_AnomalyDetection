package alphaBank.anomalyDetection.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class TransactionApprovementResultEntity {
	@Id
	String approvementId;
	String transferId;
	String senderId;
	String receiverId;
	int sum;
	boolean transactionApproved;
}
