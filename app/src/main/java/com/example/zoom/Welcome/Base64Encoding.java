package com.example.zoom.Welcome;
import android.os.Build;

import androidx.annotation.RequiresApi;
import java.io.IOException;

import java.util.Base64;

public class Base64Encoding  {

    @RequiresApi(api = Build.VERSION_CODES.O)
    public static void main(String[] args) throws IOException, InterruptedException {
        // Customer ID
            final String customerKey = "4d4466c8cfb24d338785066f2a6c9584";
            // Customer secret
            final String customerSecret = "4a73f0c6609d42fa8bdef82140d250d0";

            // Concatenate customer key and customer secret and use base64 to encode the concatenated string
            String plainCredentials = customerKey + ":" + customerSecret;
            String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
            // Create authorization header
            String authorizationHeader = "Basic " + base64Credentials;

//            HttpClient client = HttpClient.newHttpClient();
//
//            // Create HTTP request object
//            HttpRequest request = HttpRequest.newBuilder()
//                    .uri(URI.create("https://api.agora.io/dev/v1/projects"))
//                    .GET()
//                    .header("Authorization", authorizationHeader)
//                    .header("Content-Type", "application/json")
//                    .build();
//            // Send HTTP request
//            HttpResponse<String> response = client.send(request,
//                    HttpResponse.BodyHandlers.ofString());
//
//            System.out.println(response.body());
        }
    }


//
//    @RequiresApi(api = Build.VERSION_CODES.O)
//    public static void main(String[] args) throws IOException, InterruptedException {
//
//        // Customer ID
//        final String customerKey = "4d4466c8cfb24d338785066f2a6c9584";
//        // Customer secret
//        final String customerSecret = "4a73f0c6609d42fa8bdef82140d250d0";
//
//        // Concatenate customer key and customer secret and use base64 to encode the concatenated string
//        String plainCredentials = customerKey + ":" + customerSecret;
//        String base64Credentials = new String(Base64.getEncoder().encode(plainCredentials.getBytes()));
//        // Create authorization header
//        String authorizationHeader = "Basic " + base64Credentials;
//
////        HttpClient client = HttpClient.newHttpClient();
////
////        HttpRequest request = HttpRequest.newBuilder()
////                .uri(URI.create("https://api.agora.io/dev/v1/projects"))
////                .GET()
////                .header("Authorization", authorizationHeader)
////                .header("Content-Type", "application/json")
////                .build();
////        // Send HTTP request
////        HttpResponse<String> response = client.send(request,
////                HttpResponse.BodyHandlers.ofString());
//
////        System.out.println(response.body());
//    }
//
//    @Override
//    protected Object doInBackground(Object[] objects) {
//
//        try {
//
//            //------------------>>
//            HttpGet httppost = new HttpGet("https://api.agora.io/v1/apps/<appid>/cloud_recording/resourceid/<resourceid>/sid/<sid>/mode/<mode>/query'");
//            HttpClient httpclient = new DefaultHttpClient();
//            HttpResponse response = httpclient.execute(httppost);
//
//            // StatusLine stat = response.getStatusLine();
//            int status = response.getStatusLine().getStatusCode();
//
//            if (status == 200) {
//                HttpEntity entity = response.getEntity();
//                String data = EntityUtils.toString(entity);
//                System.out.println(data+"ABRAR");
//
//
//                JSONObject jsono = new JSONObject(data);
//
//                return true;
//            }
//
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (JSONException e) {
//
//            e.printStackTrace();
//        }
//        return false;
//    }

