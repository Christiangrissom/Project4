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

    private static ArrayList<String> DD = new ArrayList();//being referenced and initialized by the method initialize
    Insets gridPadding;
    Slider slider;
    GridPane layout;
    Controller c = new Controller();


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
            if(true){
            System.out.println("The button works, and so does the if statement.");
            }
        });
        layout.add(Show_Station,0,3);


        ArrayList<String> SL = new ArrayList();//use a method or a class to simplify this
        SL.add("STA1");
        SL.add("STA2");
        SL.add("STA3");
        SL.add("STA4");
        SL.remove("STA4");
        ObservableList<String> names = FXCollections.observableArrayList(SL);
        StationList = new ListView<String>(names);
        StationList.setEditable(false);
        layout.add(StationList,0,4);

        Compare_Label = new Label();
        Compare_Label.setFont(BoldFont);
        Compare_Label.setText("Compare with:");
        layout.add(Compare_Label,0,5);

        initializeDD();

        Calc_HD = new Button();
        Calc_HD.setText("Calculate HD");
        Calc_HD.setOnAction(actionEvent -> {
            calculation_HD((int)slider.getValue(),Dropdown.getId());
        });
        layout.add(Calc_HD,0,6);

        Distance_Zero = new Label();
        Distance_Zero.setFont(BoldFont);
        Distance_Zero.setText("    Distance 0");
        layout.add(Distance_Zero,0,7);

        Distance_Zero_T = new TextField();
        Distance_Zero_T.setEditable(false);
        Distance_Zero_T.setText("Not finished");
        layout.addRow(7,Distance_Zero_T);

        Distance_One = new Label();
        Distance_One.setFont(BoldFont);
        Distance_One.setText("    Distance 1");
        layout.add(Distance_One,0,8);

        Distance_One_T = new TextField();
        Distance_One_T.setEditable(false);
        Distance_One_T.setText("Not finished");
        layout.addRow(8,Distance_One_T);


        Distance_Two = new Label();
        Distance_Two.setFont(BoldFont);
        Distance_Two.setText("    Distance 2");
        layout.add(Distance_Two,0,9);

        Distance_Two_T = new TextField();
        Distance_Two_T.setEditable(false);
        Distance_Two_T.setText("Not finished");
        layout.addRow(9,Distance_Two_T);


        Distance_Three = new Label();
        Distance_Three.setFont(BoldFont);
        Distance_Three.setText("    Distance 3");
        layout.add(Distance_Three, 0, 10);

        Distance_Three_T = new TextField();
        Distance_Three_T.setEditable(false);
        Distance_Three_T.setText("Not finished");
        layout.addRow(10,Distance_Three_T);


        Distance_Four = new Label();
        Distance_Four.setFont(BoldFont);
        Distance_Four.setText("    Distance 4");
        layout.add(Distance_Four, 0,11);

        Distance_Four_T = new TextField();
        Distance_Four_T.setEditable(false);
        Distance_Four_T.setText("Not finished");
        layout.addRow(11,Distance_Four_T);

        Add_Station = new Button();
        Add_Station.setText("Add Station");
        Add_Station.setOnAction(actionEvent -> {
            testForSimilar(Add_Station_T.getText());
            System.out.println("Add_Station button works.");
        });
        layout.add(Add_Station,0,12);

        Add_Station_T = new TextField();
        Add_Station_T.setEditable(true);
        Add_Station_T.setText("");
        layout.add(Add_Station_T,1,12);



        gridPadding = new Insets(10,10,10,10);
        layout.setPadding(gridPadding);
        layout.setHgap(2);
        layout.setVgap(10);

        Scene scene = new Scene(layout);

        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private void initializeDD() {
        ObservableList<String> Drop = FXCollections.observableArrayList(DD);
        Dropdown = new ComboBox(Drop);
        Dropdown.setEditable(false);
        layout.add(Dropdown,1,5);
    }


    public static void main(String[] args) {
            initialize();
            launch(args);
    }

    private static void initialize() {
        DD.clear();
        try {
            FileReader file = new FileReader("Mesonet.txt");
            Scanner input = new Scanner(file);
            while(input.hasNextLine()) {
                DD.add(input.nextLine());
            }

        } catch (FileNotFoundException e) {
            System.out.println("Something has went wrong in Main! (Reading the file)");
            e.printStackTrace();
        }

    }

    public void testForSimilar(String s){
        if(DD.contains(s)){
            System.out.println("That already exists!");
        }
        else{
            System.out.println("The station ID has been added.");
            DD.add(s);
            Collections.sort(DD);
            initializeDD();
        }
    }
    public void calculation_HD(int hammNum, String ID){
        if(ID==null){
            System.out.println("Please enter a valid station ID.");
        }
        else{
            System.out.println("hammNum is: " + hammNum);
            System.out.println("Station ID is: " + ID);
        }
    }



}
