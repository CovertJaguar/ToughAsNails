package toughasnails.util;

/**
 * @author Vexatos
 */
public class TimeUtil {

	public static final class F {

		public static final float TICKS_IN_DAY = 36000.0F;
		public static final float TICKS_IN_NIGHT = 24000.0F;
		public static final float TICKS_TOTAL = TICKS_IN_DAY + TICKS_IN_NIGHT;
		public static final float TICKS_TO_NOON = TICKS_IN_DAY / 2.0F;
		public static final float TICKS_TO_MIDNIGHT = TICKS_IN_NIGHT / 2.0F;
	}

	public static final class I {

		public static final int TICKS_IN_DAY = 36000;
		public static final int TICKS_IN_NIGHT = 24000;
		public static final int TICKS_TOTAL = TICKS_IN_DAY + TICKS_IN_NIGHT;
		public static final int TICKS_TO_NOON = TICKS_IN_DAY / 2;
		public static final int TICKS_TO_MIDNIGHT = TICKS_IN_NIGHT / 2;
	}
}
