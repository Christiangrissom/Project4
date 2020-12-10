package sample;

import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.FileNotFoundException;
import java.io.FileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * @author Christian Grissom
 * @version 1.0
 */
public class Main extends Application{
    ComboBox Dropdown;
    ListView StationList;
    Label HammDist_Label;
    Label Compare_Label;
    Label Distance_Zero;
    Label Distance_One;
    Label Distance_Two;
    Label Distance_Three;
    Label Distance_Four;
    Button Show_Station;
    Button Calc_HD;
    Button Add_Station;
    TextField HammDist_Field;
    TextField Distance_Zero_T;
    TextField Distance_One_T;
    TextField Distance_Two_T;
    TextField Distance_Three_T;
    TextField Distance_Four_T;
    TextField Add_Station_T;
    Hyperlink hyperlink;





    private static ArrayList<String> Dist_Array_1 = new ArrayList();

    private static ArrayList<String> Dist_Array_2 = new ArrayList();

    private static ArrayList<String> Dist_Array_3 = new ArrayList();

    private static ArrayList<String> Dist_Array_4 = new ArrayList();


    private static ArrayList<String> Array_To_Display = new ArrayList();//array used to display alike stations

    private static ArrayList<String> DropD = new ArrayList();//being referenced and initialized by the method initialize

    private static Label User_Console = new Label();



    Insets gridPadding;
    Slider slider;
    GridPane layout;


    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Hamming Distance");

        layout = new GridPane();
        layout.setAlignment(Pos.TOP_LEFT);



        HammDist_Label = new Label();
        HammDist_Label.setText("Enter Hamming Dist:");
        Font BoldFont = Font.font("Verdana", FontWeight.BOLD, 12);
        HammDist_Label.setFont(BoldFont);
        layout.add(HammDist_Label, 0, 0);



        slider = new Slider(1,4,1 );
        slider.valueProperty().addListener(new ChangeListener<Number>() {
            @Override
            public void changed(
                    ObservableValue<? extends Number> observableValue,
                    Number oldValue,
                    Number newValue) {
                HammDist_Field.setText(String.valueOf((int)slider.getValue()));
            }
        });

        slider.setSnapToTicks(true);
        slider.setMajorTickUnit(1);
        slider.setMinorTickCount(0);
        slider.setShowTickMarks(true);
        slider.setShowTickLabels(true);
        slider.setValueChanging(true);
        layout.add(slider, 0,2);

        HammDist_Field = new TextField();
        HammDist_Field.setEditable(false);
        HammDist_Field.setPrefColumnCount(5);
        HammDist_Field.setText("1");
        layout.addRow(0, HammDist_Field);

        Show_Station = new Button();
        Show_Station.setText("Show Station");
        Show_Station.setOnAction(actionEvent -> {//change this to work for the next part of the code
            calculation_HD((int)slider.getValue(),(String)Dropdown.getValue());
            stationListInitialize();
        });
        layout.add(Show_Station,0,3);


        User_Console.setText("Warnings and important messages will appear here.");
        stationListInitialize();



        Compare_Label = new Label();
        Compare_Label.setFont(BoldFont);
        Compare_Label.setText("Compare with:");
        layout.add(Compare_Label,0,5);

        initializeDD();

        Calc_HD = new Button();
        Calc_HD.setText("Calculate HD");
        Calc_HD.setOnAction(actionEvent -> {
            calculation_HD((int)slider.getValue(),(String)Dropdown.getValue());
            distanceUpdate();
        });
        layout.add(Calc_HD,0,6);

        Distance_Zero = new Label();
        Distance_Zero.setFont(BoldFont);
        Distance_Zero.setText("    Distance 0");
        layout.add(Distance_Zero,0,7);

        Distance_Zero_T = new TextField();
        Distance_Zero_T.setEditable(false);
        Distance_Zero_T.setText("0");
        Distance_Zero_T.setMaxWidth(120);
        layout.addRow(7,Distance_Zero_T);

