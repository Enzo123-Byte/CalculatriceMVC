package view;


import java.util.List;

public interface CalculatorViewInterface {
    void changeAccu(String newAccu);
    void changePile(List<Double> pile);
    void displayError(String message);
}
