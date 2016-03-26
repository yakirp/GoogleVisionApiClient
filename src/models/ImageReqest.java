package models;

import java.util.List;

public class ImageReqest {

	  public Image image;
	  public List<Features> features;
	public ImageReqest(Image image, List<Features> features) {
		super();
		this.image = image;
		this.features = features;
	}
	  
}


/*

{
   "requests": [
      {
         "image": {
            "content": "/9j/4AAQS...0LSP//Z"
         },
         "features": [
            {
               "type": "LANDMARK_DETECTION",
               "maxResults": "10"
            },
            {
               "type": "LOGO_DETECTION",
               "maxResults": "10"
            },
            {
               "type": "LABEL_DETECTION",
               "maxResults": "10"
            }
         ]
      }
   ]
}
*/