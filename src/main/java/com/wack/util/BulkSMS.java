package com.wack.util;


import java.net.*;
import java.io.*;
import java.util.Base64;
/**
 * This is used to send SMS using Bulk SMS
 * 
 * @author pbatanado
 *
 */
public class BulkSMS {

    static public void main(String[] args) throws Exception {

        // This URL is used for sending messages
        String myURI = "https://api.bulksms.com/v1/messages";

        // change these values to match your own account
        String myUsername = "softenza";
        String myPassword = "Softenza123";

        // the details of the message we want to send
        String myData = "{from: \"16783145397\",to: \"14704370515\", encoding: \"UNICODE\", body: \"Je ne sais pas?\"}";

        // if your message does not contain unicode, the "encoding" is not required:
        // String myData = "{to: \"1111111\", body: \"Hello Mr. Smith!\"}";

        // build the request based on the supplied settings
        URL url = new URL(myURI);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.setDoOutput(true);

        // supply the credentials
        String authStr = myUsername + ":" + myPassword;
        String authEncoded = Base64.getEncoder().encodeToString(authStr.getBytes());
        request.setRequestProperty("Authorization", "Basic " + authEncoded);

        // we want to use HTTP POST
        request.setRequestMethod("POST");
        request.setRequestProperty( "Content-Type", "application/json");

        // write the data to the request
        OutputStreamWriter out = new OutputStreamWriter(request.getOutputStream());
        out.write(myData);
        out.close();

        // try ... catch to handle errors nicely
        try {
          // make the call to the API
          InputStream response = request.getInputStream();
          BufferedReader in = new BufferedReader(new InputStreamReader(response));
          String replyText;
          while ((replyText = in.readLine()) != null) {
            System.out.println(replyText);
          }
          in.close();
        } catch (IOException ex) {
          System.out.println("An error occurred:" + ex.getMessage());
          BufferedReader in = new BufferedReader(new InputStreamReader(request.getErrorStream()));
          // print the detail that comes with the error
          String replyText;
          while ((replyText = in.readLine()) != null) {
            System.out.println(replyText);
          }
          in.close();
        }
        request.disconnect();
      }
    
	static public String stringToHex(String s) {
		char[] chars = s.toCharArray();
		String next;
		StringBuffer output = new StringBuffer();
		for (int i = 0; i < chars.length; i++) {
			next = Integer.toHexString((int) chars[i]);
			// Unfortunately, toHexString doesn't pad with zeroes, so we have
			// to.
			for (int j = 0; j < (4 - next.length()); j++) {
				output.append("0");
			}
			output.append(next);
		}
		return output.toString();
	}

	static public String sendSMS(String theUrl, String userName, String password, String message, String phone) {
		String retVal = "";
		try {
			// cannot send more than 160, hence truncating
			message = Utils.truncateString(message, 160);
			// Construct data
			String data = "";
			data += "username=" + URLEncoder.encode(userName, "ISO-8859-1");
			data += "&password=" + URLEncoder.encode(password, "ISO-8859-1");
			data += "&message=" + URLEncoder.encode(message, "ISO-8859-1");
			data += "&want_report=1";
			data += "&msisdn=" + phone;
			URL url = new URL(theUrl);

			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
			wr.write(data);
			wr.flush();

			// Get the response
			BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line;
			StringBuffer sb = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				// Print the response output...
				sb.append(line);
			}

			retVal = sb.toString();
			wr.close();
			rd.close();

		} catch (Exception e) {
			e.printStackTrace();
			retVal = e.getMessage();
		}

		return retVal;
	}
}
