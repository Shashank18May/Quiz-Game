import org.jetbrains.annotations.NotNull;

import java.awt.event.*;
import java.awt.*;
import javax.swing.*;
import javax.swing.Timer;
import java.util.*;
import java.util.List;


public class Quiz implements ActionListener{

    String[] Questions = {
            //1
            "What is the capital of France?",
            //2
            "Who wrote the Harry Potter book series?",
            //3
            "Which is the smallest continent?",
            //4
            "What is the currency of Japan?",
            //5
            "What is the chemical symbol for gold?",

            //6
            "Which is the biggest planet?",
            //7
            "Which is the highest number in a deck?",
            //8
            "Which is the world's largest desert?",

            //9
            "What is the national animal of Canada?",
            //10
            "What is the capital of India?",
            //11
            "What is the tallest mountain in the world?",

            //12
            "What is the largest mammal on earth?",
            //13
            "What is the currency of USA?",
            //14
            "What is the smallest country in the world by land?",

            //15
            "Which planet is closest to the sun?",
            //16
            "What is the largest organ in the human body?",

            //17
            "In what year did World War II end?",
            //18
            "What is the capital of Brazil?",
            //19
            "What is the primary language spoken in Argentina?",

            //20
            "What is the national animal of Australia?",
            //21
            "What is the capital of Spain?",
            //22
            "Which is the longest river in Africa?",
            //23
            "What is the largest ocean in the world?",
            //24
            "Which country is known as the Land of Rising Sun?",
            //25
            "What is the tallest animal on earth?",
            //26
            "What is the national flower of Japan?"
    };

    String[][] Options = {
            //1
            {"Paris", "Berlin", "London", "Madrid"},
            //2
            {"J.K. Rowling", "Stephen King", "George R.R. Martin", "Suzanne Collins"},
            //3
            {"Australia", "South America", "Europe", "Antarctica"},
            //4
            {"Pound","Sterling","Yen","Dollar"},
            //5
            {"Au", "Ag", "Cu", "Fe"},
            //6
            {"Jupiter", "Saturn", "Uranus", "Neptune"},
            //7
            {"10", "11", "12", "13"},
            //8
            {"Sahara", "Gobi", "Kalahari", "Arabian"},
            //9
            {"Grizzly Bear", "Moose", "Beaver", "Canadian Goose"},
            //10
            {"New Delhi", "Mumbai", "Kolkata", "Chennai"},
            //11
            {"K2", "Mount Everest", "Kanchenjunga", "Lhotse"},

            //12
            {"Blue whale", "Elephant", "Giraffe", "Hippopotamus"},
            //13
            {"Yen", "Euro", "Pound sterling", "Dollar"},
            //14
            {"Vatican City", "Monaco", "Nauru", "Tuvalu"},

            //15
            {"Mercury", "Venus", "Earth", "Mars"},
            //16
            {"Skin", "Heart", "Liver", "Lungs"},

            //17
            {"1945", "1944", "1943", "1942"},
            //18
            {"Brasília", "São Paulo", "Rio de Janeiro", "Belo Horizonte"},
            //19
            {"Spanish", "Portuguese", "English", "French"},

            //20
            {"Kangaroo", "Koala", "Emu", "Tasmanian devil"},
            //21
            {"Madrid", "Barcelona", "Valencia", "Seville"},
            //22
            {"Nile", "Congo", "Zambezi", "Niger"},
            //23
            {"Pacific", "Atlantic", "Indian", "Arctic"},
            //24
            {"Japan", "China", "South Korea", "North Korea"},
            //25
            {"Elephant","Giraffe", "Hippopotamus", "Gorilla"},
            //26
            {"Cherry blossom", "Lotus", "Rose", "Lily"}
                          };

    char[] answers = {
            'A',//1
            'A',//2
            'A',//3
            'C',//4
            'A',//5
            'A',//6
            'A',//7
            'A',//8
            'C',//9
            'A',//10
            'B',//11
            'A',//12
            'D',//13
            'A',//14
            'A',//15
            'C',//16
            'A',//17
            'A',//18
            'A',//19
            'A',//20
            'A',//21
            'A',//22
            'A',//23
            'A',//24
            'B',//25
            'A'//26
    };
    char answer;
    int index;
    int right_guesses = 0;
    int total_questions = 10;
    int result;
    int time = 10000;
    int k;
    int seconds;
    int milliseconds;
    List<Integer> list = new ArrayList<>();

    String millisecond_string = String.format("%02d", milliseconds);
    String seconds_string = String.format("%02d", seconds);

    JFrame frame = new JFrame();
    JTextField textField = new JTextField();
    JTextArea textArea = new JTextArea();

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

