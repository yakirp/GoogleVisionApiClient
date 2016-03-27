package vision;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
 
import org.apache.commons.io.FileUtils;
 

public class Main {

	 
	 
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
         
		File file = new File("c:\\vision\\input_bar.jpg");
	 
	  
		GoogleVisionApi api = new GoogleVisionApi("asdsad");
		api.addImage("http://images1.ynet.co.il/PicServer4/2016/03/21/6891158/68911530991498640360no.jpg");
	 
		
		System.err.println(api.execute());
		
	 
	 
	}

}
