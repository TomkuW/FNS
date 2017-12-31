package Projekt.Okienka;

import Projekt.PodlaczonieDoBazy.ConntectToDB;
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
 * Created by Tomek on 2017-12-30.
 */

public class Faktura {


        static DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        static Date date = new Date();
        static String currentDate = dateFormat.format(date);
        private static Font catFont = new Font(Font.FontFamily.TIMES_ROMAN, 18,
                Font.BOLD);
    private static Font myFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
            Font.BOLD);
        private static Font greyFont = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                Font.NORMAL, BaseColor.DARK_GRAY);
        private static Font subFont = new Font(Font.FontFamily.TIMES_ROMAN, 10,
                Font.BOLD);
        private static Font smallBold = new Font(Font.FontFamily.TIMES_ROMAN, 12,
                Font.BOLD);
        private static Document document;
        private String[] user;
        private ResultSet rs2;
    private ResultSet rs3;
        private String date1, date2;

        /**
         *
         * @param dates - date1 is start date, date2 is finish date
         * @param user - Information about current user
         * @param rs2 - ResultSet with data about products
         */
        public Faktura(String[] dates, String[] user, ResultSet rs2,ResultSet rs3) {
            this.user = user;
            this.rs2 = rs2;
            this.rs3=rs3;


            date1 = LocalDate.now().toString();

        }
        /**
         * Creates PDF file with report.
         */
        public void create() {
            try {
                document = new Document();

                PdfWriter.getInstance(document, new FileOutputStream
                        ("Faktury\\Faktura_Sprzedazy_" +ZamowieniaKontroler.getSelectedZamowienieId()+"_"+ date1 + "" +
                                ".pdf"));

                document.open();
                addMetaData(document);
                addTitlePage(document);
                addTitlePagetwo(document);
                addContent(document);
                addEndPAge(document);
                document.close();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        /**
         * Set meta dates to document (eg. tittle)
         *
         * @param document - document to add meta data
         */
        private static void addMetaData(Document document) throws DocumentException {
            document.addTitle("Faktura Vat nr.: "+ ZamowieniaKontroler.getSelectedZamowienieId() +"/"+ LocalDate.now
                    ());

            try {

                Image image = Image.getInstance("https://chart.googleapis.com/chart?cht=qr&chl=FNS%20Software%20Faktura%20Sprzeda%C5%BCowa%20-%20Pozdrawiam%20kto%20zagl%C4%85da%20%3AD&chs=180x180&choe=UTF-8&chld=L|2");
                image.setAbsolutePosition(500,750f);
                image.scaleAbsolute(80f, 80f);
                document.add(image);

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        /**
         * Add first part to document. For exaple tittle, author name or headers.
         *
         * @param document - document to add tittle page
         * @throws DocumentException - Throws when occurs problem with document
         */
        private void addTitlePage(Document document)
                throws DocumentException {
            Paragraph prefac = new Paragraph();

            addEmptyLine(prefac, 0);
            prefac.add(new Paragraph("Rzeszow, dnia " + LocalDate.now(), subFont));
            prefac.setAlignment(Element.ALIGN_LEFT);

            document.add(prefac);

            Paragraph prs = new Paragraph();
            addEmptyLine(prs, 3);
            prs.add(new Paragraph("ORYGINAL", catFont));
            prs.setAlignment(Element.ALIGN_CENTER);
            document.add(prs);

            Paragraph pr = new Paragraph();
            addEmptyLine(pr, 1);
            pr.add(new Paragraph("Faktura Vat nr.:" + ZamowieniaKontroler.getSelectedZamowienieId() + "/" + LocalDate.now(),
                    catFont));
            pr.setAlignment(Element.ALIGN_CENTER);
            document.add(pr);

            Paragraph per = new Paragraph();
            addEmptyLine(per, 2);
            per.add(new Paragraph("Sprzedawca: ",myFont));
            per.setAlignment(Element.ALIGN_LEFT);
            per.add(new Paragraph("FNS SOFTWARE",greyFont));
            per.add(new Paragraph("Inżynierski projekt (zespołowy)"));
            per.add(new Paragraph("III 2017/2018 Informatyka i Ekonometria"));
            addEmptyLine(per, 1);
            document.add(per);

        }
    private void addTitlePagetwo(Document document)
            throws DocumentException {
        Paragraph pree = new Paragraph();
            pree.add(new Paragraph("Nabywca: ",myFont));
            pree.setAlignment(Element.ALIGN_RIGHT);
        try {
            while (rs3.next()) {
                pree.add(new Paragraph(new Phrase(rs3.getString("k.imie") +" "+ rs3.getString("k.nazwisko"))));
                pree.add(new Paragraph(new Phrase("ul. " +rs3.getString("k.ulica"))));
                pree.add(new Paragraph(new Phrase("Nr domu: "+rs3.getString("k.nr_dom"))));
               pree.add(new Paragraph(new Phrase("Miasto: " +rs3.getString("k.miejscowosc"))));
               pree.add(new Paragraph(new Phrase("Nr telefonu: "+ rs3.getString("k.nr_telefon"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        addEmptyLine(pree, 1);
            document.add(pree);

    }


        private void addEndPAge(Document document) throws DocumentException{
            Paragraph paragraph = new Paragraph();


            addEmptyLine(paragraph, 1);
            paragraph.setAlignment(Element.ALIGN_LEFT);
            paragraph.add(new Paragraph(
                    "Faktura wystawiona przez: " + ConntectToDB.getCurrentUser()[1]+" "+ConntectToDB.getCurrentUser()
                            [2]));
            addEmptyLine(paragraph, 3);
            document.add(paragraph);

            Paragraph paragrap = new Paragraph();

            addEmptyLine(paragrap,2);
            paragrap.add(new Paragraph("..................................." ));
            paragrap.setAlignment(Element.ALIGN_RIGHT);
            addEmptyLine(paragrap, 1);
            paragrap.add(new Paragraph("Podpis Sprzedawcy ") );
            paragrap.setAlignment(Element.ALIGN_RIGHT);
            document.add(paragrap);
        }

        /**
         * Add content to document. In this case it is use to add Table
         *
         * @param document - document do add content
         * @throws DocumentException - Throws when occurs problem with document
         * @throws BadElementException - Throws when occurs problem with element to
         * addition
         * @throws SQLException - Throws when occurs problem with SQL query
         */
        private void addContent(Document document) throws DocumentException, BadElementException, SQLException {
            createTable();

        }


        //--

    private void createTable() throws BadElementException, SQLException, DocumentException {
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100);

        PdfPCell c1 = new PdfPCell(new Phrase("Nazwa"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Technologia"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Predkosc"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Ilosc"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Umowa od"));
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);


        c1 = new PdfPCell(new Phrase("Umowa do"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Vat"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cena netto"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase("Cena Brutto"));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        c1.setBackgroundColor(BaseColor.LIGHT_GRAY);
        table.addCell(c1);

        table.setHeaderRows(1);
        DecimalFormat df=new java.text.DecimalFormat("0.00");
        while (rs2.next()) {

            table.addCell(rs2.getString("u.nazwa"));
            table.addCell(rs2.getString("u.technologia"));
            table.addCell(rs2.getString("u.predkosc"));
            table.addCell(String.valueOf(rs2.getInt("ilosc")));
            table.addCell(String.valueOf(rs2.getDate("umowa_od")));
            table.addCell(String.valueOf(rs2.getDate("umowa_do")));
            table.addCell(String.valueOf("23%"));
            table.addCell(String.valueOf(df.format(rs2.getDouble("u.cena") - (0.23 *rs2.getDouble("u.cena")))));
            table.addCell(String.valueOf(df.format(rs2.getDouble("u.cena"))));
        }

        document.add(table);

    }

        /**
         * Creates empty line in document
         *
         * @param paragraph - Paragraph in document where method creates empty line
         * @param number - Number of empty lines added to paragraph
         */
        private static void addEmptyLine(Paragraph paragraph, int number) {
            for (int i = 0; i < number; i++) {
                paragraph.add(new Paragraph(" "));
            }
        }


    }




