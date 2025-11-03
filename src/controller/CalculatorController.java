package controller;

import java.util.ArrayList;
import java.util.List;

import model.CalculatorModel;                 // NOTE: on utilise la classe concrète (simple pour démarrer)
import view.CalculatorViewInterface;

public class CalculatorController implements CalculatorControllerInterface {

    private final CalculatorModel model;
    private final CalculatorViewInterface view;

    // tampon de saisie côté contrôleur (construction du nombre : "1", "12", "12.3", etc.)
    private final StringBuilder input = new StringBuilder();

    public CalculatorController(CalculatorModel model, CalculatorViewInterface view) {
        this.model = model;
        this.view = view;
        pushUpdate(); // affichage initial
    }

    // ----- helpers -----
    private void setAccuFromInputIfAny() {
        if (input.length() > 0) {
            try {
                model.setAccu(Double.valueOf(input.toString()));
            } catch (NumberFormatException e) {
                view.displayError("Nombre invalide: " + input);
            }
        }
    }

    private void clearInput() { input.setLength(0); }

    private void pushUpdate() {
        String accuStr;
        if (model.getAccu() != null) {
            accuStr = model.getAccu().toString();
        } else if (input.length() > 0) {
            accuStr = input.toString();
        } else {
            accuStr = "";
        }

        // Adapter le type pour la vue (List<Double>)
        List<Double> pileAsList = new ArrayList<>(model.getPile());
        view.changeAccu(accuStr);
        view.changePile(pileAsList);
    }

    private void safeRun(Runnable r) {
        try {
            r.run();
            pushUpdate();
        } catch (ArithmeticException | IllegalStateException ex) {
            view.displayError(ex.getMessage());
            pushUpdate();
        }
    }

    // ----- implémentation des callbacks -----
    @Override
    public void onDigit(String d) {
        // autoriser [0-9] et un seul point décimal
        if (d.equals(".") && input.indexOf(".") >= 0) return;
        if (!d.matches("[0-9\\.]")) {
            view.displayError("Entrée non reconnue: " + d);
            return;
        }
        input.append(d);
        // refléter la saisie côté modèle (utile si on appuie directement sur +, -, etc.)
        setAccuFromInputIfAny();
        pushUpdate();
    }

    @Override
    public void onEnter() { // PUSH
        safeRun(() -> {
            setAccuFromInputIfAny();
            model.push();
            clearInput();
        });
    }

    @Override public void onClear()     { safeRun(() -> { model.clear(); clearInput(); }); }
    @Override public void onDrop()      { safeRun(model::drop); }
    @Override public void onSwap()      { safeRun(model::swap); }
    @Override public void onOpposite()  { safeRun(model::opposite); }

    @Override public void onAdd() {
        safeRun(() -> {
            setAccuFromInputIfAny();
            model.add();
            clearInput(); // après opération binaire, on repart d’un résultat propre
        });
    }

    @Override public void onSubtract() {
        safeRun(() -> {
            setAccuFromInputIfAny();
            model.subtract();
            clearInput();
        });
    }

    @Override public void onMultiply() {
        safeRun(() -> {
            setAccuFromInputIfAny();
            model.multiply();
            clearInput();
        });
    }

    @Override public void onDivide() {
        safeRun(() -> {
            setAccuFromInputIfAny();
            model.divide();
            clearInput();
        });
    }

    @Override public void onQuit() {
        // rien à faire dans le contrôleur MVC “libre” ; la vue décidera (console: fermer la boucle)
    }
}