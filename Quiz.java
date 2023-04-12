import javax.swing.*;
import javax.swing.Timer;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.*;
import java.util.List;


public class Quiz implements ActionListener{

    String answer;
    String Questions;
    int index;
    int right_guesses = 0;
    int total_questions = 10;
    int result;
    int time = 10000;
    int k;
    int seconds;
    int milliseconds;
    List<Integer> list = new ArrayList<>();
    List<String> ar = new ArrayList<>();
    String[] row = {};

    Scanner sc = new Scanner(new File("path to your csv"));




    String millisecond_string = String.format("%02d", milliseconds);
    String seconds_string = String.format("%02d", seconds);

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextField textArea = new JTextField();

    JButton buttonA = new JButton();
    JButton buttonB = new JButton();
    JButton buttonC = new JButton();
    JButton buttonD = new JButton();

    JLabel answer_labelA =new JLabel();
    JLabel answer_labelB =new JLabel();
    JLabel answer_labelC =new JLabel();
    JLabel answer_labelD =new JLabel();

    JLabel seconds_left = new JLabel();

    JTextField number_right = new JTextField();
    JTextField score = new JTextField();

    Timer timer=new Timer(10    ,new ActionListener(){
        public void actionPerformed(ActionEvent e) {
            time=time-10;
            seconds = (time/1000)%60;
            milliseconds = (time/10)%100;

            millisecond_string = String.format("%01d", milliseconds);
            seconds_string = String.format("%02d", seconds);
            seconds_left.setText(seconds_string+"."+millisecond_string);
            if(time<=0){
                time=100000;
                displayAnswer();
            }

        }
    }
    );

    public void buttonManager(JButton button){
        button.setFont(new Font("Sans Serif",Font.BOLD,35));
        button.setFocusable(false);
        button.setForeground(new Color(25,133,161));
        button.setBackground(new Color(70, 73, 76));
        button.addActionListener(this);

    }

    public void labelManager(JLabel label){
        label.setBackground(new Color(76, 92, 104));
        label.setForeground(new Color(255,255,255));
        label.setFont(new Font("Georgia",Font.PLAIN,35));
        label.setOpaque(true);


    }


    public Quiz () throws Exception {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color(70, 73, 76));
        frame.setLayout(null);
        frame.setResizable(false);

