package com.side.project.controller;

import com.side.project.parse.ExcelParse;
import com.side.project.repository.BubjungdongRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class ParseController {

    @Autowired
    BubjungdongRepository bubjungdongRepository;

    @GetMapping("/parse")
    public void bubjungdongParse() throws IOException {
        ExcelParse excelParse = new ExcelParse();
        excelParse.bubjungdong(bubjungdongRepository);
    }
}
