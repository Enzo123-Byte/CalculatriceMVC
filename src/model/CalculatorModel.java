package model;

import java.util.Stack;

public class CalculatorModel implements CalculatorModelInterface {
    private Stack<Double> pile; 
    private Double accu;

    public CalculatorModel() {
        this.accu = null;
        this.pile = new Stack<>();
    }

    public Stack<Double> getPile() {
        return pile;
    }

    public Double getAccu() {
        return accu;
    }

    public void setAccu(Double accu) {
        this.accu = accu;
    }

   public void add(){
    if (accu!=null && !pile.isEmpty()) {
        Double b =accu;
        Double a =pile.pop();
        setAccu(a+b);
    }
    else if(accu==null && pile.size()>=2){
        Double b=pile.pop();
        Double a=pile.pop();
        setAccu(a+b);
    }
    else {
        throw new IllegalStateException("Pas assez d'opérandes pour additionner");
    }
    }
   
  public void subtract(){
    if (accu!=null && !pile.isEmpty()) {
        Double b =accu;
        Double a =pile.pop();
        setAccu(a-b);
    }
    else if(accu==null && pile.size()>=2){
        Double b=pile.pop();
        Double a=pile.pop();
        setAccu(a-b);
    }
    else {
        throw new IllegalStateException("Pas assez d'opérandes pour soustraire");
    }
    }  

    public void multiply(){
    if (accu!=null && !pile.isEmpty()) {
        Double b =accu;
        Double a =pile.pop();
        setAccu(a*b);
    }
    else if(accu==null && pile.size()>=2){
        Double b=pile.pop();
        Double a=pile.pop();
        setAccu(a*b);
    }
    else {
        throw new IllegalStateException("Pas assez d'opérandes pour multiplier");
    }
    }  
    
   public void divide(){
    if (accu!=null && !pile.isEmpty()) {
        Double b =accu;
        Double a =pile.pop();
        if (b == 0.0) throw new ArithmeticException("Division par zéro");
        setAccu(a / b);
        
    }
    else if(accu==null && pile.size()>=2){
        Double b=pile.pop();
        Double a=pile.pop();
        if (b == 0.0) throw new ArithmeticException("Division par zéro");
        setAccu(a / b);
    }
    else {
        throw new IllegalStateException("Pas assez d'opérandes pour diviser");
    }
    }  

    public void opposite(){
    if (accu!=null) {
        accu=-accu;
    }
    else if (!pile.isEmpty()) {
    setAccu(-pile.pop());

    }
    else {
        throw new IllegalStateException("Pas assez d'opérandes pour prendre l'opposé");
    }
    }  

    public void push(){
        if(accu==null){
            throw new IllegalStateException("Accu vide");
        }
        pile.push(accu);
        accu=null;
    }
    
    public void pop(){
        if (pile.isEmpty()){
            throw new IllegalStateException("La pile est vide");
        }
        accu=pile.pop();
    }

    public void clear() 
        { accu = null; 
        }
    
    public void drop(){
        if(pile.isEmpty()){
            throw new IllegalStateException("La pile est vide");
        }
        pile.pop();
    }

    public void swap(){
        if (pile.size() < 2) {
            throw new IllegalStateException("Besoin d'au moins 2 éléments pour swap");
        }
        Double b=pile.pop();
        Double a=pile.pop();
        pile.push(b);
        pile.push(a);
    }


    public void dropAll() 
        { pile.clear(); }
}


