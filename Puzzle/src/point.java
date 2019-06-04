public class point
{
	int row;
	int cal;

	public point(int r, int c)
	{
		row = r;
		cal = c;
	}

	public void set(int r, int c)
	{
		row = r;
		cal = c;
	}

	public int getRow()
	{
		return row;
	}

	public int getCal()
	{
		return cal;
	}

	public boolean neighbor(int r, int c)
	{
		int n = Math.abs(row - r) + Math.abs(cal - c);
		return n == 1;
	}
}
