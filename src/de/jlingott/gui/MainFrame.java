package de.jlingott.gui;

import de.jlingott.logic.CsvFileHandler;
import de.jlingott.model.Contact;
import de.jlingott.settings.AppSystemValues;
import de.jlingott.settings.AppTexts;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.text.NumberFormatter;
import java.awt.*;
import java.text.NumberFormat;
import java.util.List;

public class MainFrame extends JFrame{
    //region 1. Konstanten
    //endregion

    //region 2. Variablen
    private List<Contact> contactsList;

    //einzelne Swing Elemente erstellen
    private JList listContactSelection;

    private JButton btnCreateChangeContact;
    private JButton btnClearContact;
    private JButton btnDeleteContact;

    private JTextField txtName;
    private JTextField txtLastName;
    private JTextField txtTel;
    private JTextField txtMail;
    private JTextField txtBday;

    private JTextArea txtAreaAdress;

    private JFormattedTextField numberAge;

    private JLabel lblName;
    private JLabel lblLastName;
    private JLabel lblTel;
    private JLabel lblMail;
    private JLabel lblBday;
    private JLabel lblAge;
    private JLabel lblAdress;

    private JPanel penelInputs;
    //endregion

    //region 3. Konstruktor

    public MainFrame(){
        contactsList = CsvFileHandler.getOnlyOneInstanceEver().readFromCsvFile();

    }
    //endregion

    //region 4. Funktionen Methoden

    /**
     * Öffnet das Graphic User Interface
     */
    public void openGui(){

        initSwingElements();

        setupSwingElements();

        addSwingElementsToFrame();

        //region GUI Funktionen

        handleCreateChangeButton();

        selectContact();

        handleClearButton();

        handleDeleteButton();
        //endregion

        //mainframe anzeigen
        this.setVisible(true);
    }

    //region 4.1 Swing Elements
    /**
     * Initialisiert alle Swing Elemente
     */
    private void initSwingElements() {
        penelInputs = new JPanel();

        lblLastName = new JLabel(AppTexts.LABEL_LASTNAME);
        lblName = new JLabel(AppTexts.LABEL_NAME);
        lblTel = new JLabel(AppTexts.LABEL_TEL);
        lblMail = new JLabel(AppTexts.LABEL_MAIL);
        lblBday = new JLabel(AppTexts.LABEL_BDAY);
        lblAge = new JLabel(AppTexts.LABEL_AGE);
        lblAdress = new JLabel(AppTexts.LABEL_ADRESS);

        txtTel = new JTextField();
        txtLastName = new JTextField();
        txtName = new JTextField();
        txtMail = new JTextField();
        txtBday = new JTextField();

        txtAreaAdress = new JTextArea();

        numberAge = numberField(numberAge);

        btnClearContact = new JButton(AppTexts.BTN_TEXT_DESELECT);
        btnCreateChangeContact = new JButton(AppTexts.BTN_TEXT_CREATE_CONTACT);
        btnDeleteContact = new JButton(AppTexts.BTN_TEXT_DELETE_CONTACT);

        listContactSelection = new JList(contactsList.toArray());


    }

