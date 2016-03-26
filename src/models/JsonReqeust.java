package models;

import java.util.ArrayList;
import java.util.List;

public class JsonReqeust {
	public List<ImageReqest> requests;

	public JsonReqeust() {
		super();
		requests = new ArrayList<>();
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