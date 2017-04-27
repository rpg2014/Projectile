

public class Projectile
{
    //inital conditions
    double timeStep = 0.0005;
    double Y0Pos = 0.0;
    double X0Pos= 0.0;

    
    //projectile constants
    double mass = 0.24;//0.0024;
    double g0 = -9.80665;
    double Area = 0.001122; //cross sectional area
    double dragCoef = 0.001;

    //earth / atmosphere constants
    double RadEar = 6371000; // mean radius of earth for varying g
    double AirDensity = 1.167;
    double p0 = 101.325;
    double t0 = 288.15;
    double L = 0.0065; //temp lapse rate
    double R = 8.31447; // gas constant
    double M = 0.0289644; //molar mass of air
    double Cd = .00047; // i dont know what this is
    
    
    //declare variables
    double YPos = Y0Pos;
    double XPos = X0Pos;
    double g;
    double X0Vel;
    double Y0Vel;
    double TotalVel;
    double DragForce;
    double XDragForce;
    double YDragForce;
    double Angle;
    double XAccel;
    double YAccel;
    boolean OnGround;
    
    //create pos and vel objects
    Position pos = new Position(X0Pos, Y0Pos);
    public Velocity vel = new Velocity();



    public Projectile(double v, double degrees, int a)
    {
        TotalVel = v;
        Y0Vel = v*Math.sin(degrees);
        X0Vel = v*Math.cos(degrees);
        Angle = degrees;
        vel.setVel(X0Vel,Y0Vel);
        vel.setTotalVel(v);

    }
    public Projectile(double xv , double yv)
    {

    	//System.out.println("original x vel = " + X0Vel);

    	//System.out.println("original y vel = " + Y0Vel);
    	vel.setTotalVel(Math.sqrt((Math.pow(xv, 2)+Math.pow(yv, 2))));
    	vel.setAngle(Math.atan2(yv, xv));
    	vel.setVel(xv, yv);

    }

 public Projectile() {
		// TODO Auto-generated constructor stub
	}


/**
  * update methods
  * i want to eventually make the update timestep one method
  */
 	public void setInitCond(double v, double a, int i)
 	{
 		vel.setVel(v,a,1);

 	}
    public void updatePos()
    {
//    	double k1,k2,k3,k4;
    	pos.setYPos(pos.getYPos() + timeStep*vel.getYVel());
    	pos.setXPos(pos.getXPos() + timeStep*vel.getXVel());
    	//System.out.println("new pos coods = (" + XPos +","+YPos+")" );
//    	k1 = pos.getYPos()+timeStep*vel.getYVel();
//    	k2 = pos.getYPos()+(timeStep/2*k1)+(timeStep/2)*vel.getYVel();
//    	k3 = pos.getYPos()+(timeStep/2*k2)+(timeStep/2)*vel.getYVel();
//    	k4 = pos.getYPos()+(timeStep*k3)+(timeStep)*vel.getYVel();
//    	pos.setYPos(pos.getYPos()+timeStep*((k1/six)+(k2/three)+(k3/three)+(k4/six)));//pos.getYPos()+

//    	k1 = pos.getXPos()+timeStep*vel.getXVel();
//    	k2 = pos.getXPos()+(timeStep/2*k1)+(timeStep/2)*vel.getXVel();
//    	k3 = pos.getXPos()+(timeStep/2*k2)+(timeStep/2)*vel.getXVel();
//    	k4 = pos.getXPos()+(timeStep*k3)+(timeStep)*vel.getXVel();
//    	pos.setXPos(pos.getXPos()+timeStep*((k1/six)+(k2/three)+(k3/three)+(k4/six)));//pos.getXPos()+

    }

	public void updateVel() {
//		double k1,k2,k3,k4;
		vel.setYVel(vel.getYVel() + timeStep*YAccel);
		vel.setXVel(vel.getXVel() + timeStep*XAccel);
//		k1 = vel.getYVel()+timeStep*YAccel;
//    	k2 = vel.getYVel()+(timeStep/2*k1)+(timeStep/2)*YAccel;
//    	k3 = vel.getYVel()+(timeStep/2*k2)+(timeStep/2)*YAccel;
//    	k4 = vel.getYVel()+(timeStep*k3)+(timeStep)*YAccel;
//    	vel.setYVel(vel.getYVel()+timeStep*((k1/six)+(k2/three)+(k3/three)+(k4/six)));//vel.getYVel()+

//    	k1 = vel.getXVel()+timeStep*XAccel;
//    	k2 = vel.getXVel()+(timeStep/2*k1)+(timeStep/2)*XAccel;
//    	k3 = vel.getXVel()+(timeStep/2*k2)+(timeStep/2)*XAccel;
//    	k4 = vel.getXVel()+(timeStep*k3)+(timeStep)*XAccel;
//    	vel.setXVel(vel.getXVel()+timeStep*((k1/six)+(k2/three)+(k3/three)+(k4/six)));//


		vel.setTotalVel(Math.sqrt((Math.pow(vel.getXVel(), 2)+Math.pow(vel.getYVel(), 2))));
		vel.setAngle(Math.atan2(vel.getYVel(), vel.getXVel()));
	}
	public void updateAccel() {
		YAccel = (YDragForce/mass)+g;
		XAccel = XDragForce/mass;
	}
	public void updateDragForce() {
		//=0.5*p*Cd*A*L8^2
		double T = t0-(L*pos.getYPos());
		double pressure = p0*1000*Math.pow((1-((L*pos.getYPos())/t0)),(-g0*M)/(R*L));
//		System.out.println("base = " +(1-((L*pos.getYPos())/t0)));
//		System.out.println("exponent = "+(-g0*M)/(R*L));
		double p =(pressure*M)/(R*T);
//		System.out.println("density = " +p);
		DragForce = 0.5*p*Cd*Area*Math.pow(vel.getTotalVel(), 2);
		XDragForce = -Math.abs(DragForce*Math.cos(vel.getAngle()));
		YDragForce = -DragForce*Math.sin(vel.getAngle());

	}
	public void updateG() {
		g = g0*Math.pow(RadEar/(RadEar+pos.getYPos()),2);

	}

//	public double nextTimeStep(double x, double v)//trying to do a common update method
//	{
//		x = x+ v*timeStep;
//		return x;
//	}

/**
 * Get methods.
 */
	public Position getPos()
	{
		return pos;

	}
	public double getYPos() {
		// TODO Auto-generated method stub
		return pos.getYPos();
	}

/**
 * set methods
 * should be rarely needed
 */

}
