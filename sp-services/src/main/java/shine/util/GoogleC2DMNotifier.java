package shine.util;

import static shine.util.Constants.UTF8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import shine.dao.exception.ShineException;

import com.shine.GeneralError;

public class GoogleC2DMNotifier {

	private static Logger log = LoggerFactory.getLogger(GoogleC2DMNotifier.class);

	private static String c2dmurl = "https://android.apis.google.com/c2dm/send";
	private static String auth = null;//"DQAAALgAAAD1AldwSx88Cxk5GlU88NkStpGHzYGYRnRj5JTDk2WgZUm1ZlckfZU3_OhpsEwPE2ZyY-vIfRrT4TpHHkKv8n6d1m40XIlswEljldhkK_KHzOblUUFj6o6ELaujeXPhKP_LY-r1rWU8w0ZCq2i3BRTkLdEmwi2y0chG24Jy1xxLs5l8Md1onVe1utsavrMj58SGgidgTH6gqPC5dY-128XOqOWcLKBOTmqW2YTd7J91CRrV68kbQS1cE_38B2m2JpQ";

	static void getAUth() throws ShineException {

		log("Obtaining the Google C2DM Client Login token.");

		// Make POST request
		HttpResponse res = null;
		try {
			DefaultHttpClient client = new DefaultHttpClient();
			URI uri = new URI("https://www.google.com/accounts/ClientLogin");

			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("accountType", "GOOGLE_OR_HOSTED"));
			params.add(new BasicNameValuePair("Email", "streetpin@gmail.com"));
			params.add(new BasicNameValuePair("Passwd", "t1mbuick"));
			params.add(new BasicNameValuePair("service", "ac2dm"));
			params.add(new BasicNameValuePair("source", "Proximate-StreetPin-2"));

			HttpPost post = new HttpPost(uri);
			UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, "UTF-8");
			post.setEntity(entity);
			res = client.execute(post);
		} catch (Exception e) {

			log("Error obtaining the Google C2DM Client Login token.", e);
			throw new ShineException(e);
		}

		log("response=" + res);
		if (res != null) {
			log("Response status code = " + res.getStatusLine().getStatusCode());
			log("Response status      = " + res.getStatusLine().getReasonPhrase());

			HttpEntity sdf = res.getEntity();

			try {
				BufferedReader reader = new BufferedReader(new InputStreamReader(
						sdf.getContent()));
				String clientAuthToken = null;
				for (int i = 0; i < 3; i++) {
					String[] split = reader.readLine().split("=");
					if ("Auth".equals(split[0])) {
						// Finally get the authentication token
						clientAuthToken = split[1];
						log("clientAuthToken = " + clientAuthToken);
						break;
					}
				}
				if (clientAuthToken == null) {
					throw new ShineException(GeneralError.SYSTEM_ERROR, "clientAuth is null");

				}
				auth = clientAuthToken.trim();
			} catch (IllegalStateException e) {
				throw new ShineException(e);
			} catch (IOException e) {
				throw new ShineException(e);
			}

		}
	}

	private static void log(String string, Exception e) {
		System.out.println(string);
		e.printStackTrace();
	}

	private static void log(String string) {
		System.out.println(string);
		log.debug(string);

	}

