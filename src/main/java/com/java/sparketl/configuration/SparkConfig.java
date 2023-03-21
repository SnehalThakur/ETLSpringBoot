package com.java.sparketl.configuration;

import lombok.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.apache.spark.sql.SparkSession;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;


@Configuration
public class SparkConfig {
    private String appName="ETL App";
    private String masterUri="local[2]";

    @Bean
    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

//    @Bean
//    public SparkSession session() {
//        return SparkSession.builder().master(masterUri).appName("Scala Spark Example").getOrCreate();
//    }
//
//    @Bean
//    public SparkConf conf() {
//        return new SparkConf().setAppName(appName).setMaster(masterUri);
//    }
//
//    @Bean
//    public JavaSparkContext sc() {
//        return new JavaSparkContext(conf());
//    }

}
