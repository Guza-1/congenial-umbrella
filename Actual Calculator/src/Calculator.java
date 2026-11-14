import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class Calculator extends JFrame implements ActionListener {

    Color customwhite=new Color(212,212,210);
    Color customlightgrey=new Color(80,80,80);
    Color customblack=new Color(28,28,28);
    Color customorange=new Color(255,149,0);
    JPanel displaypanel;
    JLabel displaylabel;
    String displaytext;
    JPanel buttonpanel;
    int value_count;
    double A;
    double B;
    char operator;

    String[] Top_symbol_array={"SQRT","ACC","P%","Back"};
    char[] button_symbol_array={'+','1','2','3','-','4','5','6','/','7','8','9','x','.','0','='};
    char[][] button_symbols={{'+','1','2','3'},{'-','4','5','6'},{'/','7','8','9'},{'x','.','0','='}};
    String[] numberarray={"1","2","3","4","5","6","7","8","9","0","."};
    String[] operators={"+","-","/","x","="};
    JButton[][] buttonarray;
    JButton[][] button_array;


    public Calculator() {
        this.setTitle("TrueCalculator");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //this.setLayout(new BorderLayout());
        this.setResizable(false);
        value_count=0;
        A=0;
        B=0;
        displaytext="";

        System.out.println(button_symbols);

        buttonarray=new JButton[5][4];
        displaypanel = new JPanel();
        displaypanel.setBackground(customwhite);
        displaypanel.setPreferredSize(new Dimension(600, 800));
        displaypanel.setLayout(new BorderLayout());

        displaylabel=new JLabel("0");
        displaylabel.setBackground(customblack);
        displaylabel.setForeground(customwhite);
        displaylabel.setFont(new Font("Times New Roman", Font.BOLD, 100));
        displaylabel.setOpaque(true);


        displaypanel.add(displaylabel,BorderLayout.NORTH);

        buttonpanel = new JPanel();
        buttonpanel.setBackground(customblack);
        buttonpanel.setLayout(new GridLayout(5, 4));
        JButton[][] button1_array=getButtonarray();
        for(int y=0;y<5;y++){
            for(int x=0;x<4;x++){
                //System.out.println(b);
                button1_array[y][x].addActionListener(this);
                buttonpanel.add(button1_array[y][x]);
            }
        }


        buttonpanel.setFocusable(false);
        displaypanel.add(buttonpanel);
        this.add(displaypanel);
        this.setFocusable(false);
        this.pack();


        this.setVisible(true);

    }


    public JButton[][] getButtonarray() {
        button_array=new JButton[5][4];

        for(int j=0;j<4;j++){
            for(int i=0;i<5;i++){
                JButton temp_button;
                if (i==0){
                    temp_button=new JButton(Top_symbol_array[j]);
                    temp_button.setBackground(customorange);
                    temp_button.setForeground(customlightgrey);
                    temp_button.setBorder(BorderFactory.createLineBorder(customblack));

                }
                else{
                    temp_button=new JButton(String.valueOf(button_symbols[i-1][j]));
                    temp_button.setBorder(BorderFactory.createLineBorder(customblack));
                    if (j==0){
                        temp_button.setBackground(customlightgrey);
                        temp_button.setForeground(customwhite);
                    }
                }
                button_array[i][j]=temp_button;
                System.out.println(button_array[i][j].getText());

            }
        }

        return button_array;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String symbol=e.getActionCommand();
        if(Arrays.asList(Top_symbol_array).contains(symbol)){
            switch (symbol){
                case "ACC":
                    displaylabel.setText("0");
                    displaytext="";
                    A=0;
                    B=0;
                    value_count=0;
                    break;
                case "SQRT":
                    Double resource=Double.parseDouble(displaylabel.getText());
                    resource=Math.sqrt(resource);
                    resource=Round_to_2Dp(resource);
                    displaylabel.setText(String.valueOf(resource));
                    displaytext="";
                    break;
                case "P%":
                    displaylabel.setText("0");
                    displaytext="";
                    A=0;
                    B=0;
                    value_count=0;
                    int choice=JOptionPane.showOptionDialog(null,"Are you seriously using a" +
                            " CALCULATOR to get a percentage?🙂","You're not serious",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE,
                            null,null,null);
                    switch (choice) {
                        case 0:
                            JOptionPane.showMessageDialog(null, "Go Back to primary😁");
                            break;
                        case 1:
                            JOptionPane.showMessageDialog(null, "Lets pretend you didn't press that😁" +
                                    "Continue responsible calculating");
                            break;
                        case -1:
                            JOptionPane.showMessageDialog(null, "Trying to run away from your problems huh?💀");
                            break;
                    }
                case "Back":
                    String newword="";
                    for(int i=0;i<displaytext.length()-1;i++){
                        newword=newword+displaytext.charAt(i);
                    }
                    displaytext=newword;
                    displaylabel.setText(newword);
                   

            }

        }
        else{
            System.out.println(Arrays.asList(operators).contains(symbol));
            if(Arrays.asList(operators).contains(symbol)){
                if(symbol.equals("=")==false){
                if(value_count==0){
                operator=symbol.charAt(0);
                A=Double.parseDouble(displaylabel.getText());
                value_count=value_count+1;
                displaytext="";
                displaylabel.setText("0");
                }}
                else{

                    if(value_count==0 ){
                        displaylabel.setText("Ans: "+displaylabel.getText());
                    }
                    else{
                        double result;
                        B=Double.parseDouble(displaylabel.getText());
                        switch(operator){
                            case '+': result=A+B;
                            break;
                            case '-': result=A-B;
                            break;
                            case 'x': result=A*B;
                            break;
                            case '/': result=A/B;
                            break;
                            default:
                                throw new IllegalStateException("Unexpected value: " + operator);
                        }
                        if(result==(int)result){
                            displaytext=String.valueOf((int)result) ;
                            displaylabel.setText("Ans: "+displaytext);
                        }
                        else {
                            result=Round_to_2Dp(result);
                            displaytext = String.valueOf(result);
                            displaylabel.setText("Ans: " + displaytext);
                        }
                        displaytext="";
                        A=0;
                        B=0;
                        value_count=0;
                    }
                }
            }
            else{
                displaytext=displaytext+symbol;
                displaylabel.setText(displaytext);
            }
        }
    }
    public double Round_to_2Dp(double value){
        value=(double) Math.round(value*100)/100;
        System.out.println(value);
        return value;
    }
    }