//	public static void sendMessage(String userregid, int messageCode) {
//		try {
//			log("Started");
//
//			if (auth == null) {
//				getAUth();
//			}
//
//			// Send a sync message to this Android device.
//			StringBuilder postDataBuilder = new StringBuilder();
//			postDataBuilder.append("registration_id").append("=")
//					.append(userregid);
//
//			// if (delayWhileIdle) {
//			// postDataBuilder.append("&").append(PARAM_DELAY_WHILE_IDLE)
//			// .append("=1");
//			// }
//			postDataBuilder.append("&").append("collapse_key").append("=")
//					.append("0");
//
//			postDataBuilder.append("&").append("data.payload").append("=")
//					.append(messageCode);
////			postDataBuilder.append("&").append("logintoken").append("=")
////					.append(auth);
//
//			byte[] postData = postDataBuilder.toString().getBytes(UTF8);
//
//			// Hit the dm URL.
//
//			URL url = new URL("http://android.apis.google.com/c2dm/send");
//
//			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
////			conn.setHostnameVerifier(new CustomizedHostnameVerifier());
//			conn.setDoOutput(true);
//			conn.setUseCaches(false);
//			conn.setRequestMethod("POST");
//			conn.setRequestProperty("Content-Type",
//					"application/x-www-form-urlencoded;charset=UTF-8");
//			conn.setRequestProperty("Content-Length",
//					Integer.toString(postData.length));
//			conn.setRequestProperty("Authorization", "GoogleLogin auth=" + auth);
//
//			OutputStream out = conn.getOutputStream();
//			out.write(postData);
//			out.close();
//
//			int responseCode = conn.getResponseCode();
//
//			log("responseCode: " + String.valueOf(responseCode));
//			// Validate the response code
//			while (responseCode == 503) {
//
//				break;
//			}
//
//			if (responseCode == 401 || responseCode == 403) {
//				// The token is too old - return false to retry later, will
//				// fetch the token
//				// from DB. This happens if the password is changed or token
//				// expires. Either admin
//				// is updating the token, or Update-Client-Auth was received by
//				// another server,
//				// and next retry will get the good one from database.
//				log("Unauthorized");
//			}
//
//			// Check for updated token header
//			String updatedAuthToken = conn.getHeaderField("Update-Client-Auth");
//			if (updatedAuthToken != null && !auth.equals(updatedAuthToken)) {
//				log("C2DM" +
//						"Got updated auth token from datamessaging servers: "
//						+ updatedAuthToken);
//			}
//			InputStream is = conn.getInputStream();
//			if (is == null) {
//				return;
//			}
//
//			String responseLine = new BufferedReader(new InputStreamReader(
//					is)).readLine();
//
//			// NOTE: You *MUST* use exponential backoff if you receive a 503
//			// response code.
//			// Since App Engine's task queue mechanism automatically does this
//			// for tasks that
//			// return non-success error codes, this is not explicitly
//			// implemented here.
//			// If we weren't using App Engine, we'd need to manually implement
//			// this.
//			if (responseLine == null || responseLine.equals("")) {
//				log("C2DM" + "Got " + responseCode
//						+ " response from Google AC2DM endpoint.");
//				throw new IOException(
//						"Got empty response from Google AC2DM endpoint.");
//			}
//			log("responseLine: " + responseLine);
//			String[] responseParts = responseLine.split("=", 2);
//			if (responseParts.length != 2) {
//				log("C2DM" + "Invalid message from google: " + responseCode
//						+ " " + responseLine);
//				throw new IOException("Invalid response from Google "
//						+ responseCode + " " + responseLine);
//			}
//
//			if (responseParts[0].equals("id")) {
//				log("Successfully sent data message to device: "
//						+ responseLine);
//			}
//
//			if (responseParts[0].equals("Error")) {
//				String err = responseParts[1];
//				log("Got error response from Google datamessaging endpoint: "
//						+ err);
//				// No retry.
//				throw new IOException(err);
//			}
//		} catch (Exception e) {
//			throw new RuntimeException(e);
//		}
//	}
//
//	public static void sendMessage2(String userregid, int messageCode) throws ShineException {
//
//		if (auth == null) {
//			getAUth();
//		}
//
//		// Create a new HttpClient and Post Header 
//		HttpClient httpclient = new DefaultHttpClient();
//		HttpPost httppost = new HttpPost("http://android.apis.google.com/c2dm/send");
//
//		//Your Authorization Token
//		httppost.addHeader(new BasicHeader("Authorization", "GoogleLogin auth=" + auth));
////		httppost.addHeader(new BasicHeader("Content-Type", "application/x-www-form-urlencoded"));
//
//		// Add your data 
//		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(2);
//		nameValuePairs.add(new BasicNameValuePair("registration_id", userregid));
//		nameValuePairs.add(new BasicNameValuePair("collapse_key", "TEST"));
//
//		//.<message> is the key and Message is "Push Contact"
//		nameValuePairs.add(new BasicNameValuePair("data.message", "" + messageCode));
//		try {
//			httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs, "UTF-8"));
//		} catch (UnsupportedEncodingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		// Execute HTTP Post Request 
//		System.out.println("Executing  sendMessage");
//		HttpResponse response = getPostResponse(httpclient, httppost);
//		StatusLine statusLine = response.getStatusLine();
//		log(statusLine.toString());
//
//		// NOTE: You *MUST* use exponential backoff if you receive a 503
//		// response code.
//
//		int attempt = 0;
//		w: while (statusLine.getStatusCode() == 503) {
//			int sleep = 0;
//			switch (attempt) {
//			case 0:
//				sleep = 1;
//				break;
//			case 1:
//				sleep = 3;
//				break;
//			case 2:
//				sleep = 7;
//				break;
//			default:
//				throw new ShineException("c2dm max tries exceeded 503 received");
//			}
//			try {
//				Thread.sleep(sleep);
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//			response = getPostResponse(httpclient, httppost);
//			statusLine = response.getStatusLine();
//		}
//
//		if (statusLine.getStatusCode() == 401 || statusLine.getStatusCode() == 403) {
//			throw new ShineException("c2dm Unauthorised" + statusLine);
//		}
//
//		if (statusLine.getStatusCode() == 200) {
//			//all good
//			//TODO: get new auth if available
//
//			Header updatedAuthToken = response.getFirstHeader("Update-Client-Auth");
//			if (updatedAuthToken != null && !auth.equals(updatedAuthToken.getValue())) {
//				log("C2DM Got updated auth token from datamessaging servers: "
//						+ updatedAuthToken);
//				auth = updatedAuthToken.getValue();
//			}
//
//			if (response.getEntity() != null) {
//				try {
//					System.out.println(EntityUtils.toString(response.getEntity()));
//				} catch (ParseException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				} catch (IOException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
//		} else {
//			throw new ShineException("c2dm failed" + statusLine);
//		}
//
//	}

	private static HttpResponse getPostResponse(HttpClient httpclient, HttpPost httppost) throws ShineException {
		try {
			HttpResponse response = httpclient.execute(httppost);
			return response;
		} catch (ClientProtocolException e) {
			throw new ShineException(e);
		} catch (IOException e) {
			throw new ShineException(e);
		}
	}

	public static void sendMessage3(String deviceRegistrationID, char messageCode) throws ShineException,
			IOException {
		sendMessage3(deviceRegistrationID, "" + messageCode, null);
	}

