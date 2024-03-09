package Controller;


public class StaticController {
	private static StaticController instance;
	private String pageColor;
	
	private StaticController() {
        pageColor="Dark Aurora";
    }
	
	public static StaticController getInstance() {
        if (instance == null) {
            instance = new StaticController();
        }
        return instance;
    }

	public String getPageColor() {
		return pageColor;
	}

	public void setPageColor(String pageColor) {
		this.pageColor = pageColor;
	}
	
	
}