        Distance_One = new Label();
        Distance_One.setFont(BoldFont);
        Distance_One.setText("    Distance 1");
        layout.add(Distance_One,0,8);

        Distance_One_T = new TextField();
        Distance_One_T.setEditable(false);
        Distance_One_T.setText("0");
        Distance_One_T.setMaxWidth(120);

        layout.addRow(8,Distance_One_T);


        Distance_Two = new Label();
        Distance_Two.setFont(BoldFont);
        Distance_Two.setText("    Distance 2");
        layout.add(Distance_Two,0,9);

        Distance_Two_T = new TextField();
        Distance_Two_T.setEditable(false);
        Distance_Two_T.setText("0");
        Distance_Two_T.setMaxWidth(120);

        layout.addRow(9,Distance_Two_T);


        Distance_Three = new Label();
        Distance_Three.setFont(BoldFont);
        Distance_Three.setText("    Distance 3");
        layout.add(Distance_Three, 0, 10);

        Distance_Three_T = new TextField();
        Distance_Three_T.setEditable(false);
        Distance_Three_T.setText("0");
        Distance_Three_T.setMaxWidth(120);
        layout.addRow(10,Distance_Three_T);


        Distance_Four = new Label();
        Distance_Four.setFont(BoldFont);
        Distance_Four.setText("    Distance 4");
        layout.add(Distance_Four, 0,11);

        Distance_Four_T = new TextField();
        Distance_Four_T.setMaxWidth(120);
        Distance_Four_T.setEditable(false);
        Distance_Four_T.setText("0");
        layout.addRow(11,Distance_Four_T);

        Add_Station = new Button();
        Add_Station.setText("Add Station");
        Add_Station.setOnAction(actionEvent -> {
            System.out.println("the text in the box to the left is: " + Add_Station_T.getText());
            System.out.println("The value of that is:" + Add_Station_T.getText().length());
            testForSimilar(Add_Station_T.getText());
        });
        layout.add(Add_Station,0,12);

        Add_Station_T = new TextField();
        Add_Station_T.setEditable(true);
        Add_Station_T.setText("Enter a station ID here.");
        Add_Station_T.setMaxWidth(140);
        layout.add(Add_Station_T,1,12);


        initializeUser();

        hyperlink =  new Hyperlink("https://en.wikipedia.org/wiki/Hamming_distance");


        gridPadding = new Insets(10,10,10,10);
        layout.setPadding(gridPadding);
        layout.setHgap(10);
        layout.setVgap(10);

        Scene scene = new Scene(layout);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * initializeUser() is used to initialize the User_Console, and is used to clean up the code a bit
     */
    private void initializeUser() {
        User_Console.setWrapText(true);
        User_Console.setMaxWidth(180);
        User_Console.setMaxHeight(400);
        Font User_Console_Font = Font.font("Verdana", FontWeight.EXTRA_BOLD, 14);
        User_Console.setFont(User_Console_Font);
        layout.add(User_Console,1,4);
    }

    /**
     * ChangeUser() updates the text to the right of the list of stations. It is very important,
     * as it is the only way of communicating with the user directly.
     * @param text The string that User_Console will project.
     */
    public void changeUser(String text){
        User_Console.setText(text);
    }

    /**
     *Initializes StationList listview. Using this format can help simplify creating and displaying lists.
     */
    private void stationListInitialize() {
        ObservableList<String> names = FXCollections.observableArrayList(Array_To_Display);
        StationList = new ListView<String>(names);
        StationList.setEditable(false);
        layout.add(StationList,0,4);
    }

    /**
     * InitializeDD() initializes the dropdown menu. It is also used to update the menu.
     */
    private void initializeDD() {
        ObservableList<String> Drop = FXCollections.observableArrayList(DropD);
        Dropdown = new ComboBox(Drop);
        Dropdown.setEditable(false);
        layout.add(Dropdown,1,5);
    }


