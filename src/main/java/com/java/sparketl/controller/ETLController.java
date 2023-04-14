package com.java.sparketl.controller;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.sparketl.configuration.FormRequestBody;
import com.java.sparketl.model.BestBuy;
import com.java.sparketl.model.Employee;
import com.java.sparketl.model.IndiaCities;
import com.java.sparketl.model.IndiaPO;
import com.java.sparketl.repositoriy.EmployeeRepo;
import com.java.sparketl.services.BestBuyService;
import com.java.sparketl.services.EmployeeService;
//import org.apache.spark.sql.SparkSession;
import com.java.sparketl.services.IndiaPOService;
import com.java.sparketl.services.IndianCitiesService;
import com.java.sparketl.util.InputFileReader;
import com.java.sparketl.util.OutputFileWriter;
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
    IndianCitiesService indianCitiesService;

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    InputFileReader inputFileReader;

    @Autowired
    OutputFileWriter outputFileWriter;

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
            else if (query.equals("Display none"))
                query = "select * from india_po where officename IS NULL OR pincode IS NULL OR officetype IS NULL OR deliverystatus IS NULL OR divisionname IS NULL OR regionname IS NULL OR circlename IS NULL OR taluk IS NULL OR districtname IS NULL OR statename IS NULL OR telephone IS NULL OR relatedsuboffice IS NULL OR relatedheadoffice IS NULL OR longitude IS NULL OR latitude IS NULL OR officename='null' OR pincode='null' OR officetype='null' OR deliverystatus='null' OR divisionname='null' OR regionname='null' OR circlename='null' OR taluk='null' OR districtname='null' OR statename='null' OR telephone='null' OR relatedsuboffice='null' OR relatedheadoffice='null' OR longitude='null' OR latitude='null' OR officename='NA' OR pincode='NA' OR officetype='NA' OR deliverystatus='NA' OR divisionname='NA' OR regionname='NA' OR circlename='NA' OR taluk='NA' OR districtname='NA' OR statename='NA' OR telephone='NA' OR relatedsuboffice='NA' OR relatedheadoffice='NA' OR longitude='NA' OR latitude='NA'";
