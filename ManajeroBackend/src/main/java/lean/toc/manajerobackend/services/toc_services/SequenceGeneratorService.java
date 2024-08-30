package lean.toc.manajerobackend.services.toc_services;


import lean.toc.manajerobackend.models.toc_models.Sequence;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SequenceGeneratorService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Long generateSequence(String seqName) {
        Query query = new Query(Criteria.where("_id").is(seqName));
        Update update = new Update().inc("seq", 1);
        FindAndModifyOptions options = new FindAndModifyOptions().returnNew(true).upsert(true);

        Sequence counter = mongoTemplate.findAndModify(query, update, options, Sequence.class);
        return counter != null ? counter.getSeq() : 1L;
    }
}

