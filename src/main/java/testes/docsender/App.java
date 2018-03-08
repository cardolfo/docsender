package testes.docsender;

import java.io.File;
import java.io.IOException;
import java.util.Base64;

import org.apache.commons.io.FileUtils;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;



/**
 * Hello world!
 *
 */
public class App {

	private static String url = "http://localhost:3000/documents/create";
	private static String fileName = "/home/tj.ce.gov.br/8880/Documentos/wiki_cnc.pdf";

	public static void main(String[] args) {
		// Boolean success = true;
		try {
			JSONObject jsonObject = constructPictureJson();
			DefaultHttpClient httpClient = new DefaultHttpClient();

			ResponseHandler<String> responseHandler = new BasicResponseHandler();
			HttpPost postMethod = new HttpPost(url);
			postMethod.setEntity(new StringEntity(jsonObject.toString()));
			postMethod.setHeader("Accept", "application/json");
			postMethod.setHeader("Content-type", "application/json");
			postMethod.setHeader("Data-type", "json");
			httpClient.execute(postMethod, responseHandler);
		} catch (Exception exception) {
			exception.printStackTrace();
			// Log.d("Uploader Class Error", "Error code: " + error.getStatusCode());
			// Log.d("Uploader Class Error", "Error message: " + error.getMessage());
			// success = false;
		}
		// Log.d("server resposne", response);
		// return success;
	}

	public static JSONObject constructPictureJson() throws JSONException, IOException {
		//String userId = "1";
		//String folderId = "1";
		String[] file = fileName.split("/");
		JSONObject fileData = new JSONObject();		
		//pictureData.put("user_id", userId);
		//pictureData.put("folder_id", folderId);
		//pictureData.put("picture_name", "picture name");
		//pictureData.put("picture_description", "1");
		//pictureData.put("content_type", "pdf");
		//pictureData.put("original_filename", "base64:" + file[file.length - 1]);
		fileData.put("file_name", file[file.length - 1]);
		fileData.put("file", encodePicture(fileName));

		return fileData;
	}

	public static String encodePicture(String fileName) throws IOException {
		File picture = new File(fileName);		
		return Base64.getEncoder().encodeToString(FileUtils.readFileToByteArray(picture));
	}
}
