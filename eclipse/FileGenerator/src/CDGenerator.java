import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CDGenerator implements IGenerator {

	private static final String DUMMY_JSON = "{\r\n" + 
			"  \"parent\": \"item/generated\",\r\n" + 
			"  \"textures\": {\r\n" + 
			"    \"layer0\": \"ac:items/%name%\"\r\n" + 
			"  }\r\n" + 
			"}";
	
	
	public void run() throws Exception {

		readStrings();
		generateModelFiles();
		generateLanguage();
		generateRegistry();
	}
	
	
	List<String> names = new ArrayList<String>();
	
	private void readStrings() {
		
		for(File f : new File("input/cd").listFiles()) {
			names.add(f.getName().replace(".png", ""));
		}
		
	}

	private void generateLanguage() {
		for(String name : names) {

			String tp = "item.ac." + name + ".name=" + capitaliseFirstLetter(name);
			System.out.println(tp);
		}
	}
	
	private void generateRegistry() {
		for(String name : names) {

			String tp = "public static _ACItemFood " + name + ";";
			
			if(name.contains("empty")) {
				tp = "public static _ACItem " + name + ";";
			}
			
			System.out.println(tp);
		}
		
		for(String name : names) {

			String tp = "event.getRegistry().register(" + name + " = new _ACItemFood(\"" + name + "\", EnumAnimation.DRINK));";
			if(name.contains("empty")) {
				tp = "event.getRegistry().register(" + name + " = new _ACItem(\"" + name + "\"));";
			}
			
			System.out.println(tp);
		}
	}

	private String capitaliseFirstLetter(String str) {
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	private void generateModelFiles() throws IOException {
		File folder = new File("output/cd2");
		folder.mkdirs();

		for(String name : names) {

			File jsonFile = new File(folder, name + ".json");
			jsonFile.createNewFile();
			BufferedWriter writer = new BufferedWriter(new FileWriter(jsonFile));
			writer.write(DUMMY_JSON.replace("%name%", name));
			writer.close();

		}
	}

}
