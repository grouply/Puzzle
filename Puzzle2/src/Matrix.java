import java.awt.image.BufferedImage;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.ImageIcon;

public class Matrix
{
	private JButton [][] button;
	private JPanel panel;
	private int row;
	private int cal;
	private int [][] matrix;
	private GameOver gOver;

	public Matrix(JButton [][] b, JPanel p, int len, GameOver g)
	{
		this.button = b;
		this.panel = p;
		this.gOver = g;

		row = b.length;
		cal = b[0].length;

		matrix = new int[row][cal];
		matrix[row-1][cal-1] = row * cal;
		gOver.start(matrix);
		panel.removeAll();
		point pint = new point(row-1, cal-1);
		for (int i = 0; i < row; i++)
			for (int j = 0; j < cal; j++)
			{
				button[i][j] = new JButton();
				button[i][j].setBounds(j*len, i*len, len, len);
				button[i][j].addActionListener(new ButtonClick(button, pint, matrix, i, j, gOver));
				panel.add(button[i][j]);
			}
	}

	public void init(BufferedImage [][] image)
	{
		if (button == null || image == null)
			return;

		ImageIcon icon;
		int r, c, rad, d, m;
		boolean [] visit = new boolean[row*cal-1];
		for (int i = 0; i < row*cal - 1; i++)
		{
			r = i / cal;
			c = i % cal;
			rad = (int)(Math.random()*(row*cal-1-i));
			for (d = m = 0; d < row*cal - 1 && m <= rad; d++)
				if (!visit[d])
					m++;
			matrix[i/cal][i%cal] = d;
			visit[--d] = true;
			icon = new ImageIcon(image[d/cal][d%cal]);
			button[i/cal][i%cal].setIcon(icon);
		}
	}
}
