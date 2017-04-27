
import java.io.*;
import java.util.ArrayList;

public class Calcsteps {

	double initTotalV;
	double initAngle;
	public Projectile proj = new Projectile();
	public boolean record = false;
	String fileName = "output.csv";
	//File file = new File("C:\\Users\\pgive_000\\Dropbox\\sophmore college work\\Phys lab\\final\\output.csv");
	{

	}
	ArrayList al = new ArrayList();

	public Calcsteps(double v, double d) {
		initTotalV = v;
		initAngle = d * (Math.PI / 180);
		proj.setInitCond(v, initAngle, 1);

	}

	public void updateLoop() {
		try {
			Writer writer = null;

			int i = 0;
			if (record)
			{
				 writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName), "utf-8"));
				System.out.print("Writing");
			}
			while (this.proj.OnGround != true) {

				i++;
				this.update();

				//calc.draw();  // I want to eventually add a graphics part to it.
				if (i > 10) {
					if (this.proj.pos.getYPos() <= (double) 0)
						this.proj.OnGround = true;
				}
				if (record) {
					//				if (i%2==0)

					writer.write(proj.pos.toString(1) + "\n");
					//				Thread.sleep((long) 0.1);
					if (i % 10000 == 0)
						System.out.print(".");

				}
			}
			if(record)
			{
				writer.close();
				System.out.print("\n");
				System.out.println("Number of steps: "+i);
			}
			
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void update() {

		proj.updatePos();
		//			System.out.println("Position= ("+proj.pos.getXPos()+","+proj.pos.getYPos()+")");
		proj.updateAccel();
		proj.updateVel();
		proj.updateG();
		//System.out.println("velocity= ("+proj.vel.getXVel()+","+proj.vel.getYVel()+")");
		proj.updateDragForce();

		//System.out.println("i= "+i);
	}

}
