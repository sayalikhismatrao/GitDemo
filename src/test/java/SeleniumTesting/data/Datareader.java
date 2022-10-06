package SeleniumTesting.data;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Datareader {

	public static List<HashMap<String, String>> getJsonData() throws IOException {
		// read json to string
		String jsonContent = FileUtils.readFileToString(new File(System.getProperty("user.dir")+"\\src\\test\\java\\SeleniumTesting\\data\\purchaseOrder.json"),StandardCharsets.UTF_8);
		// convert string to hashmap Jackson Databind --> it is a framework which will help to convert string to hashmap
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data= mapper.readValue(jsonContent, new TypeReference<List<HashMap<String, String>>>(){});
		return data;

	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}





/*	method("purchaseOrder.json");

	System.out.println(method(("user.dir")+"\\src\\test\\java\\SeleniumTesting\\data\\purchaseOrder.json"));

	private static String method(String filePath)
    {

        StringBuilder builder = new StringBuilder();
 
        try (BufferedReader buffer = new BufferedReader(
                 new FileReader(filePath))) {
 
            String str;
 
            while ((str = buffer.readLine()) != null) {
 
                builder.append(str).append("\n");
            }
        }
 
        catch (IOException e) {
    
            e.printStackTrace();
        }
        
        return builder.toString();
    }*/

