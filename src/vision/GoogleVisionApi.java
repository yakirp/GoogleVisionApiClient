package vision;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;

import com.google.gson.Gson;

import models.Features;
import models.Image;
import models.ImageReqest;
import models.JsonReqeust;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class GoogleVisionApi {

	private static final String API_KEY = "";
	private OkHttpClient client;
	private JsonReqeust jReqeust;

	public GoogleVisionApi() {
		jReqeust = new JsonReqeust();
		client = new OkHttpClient();
	}

	public void addImage(String url) throws IOException {
		Request request = new Request.Builder().url(url).build();
		Response response = client.newCall(request).execute();
		InputStream in = response.body().byteStream();
		byte[] data = readBytes(in);
		response.body().close();
		addImage(data);
	}

	public byte[] readBytes(InputStream inputStream) throws IOException {
		// this dynamically extends to take the bytes you read
		ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();

		// this is storage overwritten on each iteration with bytes
		int bufferSize = 1024;
		byte[] buffer = new byte[bufferSize];

		// we need to know how may bytes were read to write them to the
		// byteBuffer
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			byteBuffer.write(buffer, 0, len);
		}

		// and then we can return your byte array.
		return byteBuffer.toByteArray();
	}

	public void addImage(File file) throws IOException {
		addImage(FileUtils.readFileToByteArray(file));
	}

	private void addImage(byte[] image) {

		jReqeust.requests.add(imageToJson(image));

	}

	public String execute() throws IOException {

		MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

		Request request = new Request.Builder()
				.url("https://vision.googleapis.com/v1/images:annotate?key="+API_KEY)
				.post(RequestBody.create(MEDIA_TYPE_MARKDOWN, toJson())).build();

		Response response = client.newCall(request).execute();
		if (!response.isSuccessful())
			throw new IOException("Unexpected code " + response);

		return response.body().string();
	}

	public ImageReqest imageToJson(byte[] data) {

		byte[] dataBase64 = Base64.encodeBase64(data);
		
				List<Features> fList = new ArrayList<>();
		fList.add(new Features("LABEL_DETECTION", "5"));
		fList.add(new Features("TEXT_DETECTION", "5"));
		fList.add(new Features("FACE_DETECTION", "5"));
		fList.add(new Features("LANDMARK_DETECTION", "5"));
		fList.add(new Features("LOGO_DETECTION", "5"));
		fList.add(new Features("IMAGE_PROPERTIES", "5"));
		  
		 

		Image image = new Image(new String(dataBase64));

		return new ImageReqest(image, fList);

	}

	private String toJson() {
		return new Gson().toJson(jReqeust, JsonReqeust.class);
	}

}
