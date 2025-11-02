import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class KidsQuizGame implements ActionListener {
    JFrame frame;
    JLabel questionLabel, scoreLabel, backgroundLabel;
    JRadioButton opt1, opt2, opt3, opt4;
    JButton submitBtn, nextBtn;
    ButtonGroup optionsGroup;
    int currentQuestion = 0, score = 0;

    //  Kid-friendly Questions
    String[] questions = {
        "1. What color is the sky on a sunny day?",
        "2. Which animal says 'Meow'?",
        "3. Which planet is known as the Red Planet?",
        "4. How many legs does a spider have?",
        "5. Which animal is known as the King of the Jungle?"
    };

    String[][] options = {
        {"Blue", "Green", "Yellow", "Red"},
        {"Dog", "Cat", "Cow", "Bird"},
        {"Earth", "Mars", "Venus", "Jupiter"},
        {"6", "8", "10", "12"},
        {"Tiger", "Elephant", "Lion", "Dog"}
    };

    String[] answers = {"Blue", "Cat", "Mars", "8", "Lion"};

    KidsQuizGame() {
        frame = new JFrame("Kids Fun Quiz");
        frame.setSize(750, 600);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //  Cartoon Background
        ImageIcon bgImage = new ImageIcon("cartoon_bg.jpg"); // use any cartoon image
        Image img = bgImage.getImage().getScaledInstance(750, 600, Image.SCALE_SMOOTH);
        bgImage = new ImageIcon(img);
        backgroundLabel = new JLabel(bgImage);
        backgroundLabel.setBounds(0, 0, 750, 600);
        backgroundLabel.setLayout(null);
        frame.setContentPane(backgroundLabel);

        // Title
        JLabel title = new JLabel(" Welcome to Kids Quiz ", JLabel.CENTER);
        title.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        title.setForeground(new Color(25, 25, 112));
        title.setBounds(80, 10, 600, 50);
        backgroundLabel.add(title);

        // Question Label
        questionLabel = new JLabel(questions[0]);
        questionLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 22));
        questionLabel.setForeground(new Color(10, 30, 60));
        questionLabel.setBounds(100, 120, 600, 40);
        backgroundLabel.add(questionLabel);

        // Options
        opt1 = new JRadioButton(options[0][0]);
        opt2 = new JRadioButton(options[0][1]);
        opt3 = new JRadioButton(options[0][2]);
        opt4 = new JRadioButton(options[0][3]);
        JRadioButton[] opts = {opt1, opt2, opt3, opt4};

        int y = 180;
        for (JRadioButton rb : opts) {
            rb.setBounds(150, y, 400, 30);
            rb.setFont(new Font("Comic Sans MS", Font.PLAIN, 18));
            rb.setOpaque(false);
            rb.setForeground(Color.BLACK);
            backgroundLabel.add(rb);
            y += 40;
        }

        optionsGroup = new ButtonGroup();
        optionsGroup.add(opt1);
        optionsGroup.add(opt2);
        optionsGroup.add(opt3);
        optionsGroup.add(opt4);

        // Buttons
        submitBtn = new JButton(" Submit");
        nextBtn = new JButton(" Next");
        submitBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));
        nextBtn.setFont(new Font("Comic Sans MS", Font.BOLD, 16));

        submitBtn.setBackground(new Color(144, 238, 144));
        nextBtn.setBackground(new Color(173, 216, 230));

        submitBtn.setBounds(180, 400, 150, 40);
        nextBtn.setBounds(370, 400, 150, 40);
        backgroundLabel.add(submitBtn);
        backgroundLabel.add(nextBtn);

        submitBtn.addActionListener(this);
        nextBtn.addActionListener(this);

        // Score label
        scoreLabel = new JLabel("Score: 0");
        scoreLabel.setFont(new Font("Comic Sans MS", Font.BOLD, 20));
        scoreLabel.setForeground(Color.DARK_GRAY);
        scoreLabel.setBounds(300, 470, 200, 40);
        backgroundLabel.add(scoreLabel);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == submitBtn) {
            String selected = null;
            if (opt1.isSelected()) selected = opt1.getText();
            if (opt2.isSelected()) selected = opt2.getText();
            if (opt3.isSelected()) selected = opt3.getText();
            if (opt4.isSelected()) selected = opt4.getText();

            if (selected == null) {
                JOptionPane.showMessageDialog(frame, "Please select an answer!");
                return;
            }

            if (selected.equals(answers[currentQuestion])) {
                score++;
                JOptionPane.showMessageDialog(frame, " Correct! Great job!");
            } else {
                JOptionPane.showMessageDialog(frame, " Oops! The correct answer is " + answers[currentQuestion]);
            }

            scoreLabel.setText("Score: " + score);
        }

        if (e.getSource() == nextBtn) {
            currentQuestion++;
            if (currentQuestion < questions.length) {
                questionLabel.setText(questions[currentQuestion]);
                opt1.setText(options[currentQuestion][0]);
                opt2.setText(options[currentQuestion][1]);
                opt3.setText(options[currentQuestion][2]);
                opt4.setText(options[currentQuestion][3]);
                optionsGroup.clearSelection();
            } else {
                JOptionPane.showMessageDialog(frame,
                        "Quiz Over!\nYour final score: " + score + "/" + questions.length);

                int playAgain = JOptionPane.showConfirmDialog(frame,
                        "Do you want to play again?", "Kids Quiz", JOptionPane.YES_NO_OPTION);

                if (playAgain == JOptionPane.YES_OPTION) {
                    currentQuestion = 0;
                    score = 0;
                    scoreLabel.setText("Score: 0");
                    questionLabel.setText(questions[currentQuestion]);
                    opt1.setText(options[currentQuestion][0]);
                    opt2.setText(options[currentQuestion][1]);
                    opt3.setText(options[currentQuestion][2]);
                    opt4.setText(options[currentQuestion][3]);
                    optionsGroup.clearSelection();
                } else {
                    System.exit(0);
                }
            }
        }
    }

    public static void main(String[] args) {
        new KidsQuizGame();
    }
}
