import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Container;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class CardFrame extends MenuFrame
{
	private CardLayout card;
	private Container con;
	protected JPanel [] panel = {
		new JPanel(), new JPanel()
	};

	public CardFrame()
	{
		con = this.getContentPane();
		card = new CardLayout();
		con.setLayout(card);

		panel[0].setLayout(null);
		JLabel label;
		label = new JLabel("拼图游戏！");
		label.setBounds(11, 15, 190, 30);
		panel[0].add(label);
		label = new JLabel("先确定背景图片");
		label.setBounds(10, 30, 190, 45);
		panel[0].add(label);
		label = new JLabel("再点击开始游戏！");
		label.setBounds(10, 45, 190, 60);
		panel[0].add(label);
		
		
		

		panel[1].setLayout(new BorderLayout());
		JButton button = new JButton("继续");
		button.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent e)
				{
					FrameGetFocus();
				}
			}
		);
		panel[1].add(button, BorderLayout.CENTER);

		con.add(panel[0], "Game");
		con.add(panel[1], "Pause");
		card.show(con, "Game");

		this.addWindowListener(new WindowAdapter()
			{
				public void windowDeactivated(WindowEvent e)
				{
					FrameLostFocus();
				}
			}
		);
	}

	protected void nextCard()
	{
		card.next(con);
	}

	protected abstract void FrameLostFocus();
	protected abstract void FrameGetFocus();
}
