package com.java.sparketl.util;

import com.java.sparketl.model.Employee;
import com.java.sparketl.repositoriy.BestBuyRepo;
import com.java.sparketl.repositoriy.EmployeeRepo;
import com.java.sparketl.repositoriy.IndiaPORepo;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.List;
import  com.aspose.cells.Workbook;

@Component
public class OutputFileWriter {

    @Autowired
    EmployeeRepo employeeRepo;

    @Autowired
    BestBuyRepo bestBuyRepo;

    @Autowired
    IndiaPORepo indiaPORepo;

    public void writeEmployeeToCsv(Writer writer) {

        List<Employee> employees = employeeRepo.findAll();
        try (CSVPrinter csvPrinter = new CSVPrinter(writer, CSVFormat.DEFAULT)) {
            csvPrinter.printRecord("id","firstname","lastname","date_of_birth","gender","designation","date_of_joining","department");
            for (Employee employee : employees) {
                csvPrinter.printRecord(employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getDateOfBirth(), employee.getGender(), employee.getDesignation(), employee.getDateOfJoining(), employee.getDepartment());
            }
        } catch (IOException e) {
            System.out.println("Error While writing CSV "+ e);
            throw new RuntimeException(e);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

//    public List<Employee> writeEmployeeToJson(List<Employee> employeeList, String fileOutputPath){
//        Iterator<Employee> employeeIterator = employeeList.iterator();
//        while (employeeIterator.hasNext()) {
//            Employee columns = employeeIterator.next();
//            JSONObject record = new JSONObject();
//            for (String column : columns) {
//                record.put(column, dataSet.getObject(column));
//            }
//            array.add(record);
//        }
//        jsonObject.put(tableName, array);
//        try {
//            FileWriter file = new FileWriter("output.json");
//            file.write(jsonObject.toJSONString());
//            file.close();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }

    public static void printEmployeeToCsv(List<Employee> resultArray, String file_name) throws Exception{

        File csvOutputFile = new File(file_name);
        FileWriter fileWriter = new FileWriter(csvOutputFile, false);

        for(Employee mapping : resultArray) {
            fileWriter.write(mapping + "\n");
        }
        fileWriter.close();
    }

    public void jsonFileWriter(String filepath, String jsonFile) throws IOException {
        FileWriter file = new FileWriter(filepath);
        file.write(jsonFile);
        file.close();
    }

    public void saveJsonToTxt(String fileName, String jsonFile) throws Exception {
        String filepath = "C:\\Users\\snehal\\IdeaProjects\\spark-etl\\src\\main\\resources\\Output\\"+fileName+".json";
        jsonFileWriter(filepath, jsonFile);
        Workbook workbook = new Workbook(filepath);
        String outputFilePath = "C:\\Users\\snehal\\IdeaProjects\\spark-etl\\src\\main\\resources\\Output\\"+fileName+".txt";
        workbook.save(outputFilePath);
        System.out.println("File successfully saved"+ outputFilePath);
    }

    public void saveJsonToCsv(String fileName, String jsonFile) throws Exception {
        String filepath = "C:\\Users\\snehal\\IdeaProjects\\spark-etl\\src\\main\\resources\\Output\\"+fileName+".json";
        jsonFileWriter(filepath, jsonFile);
        Workbook workbook = new Workbook(filepath);
        String outputFilePath = "C:\\Users\\snehal\\IdeaProjects\\spark-etl\\src\\main\\resources\\Output\\"+fileName+".csv";
        workbook.save(outputFilePath);
        System.out.println("File successfully saved"+ outputFilePath);
    }

    public void saveJsonToTsv(String fileName, String jsonFile) throws Exception {
        String filepath = "C:\\Users\\snehal\\IdeaProjects\\spark-etl\\src\\main\\resources\\Output\\"+fileName+".json";
        jsonFileWriter(filepath, jsonFile);
        Workbook workbook = new Workbook(filepath);
        String outputFilePath = "C:\\Users\\snehal\\IdeaProjects\\spark-etl\\src\\main\\resources\\Output\\"+fileName+".tsv";
        workbook.save(outputFilePath);
        System.out.println("File successfully saved"+ outputFilePath);
    }

    public void saveJsonFile(String fileName, String jsonFile) throws Exception {
        String filepath = "C:\\Users\\snehal\\IdeaProjects\\spark-etl\\src\\main\\resources\\Output\\"+fileName+".json";
        jsonFileWriter(filepath, jsonFile);
        System.out.println("File successfully saved"+ filepath);
    }

}
