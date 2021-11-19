package de.jlingott.settings;

import java.awt.*;

public class AppSystemValues {
    public static String DEFAULT_STRING = ">noValueSetYet<";
    public static int DEFAULT_INT = 0;
    public static String DEFAULT_NUMBERSTRING = "0";
    public static String CSV_SPLIT_CHAR = ";";

    public static final String FILE_PATH_CSV = "src/de/jlingott/resources/Contacts.csv";

    public static final int FIRST_COLUMN = 320;
    public static final int SECOND_COLUMN = 540;

    public static final int FIRST_ROW_LABEL = 10;
    public static final int SECOND_ROW_LABEL = 75;
    public static final int THIRD_ROW_LABEL = 140;
    public static final int FOURTH_ROW_LABEL = 205;

    public static final int FIRST_ROW_INPUT = 35;
    public static final int SECOND_ROW_INPUT = 100;
    public static final int THIRD_ROW_INPUT = 165;
    public static final int FOURTH_ROW_INPUT = 230;

    public static final Dimension MAINFRAME_DIMENSION = new Dimension(770,500);
    public static final Dimension LIST_DIMENSION = new Dimension(300,300);
    public static final Dimension BUTTON_DIMENSION = new Dimension(300,30);
    public static final Dimension TEXT_DIMENSION = new Dimension(200,30);
    public static final Dimension TEXTAREA_DIMENSION = new Dimension(418,200);
    public static final Dimension LABEL_DIMENSION = new Dimension(200,30);

    public static final int NO_CONTACT_SELECTED = -1;
    public static final boolean BUTTON_ENABLED = true;
    public static final boolean BUTTON_DISABLED = false;

    public static final boolean FORMATTER_FOR_CSV = true;
    public static final boolean FORMATTER_FROM_CSV = false;

    //privater Konstruktor da AppSystemValues nur Konstanten beinhaltet
    private AppSystemValues(){

    }
}
