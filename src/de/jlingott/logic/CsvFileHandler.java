package de.jlingott.logic;

import de.jlingott.model.Contact;
import de.jlingott.settings.AppSystemValues;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFileHandler {
    //region 2. Variablen
    private static CsvFileHandler onlyOneInstanceEver;
    //endregion

    //region 3. Konstruktor
    public CsvFileHandler(){
//        System.out.println("Instance erstellet");
    }
    //endregion

    //region 4. Funktionen Methoden

    public static synchronized CsvFileHandler getOnlyOneInstanceEver(){

        if(onlyOneInstanceEver == null){

            onlyOneInstanceEver = new CsvFileHandler();
        }
        return onlyOneInstanceEver;
    }

    /**
     * Speichert alle die übergebenen Kontakte in eine CSV Datei
     *
     * @param contacts Liste der Kontakte die gespeichert werden soll
     */
    public void saveToCsv(List<Contact> contacts){

        //Anlegen eines Datenobjektes
        File csvFile = new File(AppSystemValues.FILE_PATH_CSV);

        //region Stream erstellen
        FileOutputStream fos = null;

        OutputStreamWriter osw = null;

        BufferedWriter out = null;
        //endregion

        try{
            fos = new FileOutputStream(csvFile);
            osw = new OutputStreamWriter(fos);
            out = new BufferedWriter(osw);

            for (Contact contact : contacts){
                out.write(contact.saveAttributesToCsv());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try {
                //Daten in das Dokument schreiben
                out.flush();

                //Verbindung trennen
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
    public List <Contact> readFromCsvFile(){

        //Kontaktliste erstellen
        List <Contact> contactListFromFile = new ArrayList<>();

        //Dateiobjekt erstellen
        File csvFile = new File(AppSystemValues.FILE_PATH_CSV);

        FileInputStream fis = null;
        InputStreamReader isr = null;
        BufferedReader in = null;

        try{
            //prüfen ob die datei exisitert wenn nicht erstellen
            if(csvFile.exists()){

                fis = new FileInputStream(csvFile);
                isr = new InputStreamReader(fis);
                in = new BufferedReader(isr);

                //end of line deffinieren
                boolean eof = false;

                while(!eof){
                    //einzelne Line auslesen
                    String strReadCsvLine = in.readLine();

                    if(strReadCsvLine == null){
                        //wenn ende erreicht ist
                        eof = true;
                    }else{
                        //falls nicht neues Contact Objekt erstellen und Liste hinzufügen
                        Contact contact = new Contact();

                        //Attribute für den Kontakt setzen
                        contact.setAllAttributesFromCsvLine(strReadCsvLine);
                        //Objekt in die Liste hinzufügen
                        contactListFromFile.add(contact);
                    }
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            if (in != null) {
                try {
                    in.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return contactListFromFile;
    }

    //endregion
}