    Timer timer=new Timer(10,new ActionListener(){
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

    public void buttonManager(@NotNull JButton button){
        button.setFont(new Font("MV Boli",Font.BOLD,35));
        button.setFocusable(false);
        button.setForeground(new Color(0,0,0));
        button.setBackground(new Color(0,255,0));
        button.addActionListener(this);
    }

    public void labelManager(@NotNull JLabel label){
        label.setBackground(new Color(255,255,0));
        label.setForeground(new Color(102,0,255));
        label.setFont(new Font("MV Boli",Font.PLAIN,35));
        label.setOpaque(true);


    }

    public Quiz() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(650,650);
        frame.getContentPane().setBackground(new Color(255,102,153));
        frame.setLayout(null);
        frame.setResizable(false);

        textField.setBounds(0,0,650,50);
        textField.setBackground(new Color(102,255,255));
        textField.setForeground(new Color(0,0,255));
        textField.setFont(new Font("Ink Free",Font.PLAIN,40));
        textField.setBorder(BorderFactory.createBevelBorder(1));
        textField.setHorizontalAlignment(JTextField.CENTER);
        textField.setEditable(false);


        textArea.setBounds(0,55,650,50);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setBackground(new Color(255,255,0));
        textArea.setForeground(new Color(102,0,255));
        textArea.setFont(new Font("MV Boli",Font.PLAIN,25));
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
        seconds_left.setBackground(new Color(102, 255, 51));
        seconds_left.setForeground(new Color(255,0,0));
        seconds_left.setFont(new Font("Ink Free",Font.BOLD,45));
        seconds_left.setBorder(BorderFactory.createBevelBorder(1,Color.black,Color.PINK));
        seconds_left.setOpaque(true);
        seconds_left.setHorizontalAlignment(JTextField.LEFT);
        seconds_left.setText(String.valueOf(seconds));

        number_right.setBounds(225,225,200,100);
        number_right.setForeground(new Color(0,0,0));
        number_right.setBackground(new Color(25,255,0));
        number_right.setFont(new Font("Ink Free",Font.BOLD,50));
        number_right.setHorizontalAlignment(JTextField.CENTER);
        number_right.setEditable(true);

        score.setBounds(225,325,200,100);
        score.setForeground(new Color(0,0,0));
        score.setBackground(new Color(25,255,0));
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

    public void nextQuestion(){
        if(index>= total_questions){
            results();
        }
        else {
            textField.setText("Question Number "+(index+1));
            Random r = new Random();
            String options = Questions[r.nextInt(Questions.length)];
            textArea.setText(options);
            k = Arrays.asList(Questions).indexOf(options);
            if(list.contains(k)){
                options = Questions[r.nextInt(Questions.length)];
                textArea.setText(options);
                k=Arrays.asList(Questions).indexOf(options);
                list.add(k);
            }else{
                list.add(k);
            }
            answer_labelA.setText("         "+Options[k][0]);
            answer_labelB.setText("         "+Options[k][1]);
            answer_labelC.setText("         "+Options[k][2]);
            answer_labelD.setText("         "+Options[k][3]);
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
            answer='A';
            if(answer == answers[k]){
                right_guesses++;
            }

        }
        if(e.getSource()==buttonB){
            answer='B';
            if(answer == answers[k]){
                right_guesses++;
            }

        }
        if(e.getSource()==buttonC){
            answer='C';
            if(answer == answers[k]){
                right_guesses++;
            }

        }
        if(e.getSource()==buttonD){
            answer='D';
            if(answer == answers[k]){
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

        if(answers[k]!='A'){
            answer_labelA.setForeground(new Color(255,0,0));

        }else {answer_labelA.setForeground(new Color(0,255,0));}
        if(answers[k]!='B'){
            answer_labelB.setForeground(new Color(255,0,0));

        }else {answer_labelB.setForeground(new Color(0,255,0));}

        if(answers[k]!='C'){
            answer_labelC.setForeground(new Color(255,0,0));

        }else {answer_labelC.setForeground(new Color(0,255,0));}
        if(answers[k]!='D'){
            answer_labelD.setForeground(new Color(255,0,0));

        }else {answer_labelD.setForeground(new Color(0,255,0));}
        Timer pause=new Timer(1500,new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                answer_labelA.setForeground(new Color(102,0,255));
                answer_labelB.setForeground(new Color(102,0,255));
                answer_labelC.setForeground(new Color(102,0,255));
                answer_labelD.setForeground(new Color(102,0,255));

                answer=' ';
                seconds=10;
                seconds_left.setText(String.valueOf(seconds));

                buttonA.setEnabled(true);
                buttonB.setEnabled(true);
                buttonC.setEnabled(true);
                buttonD.setEnabled(true);

                index++;
                nextQuestion();

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
        textArea.setText("                  Your score is :-");
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
