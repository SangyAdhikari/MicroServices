package com.stackroute.registrationservice.Services;
import static org.springframework.data.mongodb.core.query.Query.query;
import java.util.Objects;
import static org.springframework.data.mongodb.core.FindAndModifyOptions.options;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;




import com.stackroute.registrationservice.Entity.DbSequence;
@Service
public class SequenceGeneratorService {
	
	@Autowired
    private  MongoOperations mongoOperations;

    @Autowired
    public SequenceGeneratorService(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public  int generateSequence(String seqName) {
    	Query query=new Query(Criteria.where("id").is(seqName));
    	Update update=new Update().inc("seqNo", 1);

        DbSequence counter = mongoOperations.findAndModify(query,
                update, options().returnNew(true).upsert(true),
                DbSequence.class);
        return !Objects.isNull(counter) ? counter.getSeqNo() : 1;

    }
}