    /**
     * Setzt alle standard settings der Init Swing Elemente
     */
    private void setupSwingElements() {
        this.setTitle(AppTexts.APPLICATIONNAME);
        this.setLayout(null);
        this.setSize(AppSystemValues.MAINFRAME_DIMENSION);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        listContactSelection = setupJList(listContactSelection,10,10);

        btnCreateChangeContact = setupJButton(btnCreateChangeContact,10, 320,AppSystemValues.BUTTON_ENABLED);
        btnClearContact = setupJButton(btnClearContact,10,360,AppSystemValues.BUTTON_DISABLED);
        btnDeleteContact = setupJButton(btnDeleteContact, 10,400,AppSystemValues.BUTTON_DISABLED);

        lblName = setupJLabel(lblName, AppSystemValues.FIRST_COLUMN, AppSystemValues.FIRST_ROW_LABEL);
        lblTel = setupJLabel(lblTel, AppSystemValues.FIRST_COLUMN, AppSystemValues.SECOND_ROW_LABEL);
        lblBday = setupJLabel(lblBday, AppSystemValues.FIRST_COLUMN, AppSystemValues.THIRD_ROW_LABEL);
        lblLastName = setupJLabel(lblLastName, AppSystemValues.SECOND_COLUMN,AppSystemValues.FIRST_ROW_LABEL);
        lblMail = setupJLabel(lblMail, AppSystemValues.SECOND_COLUMN, AppSystemValues.SECOND_ROW_LABEL);
        lblAge = setupJLabel(lblAge, AppSystemValues.SECOND_COLUMN,AppSystemValues.THIRD_ROW_LABEL);
        lblAdress = setupJLabel(lblAdress, AppSystemValues.FIRST_COLUMN, AppSystemValues.FOURTH_ROW_LABEL);

        txtName = setupJTextField(txtName, AppSystemValues.FIRST_COLUMN,AppSystemValues.FIRST_ROW_INPUT);
        txtTel = setupJTextField(txtTel, AppSystemValues.FIRST_COLUMN,AppSystemValues.SECOND_ROW_INPUT);
        txtBday = setupJTextField(txtBday, AppSystemValues.FIRST_COLUMN,AppSystemValues.THIRD_ROW_INPUT);
        txtLastName = setupJTextField(txtLastName, AppSystemValues.SECOND_COLUMN,AppSystemValues.FIRST_ROW_INPUT);
        txtMail = setupJTextField(txtMail, AppSystemValues.SECOND_COLUMN,AppSystemValues.SECOND_ROW_INPUT);

        numberAge = setupJFormattedTextField(numberAge, AppSystemValues.SECOND_COLUMN,AppSystemValues.THIRD_ROW_INPUT);

        txtAreaAdress = setupJTextArea(txtAreaAdress, AppSystemValues.FIRST_COLUMN,AppSystemValues.FOURTH_ROW_INPUT);

        penelInputs.setSize(AppSystemValues.MAINFRAME_DIMENSION);
        penelInputs.setLayout(null);
    }

    /**
     * Fügt die Init Swing Elemente dem Frame hinzu
     */
    private void addSwingElementsToFrame() {
        //elemente dem Panel hinzufügen
        penelInputs.add(lblName);
        penelInputs.add(txtName);

        penelInputs.add(lblLastName);
        penelInputs.add(txtLastName);

        penelInputs.add(lblTel);
        penelInputs.add(txtTel);

        penelInputs.add(lblMail);
        penelInputs.add(txtMail);

        penelInputs.add(lblBday);
        penelInputs.add(txtBday);

        penelInputs.add(lblAdress);
        penelInputs.add(txtAreaAdress);

        penelInputs.add(lblAge);
        penelInputs.add(numberAge);

        // elemente Mainframe hinzufügen
        this.add(listContactSelection);
        this.add(btnCreateChangeContact);
        this.add(btnClearContact);
        this.add(btnDeleteContact);
        this.add(penelInputs);
    }
    //endregion

    //region 4.2 Button Listener Funktion (Save / Change)
    /**
     * diese Funktion kümmert sich um die ausführung des ActionListeners vom Create und Change des Kontaktes
     * zudem aktuellisiert er die JList
     */
    private void handleCreateChangeButton() {

        btnCreateChangeContact.addActionListener(e -> {

            String strLastName = txtLastName.getText();
            String strName = txtName.getText();
            String strTel = txtTel.getText();
            String strMail = txtMail.getText();
            String strBday = txtBday.getText();
            String strAdress = txtAreaAdress.getText();

            //input in integer umwandeln
            int iAge = Integer.valueOf(numberAge.getText());
            int contactIndex = listContactSelection.getSelectedIndex();

            //AreaText Formatieren für CSV
            strAdress = textAreaFormatter(AppSystemValues.FORMATTER_FOR_CSV, strAdress);

            if(contactSelected(contactIndex)){

                //Datensatz ändern
                Contact contact = contactsList.get(contactIndex);
                contact.changeAttributesFromCsvLine(strName,strLastName,strTel,strMail,strBday,iAge,strAdress);
            }else{

                //Datensatz speichern
                contactsList.add(new Contact(strName,strLastName,strTel,strMail,strBday,iAge,strAdress));

                //aktueller Datensatz der JListe der erstellt wurde
                contactIndex = (contactsList.size()-1);
            }
            CsvFileHandler.getOnlyOneInstanceEver().saveToCsv(contactsList);

            //JListe Aktuellisieren
            listContactSelection.setListData(contactsList.toArray());

            //JList Kontakt auswählen
            listContactSelection.setSelectedIndex(contactIndex);

        });
    }
    //endregion