//	public static void sendMessage3(String deviceRegistrationID, String subject, String message, Integer intX)
//			throws ShineException,
//			IOException {
//		String payload = subject + "##" + message;
//		sendMessage3(deviceRegistrationID, payload, intX);
//	}

	public static boolean sendMessage3(String deviceRegistrationID, String message, Integer intX)
			throws ShineException,
			IOException {
		if (auth == null) {
			getAUth();
		}

		URL url = new URL("http://android.apis.google.com/c2dm/send");
		HttpURLConnection request = (HttpURLConnection) url.openConnection();
//		request.setHostnameVerifier(new CustomizedHostnameVerifier());

		request.setDoOutput(true);
		request.setUseCaches(true);
		StringBuilder buf = new StringBuilder();
		buf.append("registration_id").append("=").append((URLEncoder.encode(deviceRegistrationID, UTF8)));
		buf.append("&collapse_key").append("=").append((URLEncoder.encode("0", UTF8)));
		buf.append("&data.message").append("=").append((URLEncoder.encode(message, UTF8)));
		if (intX != null) {
			buf.append("&data.intextra").append("=").append((URLEncoder.encode(intX.toString(), UTF8)));
		}
		request.setRequestMethod("POST");
		String urlStr = buf.toString();
		log(urlStr);
		request.setRequestProperty("Authorization", "GoogleLogin auth=" + auth);
		OutputStreamWriter post = new OutputStreamWriter(request.getOutputStream());
		post.write(urlStr);
		post.flush();
		int responseCode = request.getResponseCode();
		log("responseCode: " + String.valueOf(responseCode));
		// Validate the response code
		if (responseCode == 503) {
			//TODO: backoff

		}

		if (responseCode == 401 || responseCode == 403) {
			// The token is too old - return false to retry later, will fetch the token from DB. This happens if the password is changed or token
			// expires. Either admin is updating the token, or Update-Client-Auth was received by
			// another server, and next retry will get the good one from database.
			log(" c2dm notifier received Unauthorized" + responseCode);
			return false;
		}

		// Check for updated token header
		String updatedAuthToken = request.getHeaderField("Update-Client-Auth");
		if (updatedAuthToken != null && !auth.equals(updatedAuthToken)) {
			log("C2DM Got updated auth token from datamessaging servers: " + updatedAuthToken);
			auth = updatedAuthToken;

		}
		InputStream is = request.getInputStream();
		if (is == null) {
			log("Input stream returned from c2dm is null");
			return false;
		}

		String responseLine = new BufferedReader(new InputStreamReader(
				is)).readLine();

		// NOTE: You *MUST* use exponential backoff if you receive a 503
		// response code.
		// Since App Engine's task queue mechanism automatically does this
		// for tasks that
		// return non-success error codes, this is not explicitly
		// implemented here.
		// If we weren't using App Engine, we'd need to manually implement
		// this.
		if (responseLine == null || responseLine.equals("")) {
			log("C2DM" + "Got " + responseCode
					+ " response from Google AC2DM endpoint.");
			throw new IOException(
					"Got empty response from Google AC2DM endpoint.");
		}
		log("responseLine: " + responseLine);
		String[] responseParts = responseLine.split("=", 2);
		if (responseParts.length != 2) {
			log("C2DM Invalid message from google: " + responseCode
					+ " " + responseLine);
			throw new IOException("Invalid response from Google "
					+ responseCode + " " + responseLine);
		}

		if (responseParts[0].equals("id")) {
			log("Successfully sent data message to device: "
					+ responseLine);
			return true;
		}

		if (responseParts[0].equals("Error")) {
			String err = responseParts[1];
			log("Got error response from Google datamessaging endpoint: "
					+ err);
			// No retry.
			throw new IOException(err);
		}
		return false;
	}

	private static class CustomizedHostnameVerifier implements HostnameVerifier {
		public boolean verify(String hostname, SSLSession session) {
			return true;
		}
	}

}
