package alphaBank.anomalyDetection.service;

import java.util.function.Consumer;

import alphaBank.anomalyDetection.dto.MessageDto;

public interface IAnomalyDetectionService {
	
	Consumer<MessageDto> dataReceiver();

}
