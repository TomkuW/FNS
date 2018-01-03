package Projekt.KontroleryFinanse;

import Projekt.PodlaczenieDoBazy.ConntectToDB;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

/**
 * Klasa do generowania raportu zbiorczego ilosci sprzedanych pakietow w ciągu ostatniego roku
 *
 * Created by Tomek on 2017-12-29.
 */
public class WPDF {

    static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
    static Date date = new Date();
    static String currentDate = dateFormat.format(date);
    private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
            Font.BOLD);
    private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
    private static Document document;
    private String[] user;
    private ResultSet rs;
    private String date1, date2;

    /**
     * Konstruktor
     * @param dates - Przedział dat w okresie roku
     * @param user - Informacja o uzytkowniku
     * @param rs - ResultSet z danymi o zamowieniach
     */
    public WPDF(String[] dates, String[] user, ResultSet rs) {
        this.user = user;
        this.rs = rs;
        date1 = LocalDate.now().minusYears(1).toString();
        date2 = LocalDate.now().toString();
    }

    /**
     * Metoda tworzy plik PDF z raportem
     */
    public void create() {
        try {
            document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream
                    ("Raporty\\Raport_Roczny_Ilosc_Sprzedazy_" + currentDate + ".pdf"));

            document.open();
            addMetaData(document);
            addTitlePage(document);
            addContent(document);
            addEndPAge(document);
            document.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda ustawia date dokumentu
     * @param document - Dodawanie metadanych
     * @throws DocumentException Zgłaszane, gdy występuje problem z dokumentem
     */
    private static void addMetaData(Document document) throws DocumentException {

        document.addTitle("Raport sprzedazy " + currentDate);
        try {
            Image image = Image.getInstance("https://chart.googleapis.com/chart?cht=qr&chl=Zespo%C5%82owy%20Projekt%20In%C5%BCynierski%20FNS%20Software&chs=180x180&choe=UTF-8&chld=L|2");
            image.setAbsolutePosition(500, 760f);
            image.scaleAbsolute(70f, 70f);
            document.add(image);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Metoda dodająca pierwszą część do dokumentu tj. tytuł, imie i nazwisko autora lub nagłówki.
     * @param document Dodawanie stronę tytułową
     * @throws DocumentException Zgłaszane, gdy występuje problem z dokumentem
     */
    private void addTitlePage(Document document)
            throws DocumentException {
        Paragraph preface = new Paragraph();

        addEmptyLine(preface, 1);
        preface.add(new Paragraph("Roczny raport ilosci sprzedanych uslug " + currentDate, catFont));

        addEmptyLine(preface, 1);
        preface.add(new Paragraph(
                "Raport wygenerowany przez: " + ConntectToDB.getCurrentUser()[1] + " " + ConntectToDB.getCurrentUser()[2]));
        addEmptyLine(preface, 3);
        preface.add(new Paragraph("Wykaz uslug sprzedanych w okresie " + date1 + " do " + date2, smallBold));
        addEmptyLine(preface, 2);

        document.add(preface);

    }

    /**
     * Metoda dodająca ostatnią częśc do dokumentu tj. miejsce na podpis, date wygenerowania.
     * @param document Dodawanie dane na końcu dokumentu
     * @throws DocumentException Zgłaszane, gdy występuje problem z dokumentem
     */
    private void addEndPAge(Document document) throws DocumentException {
        Paragraph paragraph = new Paragraph();

        addEmptyLine(paragraph, 2);
        SimpleDateFormat simpleDateFormat =
                new SimpleDateFormat("MM/dd/yyyy");
        paragraph.add(new Paragraph("Wygenerowano: " + LocalDate.now()));

        addEmptyLine(paragraph, 3);
        paragraph.add(new Paragraph("..................................."));
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        addEmptyLine(paragraph, 1);
        paragraph.add(new Paragraph("Podpis Ksiegowego "));
        paragraph.setAlignment(Element.ALIGN_RIGHT);
        document.add(paragraph);
    }

    /**
     * Metoda pozwalająca dodać zawartość do dokumentu, w tym przypadku tabelke
     * @param document Dodanie tabeli z wypełnieniem
     * @throws DocumentException Zgłaszane, gdy występuje problem z dokumentem
     * @throws BadElementException Zgłasza, gdy występuje problem z elementem dodatkowym
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    private void addContent(Document document) throws DocumentException, BadElementException, SQLException {
        createTable();
    }

    /**
     * Metoda tworzenia tabeli z danymi z ResultSet
     * @throws DocumentException Zgłaszane, gdy występuje problem z dokumentem
     * @throws BadElementException Zgłasza, gdy występuje problem z elementem dodatkowym
     * @throws SQLException Zgłasza, gdy występuje problem z zapytaniem SQL
     */
    private void createTable() throws BadElementException, SQLException, DocumentException {
        PdfPTable table = new PdfPTable(6);

        PdfPCell c1 = new PdfPCell(new Phrase("Id"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Nazwa"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Data sprzedazy"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ilosc"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cena"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Wartosc"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        table.setHeaderRows(1);
        DecimalFormat df = new java.text.DecimalFormat("0.00");
        while (rs.next()) {

            table.addCell(String.valueOf(rs.getInt("z.pakiet_id")));
            table.addCell(rs.getString("u.nazwa"));
            table.addCell(String.valueOf(rs.getDate("umowa_od")));
            table.addCell(String.valueOf(rs.getInt("ilosc")));
            table.addCell(String.valueOf(df.format(rs.getDouble("u.cena"))));
            table.addCell(String.valueOf(df.format(rs.getDouble("u.cena") * rs.getInt("ilosc"))));
        }

        document.add(table);

    }

    /**
     * Metoda tworząca pustą linię w dokumencie
     * @param paragraph Akapit w dokumencie, gdzie metoda tworzy pustą linię
     * @param number Liczba pustych linii dodanych do akapitu
     */
    private static void addEmptyLine(Paragraph paragraph, int number) {
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
    }

}
