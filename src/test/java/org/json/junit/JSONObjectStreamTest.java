package org.json.junit;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class JSONObjectStreamTest {

    /**
     * test size of the stream
     */
    @Test
    public void testGettingJSONObjectSize_1() throws IOException {
        FileReader xmlReader = new FileReader("src/test/resources/aawikibooks-20211120-pages-logging.xml");
        JSONObject jsonObject= XML.toJSONObject(xmlReader);
        xmlReader.close();
        int expectedSize = 41062;
        long actualSize = jsonObject.toStream().count();
        assertEquals(expectedSize, actualSize);
    }

    /**
     * test size of the stream
     */
    @Test
    public void testGettingJSONObjectSize_2() throws IOException {
        FileReader xmlReader = new FileReader("src/test/resources/aawikibooks-20211101-abstract.xml");
        JSONObject jsonObject= XML.toJSONObject(xmlReader);
        xmlReader.close();
        int expectedSize = 4;
        long actualSize = jsonObject.toStream().count();
        assertEquals(expectedSize, actualSize);
    }

    /**
     * test size of the stream
     */
    @Test
    public void testGettingJSONObjectSize_3() throws IOException {
        FileReader xmlReader = new FileReader("src/test/resources/aawikibooks-20211120-stub-meta-history.xml");
        JSONObject jsonObject= XML.toJSONObject(xmlReader);
        xmlReader.close();
        int expectedSize = 1661;
        long actualSize = jsonObject.toStream().count();
        assertEquals(expectedSize, actualSize);
    }

    /**
     * test size of the stream
     */
    @Test
    public void testGettingJSONObjectSize_4() throws IOException {
        FileReader xmlReader = new FileReader("src/test/resources/abwiki-20211101-abstract.xml");
        JSONObject jsonObject= XML.toJSONObject(xmlReader);
        xmlReader.close();
        int expectedSize = 194241;
        long actualSize = jsonObject.toStream().count();
        assertEquals(expectedSize, actualSize);
    }

    /**
     * test size of the stream
     */
    @Test
    public void testGettingJSONObjectSize_5() throws IOException {
        FileReader xmlReader = new FileReader("src/test/resources/abwiki-20211101-pages-articles.xml");
        JSONObject jsonObject= XML.toJSONObject(xmlReader);
        xmlReader.close();
        int expectedSize = 274411;
        long actualSize = jsonObject.toStream().count();
        assertEquals(expectedSize, actualSize);
    }

    /**
     * test size of the stream
     */
    @Test
    public void testGettingJSONObjectSize_6() throws IOException {
        FileReader xmlReader = new FileReader("src/test/resources/abwiki-20211101-pages-articles-multistream.xml");
        JSONObject jsonObject= XML.toJSONObject(xmlReader);
        xmlReader.close();
        int expectedSize = 274411;
        long actualSize = jsonObject.toStream().count();
        assertEquals(expectedSize, actualSize);
    }
    /**
     * test size of the stream
     */
    @Test
    public void testGettingJSONObjectSize_7() throws IOException {
        FileReader xmlReader = new FileReader("src/test/resources/abwiki-20211101-pages-logging.xml");
        JSONObject jsonObject= XML.toJSONObject(xmlReader);
        xmlReader.close();
        int expectedSize = 485690;
        long actualSize = jsonObject.toStream().count();
        assertEquals(expectedSize, actualSize);
    }    /**
     * test size of the stream
     */
    @Test
    public void testGettingJSONObjectSize_8() throws IOException {
        FileReader xmlReader = new FileReader("src/test/resources/abwiki-20211101-pages-meta-current.xml");
        JSONObject jsonObject= XML.toJSONObject(xmlReader);
        xmlReader.close();
        int expectedSize = 302355;
        long actualSize = jsonObject.toStream().count();
        assertEquals(expectedSize, actualSize);
    }

    /**
     * test size of the stream
     */
    @Test
    public void testGettingJSONObjectSize_10() throws IOException {
        FileReader xmlReader = new FileReader("src/test/resources/aawikibooks-20211101-abstract.xml");
        JSONObject jsonObject= XML.toJSONObject(xmlReader);
        xmlReader.close();
        int expectedSize = 4;
        long actualSize = jsonObject.toStream().count();
        assertEquals(expectedSize, actualSize);
    }


    /**
     * 
     * test the  size of JSONArray
     */
    @Test
    public void testGettingJSONArraySize() throws IOException {
        FileReader xmlReader = new FileReader("src/test/resources/abwiki-20211101-abstract.xml");
        JSONObject jsonObject= XML.toJSONObject(xmlReader);
        xmlReader.close();
        JSONArray docArray = (JSONArray) (jsonObject.query("/feed/doc"));
        int expectedSize = docArray.length();
        List<Object> keys = jsonObject.toStream()
                .map(node -> new ArrayList<Object>(((HashMap) node).keySet()).get(0))
                .collect(Collectors.toList());
        String[] lastKeys = keys.get(keys.size()-1).toString().substring(1).split("/");
        int actualSize = Integer.valueOf(lastKeys[2]);
        assertEquals(expectedSize, actualSize);
    }
}
