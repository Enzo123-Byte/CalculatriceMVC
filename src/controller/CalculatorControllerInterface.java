package controller;


public interface CalculatorControllerInterface {
    // Saisie
    void onDigit(String d);
    void onEnter();   // PUSH
    void onClear();
    void onDrop();
    void onSwap();
    void onOpposite();

    // Opérations
    void onAdd();
    void onSubtract();
    void onMultiply();
    void onDivide();

    // Fin (utile pour vue console)
    void onQuit();
}