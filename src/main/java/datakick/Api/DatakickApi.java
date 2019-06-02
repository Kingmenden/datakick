package datakick.Api;

import com.google.gson.Gson;
import datakick.Models.DatakickProduct;
import datakick.Util.UtilBase64Image;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

public class DatakickApi {
    String BASE_URL = "https://www.datakick.org/api/items";
    String USER_AGENT = "Mozilla/5.0";
    String CONTENT_TYPE = "multipart/form-data";

    public DatakickProduct lookupDatakickItem(String upc) throws Exception {
        URL url = new URL(BASE_URL + "/" + upc);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //set request method to GET
        con.setRequestMethod("GET");

        //add request header
        con.setRequestProperty("User-Agent", USER_AGENT);

        return getConnectionResponse(con);
    }

    public DatakickProduct addDatakickItem(String upc, String... values) throws Exception {
        //set the parameters for the PUT request
        String urlParameters = createParameters(values);
        URL url = new URL(BASE_URL + "/" + upc + "?" + urlParameters);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //set request method to PUT
        con.setRequestMethod("PUT");

        //add request headers
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        // getConnection response in the form of DatakickProduct
        return getConnectionResponse(con);
    }

    public DatakickProduct[] getListOfItems() throws Exception {
        URL url = new URL(BASE_URL);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //set request method to GET
        con.setRequestMethod("GET");

        //add request headers
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");

        int responseCode = con.getResponseCode();

        return getConnectionResponseObjects(con);
    }

    public DatakickProduct[] queryForListOfItems(String query) throws Exception {
        query = createQuery(query);
        URL url = new URL(BASE_URL + "?query=" + query);
        HttpURLConnection con = (HttpURLConnection) url.openConnection();

        //set request method to GET
        con.setRequestMethod("GET");

        //add request headers
        con.setRequestProperty("User-Agent", USER_AGENT);

        int responseCode = con.getResponseCode();

       return getConnectionResponseObjects(con);
    }

//    public DatakickProduct addImage(String upc, String imagePath) throws Exception{
////        String data = UtilBase64Image.encoder(imagePath);
//        byte[] bos = UtilBase64Image.toByteArray(imagePath);
//        URL url = new URL(BASE_URL + "/" + upc + "/images");
//
//        HttpURLConnection con = (HttpURLConnection) url.openConnection();
//
//        //set request method to GET
//        con.setRequestMethod("POST");
//
//        //add request headers
//        con.setRequestProperty("User-Agent", USER_AGENT);
//        con.setRequestProperty("Content-Type", CONTENT_TYPE);
//        con.setDoOutput(true);
//        OutputStream os = con.getOutputStream();
//        ByteArrayOutputStream byteOutStream = new ByteArrayOutputStream();
////        OutputStreamWriter osw = new OutputStreamWriter(os, StandardCharsets.);
//        byteOutStream = byteOutStream.writeTo(os);
//        osw.write(String.valueOf(byteOutStream));
//        osw.flush();
//        osw.close();
//        os.close();
//        con.connect();
//        int responseCode = con.getResponseCode();
//        return getConnectionResponse(con);
//    }

    private DatakickProduct getConnectionResponse(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();

        if (responseCode == 200) {
            String responseString = makeCall(con);
            Gson gson = new Gson();
            DatakickProduct obj = gson.fromJson(responseString, DatakickProduct.class);

            return obj;
        }else {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }
    }

    private DatakickProduct[] getConnectionResponseObjects(HttpURLConnection con) throws IOException {
        int responseCode = con.getResponseCode();

        if (responseCode == 200) {
            String responseString = makeCall(con);
            Gson gson = new Gson();
            DatakickProduct[] obj = gson.fromJson(responseString, DatakickProduct[].class);

            return obj;
        }else {
            throw new RuntimeException("HttpResponseCode: " + responseCode);
        }
    }

    public String makeCall(HttpURLConnection con) throws IOException{
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String responseString = response.toString();
        return responseString;
    }

    public String createParameters(String... values){
        String args = "";
        for(String value: values){
            args = args + value + "&";
        }
        args = args.substring(0, args.length() - 1);
        return args;
    }

    public String createQuery(String query){
        String newQuery = " ";
        if(query.contains(" ")){
            newQuery = query.replace(" ", "+");
        }else{
            return query;
        }
        return newQuery;
    }
}
