package Properties;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class SetProperties {
	
	public static Properties getPropertiesFile() {
		String path = System.getProperty("user.dir")+File.separator+"src"+File.separator+"test"+File.separator+"resources"+File.separator+"Properties"+File.separator+"data1.properties";
		
		File src = new File(path);
		
		Properties prop = new Properties();
		
		try {
			FileInputStream fin = new FileInputStream(src);
			prop.load(fin);
			}
			catch (IOException e) {
				e.printStackTrace();
			}
			return prop;
	}
}
