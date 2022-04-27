package alphaBank.anomalyDetection.dao;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import alphaBank.anomalyDetection.model.TransactionApprovementResultEntity;


@Repository
public interface ResultRepository extends MongoRepository<TransactionApprovementResultEntity, String>{

	TransactionApprovementResultEntity findByTransferId(String transferId);

}