        textField.setBounds(0,0,650,50);
        textField.setForeground(new Color(255,255,255));
        textField.setBackground(new Color(70, 73, 76));
        textField.setFont(new Font("Ink Free",Font.PLAIN,40));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);


        textArea.setBounds(0,55,650,50);
        textArea.setHorizontalAlignment(JTextField.CENTER);
        textArea.setForeground(new Color(25,155,161));
        textArea.setBackground(new Color(70, 73, 76));
        textArea.setFont(new Font("Serif",Font.BOLD,25));
        textArea.setBorder(BorderFactory.createBevelBorder(2));
        textArea.setEditable(false);

        buttonA.setBounds(0,110,100,100);
        buttonA.setText("A");
        buttonManager(buttonA);

        buttonB.setBounds(0,210,100,100);
        buttonB.setText("B");
        buttonManager(buttonB);

        buttonC.setBounds(0,310,100,100);
        buttonC.setText("C");
        buttonManager(buttonC);

        buttonD.setBounds(0,410,100,100);
        buttonD.setText("D");
        buttonManager(buttonD);

        answer_labelA.setBounds(100,110,525,100);
        labelManager(answer_labelA);
        answer_labelB.setBounds(100,210,525,100);
        labelManager(answer_labelB);
        answer_labelC.setBounds(100,310,525,100);
        labelManager(answer_labelC);
        answer_labelD.setBounds(100,410,525,100);
        labelManager(answer_labelD);

        seconds_left.setBounds(535,510,210,100);
        seconds_left.setBackground(new Color(70, 73, 76));
        seconds_left.setForeground(new Color(255,255,255));
        seconds_left.setFont(new Font("Ink Free",Font.BOLD,45));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1,Color.black,Color.darkGray));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.LEFT);
        seconds_left.setText(String.valueOf(seconds));

        number_right.setBounds(225,225,200,100);
        number_right.setForeground(new Color(70, 73, 76));
        number_right.setBackground(new Color(197, 195, 198));
        number_right.setFont(new Font("Ink Free",Font.BOLD,50));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(true);

        score.setBounds(225,325,200,100);
        score.setForeground(new Color(70, 73, 76));
        score.setBackground(new Color(197, 195, 198));
        score.setFont(new Font("Ink Free",Font.BOLD,50));
        score.setHorizontalAlignment(JTextField.CENTER);
        score.setEditable(false);


        frame.add(seconds_left);
        frame.add(answer_labelA);
        frame.add(answer_labelB);
        frame.add(answer_labelC);
        frame.add(answer_labelD);
        frame.add(buttonA);
        frame.add(buttonB);
        frame.add(buttonC);
        frame.add(buttonD);
        frame.add(textArea);
        frame.add(textField);
        frame.setVisible(true);

        nextQuestion();
    }

    public void nextQuestion() {
        if(index>= total_questions){
            results();
        }
        else {
            textField.setText("Question Number "+(index+1));
            sc.useDelimiter("\n");
            while(sc.hasNextLine()){

                ar.add(sc.nextLine());
            }
            Random r =new Random();
            Questions = ar.get(r.nextInt(ar.size()));
            row = Questions.split(",");
            textArea.setText(row[0]);
            k = ar.indexOf(Questions);
            if(list.contains(k)){
                Questions = ar.get(r.nextInt(ar.size()));
                row = Questions.split(",");
                textArea.setText(row[0]);
                k=ar.indexOf(Questions);
                list.add(k);


            }else{
                list.add(k);
            }
            answer_labelA.setText("        "+row[1]);
            answer_labelB.setText("        "+row[2]);
            answer_labelC.setText("        "+row[3]);
            answer_labelD.setText("        "+row[4]);
            time=10000;
            timer.start();

        }

    }


    @Override
    public void actionPerformed(ActionEvent e) {

        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(e.getSource()==buttonA){
            answer="A";
            if(answer.equals(row[5])){
                right_guesses++;
            }

        }
        if(e.getSource()==buttonB){
            answer="B";
            if(answer.equals(row[5])){
                right_guesses++;
            }

        }
        if(e.getSource()==buttonC){
            answer="C";
            if(answer.equals(row[5])){
                right_guesses++;
            }

        }
        if(e.getSource()==buttonD){
            answer="D";
            if(answer.equals(row[5])){
                right_guesses++;
            }

        }
        displayAnswer();
    }
    public void displayAnswer(){

        timer.stop();
        buttonA.setEnabled(false);
        buttonB.setEnabled(false);
        buttonC.setEnabled(false);
        buttonD.setEnabled(false);

        if(!Objects.equals(row[5], "A")){
            answer_labelA.setForeground(new Color(255,0,0));

        }else {answer_labelA.setForeground(new Color(0,255,0));}
        if(!Objects.equals(row[5], "B")){
            answer_labelB.setForeground(new Color(255,0,0));

        }else {answer_labelB.setForeground(new Color(0,255,0));}

        if(!Objects.equals(row[5], "C")){
            answer_labelC.setForeground(new Color(255,0,0));

        }else {answer_labelC.setForeground(new Color(0,255,0));}
        if(!Objects.equals(row[5], "D")){
            answer_labelD.setForeground(new Color(255,0,0));

        }else {answer_labelD.setForeground(new Color(0,255,0));}
        Timer pause=new Timer(1500,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(255,255,255));
                answer_labelB.setForeground(new Color(255,255,255));
                answer_labelC.setForeground(new Color(255,255,255));
                answer_labelD.setForeground(new Color(255,255,255));

                answer=" ";
                seconds=10;
                seconds_left.setText(String.valueOf(seconds));

                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);

                index++;
                try {
                    nextQuestion();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }

            }
        }
        );
        pause.setRepeats(false);
        pause.start();
    }
    public void results(){
        frame.remove(buttonA);
        frame.remove(buttonB);
        frame.remove(buttonC);
        frame.remove(buttonD);


        buttonA.setEnabled(false);
        buttonA.setText("");
        buttonB.setEnabled(false);
        buttonB.setText("");
        buttonC.setEnabled(false);
        buttonC.setText("");
        buttonD.setEnabled(false);
        buttonD.setText("");


        result = (int)((right_guesses/(double) total_questions)*100);

        textField.setText("RESULTS!");
        textArea.setBorder(BorderFactory.createBevelBorder(2));
        textArea.setText("Your score is :-");
        answer_labelA.setText("");
        answer_labelA.setOpaque(false);
        answer_labelB.setText("");
        answer_labelB.setOpaque(false);
        answer_labelC.setText("");
        answer_labelC.setOpaque(false);
        answer_labelD.setText("");
        answer_labelD.setOpaque(false);


        number_right.setText(right_guesses+"/"+ total_questions );
        score.setText((result)+"%");

        frame.add(score);
        frame.add(number_right);

    }
}
