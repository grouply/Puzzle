import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JOptionPane;

public class ButtonClick implements ActionListener
{
	private JButton [][] button;
	private point pint;
	private int row;
	private int cal;
	private int [][] matrix;
	private GameOver gOver;
	private boolean end;

	public ButtonClick(JButton [][] b, point p, int [][] m, int r, int c, GameOver g)
	{
		button = b;
		pint = p;
		matrix = m;
		row = r;
		cal = c;
		gOver = g;
		end = false;
	}

	public void actionPerformed(ActionEvent e)
	{
		if (end || !pint.neighbor(row, cal))
			return;

		int r = pint.getRow();
		int c = pint.getCal();

		button[r][c].setIcon(button[row][cal].getIcon());
		button[row][cal].setIcon(null);
		pint.set(row, cal);
		int t = matrix[r][c];
		matrix[r][c] = matrix[row][cal];
		matrix[row][cal] = t;
		end = gOver.judge();
	}
}
