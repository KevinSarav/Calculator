import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Calculator extends JFrame implements ActionListener {
    double input, result = 0.0;
    List<String> ops, inps = new ArrayList<>();
    boolean percen = false;
    String strInp = "0";

    public static void main(String[] args) {
        Calculator gui = new  Calculator();
        gui.setVisible(true);
    }

    public Calculator(){
        setSize(500, 400);
        getContentPane().setBackground(Color.black);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(6, 4));

        JButton percent = new JButton("perc");  percent.addActionListener(this);    add(percent);
        JButton sqRt = new JButton("sqRt"); sqRt.addActionListener(this);   add(sqRt);
        JButton sqr = new JButton("x^2");   sqr.addActionListener(this);    add(sqr);
        JButton inv = new JButton("1/x");   inv.addActionListener(this);    add(inv);
        JButton clEntry = new JButton("CE");    clEntry.addActionListener(this);    add(clEntry);
        JButton cl = new JButton("C");      cl.addActionListener(this);     add(cl);
        JButton del = new JButton("del");   del.addActionListener(this);    add(del);
        JButton div = new JButton("/");     div.addActionListener(this);    add(div);
        JButton sev = new JButton("7");     sev.addActionListener(this);    add(sev);
        JButton eig = new JButton("8");     eig.addActionListener(this);    add(eig);
        JButton nin  = new JButton("9");    nin.addActionListener(this);    add(nin);
        JButton mult = new JButton("X");    mult.addActionListener(this);   add(mult);
        JButton fou = new JButton("4");     fou.addActionListener(this);    add(fou);
        JButton fiv = new JButton("5");     fiv.addActionListener(this);    add(fiv);
        JButton six = new JButton("6");     six.addActionListener(this);    add(six);
        JButton sub = new JButton("-");     sub.addActionListener(this);    add(sub);
        JButton one = new JButton("1");     one.addActionListener(this);    add(one);
        JButton two = new JButton("2");     two.addActionListener(this);    add(two);
        JButton thr = new JButton("3");     thr.addActionListener(this);    add(thr);
        JButton plu = new JButton("+");     plu.addActionListener(this);    add(plu);
        JButton min = new JButton("min");   min.addActionListener(this);    add(min);
        JButton zer = new JButton("0");     zer.addActionListener(this);    add(zer);
        JButton dec = new JButton(".");     dec.addActionListener(this);    add(dec);
        JButton equ = new JButton("=");     equ.addActionListener(this);    add(equ);
    }

    public void actionPerformed(ActionEvent e){
        String choice = e.getActionCommand();
        input = Double.parseDouble(strInp);

        switch(choice){
            case "perc":
                String lastOp = ops.get(ops.size() - 1);    
                if(ops.size() != 0){
                    if(lastOp.equals("+") || lastOp.equals("-"))
                        input *= 0.01 * calculate(inps, ops);
                    else
                        input *= 0.01;
                    strInp = String.valueOf(input);
                    percen = true;
                    break;
                }
                strInp = "0";
            case "sqRt":    strInp = String.valueOf(Math.sqrt(input));       break;
            case "x^2":     strInp = String.valueOf(input * input);     break;
            case "1/x":     strInp = String.valueOf(1 / input);  break;
            case "CE":      strInp = "0";   percen = false;    break;
            case "C":       
                strInp = "0";
                result = 0.0;
                inps.clear();
                ops.clear();
                percen = false;
                break;
            case "del":
                if(strInp.length() == 1){
                    strInp = "0";
                    break;
                }
                strInp = strInp.substring(0, strInp.length());
                break;
            case "/":   case "*":   case "-":   case "+":
                if(Character.toString(strInp.charAt(strInp.length() - 1)).equals("."))
                    strInp = strInp.substring(0, strInp.length());
                inps.add(strInp);
                input = 0.0;
                ops.add(choice);
                percen = false;
                break;
            case "7":   case "8":   case "9":   case "4":   case "5":
            case "6":   case "1":   case "2":   case "3":   case "0":
                int intCh = Integer.parseInt(choice);
                if(percen == true){
                    strInp = choice;
                    percen = false;
                    break;
                }
                if(strInp.equals("0")){
                    strInp = choice;
                    break;
                }
                strInp += choice;
                break;
            case "min": strInp = String.valueOf(input * -1);    break;
            case ".":   strInp += ".";     break;
            case "=":
                inps.add(strInp);
                result = calculate(inps, ops);
                System.out.println(result);
                inps.clear();
                ops.clear();
                inps.add(String.valueOf(result));
                break;
        }
    }

    public double calculate(List<String> inpp, List<String> opss){
        double res =  Double.parseDouble(inpp.get(0));

        for(int i = 0; i < inpp.size() - 1; i++){
            double dbl = Double.parseDouble(inpp.get(i + 1));
            switch(opss.get(i)){
                case "/":   res /= dbl;     break;
                case "*":   res *= dbl;     break;
                case "-":   res -= dbl;     break;
                case "+":   res += dbl;     break;
            }
        }
        return res;
    }
}