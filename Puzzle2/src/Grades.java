import java.io.File;
import java.io.PrintStream;

import java.util.Scanner;

import javax.swing.JOptionPane;

class Data
{
	public String name;
	public int time;
	public int step;
}

public class Grades
{
	private Puzzle app;
	private Data [] data = {
		new Data(),
		new Data(),
		new Data()
	};

	public Grades(Puzzle m)
	{
		app = m;
	}

	private void creatData()
	{
		try
		{
			File file = new File(Arg.rc);
			if (file.exists())
				return;
			file.createNewFile();

			PrintStream fout = new PrintStream(Arg.rc);
			fout.printf("%s %d %d\n", "name", 999999, 999999);
			fout.printf("%s %d %d\n", "name", 999999, 999999);
			fout.printf("%s %d %d\n", "name", 999999, 999999);
			fout.close();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, Arg.rc + " 已损坏");
			return;
		}
	}

	private void readData()
	{
		try
		{
			File file = new File(Arg.rc);
			Scanner cin = new Scanner(file);

			data[0].name = cin.next();
			data[0].time = cin.nextInt();
			data[0].step = cin.nextInt();

			data[1].name = cin.next();
			data[1].time = cin.nextInt();
			data[1].step = cin.nextInt();

			data[2].name = cin.next();
			data[2].time = cin.nextInt();
			data[2].step = cin.nextInt();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, Arg.rc + " 出现错误 你的新纪录没了哈哈哈");
		}
	}

	public void set(int t, int s)
	{
		int type = app.getTypeE();
		creatData();
		readData();
		if (t > data[type].time || (t == data[type].time && s >= data[type].step))
			return;
		data[type].name = JOptionPane.showInputDialog(null,
			"哇 你好厉害 你的名字是什么啊？"
		);
		data[type].time = t;
		data[type].step = s;

		try
		{
			PrintStream f = new PrintStream(Arg.rc);
			f.printf("%s %d %d\n", data[0].name, data[0].time, data[0].step);
			f.printf("%s %d %d\n", data[1].name, data[1].time, data[1].step);
			f.printf("%s %d %d\n", data[2].name, data[2].time, data[2].step);
			f.close();
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, Arg.rc + " has broken!");
		}

		show();
	}

	public void show()
	{
		try
		{
			creatData();
			readData();

			String title = "".format("%8s%15s%8s%8s\n", "Level", "Name", "Time", "Step");
			String h = "".format("%8s%15s%8d%8d\n", "Hard", data[0].name, data[0].time, data[0].step);
			String n = "".format("%8s%15s%8d%8d\n", "Normal", data[1].name, data[1].time, data[1].step);
			String e = "".format("%8s%15s%8d%8d\n", "Easy", data[2].name, data[2].time, data[2].step);

			JOptionPane.showMessageDialog(null, title+h+n+e);
		}
		catch (Exception e)
		{
			JOptionPane.showMessageDialog(null, Arg.rc + " has broken!");
		}
	}
}
