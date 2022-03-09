package org.json;

import java.io.*;
import java.util.Iterator;

public class Milestone1 {
    public static JSONObject saveJSON(String filename) throws IOException {
        InputStream in = Milestone1.class.getResourceAsStream(filename);
        String xml = org.apache.commons.io.IOUtils.toString(in);
        JSONObject xmlJSONObj = XML.toJSONObject(xml);
        return xmlJSONObj;

    }

    public static JSONArray performJSONArray(JSONArray inputArray) throws Exception {
        JSONArray resultArray = new JSONArray();
        for (int i = 0; i < inputArray.length(); i++) {
            if (inputArray.get(i) instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) inputArray.get(i);
                resultArray.put(i, performJSONObject(jsonObject));
            } else if (inputArray.get(i) instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) inputArray.get(i);
                resultArray.put(i, performJSONArray(jsonArray));
            } else {
                resultArray.put(i, inputArray.get(i));
            }
        }
        return resultArray;
    }

    public static JSONObject performJSONObject(JSONObject inputObject) throws Exception {
        JSONObject resultObject = new JSONObject();
        Iterator iterator = inputObject.keys();
        while (iterator.hasNext()) {
            String key = (String) iterator.next();
            if (inputObject.get(key) instanceof JSONObject) {
                JSONObject jsonObject = (JSONObject) inputObject.get(key);
                resultObject.put("swe262_"+key, performJSONObject(jsonObject));
            } else if (inputObject.get(key) instanceof JSONArray) {
                JSONArray jsonArray = (JSONArray) inputObject.get(key);
                resultObject.put("swe262_"+key, performJSONArray(jsonArray));
            } else {
                resultObject.put("swe262_"+key, inputObject.get(key));
            }
        }
        return resultObject;
    }

    public static void main(String[] args) throws Exception {
        //1
        JSONObject xmlJSONObj = saveJSON(args[0]);
        File file = new File("books.json");
        FileWriter fw = new FileWriter(file.getAbsoluteFile());
        BufferedWriter bw = new BufferedWriter(fw);
        xmlJSONObj.write(bw);
        bw.close();
        //2
        JSONPointer jsonPointer = JSONPointer.builder()
                .append("catalog")
                .append("book")
                .append(1)
                .build();
        JSONObject smallobj = (JSONObject) xmlJSONObj.query(jsonPointer);
        file = new File("small_book.json");
        fw = new FileWriter(file.getAbsoluteFile());
        bw = new BufferedWriter(fw);
        smallobj.write(bw);
        bw.close();

        //3
        if (xmlJSONObj.has(args[1])){
            file = new File("key_book.json");
            fw = new FileWriter(file.getAbsoluteFile());
            bw = new BufferedWriter(fw);
            smallobj.write(bw);
            bw.close();
        }

        //4
        JSONObject newxmlJSONObj = performJSONObject(xmlJSONObj);
        file = new File("newkey_book.json");
        fw = new FileWriter(file.getAbsoluteFile());
        bw = new BufferedWriter(fw);
        newxmlJSONObj.write(bw);
        bw.close();

        //5
        if (xmlJSONObj.has(args[2])) {
            xmlJSONObj.put(args[2], smallobj);
        }
        file = new File("replace_book.json");
        fw = new FileWriter(file.getAbsoluteFile());
        bw = new BufferedWriter(fw);
        xmlJSONObj.write(bw);
        bw.close();

    }
}