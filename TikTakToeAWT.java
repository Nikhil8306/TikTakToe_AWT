import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

public class TikTakToeAWT implements ActionListener, WindowListener {
    Frame frame;
    Button[][] buttons;
    Button restartBtn;
    Label txt;
    Label winLabel;
    String title = "Tik Tak Toe :)";
    int frameWidth = 900;
    int frameHeight = 1000;
    int blockWidth = 300;

    String currToken = "X";
    boolean gameOver;

    TikTakToeAWT(){
        gameOver = false;
        this.winLabel = new Label();
        this.restartBtn = new Button("Restart");
        restartBtn.setVisible(false);
        winLabel.setVisible(false);
        this.frame = new Frame();
        this.buttons = new Button[3][3];
        txt = new Label();
        txt.setBounds(0,3*blockWidth, frameWidth, frameHeight-(3*blockWidth));
        txt.setAlignment(1);
        frame.setSize(frameWidth, frameHeight);
        frame.setTitle(title);
        frame.setLayout(null);
        frame.setVisible(true);
        Font fnt = new Font("Serif",Font.BOLD,30);
        winLabel.setBounds(0,300,frameWidth, 300);
        winLabel.setFont(new Font("Serif",Font.BOLD,50));
        restartBtn.setFont(new Font("Serif",Font.BOLD,45));
        winLabel.setAlignment(1);
        restartBtn.setBounds(300, 600, frameWidth/3, 200);
        restartBtn.addActionListener(this);

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j] = new Button();
                buttons[i][j].setBounds(j * blockWidth, i * blockWidth, blockWidth, blockWidth);
                buttons[i][j].addActionListener(this);
                frame.add(buttons[i][j]);
                buttons[i][j].setFont(fnt);
            }
        }

        frame.add(txt);
        txt.setText("Player 1's turn");
        frame.add(winLabel);
        frame.add(restartBtn);
        txt.setFont(fnt);

        frame.addWindowListener(this);
    }
    public boolean alreadyExist(Button btn){
        if (btn.getLabel() != "") return true;
        return false;
    }
    public void hideButtons(){
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setVisible(false);
            }
        }
        txt.setVisible(false);
    }


    public void actionPerformed(ActionEvent e) {
        Button btn = (Button)e.getSource();
        if (gameOver){
            restart();
            return;
        }
        if (alreadyExist(btn)) return;
        if (currToken == "X"){
            btn.setLabel("X");
            currToken = "0";
            txt.setText("Player 2's turn");
        }
        else {btn.setLabel("0"); currToken = "X"; txt.setText("Player 1's turn");}


        if (checkWin(buttons)){
            gameOver = true;
            hideButtons();

            if (currToken == "0") winLabel.setText("Player 1 won");
            else winLabel.setText("Player 2 won");

            winLabel.setVisible(true);
            restartBtn.setVisible(true);
        }
    }
    public boolean checkWin(Button buttons[][]){
        for(int i = 0; i < 3; i++){
            if (buttons[i][0].getLabel() != "") if (buttons[i][0].getLabel() == buttons[i][1].getLabel() && buttons[i][0].getLabel() == buttons[i][2].getLabel()) return true;
            if (buttons[0][i].getLabel() != "") if (buttons[0][i].getLabel() == buttons[1][i].getLabel() && buttons[1][i].getLabel() == buttons[2][i].getLabel()) return true;
        }
        if (buttons[0][0].getLabel() != "") if (buttons[0][0].getLabel() == buttons[1][1].getLabel() && buttons[1][1].getLabel() == buttons[2][2].getLabel()) return true;
        if (buttons[2][0].getLabel() != "") if (buttons[2][0].getLabel() == buttons[1][1].getLabel() && buttons[1][1].getLabel() == buttons[0][2].getLabel()) return true;

        return false;
    }

    public void restart(){
        gameOver = false;
        txt.setVisible(true);
        txt.setText("Player 1's turn");
        currToken = "X";
        winLabel.setVisible(false);
        restartBtn.setVisible(false);
        for(int i = 0; i < 3; i++){
            for(int j = 0; j < 3; j++){
                buttons[i][j].setLabel("");
                buttons[i][j].setVisible(true);
            }
        }
    }

    public static void main(String[] args) {
        TikTakToeAWT game = new TikTakToeAWT();
    }


    public void windowOpened(WindowEvent e) {
    }

    public void windowClosing(WindowEvent e) {
        System.exit(0);
    }

    public void windowClosed(WindowEvent e) {
    }

    public void windowIconified(WindowEvent e) {
    }

    public void windowDeiconified(WindowEvent e) {
    }

    public void windowActivated(WindowEvent e) {
    }

    public void windowDeactivated(WindowEvent e) {
    }
}
