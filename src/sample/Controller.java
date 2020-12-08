package sample;

import javafx.event.ActionEvent;


public class Controller {

    static int sliderValue;


    public Controller(){

    sliderValue = 0;
}

    public void setSlider(int slider){
        System.out.println("Slider's value is :" + slider);
        sliderValue = slider;
        checkSlider();
    }
    public void checkSlider(){
        System.out.println("Checkslider value is: " + sliderValue);
        Main m = new Main();
        m.HammDist_Field.setEditable(true);
        m.HammDist_Field.setText(String.valueOf(sliderValue));
        m.HammDist_Field.setEditable(false);

    }

}