//                query = "select * from india_po where officename IS NULL || pincode IS NULL || officetype IS NULL || deliverystatus IS NULL || divisionname IS NULL || regionname IS NULL || circlename IS NULL || taluk IS NULL || districtname IS NULL || statename IS NULL || telephone IS NULL || relatedsuboffice IS NULL || relatedheadoffice IS NULL || longitude IS NULL || latitude IS NULL";
//                query = "select * from india_po where officename || pincode || officetype || deliverystatus || divisionname || regionname || circlename || taluk || districtname || statename || telephone || relatedsuboffice || relatedheadoffice || longitude || latitude IS NULL";
            else if (query.equals("Remove none"))
                query = "DELETE from india_po where officename IS NULL OR pincode IS NULL OR officetype IS NULL OR deliverystatus IS NULL OR divisionname IS NULL OR regionname IS NULL OR circlename IS NULL OR taluk IS NULL OR districtname IS NULL OR statename IS NULL OR telephone IS NULL OR relatedsuboffice IS NULL OR relatedheadoffice IS NULL OR longitude IS NULL OR latitude IS NULL OR officename='null' OR pincode='null' OR officetype='null' OR deliverystatus='null' OR divisionname='null' OR regionname='null' OR circlename='null' OR taluk='null' OR districtname='null' OR statename='null' OR telephone='null' OR relatedsuboffice='null' OR relatedheadoffice='null' OR longitude='null' OR latitude='null' OR officename='NA' OR pincode='NA' OR officetype='NA' OR deliverystatus='NA' OR divisionname='NA' OR regionname='NA' OR circlename='NA' OR taluk='NA' OR districtname='NA' OR statename='NA' OR telephone='NA' OR relatedsuboffice='NA' OR relatedheadoffice='NA' OR longitude='NA' OR latitude='NA'";
            json = indiaPOService.executeCustomQuery(query);
            /* Saving result in output file
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
            else if (query.equals("Display none"))
                query = "select * from employee where id IS NULL OR firstname IS NULL OR lastname IS NULL OR date_of_birth IS NULL OR gender IS NULL OR designation IS NULL OR date_of_joining IS NULL OR department IS NULL OR id='null' OR firstname='null' OR lastname='null' OR date_of_birth='null' OR gender='null' OR designation='null' OR date_of_joining='null' OR department='null' OR id='NA' OR firstname='NA' OR lastname='NA' OR date_of_birth='NA' OR gender='NA' OR designation='NA' OR date_of_joining='NA' OR department='NA'";
            else if (query.equals("Remove none"))
                query = "DELETE from employee where id IS NULL OR firstname IS NULL OR lastname IS NULL OR date_of_birth IS NULL OR gender IS NULL OR designation IS NULL OR date_of_joining IS NULL OR department IS NULL OR id='null' OR firstname='null' OR lastname='null' OR date_of_birth='null' OR gender='null' OR designation='null' OR date_of_joining='null' OR department='null' OR id='NA' OR firstname='NA' OR lastname='NA' OR date_of_birth='NA' OR gender='NA' OR designation='NA' OR date_of_joining='NA' OR department='NA'";

            json = employeeService.executeCustomQuery(query);
            System.out.println("Text JSON Response" + json);
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        }else  if(formRequestBody.getInputFileType().equals("json")){
            List<BestBuy> bestBuyList = inputFileReader.readJsonFile(inputFilePath);
            List<BestBuy> bbList = bestBuyService.saveBBList(bestBuyList);
            if(query.equals(""))
                query = "select * from bestbuy";
            else if (query.equals("Display none"))
                query = "select * from bestbuy where name IS NULL OR shortDescription IS NULL OR bestSellingRank IS NULL OR thumbnailImage IS NULL OR salePrice IS NULL OR manufacturer IS NULL OR url IS NULL OR type IS NULL OR image IS NULL OR customerReviewCount IS NULL OR shipping IS NULL OR salePrice_range IS NULL OR objectID IS NULL OR categories IS NULL OR name='null' OR shortDescription='null' OR bestSellingRank='null' OR thumbnailImage='null' OR salePrice='null' OR manufacturer='null' OR url='null' OR type='null' OR image='null' OR customerReviewCount='null' OR shipping='null' OR salePrice_range='null' OR objectID='null' OR categories='null' OR name='NA' OR shortDescription='NA' OR customerReviewCount='NA' OR shipping='NA' OR salePrice_range='NA' OR objectID='NA' OR categories='NA' OR bestSellingRank='NA' OR thumbnailImage='NA' OR salePrice='NA' OR manufacturer='NA' OR url='NA' OR type='NA' OR image='NA'";
            else if (query.equals("Remove none"))
                query = "DELETE from bestbuy where name IS NULL OR shortDescription IS NULL OR bestSellingRank IS NULL OR thumbnailImage IS NULL OR salePrice IS NULL OR manufacturer IS NULL OR url IS NULL OR type IS NULL OR image IS NULL OR customerReviewCount IS NULL OR shipping IS NULL OR salePrice_range IS NULL OR objectID IS NULL OR categories IS NULL OR name='null' OR shortDescription='null' OR bestSellingRank='null' OR thumbnailImage='null' OR salePrice='null' OR manufacturer='null' OR url='null' OR type='null' OR image='null' OR customerReviewCount='null' OR shipping='null' OR salePrice_range='null' OR objectID='null' OR categories='null' OR name='NA' OR shortDescription='NA' OR customerReviewCount='NA' OR shipping='NA' OR salePrice_range='NA' OR objectID='NA' OR categories='NA' OR bestSellingRank='NA' OR thumbnailImage='NA' OR salePrice='NA' OR manufacturer='NA' OR url='NA' OR type='NA' OR image='NA'";

            json = bestBuyService.executeCustomQuery(query);
            System.out.println("JSON Response" + json);
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        }else  if(formRequestBody.getInputFileType().equals("tsv")){
            List<IndiaCities> indiaCitiesList = inputFileReader.readTSVFile(inputFilePath);
            List<IndiaCities> citiesList = indianCitiesService.saveIndiaCitiesList(indiaCitiesList);
            if(query.equals(""))
                query = "select * from india_cities";
            else if (query.equals("Display none"))
                query = "select * from india_cities where city IS NULL OR lat IS NULL OR lng IS NULL OR country IS NULL OR iso2 IS NULL OR admin_name IS NULL OR capital IS NULL OR population IS NULL OR population_proper IS NULL OR city='null' OR lat='null' OR lng='null' OR country='null' OR iso2='null' OR admin_name='null' OR capital='null' OR population='null' OR population_proper='null' OR customerReviewCount='null' OR city='NA' OR lat='NA' OR lng='NA' OR country='NA' OR iso2='NA' OR admin_name='NA' OR capital='NA' OR population='NA' OR population_proper='NA'";
            else if (query.equals("Remove none"))
                query = "DELETE from india_cities where city IS NULL OR lat IS NULL OR lng IS NULL OR country IS NULL OR iso2 IS NULL OR admin_name IS NULL OR capital IS NULL OR population IS NULL OR population_proper IS NULL OR city='null' OR lat='null' OR lng='null' OR country='null' OR iso2='null' OR admin_name='null' OR capital='null' OR population='null' OR population_proper='null' OR customerReviewCount='null' OR city='NA' OR lat='NA' OR lng='NA' OR country='NA' OR iso2='NA' OR admin_name='NA' OR capital='NA' OR population='NA' OR population_proper='NA'";

            json = indianCitiesService.executeCustomQuery(query);
            System.out.println("JSON Response" + json);
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>(json, headers, HttpStatus.OK);
    }


    @RequestMapping(
            value = "/exportfile",
            method = RequestMethod.POST,
            consumes = "application/json",
            produces="application/json")
    public ResponseEntity<String> export(@RequestBody FormRequestBody formRequestBody)
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
            query = "select * from india_po";
            json = indiaPOService.executeCustomQuery(query);
            System.out.println("CSV JSON Response" + json);
            if(outputFileType.equals("tsv"))
                outputFileWriter.saveJsonToTsv("indiaPO", json);
            else if (outputFileType.equals("csv"))
                outputFileWriter.saveJsonToCsv("indiaPO", json);
            else if (outputFileType.equals("text"))
                outputFileWriter.saveJsonToTxt("indiaPO", json);
            else if (outputFileType.equals("json"))
                outputFileWriter.saveJsonFile("indiaPO", json);
            return new ResponseEntity<>("File Successfully Exported", headers, HttpStatus.OK);
        }else if(formRequestBody.getInputFileType().equals("text")){
            query = "select * from employee";
            json = employeeService.executeCustomQuery(query);
            System.out.println("Text JSON Response" + json);
            if(outputFileType.equals("tsv"))
                outputFileWriter.saveJsonToTsv("employee", json);
            else if (outputFileType.equals("csv"))
                outputFileWriter.saveJsonToCsv("employee", json);
            else if (outputFileType.equals("text"))
                outputFileWriter.saveJsonToTxt("employee", json);
            else if (outputFileType.equals("json"))
                outputFileWriter.saveJsonFile("employee", json);

            return new ResponseEntity<>("File Successfully Exported", headers, HttpStatus.OK);
        }else  if(formRequestBody.getInputFileType().equals("json")){
            query = "select * from bestbuy";
            json = bestBuyService.executeCustomQuery(query);
            System.out.println("JSON Response" + json);
            if(outputFileType.equals("tsv"))
                outputFileWriter.saveJsonToTsv("bestbuy", json);
            else if (outputFileType.equals("csv"))
                outputFileWriter.saveJsonToCsv("bestbuy", json);
            else if (outputFileType.equals("text"))
                outputFileWriter.saveJsonToTxt("bestbuy", json);
            else if (outputFileType.equals("json"))
                outputFileWriter.saveJsonFile("bestbuy", json);

            return new ResponseEntity<>("File Successfully Exported", headers, HttpStatus.OK);
        }else  if(formRequestBody.getInputFileType().equals("tsv")){
            query = "select * from india_cities";
            json = indianCitiesService.executeCustomQuery(query);
            System.out.println("JSON Response" + json);

            if(outputFileType.equals("tsv"))
                outputFileWriter.saveJsonToTsv("india_cities", json);
            else if (outputFileType.equals("csv"))
                outputFileWriter.saveJsonToCsv("india_cities", json);
            else if (outputFileType.equals("text"))
                outputFileWriter.saveJsonToTxt("india_cities", json);
            else if (outputFileType.equals("json"))
                outputFileWriter.saveJsonFile("india_cities", json);
            return new ResponseEntity<>(json, headers, HttpStatus.OK);
        }
        return new ResponseEntity<>("File Successfully Exported", headers, HttpStatus.OK);
    }
}
