package com.java.sparketl.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.java.sparketl.model.BestBuy;
import com.java.sparketl.model.Employee;
import com.java.sparketl.model.IndiaPO;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
public class InputFileReader {

    public static String TYPE = "text/csv";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };

//    public static boolean hasCSVFormat(MultipartFile file) {
//        if (!TYPE.equals(file.getContentType())) {
//            return false;
//        }
//        return true;
//    }


    public static List<IndiaPO> readCSVFile(String filePath) {
        try (BufferedReader fileReader = new BufferedReader(new FileReader(filePath));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {
            List<IndiaPO> poList = new ArrayList<IndiaPO>();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                IndiaPO indiaPO = new IndiaPO(
                        csvRecord.get("officename"),
                        csvRecord.get("pincode"),
                        csvRecord.get("officetype"),
                        csvRecord.get("deliverystatus"),
                        csvRecord.get("divisionname"),
                        csvRecord.get("regionname"),
                        csvRecord.get("circlename"),
                        csvRecord.get("taluk"),
                        csvRecord.get("districtname"),
                        csvRecord.get("statename"),
                        csvRecord.get("telephone"),
                        csvRecord.get("relatedsuboffice"),
                        csvRecord.get("relatedheadoffice"),
                        csvRecord.get("longitude"),
                        csvRecord.get("latitude")
                );
                poList.add(indiaPO);
            }
            return poList;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
        }
    }


    public static List<BestBuy> readJsonFile(String filePath) {

        //JSON parser object to parse read file
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(filePath))
        {
            //Read JSON file
            Object obj = jsonParser.parse(reader);

            JSONArray bestbuyArray = (JSONArray) obj;
            System.out.println(bestbuyArray);
            List<BestBuy> bestBuyList = new ArrayList<BestBuy>();

            //Iterate over employee array
            Iterator<JSONObject> iterator = bestbuyArray.iterator();
            while(iterator.hasNext()) {
                BestBuy bestBuy = parseBestBuyObject(iterator.next());
                bestBuyList.add(bestBuy);
            }
            return bestBuyList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static BestBuy parseBestBuyObject(JSONObject bestBuy)
    {
        String name = (String) bestBuy.get("name");
        String shortDescription = (String) bestBuy.get("shortDescription");
        Long bestSellingRank = (Long) bestBuy.get("bestSellingRank");
        String thumbnailImage = (String) bestBuy.get("thumbnailImage");
        Double salePrice = (Double) bestBuy.get("salePrice");
        String manufacturer = (String) bestBuy.get("manufacturer");
        String url = (String) bestBuy.get("url");
        String type = (String) bestBuy.get("type");
        String image = (String) bestBuy.get("image");
        Long customerReviewCount = (Long) bestBuy.get("customerReviewCount");
        String shipping = (String) bestBuy.get("shipping");
        String salePriceRange = (String) bestBuy.get("salePrice_range");
        String objectID = (String) bestBuy.get("objectID");
        String categories = (String) bestBuy.get("categories");

        BestBuy bestBuyObj = new BestBuy(
                name,
                shortDescription,
                bestSellingRank,
                thumbnailImage,
                salePrice,
                manufacturer,
                url,
                type,
                image,
                customerReviewCount,
                shipping,
                salePriceRange,
                objectID,
                categories
                );
        return bestBuyObj;
    }

//    public static List<Employee> readTextFile(String filePath) throws IOException {
    public static List<Employee> readTextFile(String filePath) throws IOException {

        // File path is passed as parameter
        File file = new File(filePath);

        // Creating an object of BufferedReader class
        BufferedReader br = new BufferedReader(new FileReader(file));

        // Declaring a string variable
        String st;
        int rowCount = 0;
        List<Employee> employees = new ArrayList<Employee>();
        while ((st = br.readLine()) != null) {
            System.out.println(st);
            String id = st.split(",")[0];
            String firstname = st.split(",")[1];
            String lastname = st.split(",")[2];
            String date_of_birth = st.split(",")[3];
            String gender = st.split(",")[4];
            String designation = st.split(",")[5];
            String date_of_joining = st.split(",")[6];
            String department = st.split(",")[7];
            if(rowCount!=0) {
                Employee employee = new Employee(
                        Integer.parseInt(id),
                        firstname,
                        lastname,
                        date_of_joining,
                        gender,
                        designation,
                        date_of_joining,
                        department);

                employees.add(employee);
            }
            rowCount+=1;
        }
        return employees;
    }


}
