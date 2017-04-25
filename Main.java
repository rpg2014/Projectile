import java.io.IOException;

public class Main {
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//Adjust these two
		final  double TARGET_MIN = 999.95;
		final double TARGET_MAX = 1000.05;
		final double midPoint = (TARGET_MIN+TARGET_MAX)/2;
		double initalTotalV = 100;
		double velMod = 10;
		final double initalAngle = 45; // in degrees between 0,90 exclusive
		//final double 
		boolean inTarget = false;
		boolean record= false;
		int i=0;
		int j=0;
		while (inTarget == false)
		{
			j++;
			Calcsteps calc = new Calcsteps(initalTotalV, initalAngle); //angle in degrees
			if(record)
				calc.record = true;
			calc.updateLoop();
			if (i!=1)
			System.out.print("Position is " +calc.proj.pos);
			if (calc.proj.pos.getXPos()>TARGET_MIN && calc.proj.pos.getXPos()<TARGET_MAX)
			{
				inTarget= true;
				
				if (i==0)
				System.out.println("\nWe are in the Target");
				i++;
			}
			else // add error mechanic
			{
				double percentOff = -(calc.proj.pos.getXPos()-midPoint)/midPoint;
				if (percentOff>.1)
				{
				initalTotalV = percentOff*initalTotalV+initalTotalV;
				}
				else{
					initalTotalV = velMod*percentOff+initalTotalV;
				}
				if (initalTotalV < 1)
					initalTotalV = 10;
				if (j%50==0)
				
					velMod=velMod/2;
					
				if(j%200 == 0)
					velMod = 10;
				System.out.println("; New Velocity = "+initalTotalV);
			}
			if (i==1 && inTarget == true)
			{
				inTarget = false;
				record=true;
				
			}


			
		}
//		System.out.println("Position is " +calc.proj.pos);
		
		System.out.println("\nInital Conditions: V = " + initalTotalV +"; Angle = "+ initalAngle);
		String command = "python3 graph.py";
		Process p = Runtime.getRuntime().exec(command);

	}


}
