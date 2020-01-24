package de.cndrbrbr.ReadOSM;


public class MCWCpoint {
	
		
		static private SphericalMercator merc = new SphericalMercator();
		static public boolean ISDEGREE = true; 
		static public boolean ISBLOCKS = false; 

	// RefPoint in (0,x,0)
		static private double refLatN;

		static private double refLonE;
		
		static private double refMeterX;
		static private double refMeterY;
		
		public static double getRefLatN() {
			return refLatN;
		}
		public static double getRefLonE() {
			return refLonE;
		}
		public static void setRefLatN(double refLatN) {
			MCWCpoint.refLatN = refLatN;
			MCWCpoint.refMeterY = merc.lat2y(refLatN);
		}
		public static void setRefLonE(double refLonE) {
			MCWCpoint.refLonE = refLonE;
			MCWCpoint.refMeterX = merc.lon2x(refLonE);
		}
		
		
		static private double BlockMeterScale = 0.75; // 1 Block = 1,5 Meter	
		
		public static double getBlockMeterScale() {
			return BlockMeterScale;
		}
		public static void setBlockMeterScale(double blockMeterScale) {
			BlockMeterScale = blockMeterScale;
		}
		
		//static private double OriginBlockX = ; // 1 Block = 1,5 Meter	
		
		
		
	
		private double lonE;  		// easting
		private double latN;  		// northing
		private double meterX;	 	// rechtswert 	, positiv nach osten
		private double meterY;	 	// hochwert 	, positiv nach norden
		private double meterXs;	 	// shift rechtswert 	, positiv nach osten
		private double meterYs;	 	// shift hochwert 	, positiv nach norden


		private double blockX;		// block x 		. positiv nach osten
		private double blockZ;		// block z 		, negativ nach norden
		public double getBlockX() {
			return blockX;
		}
		public double getBlockZ() {
			return blockZ;
		}
		
		public MCWCpoint(double ilatz, double ilonx , boolean isDegreeNotBlocks) 
		{

			if (isDegreeNotBlocks) {
				latN = ilatz;
				lonE = ilonx;
				mercator2meter();
				meter2blocks();
			}
			else {
				blockX = ilonx;
				blockZ = ilatz;
				blocks2meter();
				mercator2deg();				
			}
			
		}
			
		private void mercator2meter() // transverse mercator lat lon to x,y meter
		{
			meterX =  merc.lon2x(lonE);
			meterY =  merc.lat2y(latN);
			meterXs = meterX - refMeterX;
			meterYs = meterY - refMeterY;
		}
		private void mercator2deg() // transverse mercator lat lon from x,y meter
		{
			lonE  =  merc.x2lon(meterX);
			latN  =  merc.y2lat(meterY);
		}

		private void meter2blocks() // x,y meter to block
		{
			blockX = meterXs * BlockMeterScale;
			blockZ = (-1) * meterYs * BlockMeterScale;
		}
		private void blocks2meter() // x,y meter to block
		{
			meterXs =  blockX / BlockMeterScale;
			meterYs = (-1) * blockZ / BlockMeterScale;
			meterX = meterXs + refMeterX;
			meterY = meterYs + refMeterY;
		}
		@Override
		public String toString() {

			StringBuilder outstring = new StringBuilder();
			outstring.append("X "+ blockX +  " Z "+ blockZ + " mX " + meterX  + " mY " + meterY + " mXs " +meterXs +  " mYs " +meterYs );
			outstring.append('\n');
			outstring.append(" LonE " + lonE + " LatN " + latN);
			return outstring.toString();
		}
		
		
	
}
