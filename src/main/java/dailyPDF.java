import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.google.gson.JsonObject;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import java.util.ArrayList;
import java.util.List;

public class dailyPDF {
    private static final String destPDF = "/Users/ketikakhniauri/IdeaProjects/RestFull/result/daily.pdf";


    public static void printer(String [][] data) throws IOException {
        //Filling the array list for testing
        /*for (int i = 0; i < 56; i++) {
            data.add(Integer.toString(i));
        }*/

        //Initialization
        File file = new File(destPDF);
        file.getParentFile().mkdirs();

        PdfWriter writer = new PdfWriter(destPDF);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        //Creating Table
        Table reportTable = new Table(7, true);

        //Setting names for headers
        reportTable.addCell(new Paragraph("gaurkveveli"));
        reportTable.addCell(new Paragraph("gaurkveveli"));
        reportTable.addCell(new Paragraph("gaurkveveli"));
        reportTable.addCell(new Paragraph("gaurkveveli"));
        reportTable.addCell(new Paragraph("gaurkveveli"));
        reportTable.addCell(new Paragraph("gaurkveveli"));
        reportTable.addCell(new Paragraph("gaurkveveli"));

        reportTable.addCell(new Paragraph("Total"));
        reportTable.addCell(new Paragraph("0.0"));
        reportTable.addCell(new Paragraph("0.0"));
        reportTable.addCell(new Paragraph("0.0"));
        reportTable.addCell(new Paragraph("0.0"));
        reportTable.addCell(new Paragraph("0.0"));
        reportTable.addCell(new Paragraph("0.0"));



        for (int i=0;i<data.length;i++){
            for (int j =0; j<7; j++) {
                reportTable.addCell(data[i][j]);
            }
        }

        document.add(reportTable);
        reportTable.complete();
        document.close();
    }
}