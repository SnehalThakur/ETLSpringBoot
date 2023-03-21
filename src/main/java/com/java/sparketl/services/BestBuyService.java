package com.java.sparketl.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.sparketl.model.BestBuy;
import com.java.sparketl.repositoriy.BestBuyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import javax.persistence.Query;
import javax.persistence.EntityManager;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BestBuyService {
    @Autowired
    BestBuyRepo bestBuyRepo;

    @Autowired
    private EntityManager entityManager;

    public List<BestBuy> executeBestBuyQuery(){
        List<BestBuy> records = bestBuyRepo.findAll();
        return records;
    }

    public List<BestBuy> saveBBList(List<BestBuy> bbList){
        List<BestBuy> records = bestBuyRepo.saveAll(bbList);
        return records;
    }


    public BestBuy getUserByRoll(EntityManager entityManager, String customQuery) {

        String queryStr = customQuery;
        try {
            Query query = entityManager.createNativeQuery(queryStr);
            List<Object[]> records = query.getResultList();
            return new BestBuy((Object[]) query.getSingleResult());
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    public String executeCustomQuery(String query) throws JsonProcessingException {
        List<BestBuy> results = entityManager.createNativeQuery(query, BestBuy.class).getResultList();
        // Create ObjectMapper object.
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // Serialize Object to JSON.
        String json = mapper.writeValueAsString(results);
        return json;
    }
}
