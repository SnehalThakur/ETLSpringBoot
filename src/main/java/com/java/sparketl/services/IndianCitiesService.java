package com.java.sparketl.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.sparketl.model.Employee;
import com.java.sparketl.model.IndiaCities;
import com.java.sparketl.repositoriy.EmployeeRepo;
import com.java.sparketl.repositoriy.IndiaCitiesRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Component
public class IndianCitiesService {

    @Autowired
    IndiaCitiesRepo indiaCitiesRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    public List<IndiaCities> executeIndiaCitiesQuery(){
        List<IndiaCities> records = indiaCitiesRepo.findAll();
        return records;
    }

    public List<IndiaCities> saveIndiaCitiesList(List<IndiaCities> citiesList){
        List<IndiaCities> records = indiaCitiesRepo.saveAll(citiesList);
        return records;
    }


    public String executeCustomQuery(String query) throws JsonProcessingException {

        if(query.contains("DELETE")) {
            jdbcTemplate.update(query);
            List<IndiaCities> results = jdbcTemplate.query("select * from india_cities", BeanPropertyRowMapper.newInstance(IndiaCities.class));
            // Create ObjectMapper object.
            ObjectMapper mapper = new ObjectMapper();
            // Serialize Object to JSON.
            String json = mapper.writeValueAsString(results);
            return json;
        }
        else if(query.toLowerCase().contains("select")){
            List<IndiaCities> results = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(IndiaCities.class));
            // Create ObjectMapper object.
            ObjectMapper mapper = new ObjectMapper();
            // Serialize Object to JSON.
            String json = mapper.writeValueAsString(results);
            return json;
        }
        else {
            jdbcTemplate.update(query);
            List<IndiaCities> results = jdbcTemplate.query("select * from india_cities", BeanPropertyRowMapper.newInstance(IndiaCities.class));
            // Create ObjectMapper object.
            ObjectMapper mapper = new ObjectMapper();
            // Serialize Object to JSON.
            String json = mapper.writeValueAsString(results);
            return json;
        }


        /*   List<Employee> results = entityManager.createNativeQuery(query, Employee.class).getResultList();
        // Create ObjectMapper object.
        ObjectMapper mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // Serialize Object to JSON.
        String json = mapper.writeValueAsString(results);
        return json;*/
    }


}

