package com.java.sparketl.services;
//import org.apache.spark.api.java.JavaSparkContext;
//import org.apache.spark.sql.Row;
//import org.apache.spark.sql.SparkSession;
//import org.apache.spark.sql.Dataset;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.sparketl.model.BestBuy;
import com.java.sparketl.model.Employee;
import com.java.sparketl.repositoriy.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Tuple;
import java.util.List;

@Component
public class EmployeeService {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    JdbcTemplate jdbcTemplate;

    @PersistenceContext
    private EntityManager entityManager;

    public List<Employee> executeEmployeeQuery(){
        List<Employee> records = employeeRepo.findAll();
        return records;
    }

    public List<Employee> saveEmployeeList(List<Employee> empList){
        List<Employee> records = employeeRepo.saveAll(empList);
        return records;
    }


    public String executeCustomQuery(String query) throws JsonProcessingException {

        if(query.contains("DELETE")) {
            jdbcTemplate.update(query);
            List<Employee> results = jdbcTemplate.query("select * from employee", BeanPropertyRowMapper.newInstance(Employee.class));
            // Create ObjectMapper object.
            ObjectMapper mapper = new ObjectMapper();
            // Serialize Object to JSON.
            String json = mapper.writeValueAsString(results);
            return json;
        }
        else if(query.toLowerCase().contains("select")){
            List<Employee> results = jdbcTemplate.query(query, BeanPropertyRowMapper.newInstance(Employee.class));
            // Create ObjectMapper object.
            ObjectMapper mapper = new ObjectMapper();
            // Serialize Object to JSON.
            String json = mapper.writeValueAsString(results);
            return json;
        }
        else {
            jdbcTemplate.update(query);
            List<Employee> results = jdbcTemplate.query("select * from employee", BeanPropertyRowMapper.newInstance(Employee.class));
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





//    @Autowired
//    SparkSession session;
//
//    @Autowired
//    JavaSparkContext sc;

//    SparkSession spark = null;
//    public EmployeeService() {
//        spark = SparkSession
//                .builder()
//                .master("local")
//                .appName("Scala Spark Example").getOrCreate();
//    }


//    public String executeEmployeeQuery(String query){
//        System.out.println("Hello world!");
////        String app = sc.appName();
////        System.out.println(app);
//        SparkSession spark = SparkSession
//                .builder()
//                .master("local")
//                .appName("Scala Spark Example").getOrCreate();
//        Object csvPO = spark.read().option("inferSchema", true).option("header", true).
//                csv("src/main/resources/CSVFile/allIndiaPOList.csv");
//        ((Dataset<?>) csvPO).createOrReplaceTempView("tabPO");
//        long count = spark.sql("select * from tabPO").count();
//        System.out.println(count);
//        Object sample = spark.sql("select * from tabPO").take(5);
//        return (String) sample;
////        return app;
//    }

}