    //region 4.3 Delete Contact BTN
    /**
     * BTN Funktion um Kontakte zu löschen
     */
    private void handleDeleteButton() {

        btnDeleteContact.addActionListener(e -> {

            int contactIndex = listContactSelection.getSelectedIndex();

            if(contactSelected(contactIndex)){
                //Datensatz Löschen
                contactsList.remove(contactIndex);

                CsvFileHandler.getOnlyOneInstanceEver().saveToCsv(contactsList);
                clear();
            }

            //JListe Aktuellisieren
            listContactSelection.setListData(contactsList.toArray());

        });
    }
    //endregion

    //region 4.4 Clear /Clear Button

    /**
     * Diese Funktion hebt die auswahl der Liste auf zudem setzt er alle eingabefelder auf null
     *
     */
    private void handleClearButton() {
        btnClearContact.addActionListener(e -> {
            clear();
        });
    }

    /**
     * Clearfunktion ohne Button
     */
    private void clear() {
            listContactSelection.clearSelection();
            txtName.setText(AppSystemValues.EMPTY_STRING);
            txtLastName.setText(AppSystemValues.EMPTY_STRING);
            txtTel.setText(AppSystemValues.EMPTY_STRING);
            txtMail.setText(AppSystemValues.EMPTY_STRING);
            txtBday.setText(AppSystemValues.EMPTY_STRING);
            numberAge.setText(AppSystemValues.EMPTY_INT);
            txtAreaAdress.setText(AppSystemValues.EMPTY_STRING);

            toggleButtons(AppSystemValues.BUTTON_DISABLED);
            btnCreateChangeContact.setText(AppTexts.BTN_TEXT_CREATE_CONTACT);
    }
    //endregion

    //region 4.5 Selektier ein Kontakt von der JList Funktion
    /**
     * hier soll ermittelt werden welcher Kontakt ausgewählt ist,
     * damit die Attribute in den Textfeldern angezeigt werden können.
     *
     */
    private void selectContact(){
        listContactSelection.addListSelectionListener(e -> {
            int contactIndex = listContactSelection.getSelectedIndex();

            //Prüfen ob ein Kontakt ausgewählt ist
            if(contactSelected(contactIndex)){
                //enable Button
                toggleButtons(true);

                btnCreateChangeContact.setText(AppTexts.BTN_TEXT_CHANGE_CONTACT);
                Contact contact = contactsList.get(contactIndex);

                txtName.setText(contact.getStrName());
                txtLastName.setText(contact.getStrLastName());
                txtTel.setText(contact.getStrTel());
                txtMail.setText(contact.getStrMail());
                txtBday.setText(contact.getStrBday());
                numberAge.setText(String.valueOf(contact.getiAge()));
                txtAreaAdress.setText(textAreaFormatter(AppSystemValues.FORMATTER_FROM_CSV, contact.getStrAdress()));
            }
        });
    }
    //endregion

    //region 4.6 Kontakt selected
    /**
     * Prüft ob ein Kontakt ausgewählt ist
     * @param contactIndex : Index des ausgewählten elements (-1 wenn keiner ausgewählt wurde)
     * @return false wenn kein Kontakt ausgewählt wurde
     */
    private boolean contactSelected(int contactIndex) {
        return contactIndex != (AppSystemValues.NO_CONTACT_SELECTED);
    }
    //endregion

