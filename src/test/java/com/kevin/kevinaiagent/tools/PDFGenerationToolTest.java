package com.kevin.kevinaiagent.tools;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PDFGenerationToolTest {

    @Test
    void generatePDF() {
        PDFGenerationTool pdfGenerationTool = new PDFGenerationTool();
        String content = "编程导航原创项目";
        String fileName = "编程导航.pdf";
        String result = pdfGenerationTool.generatePDF(fileName, content);
        Assertions.assertNotNull(result);
    }
}