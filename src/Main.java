import model.CalculatorModel;

public class Main {
    public static void main(String[] args) {
        CalculatorModel m = new CalculatorModel();

        m.setAccu(3.0); m.push();
        m.setAccu(4.0);
        m.add();
        System.out.println("[add #1] accu=" + m.getAccu() + " pile=" + m.getPile());
    }
}