    //region 4.7 Swing Erstellungsfunktionen
    /**
     * Diese Funktion verhindert die eingabe von Strings in dem Textfeld
     * @param txtField : JFormattedTextField
     * @return JFormattedTextField
     */
    private JFormattedTextField numberField(JFormattedTextField txtField) {
        NumberFormat format = NumberFormat.getInstance();
        NumberFormatter formatter = new NumberFormatter(format);

        formatter.setValueClass(Integer.class);
        formatter.setMinimum(AppSystemValues.DEFAULT_INT);
        formatter.setMaximum(Integer.MAX_VALUE);
        formatter.setAllowsInvalid(true);
        formatter.setCommitsOnValidEdit(true);

        txtField = new JFormattedTextField(formatter);
        txtField.setText(AppSystemValues.DEFAULT_NUMBERSTRING);

        return txtField;
    }

    private JList setupJList(JList jList, int iX, int iY){
        jList.setSelectionMode(DefaultListSelectionModel.SINGLE_SELECTION);
        jList.setSize(AppSystemValues.LIST_DIMENSION);
        jList.setLocation(iX,iY);

        return jList;
    }

    private JButton setupJButton(JButton jButton , int iX, int iY,boolean isEnabled) {
        jButton.setSize(AppSystemValues.BUTTON_DIMENSION);
        jButton.setLocation(iX,iY);
        jButton.setEnabled(isEnabled);

        return jButton;
    }

    private JLabel setupJLabel(JLabel jlabel, int iX, int iY) {
        jlabel.setSize(AppSystemValues.LABEL_DIMENSION);
        jlabel.setLocation(iX,iY);

        return jlabel;
    }
    private JTextField setupJTextField(JTextField jTextField, int iX, int iY) {
        jTextField.setSize(AppSystemValues.TEXT_DIMENSION);
        jTextField.setLocation(iX,iY);

        return jTextField;
    }

    private JFormattedTextField setupJFormattedTextField(JFormattedTextField jFormattedTextField, int iX, int iY) {
        jFormattedTextField.setSize(AppSystemValues.TEXT_DIMENSION);
        jFormattedTextField.setLocation(iX,iY);

        return jFormattedTextField;
    }

    private JTextArea setupJTextArea(JTextArea jTextArea, int iX, int iY) {
        jTextArea.setSize(AppSystemValues.TEXTAREA_DIMENSION);

        Border border = BorderFactory.createLineBorder(Color.GRAY);
        jTextArea.setBorder(BorderFactory.createCompoundBorder(border,
                BorderFactory.createEmptyBorder(10, 10, 10, 10)));
        jTextArea.setLineWrap(true);

        jTextArea.setLocation(iX,iY);

        return jTextArea;
    }
    //endregion

    //region 4.8 Textfield Formatter
    /**
     * hier wird der Text von Textareas so umgewandelt das diese in der CSV abgespeichert werden können
     * die Trennung wird mit einem ___ erstellt damit keine neue Line im CSV erstellt wird.
     * (\n) konnte bei existierten csv Daten nicht mehr mit . split getrennt werden
     *
     * @param csv : boolean : ture falls die Formatierung für die CSV Datei ist false falls die umwandlung von der csv Datei stattfinden soll.
     * @return neuer TextArea String
     */
    private String textAreaFormatter(boolean csv,String txtAreaAdress) {

        String newAreaText = "";
        String[] textAreaLine;
        if(csv) {
            textAreaLine = txtAreaAdress.split("\n");
            for (String line : textAreaLine) {
                newAreaText += line + "___";
            }
        }else{
            textAreaLine = txtAreaAdress.split("___");
            for (String line : textAreaLine) {
                newAreaText += line + "\n";
            }
        }
        return newAreaText;
    }
    //endregion

    private void toggleButtons(boolean setEnabled){

        if(setEnabled == true){
            btnDeleteContact.setEnabled(AppSystemValues.BUTTON_ENABLED);
            btnClearContact.setEnabled(AppSystemValues.BUTTON_ENABLED);
        }else{
            btnDeleteContact.setEnabled(AppSystemValues.BUTTON_DISABLED);
            btnClearContact.setEnabled(AppSystemValues.BUTTON_DISABLED);
        }
    }

}
