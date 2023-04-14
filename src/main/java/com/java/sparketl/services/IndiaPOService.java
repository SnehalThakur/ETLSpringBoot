package com.java.sparketl.services;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.sparketl.model.BestBuy;
import com.java.sparketl.model.Employee;
import com.java.sparketl.model.IndiaPO;
import com.java.sparketl.repositoriy.IndiaPORepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;

@Component
public class IndiaPOService {

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Autowired
    IndiaPORepo indiaPORepo;

    @Autowired
    private EntityManager entityManager;

    public List<IndiaPO> executeIndiaPOQuery(){
        List<IndiaPO> records = indiaPORepo.findAll();
        return records;
    }

    public List<IndiaPO> savePOList(List<IndiaPO> poList){
        List<IndiaPO> records = indiaPORepo.saveAll(poList);
        return records;
    }


    public String executeCustomQuery(String query) throws JsonProcessingException {

        if(query.contains("DELETE")) {
            jdbcTemplate.update(query);
            List<IndiaPO> results = jdbcTemplate.query("select * from india_po", BeanPropertyRowMapper.newInstance(IndiaPO.class));
            // Create ObjectMapper object.
            ObjectMapper mapper = new ObjectMapper();
            // Serialize Object to JSON.
            String json = mapper.writeValueAsString(results);
            return json;
        }
        else if(query.contains("select")){
            List<IndiaPO> results = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(IndiaPO.class));
            /*
            List<IndiaPO> results = entityManager.createNativeQuery(query, IndiaPO.class ).getResultList();
            mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
            */
            // Create ObjectMapper object.
            ObjectMapper mapper = new ObjectMapper();
            // Serialize Object to JSON.
            String json = mapper.writeValueAsString(results);
            return json;
        }
        else {
            jdbcTemplate.update(query);
            List<IndiaPO> results = jdbcTemplate.query("select * from india_po", BeanPropertyRowMapper.newInstance(IndiaPO.class));
            // Create ObjectMapper object.
            ObjectMapper mapper = new ObjectMapper();
            // Serialize Object to JSON.
            String json = mapper.writeValueAsString(results);
            return json;
        }
    }
}
