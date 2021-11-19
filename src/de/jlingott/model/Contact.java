package de.jlingott.model;

import de.jlingott.gui.MainFrame;
import de.jlingott.settings.AppSystemValues;

public class Contact {
    //region 1. Konstanten
    //endregion
    //region 2. Variablen

    private String strName;
    private String strLastName;
    private String strBday;
    private String strAdress;
    private String strTel;
    private String strMail;
    private int iAge;


    //endregion
    //region 3. Konstruktor

    public Contact(){
        this.strName = AppSystemValues.DEFAULT_STRING;
        this.strLastName = AppSystemValues.DEFAULT_STRING;
        this.strBday = AppSystemValues.DEFAULT_STRING;
        this.strAdress = AppSystemValues.DEFAULT_STRING;
        this.strTel = AppSystemValues.DEFAULT_STRING;
        this.strMail = AppSystemValues.DEFAULT_STRING;
        this.iAge = AppSystemValues.DEFAULT_INT;
    }
    public Contact(String strName,
                   String strLastName,
                   String strTel,
                   String strMail,
                   String strBday,
                   int iAge,
                   String strAdress
    ){
        this.strName = strName;
        this.strLastName = strLastName;
        this.strTel = strTel;
        this.strMail = strMail;
        this.strBday = strBday;
        this.iAge = iAge;
        this.strAdress = strAdress;
    }

    //endregion
    //region 4. Getter Setter

    public String getStrName() {
        return strName;
    }

    public void setStrName(String strName) {
        this.strName = strName;
    }

    public String getStrLastName() {
        return strLastName;
    }

    public void setStrLastName(String strLastName) {
        this.strLastName = strLastName;
    }

    public String getStrBday() {
        return strBday;
    }

    public void setStrBday(String strBday) {
        this.strBday = strBday;
    }

    public int getiAge() {
        return iAge;
    }

    public void setiAge(int iAge) {
        this.iAge = iAge;
    }

    public String getStrAdress() {return strAdress;}

    public void setStrAdress(String strAdress) {
        this.strAdress = strAdress;
    }

    public String getStrTel() {
        return strTel;
    }

    public void setStrTel(String strTel) {
        this.strTel = strTel;
    }

    public String getStrMail() {
        return strMail;
    }

    public void setStrMail(String strMail) {
        this.strMail = strMail;
    }

    //endregion

    //region 5. Funktionen Methoden

    @Override
    public String toString() {
        return strName+ " "+ strLastName;
    }

    /**
     * erstellt ein String für die CSV Datei zum speichern im CSV format
     *
     * @return String im CSV Format mit allen Contact Attributes
     */
    public String saveAttributesToCsv(){
        return this.strName + AppSystemValues.CSV_SPLIT_CHAR + this.strLastName+ AppSystemValues.CSV_SPLIT_CHAR + this.strTel+ AppSystemValues.CSV_SPLIT_CHAR + this.strMail+
                AppSystemValues.CSV_SPLIT_CHAR + this.strBday+ AppSystemValues.CSV_SPLIT_CHAR + this.iAge+AppSystemValues.CSV_SPLIT_CHAR + this.strAdress + "\n";
    }

    /**
     * gibts die einzelnen Attribute einer CSV Line in einem Array aus.
     * Diese Attribute werden dann dem {@link Contact} Objekt zugewiesen
     *
     * @param strCsvLine : String: eine Line aus der CSV datei
     */
    public void setAllAttributesFromCsvLine(String strCsvLine){
        String[] strAllAttributes = strCsvLine.split(AppSystemValues.CSV_SPLIT_CHAR);

        //Attribute setzen
        this.strName = strAllAttributes[0];
        this.strLastName = strAllAttributes[1];
        this.strTel = strAllAttributes[2];
        this.strMail = strAllAttributes[3];
        this.strBday = strAllAttributes[4];
        this.iAge = Integer.valueOf(strAllAttributes[5]);
        this.strAdress = strAllAttributes[6];
    }

    public void changeAttributesFromCsvLine(String strName,
                                            String strLastName,
                                            String strTel,
                                            String strMail,
                                            String strBday,
                                            int iAge,
                                            String strAdress){
        this.strName = strName;
        this.strLastName = strLastName;
        this.strTel = strTel;
        this.strMail = strMail;
        this.strBday = strBday;
        this.iAge = iAge;
        this.strAdress = strAdress;
    }
    //endregion
}
