
public class Velocity {

	private double XVel;
	private double YVel;
	private double TotalVel;
	private double Angle;

	public Velocity(double x , double y)
	{
		XVel = x;
		YVel = y;
		setTotalVel(Math.sqrt((Math.pow(XVel, 2)+Math.pow(YVel, 2))));
	}
	public Velocity(double v, double deg,int a)
	{
		setTotalVel(v);
		Angle = deg;
		YVel = v*Math.sin(deg);
        XVel = v*Math.cos(deg);
	}
	public Velocity() {
		XVel = 0;
		YVel = 0;
	}
	public double getXVel()
	{
		return XVel;
	}
	public double getYVel()
	{
		return YVel;
		
	}
	public void setXVel( double x)
	{
		XVel = x;
		
	}
	public void setYVel(double y)
	{
		YVel = y;
		
	}
	public void setVel(double x, double y)
	{
		XVel = x;
		YVel = y;
		TotalVel = Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
		Angle = Math.atan2(y, x);
	}
	public void setVel(double v, double a, int i)
	{
		XVel = v*Math.cos(a);
		YVel = v*Math.sin(a);
		Angle = a;
		TotalVel =v;
	}
	public double getTotalVel() {
		return TotalVel;
	}
	public void setTotalVel(double totalVel) {
		TotalVel = totalVel;
	}
	public double getAngle() {
		return Angle;
	}
	public void setAngle(double angle) {
		Angle = angle;
	}
	public String toString()
	{
		return (TotalVel +" at " + Angle + " Degrees");
	}
}
