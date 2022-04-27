package alphaBank.anomalyDetection.service;

import java.util.Random;
import java.util.function.Consumer;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import alphaBank.anomalyDetection.dao.ResultRepository;
import alphaBank.anomalyDetection.dto.MessageDto;
import alphaBank.anomalyDetection.dto.ResponseMessageDto;
import alphaBank.anomalyDetection.model.TransactionApprovementResultEntity;


@Service
public class AnomalyDetectionService implements IAnomalyDetectionService {
	private static Random random = new Random();
	StreamBridge streamBridge;
	ResultRepository repository;
	ModelMapper modelMapper;

	@Autowired
	public AnomalyDetectionService(StreamBridge streamBridge, ResultRepository repository, ModelMapper modelMapper) {
		this.streamBridge = streamBridge;
		this.repository = repository;
		this.modelMapper = modelMapper;
	}

	@Bean
	public Consumer<MessageDto> dataReceiver() {
		return this::messageProcessing;
	}

	//@Transactional
	private void messageProcessing(MessageDto messageDto) {
		boolean transactionApproved = dummyFunction();
		TransactionApprovementResultEntity entity = modelMapper.map(messageDto,
				TransactionApprovementResultEntity.class);
		if (repository.findByTransferId(entity.getTransferId()) != null) {
			return;
		}
		entity.setTransactionApproved(transactionApproved);
		
		repository.save(entity);
		ResponseMessageDto responseMessageDto = modelMapper.map(entity, ResponseMessageDto.class);
		System.out.println(messageDto);
		System.out.println(responseMessageDto);
		if (!transactionApproved) {
			notifyUser(responseMessageDto);
		}
		System.out.println(streamBridge.send("transactionClosure-out-0", responseMessageDto));
	}

	private void notifyUser(ResponseMessageDto responseMessageDto) {
		// TODO Auto-generated method stub

	}

	private static boolean dummyFunction() {
		return random.nextBoolean();
	}
}
