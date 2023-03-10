package zzzz;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PDFGen {
    public PDFGen(String texto, String nomeCurso) {
        Document doc = new Document();
        try {
            PdfWriter.getInstance(doc,new FileOutputStream("Relatorio" + nomeCurso + ".pdf"));
            doc.open();
            Paragraph paragrafo = new Paragraph(texto);
            doc.add(paragrafo);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        doc.close();
    }
}
