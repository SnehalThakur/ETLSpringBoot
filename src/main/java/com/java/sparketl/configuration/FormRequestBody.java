package com.java.sparketl.configuration;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class FormRequestBody {

    String inputFileType;

    String inputFilePath;

    String query;

    String outputFileType;

    String outputFilePath;

    public FormRequestBody() {
    }
}
