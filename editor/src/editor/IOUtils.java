package editor;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
/**
 * 	读写文件的工具类
 * 
 *
 */
public class IOUtils {
	public static void main(String[] args) {
		try {
			IOUtils.read("EditorFrame.java");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static String  read(String filename) throws FileNotFoundException{
		return read(new File(filename));
	}
	public static String read(File srcFile) throws FileNotFoundException{
		try(BufferedReader br = new BufferedReader(new  FileReader(srcFile));
				){
			StringBuilder sbf = new StringBuilder();
			String line = null;
			while((line=br.readLine())!=null){
				sbf.append(line);
				sbf.append("\n");
			}
			return sbf.toString();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	public static void write(String dest,String content){
		write(new File(dest), content);
	}
	public static void write(File dest,String content){
		try(FileWriter fw = new FileWriter(dest);){
			fw.write(content);
			fw.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