    public static void main(String[] args) {
            initializeDropDArray();
            launch(args);
    }

    /**
     * InitializeDropDArray() initializes the dropdown array, clearing all the previous info and creating a new list of station IDs (based off Mesonet.txt)
     * This is called inside of main to ensure it contains every required station. All custom stations are deleted when called.
     */
    private static void initializeDropDArray() {
        DropD.clear();
        try {
            FileReader file = new FileReader("Mesonet.txt");
            Scanner input = new Scanner(file);
            while(input.hasNextLine()) {
                DropD.add(input.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Something has went wrong in Main! (Reading the file)");
            e.printStackTrace();
        }

    }

    /**
     * testForSimilar just checks whether the dropdown menu contains the new station ID. If so, the user is informed
     * that the station with that ID already exists. If the word is new and is 4 characters, it is added to the dropdown
     * menu.
     * @param ID The string that represents the 4 character station ID.
     */
    public void testForSimilar(String ID){
        if(DropD.contains(ID)){
            changeUser("That station ID already exists!");
        }
        else if(ID.length()!=4) {
            changeUser("Please enter a valid station ID to add a station. The ID needs to be 4 letters / numbers.");
        }
         else {
            changeUser("The station ID has been added to the list below.");
            DropD.add(ID);
            Collections.sort(DropD);
            initializeDD();
        }
    }

    /**
     * calculation_HD() is used to compare a singular station to the rest in the dropdown list. This singular station is
     * the param ID. All arrays are cleared at the beginning of each use, which ensures that nothing within the arrays
     * can cause issues elsewhere in the code. Four different arrays are created, which store the respective ID compared
     * to that respective hamming distance.
     * @param hammNum The hamming distance requested by the user.
     * @param ID The station being focused on. This station will serve as the ID to compare the other ID's to.
     */
    public void calculation_HD(int hammNum, String ID){
        int NumOfChar = 4;
        int diffChars = 0;
        Array_To_Display.clear();
        Dist_Array_1.clear();
        Dist_Array_2.clear();
        Dist_Array_3.clear();
        Dist_Array_4.clear();
        if(ID == null ||ID.length()!=4){
            changeUser("Please select a station ID from the list below.");
        }
        else{
            for(int x = 0; x<DropD.size(); x++){
                for(int y = 0; y<NumOfChar; y++){
                if(ID.charAt(y)!=DropD.get(x).charAt(y)){
                    diffChars++;
                    }
                }
                switch (diffChars) {
                    case 0-> {}
                    case 1-> Dist_Array_1.add(DropD.get(x));
                    case 2-> Dist_Array_2.add(DropD.get(x));
                    case 3-> Dist_Array_3.add(DropD.get(x));
                    case 4-> Dist_Array_4.add(DropD.get(x));
                }
                diffChars = 0;
            }
            switch (hammNum){
                case 1-> Array_To_Display.addAll(Dist_Array_1);
                case 2-> Array_To_Display.addAll(Dist_Array_2);
                case 3-> Array_To_Display.addAll(Dist_Array_3);
                case 4-> Array_To_Display.addAll(Dist_Array_4);
            }
        }
    }

    /**
     * distanceUpdate() is called when the button Calculate_HD is pressed. This method assigns the correct number of
     * stations with that of the correct hamming distance. The reason there is no initialize distance is because distance
     * can update at any time, so initializing it is easier to do when adding it to the scene.
     */
    private void distanceUpdate() {
        Distance_Zero_T.setText("1");
        Distance_One_T.setText(String.valueOf(Dist_Array_1.size()));
        Distance_Two_T.setText(String.valueOf(Dist_Array_2.size()));
        Distance_Three_T.setText(String.valueOf(Dist_Array_3.size()));
        Distance_Four_T.setText(String.valueOf(Dist_Array_4.size()));
    }


}
//add a "what is hamming distance?" hyperlink at the bottom because thats cool