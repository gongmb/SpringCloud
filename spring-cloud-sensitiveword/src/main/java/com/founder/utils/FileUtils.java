package com.founder.utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

public class FileUtils {
	public static String ENCODING = "GBK";



	
	public static Set<String> readSensitiveWordFile() throws Exception{
		Set<String> set = null;

		File file = new File("D:\\Test8\\SensitiveWord\\SensitiveWord_1.txt");    //读取文件
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),ENCODING);
		try {
			if(file.isFile() && file.exists()){      //文件流是否存在
				set = new HashSet<String>();
				BufferedReader bufferedReader = new BufferedReader(read);
				String txt = null;
				while((txt = bufferedReader.readLine()) != null){    //读取文件，将文件内容放入到set中
					System.out.println(txt);
					set.add(txt);
				}
			}
			else{         //不存在抛出异常信息
				throw new Exception("敏感词库文件不存在");
			}
		} catch (Exception e) {
			throw e;
		}finally{
			read.close();     //关闭文件流
		}
		return set;
	}
	
	public static void appenStringToTxt(String filePath, String data)
			throws IOException {
		
		File file = new File(filePath);

		if (!file.exists()) {
			file.createNewFile();
		}

		   FileWriter writer = new FileWriter(filePath, true);
           writer.write(data+"\r\n");
           writer.close();

	}


	private static SensitivewordFilter filter;
	public static SensitivewordFilter getobj(){
		if(filter==null){
			filter = new SensitivewordFilter();
		}
		return filter;

	}
}
