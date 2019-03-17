import java.util.Scanner;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;

public class Calculator extends JFrame implements ActionListener {
    double input, result = 0.0;
    List<Double> inps = new ArrayList<>();
    List<String> ops = new ArrayList<>();
    boolean dec = false;

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

        switch(choice){
            case "perc":    input = (input * 0.01) * inps.get(inps.size() - 1);     break;
            case "sqRt":    input = Math.sqrt(input);       break;
            case "x^2":     input *= input;     break;
            case "1/x":     input = 1 / input;  break;
            case "CE":      input = 0;      break;
            case "C":       
                input = 0;
                inps.clear();
                ops.clear();
                break;
            case "del":
                if(dec == false){
                    input = (input - (input % 10)) / 10;   break;
                }
                else{
                    String inpStr = String.valueOf(input);
                    String[] decPlac = inpStr.toString().split("\\.");
                    int delNum = String.valueOf(decPlac[1]);
                    for(int z = 0; z < decPlac[1].length(); z++)
                        *= 0.1;
                    break;
                }
            case "/":   case "*":   case "-":   case "+":
                inps.add(input);
                input = 0;
                ops.add(choice);
                dec = false;
                break;
            case "7":   case "8":   case "9":   case "4":   case "5":
            case "6":   case "1":   case "2":   case "3":   case "0":
                int intCh = Integer.parseInt(choice);
                if(dec == false){
                    input = (input * 10) + choice;
                    break;
                }
                else{
                    String[] decPlac = input.toString().split("\\.");
                    for(int z = 0; z < decPlac[1].length(); z++)
                        intCh *= 0.1;
                    input += intCh;
                    break;
                }
            case "min": input *= -1;    break;
            case ".":   dec = true;     break;
            case "=":
                inps.add(input);
                result = calculate(inps, ops);
                System.out.println(result);
                dec = false;
                inps.clear();
                ops.clear();
                inps.add(result);
                break;
        }
    }

    public double calculate(List<Double> inpp, List<String> opss){
        double res =  inpp.get(0);
        for(int i = 1; i < inpp.length(); i++){
            for(int j = 0; j < opss.length(); j++){
                switch(opss.get(j)){
                    case "/":   res /= inpp.get(i);     break;
                    case "*":   res *= inpp.get(i);     break;
                    case "-":   res -= inpp.get(i);     break;
                    case "+":   res += inpp.get(i);     break;
                }
            }
        }
        return res;
    }
}