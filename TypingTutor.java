package august5;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.Timer;

public class TypingTutor extends JFrame implements ActionListener {
	private JLabel lbltimer;
	private JLabel lblscore;
	private JLabel lblword;
	private JTextField textf;
	private JButton startbtn;
	private JButton stopbtn;

	private Timer timer;
	private int score = 0;
	private boolean running = false;
	private int timeremaining = 50;
	String[] words = null;

	public TypingTutor(String[] args) {
		this.words = args;
		GridLayout layout = new GridLayout(3, 2);
		super.setLayout(layout);

		Font font = new Font("Comic Sans MS", 1, 100);

		lbltimer = new JLabel("Timer");
		lbltimer.setFont(font);
		lbltimer.setOpaque(true);
		lbltimer.setBackground(Color.CYAN);
		super.add(lbltimer);

		lblscore = new JLabel("Score");
		lblscore.setFont(font);
		lblscore.setOpaque(true);
		lblscore.setBackground(Color.CYAN);
		super.add(lblscore);

		lblword = new JLabel("");
		lblword.setFont(font);
		lblword.setOpaque(true);
		lblword.setBackground(Color.CYAN);
		super.add(lblword);

		textf = new JTextField();
		textf.setFont(font);
		textf.setOpaque(true);
		textf.setBackground(Color.CYAN);
		super.add(textf);

		startbtn = new JButton("Start");
		startbtn.setFont(font);
		startbtn.setOpaque(true);
		startbtn.setBackground(Color.CYAN);
        startbtn.addActionListener(this);
		super.add(startbtn);

		stopbtn = new JButton("Stop");
		stopbtn.setFont(font);
		stopbtn.setOpaque(true);
		stopbtn.setBackground(Color.CYAN);
		stopbtn.addActionListener(this);
		super.add(stopbtn);

		super.setTitle("Typing Tutor");
		super.setExtendedState(MAXIMIZED_BOTH);
		super.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		super.setVisible(true);
		setupthegame();
	}

	private void setupthegame() {
		timer = new Timer(2000, this);
		timeremaining=50;
		score = 0;
		running = false;
		lbltimer.setText("Timer: " + timeremaining);
		lblscore.setText("Score: " + score);
		lblword.setText("");
		textf.setText("");
		textf.setEnabled(false);
		startbtn.setText("Start");
		stopbtn.setText("Stop");
		
		stopbtn.setEnabled(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == stopbtn) {
			handlestop();
		} else if (e.getSource() == startbtn) {
			handlestart();

		}

		else {
			handletimer();
		}

	}

	private void handletimer() {
		timeremaining--;
		String actual, expected;
		actual = textf.getText();
		expected = lblword.getText();
		if (expected.length() > 0 && actual.equals(expected) == true) {
			score++;
		}
		lblscore.setText("Score: " + score);
		if (timeremaining == -1) {
			handlestop();
		} else {
			int ridx = (int) (Math.random() * words.length);
			lblword.setText(words[ridx]);
			textf.setText("");
			lbltimer.setText("Timer:" + timeremaining);
		}

	}

	private void handlestart() {
		if (running == true) {
			timer.stop();
			running = false;

			startbtn.setText("Resume");
			stopbtn.setEnabled(false);
			textf.setEnabled(false);
		} else {
			timer.start();
			running = true;

			startbtn.setText("Pause");
			stopbtn.setEnabled(true);
			textf.setEnabled(true);

		}

	}

	private void handlestop() {
		timer.stop();

		int choice = JOptionPane.showConfirmDialog(this, "Score: " + score + ".replay?");
		if (choice == JOptionPane.YES_OPTION) {
			setupthegame();
		} else if (choice == JOptionPane.NO_OPTION) {
			super.dispose();
		} else {
			if (timeremaining == -1) {
				setupthegame();
			} else {
				timer.start();
			}
		}

	}

}
