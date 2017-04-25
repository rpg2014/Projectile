/**'
 * Gonna eventually abstract position and velocity and stuff.  
 * but for right now just having it in the calc class works
 * @author Parker Given
 *
 */
public class Position {

	private double XPos;
	private double YPos;

	public Position(double x , double y)
	{
		XPos = x;
		YPos = y;
		
	}
	public double getXPos()
	{
		return XPos;
	}
	public double getYPos()
	{
		return YPos;
		
	}
	public void setXPos( double x)
	{
		XPos = x;
		
	}
	public void setYPos(double x)
	{
		YPos = x;
		
	}
	public void setPos(double x, double y)
	{
		XPos = x;
		YPos = y;
	}
	public String toString()
	{
		return ("("+ XPos +","+YPos+")");
	}
	public String toString(int i )
	{
		return (XPos+"," + YPos);
	}
}
