package com.java.sparketl.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.sparketl.configuration.FormRequestBody;
import com.java.sparketl.model.BestBuy;
import com.java.sparketl.model.Employee;
import com.java.sparketl.model.IndiaPO;
import com.java.sparketl.repositoriy.EmployeeRepo;
import com.java.sparketl.services.BestBuyService;
import com.java.sparketl.services.EmployeeService;
//import org.apache.spark.sql.SparkSession;
import com.java.sparketl.services.IndiaPOService;
import com.java.sparketl.util.InputFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Map;

@RestController
public class ETLController {

    @Autowired
    EmployeeService employeeService;

    @Autowired
    IndiaPOService indiaPOService;

    @Autowired
    BestBuyService bestBuyService;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    InputFileReader inputFileReader;

    @GetMapping("/")
    public String getData() {
        return  "ETL Controller";
    }

    @RequestMapping(value = "/employeeData", method = RequestMethod.GET, consumes = "application/json", produces="application/json")
    public ResponseEntity<String> getEmployeeDataData() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        List<Employee> employeeData = employeeService.executeEmployeeQuery();
        // Create ObjectMapper object.
        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // Serialize Object to JSON.
        String json = mapper.writeValueAsString(employeeData);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/bestbuyData", method = RequestMethod.GET, consumes = "application/json", produces="application/json")
    public ResponseEntity<String> getBestBuyData() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        List<BestBuy> bestBuyData = bestBuyService.executeBestBuyQuery();
        // Create ObjectMapper object.
        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // Serialize Object to JSON.
        String json = mapper.writeValueAsString(bestBuyData);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    @RequestMapping(value = "/indiaPOData", method = RequestMethod.GET, consumes = "application/json", produces="application/json")
    public ResponseEntity<String> getindiaPOData() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        List<IndiaPO> indiaPOData = indiaPOService.executeIndiaPOQuery();
        // Create ObjectMapper object.
        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // Serialize Object to JSON.
        String json = mapper.writeValueAsString(indiaPOData);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    @PostMapping("/employees")
    Employee newEmployee(@RequestBody Employee newEmployee) {
        return employeeRepo.save(newEmployee);
    }

    @RequestMapping(value = "/api/employees", method = RequestMethod.GET, consumes = "application/json", produces="application/json")
    public ResponseEntity<String>  getEmployee() throws JsonProcessingException {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        List<Employee> results = entityManager.createNativeQuery("SELECT *  FROM employee where firstname='Jatin'").getResultList();
        // Create ObjectMapper object.
        ObjectMapper mapper = new ObjectMapper();
//        mapper.enable(SerializationFeature.INDENT_OUTPUT);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        // Serialize Object to JSON.
        String json = mapper.writeValueAsString(results);
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }

    @RequestMapping(
            value = "/process",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces="application/json")
    public void process(@RequestBody Map<String, Object> payload)
            throws Exception {
        System.out.println(payload);
//        return payload;
    }


    @RequestMapping(
            value = "/execute",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces="application/json")
    public ResponseEntity<String> execute(@RequestBody FormRequestBody formRequestBody)
            throws Exception {
        String json = null;
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json");
        String inputFilePath = formRequestBody.getInputFilePath();
        // todo: update the file path
        inputFilePath = "C:\\Users\\snehal\\IdeaProjects\\spark-etl\\src\\main\\resources\\InputFiles\\"+inputFilePath;
        String inputFileType = formRequestBody.getInputFileType();
        String query = formRequestBody.getQuery();
        String outputFileType = formRequestBody.getOutputFileType();
        String outputFilePath = formRequestBody.getOutputFilePath();
        if(formRequestBody.getInputFileType().equals("csv")){
            // Reading the file
            List<IndiaPO> indiaPOList = inputFileReader.readCSVFile(inputFilePath);
            // Saving the file to MySQL DB
            List<IndiaPO> poList = indiaPOService.savePOList(indiaPOList);
            // Execute Custom Query
            if(query.equals(""))
                query = "select * from india_po";
            json = indiaPOService.executeCustomQuery(query);
/*            // Saving result in output file
            if(formRequestBody.getOutputFileType().equals("csv")){

            }else if(formRequestBody.getOutputFileType().equals("text")){

            }else if(formRequestBody.getOutputFileType().equals("json")){

            }*/
            System.out.println("CSV JSON Response" + json);
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        }else if(formRequestBody.getInputFileType().equals("text")){
            List<Employee> employeeList = inputFileReader.readTextFile(inputFilePath);
            List<Employee> empList = employeeService.saveEmployeeList(employeeList);
            if(query.equals(""))
                query = "select * from employee";
            json = employeeService.executeCustomQuery(query);
            System.out.println("Text JSON Response" + json);
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        }else  if(formRequestBody.getInputFileType().equals("json")){
            List<BestBuy> bestBuyList = inputFileReader.readJsonFile(inputFilePath);
            List<BestBuy> bbList = bestBuyService.saveBBList(bestBuyList);
            if(query.equals(""))
                query = "select * from bestbuy";
            json = bestBuyService.executeCustomQuery(query);
            System.out.println("JSON Response" + json);
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }
}
