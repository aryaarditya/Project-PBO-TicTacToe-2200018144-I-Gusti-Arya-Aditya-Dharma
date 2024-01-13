import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ItemListener, ActionListener {
    int i, j, ii, jj, x, y, yesnull;
    int a[][] = {{10, 1, 2, 3, 11}, {10, 1, 4, 7, 11}, {10, 1, 5, 9, 11}, {10, 2, 5, 8, 11},
            {10, 3, 5, 7, 11}, {10, 3, 6, 9, 11}, {10, 4, 5, 6, 11}, {10, 7, 8, 9, 11}};
    int a1[][] = {{10, 1, 2, 3, 11}, {10, 1, 4, 7, 11}, {10, 1, 5, 9, 11}, {10, 2, 5, 8, 11},
            {10, 3, 5, 7, 11}, {10, 3, 6, 9, 11}, {10, 4, 5, 6, 11}, {10, 7, 8, 9, 11}};

    boolean state, type, set;

    String player1 = "X";
    String player2 = "O";

    Checkbox c1, c2;
    JLabel l1, l2;
    JButton b[] = new JButton[9];
    JButton reset;
    JLabel playerScoreLabel;
    JLabel computerScoreLabel;
    int playerScore = 0;
    int computerScore = 0;

    public void showButton() {
        x = 10;
        y = 10;
        j = 0;
        state = true;

        for (i = 0; i <= 8; i++, x += 100, j++) {
            b[i] = new JButton();
            if (j == 3) {
                j = 0;
                y += 100;
                x = 10;
            }
            b[i].setBounds(x, y, 100, 100);
            add(b[i]);
            b[i].addActionListener(this);
            b[i].setText(null);
            b[i].setFont(new Font("Arial", Font.BOLD, 30));
            b[i].setBackground(new Color(255, 223, 186)); // Light Orange background
            b[i].setForeground(new Color(34, 49, 63)); // Dark Blue text
            b[i].setFocusPainted(false);
            b[i].setBorder(BorderFactory.createLineBorder(new Color(52, 73, 94), 2)); // Dark Blue border
            state = !state;
        }

        reset = new JButton("RESET");
        reset.setBounds(100, 350, 100, 50);
        add(reset);
        reset.addActionListener(this);
        reset.setFont(new Font("Arial", Font.BOLD, 16));
        reset.setBackground(new Color(231, 76, 60)); // Red background
        reset.setForeground(Color.WHITE);
        reset.setFocusPainted(false);

        playerScoreLabel = new JLabel("Player: " + playerScore);
        playerScoreLabel.setBounds(350, 60, 200, 30);
        playerScoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        playerScoreLabel.setForeground(new Color(26, 188, 156)); // Turquoise text
        add(playerScoreLabel);

        computerScoreLabel = new JLabel("Computer: " + computerScore);
        computerScoreLabel.setBounds(350, 90, 200, 30);
        computerScoreLabel.setFont(new Font("Arial", Font.BOLD, 16));
        computerScoreLabel.setForeground(new Color(155, 89, 182)); // Purple text
        add(computerScoreLabel);
    }

    public void check(int num1) {
        for (ii = 0; ii <= 7; ii++) {
            for (jj = 1; jj <= 3; jj++) {
                if (a[ii][jj] == num1) {
                    a[ii][4] = 11;
                }
            }
        }
    }

    public void complogic(int num) {
        for (i = 0; i <= 7; i++) {
            for (j = 1; j <= 3; j++) {
                if (a[i][j] == num) {
                    a[i][0] = 11;
                    a[i][4] = 10;
                }
            }
        }
        for (i = 0; i <= 7; i++) {
            set = true;
            if (a[i][4] == 10) {
                int count = 0;
                for (j = 1; j <= 3; j++) {
                    if (b[(a[i][j] - 1)].getText() != null) {
                        count++;
                    } else {
                        yesnull = a[i][j];
                    }
                }
                if (count == 2) {
                    b[yesnull - 1].setText(player2);
                    this.check(yesnull);
                    set = false;
                    break;
                }
            } else if (a[i][0] == 10) {
                for (j = 1; j <= 3; j++) {
                    if (b[(a[i][j] - 1)].getText() == null) {
                        b[(a[i][j] - 1)].setText(player2);
                        this.check(a[i][j]);
                        set = false;
                        break;
                    }
                }
                if (set == false)
                    break;
            }

            if (set == false)
                break;
        }
    }

    public TicTacToe() {
        super("tic tac toe by ashwani");

        setUndecorated(true);
        getRootPane().setBorder(BorderFactory.createEmptyBorder());

        CheckboxGroup cbg = new CheckboxGroup();
        c1 = new Checkbox("vs computer", cbg, false);
        c2 = new Checkbox("vs friend", cbg, false);
        c1.setBounds(120, 80, 100, 40);
        c2.setBounds(120, 150, 100, 40);
        add(c1);
        add(c2);
        c1.addItemListener(this);
        c2.addItemListener(this);

        state = true;
        type = true;
        set = true;

        setLayout(null);
        setSize(550, 450);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void itemStateChanged(ItemEvent e) {
        if (c1.getState()) {
            type = false;
        } else if (c2.getState()) {
            type = true;
        }
        remove(c1);
        remove(c2);
        repaint(0, 0, 550, 450);
        showButton();
    }

    public void actionPerformed(ActionEvent e) {
        if (type == true) {
            if (e.getSource() == reset) {
                for (i = 0; i <= 8; i++) {
                    b[i].setText(null);
                    b[i].setBackground(new Color(255, 223, 186));
                }
            } else {
                for (i = 0; i <= 8; i++) {
                    if (e.getSource() == b[i]) {
                        if (b[i].getText() == null) {
                            if (state == true) {
                                b[i].setText(player2);
                                state = false;
                            } else {
                                b[i].setText(player1);
                                state = true;
                            }
                        }
                    }
                }
            }
            checkDraw();
            checkWinner();
        } else if (type == false) {
            if (e.getSource() == reset) {
                for (i = 0; i <= 8; i++) {
                    b[i].setText(null);
                    b[i].setBackground(new Color(255, 223, 186));
                }
                for (i = 0; i <= 7; i++)
                    for (j = 0; j <= 4; j++)
                        a[i][j] = a1[i][j];
            } else {
                for (i = 0; i <= 8; i++) {
                    if (e.getSource() == b[i]) {
                        if (b[i].getText() == null) {
                            b[i].setText(player1);
                            if (b[4].getText() == null) {
                                b[4].setText(player2);
                                this.check(5);
                            } else {
                                this.complogic(i);
                            }
                        }
                    }
                }
            }
            checkDraw();
            checkWinner();
        }
    }

    public void checkDraw() {
        boolean isDraw = true;
        for (i = 0; i < 9; i++) {
            if (b[i].getText() == null) {
                isDraw = false;
                break;
            }
        }
        if (isDraw) {
            JOptionPane.showMessageDialog(TicTacToe.this, "DRAW! Click reset");
            resetGame();
        }
    }

    public void checkWinner() {
        for (i = 0; i <= 7; i++) {
            String text1 = b[(a[i][1] - 1)].getText();
            String text2 = b[(a[i][2] - 1)].getText();
            String text3 = b[(a[i][3] - 1)].getText();
            if (text1 != null && text1.equals(text2) && text2.equals(text3)) {
                if (text1.equals(player1)) {
                    playerScore++;
                } else if (text1.equals(player2)) {
                    computerScore++;
                }
                updateScoreLabel();
                highlightWinnerButtons(a[i][1] - 1, a[i][2] - 1, a[i][3] - 1);
                drawWinningLines(b[a[i][1] - 1].getX() + b[a[i][1] - 1].getWidth() / 2,
                        b[a[i][1] - 1].getY() + b[a[i][1] - 1].getHeight() / 2,
                        b[a[i][2] - 1].getX() + b[a[i][2] - 1].getWidth() / 2,
                        b[a[i][2] - 1].getY() + b[a[i][2] - 1].getHeight() / 2,
                        b[a[i][3] - 1].getX() + b[a[i][3] - 1].getWidth() / 2,
                        b[a[i][3] - 1].getY() + b[a[i][3] - 1].getHeight() / 2);
                Timer timer = new Timer(1500, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (text1.equals(player1)) {
                            JOptionPane.showMessageDialog(TicTacToe.this, "Player Win! Click reset");
                        } else if (text1.equals(player2)) {
                            JOptionPane.showMessageDialog(TicTacToe.this, "Computer Win! Click reset");
                        }
                        resetGame();
                    }
                });
                timer.setRepeats(false);
                timer.start();
                break;
            }
        }
    }

    public void highlightWinnerButtons(int index1, int index2, int index3) {
        b[index1].setBackground(new Color(46, 204, 113)); // Emerald Green background
        b[index2].setBackground(new Color(46, 204, 113)); // Emerald Green background
        b[index3].setBackground(new Color(46, 204, 113)); // Emerald Green background
    }

    public void drawWinningLines(int x1, int y1, int x2, int y2, int x3, int y3) {
        Graphics g = getGraphics();
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(new Color(231, 76, 60)); // Red color
        g2d.setStroke(new BasicStroke(5)); // Thick line

        g2d.drawLine(x1, y1, x2, y2);
        g2d.drawLine(x2, y2, x3, y3);
    }

    public void updateScoreLabel() {
        playerScoreLabel.setText("Player: " + playerScore);
        computerScoreLabel.setText("Computer: " + computerScore);
    }

    public void resetGame() {
        for (i = 0; i <= 8; i++) {
            b[i].setText(null);
            b[i].setBackground(new Color(255, 223, 186));
        }
        for (i = 0; i <= 7; i++)
            for (j = 0; j <= 4; j++)
                a[i][j] = a1[i][j];
    }
}
