import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import javax.management.StringValueExp;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SendingRequest {

    //dailyPDF daily = new dailyPDF();
    public static void main(String args[]) throws IOException, Exception {
        //Setting up connection
        URL url = new URL("http://localhost:8080/gadaixadege_war/mainclass");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        //Get Response
        InputStream is = con.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is));
        StringBuffer resp = new StringBuffer();
        String line;
        while ((line = rd.readLine()) != null) {
            resp.append(line);
            //response.append('\r');
        }
        rd.close();
        String wholeRes = resp.toString();
        //System.out.println(wholeRes);
        JsonObject jsonObject = new JsonParser().parse(wholeRes).getAsJsonObject();
        System.out.println(jsonObject.get("command").getAsString());

        //Getting values from json
        String command = jsonObject.get("command").getAsString();
        String response = jsonObject.get("response").getAsString();
        JsonArray resps = new JsonArray();
        resps = jsonObject.getAsJsonArray("ReportBody");
        System.out.println(resps);
        System.out.println(resps.get(0));
        System.out.println(resps.size());

        //String[] respsArray = new String[];
        //JsonObject param1obj = (JsonObject) resps.get(0);
        //System.out.println(param1obj.get("param1").getAsString());
        //System.out.println("kk");

        String [][] valueArr = new String[resps.size()][7];
        if (resps != null) {
            for (int i=0;i<resps.size();i++){
                JsonObject paramobj = (JsonObject) resps.get(i);
                for (int j =0; j<7; j++) {
                    valueArr[i][j] = paramobj.get("param" + String.valueOf(j)).getAsString();
                }
            }
        }
        //System.out.println(valueArr.length);

        if (response.equals("ok")) {
            if (command.equals("DailyRapprt")) {
                System.out.println("printing");
                dailyPDF.printer(valueArr);
                //aq gamovidzaxeb daily reportis rameebs da gadavcem dalshe parametrebs
            } else if (command.equals("MonthlyReport")) {
                //aq gamovidzaxeb monthly reportis rameebs da gadavcem dalshe parametrebs
            } else {
                System.out.println("Error in command value");
            }
        } else if (response.equals("WrongDataSpan")) System.out.println("Error in DataSpan");
        else if (response.equals("WrongScope")) System.out.println("Error in Scope");
        else System.out.println("No Data for the entry");
    }
}




/*
        JSONParser parser = new JSONParser();
        Object obj = parser.parse(wholeRes);
        JSONObject jsonObject = (JSONObject) obj;
        String command = (String) jsonObject.get("command");
        System.out.println(command);
*/
/*
        JsonElement e = new JsonParser().parse(wholeRes);
        JsonObject resp = e.getAsJsonObject();
        String command = String.valueOf(resp.get("command"));
        System.out.println(command);
*/
        /*
        String[] WholeResArr = wholeRes.split(":", 3);
        String comm = WholeResArr[0];
        //System.out.println(comm);
        String resp = WholeResArr[1];
        String RepBody = WholeResArr[2];

         */



