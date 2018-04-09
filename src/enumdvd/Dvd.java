package enumdvd;

import org.json.simple.JSONObject;

public enum Dvd {
	
	DVD001{
		@Override
		public String getID() {
			return null;
		}

		@Override
		public String getTitleITA() {
			return null;
		}
	};
	
	public abstract String getID();
	public abstract String getTitleITA();

}
