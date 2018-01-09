package com.founder.utils;

import com.founder.controller.HelloController;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @Description: 初始化敏感词库，将敏感词加入到HashMap中，构建DFA算法模型
 * @Project：test
 * @Author : chenming
 * @Date ： 2014年4月20日 下午2:27:06
 * @version 1.0
 */
public class SensitiveWordInit {

	private static final org.slf4j.Logger _log = LoggerFactory
			.getLogger(SensitiveWordInit.class);

	private String ENCODING = "GBK";    //字符编码
	@SuppressWarnings("rawtypes")
	public HashMap sensitiveWordMap;

	public SensitiveWordInit(){
		super();
	}

	/**
	 * @author chenming
	 * @date 2014年4月20日 下午2:28:32
	 * @version 1.0
	 */
	@SuppressWarnings("rawtypes")
	public Map initKeyWord(){
		try {
			//读取敏感词库
			Set<String> keyWordSet = readSensitiveWordFile();
			//将敏感词库加入到HashMap中
			addSensitiveWordToHashMap_1(keyWordSet);
			//spring获取application，然后application.setAttribute("sensitiveWordMap",sensitiveWordMap);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sensitiveWordMap;
	}

	/**
	 * 读取敏感词库，将敏感词放入HashSet中，构建一个DFA算法模型：<br>
	 * 中 = {
	 *      isEnd = 0
	 *      国 = {<br>
	 *      	 isEnd = 1
	 *           人 = {isEnd = 0
	 *                民 = {isEnd = 1}
	 *                }
	 *           男  = {
	 *           	   isEnd = 0
	 *           		人 = {
	 *           			 isEnd = 1
	 *           			}
	 *           	}
	 *           }
	 *      }
	 *  五 = {
	 *      isEnd = 0
	 *      星 = {
	 *      	isEnd = 0
	 *      	红 = {
	 *              isEnd = 0
	 *              旗 = {
	 *                   isEnd = 1
	 *                  }
	 *              }
	 *      	}
	 *      }
	 * @author chenming
	 * @date 2014年4月20日 下午3:04:20
	 * @param keyWordSet  敏感词库
	 * @version 1.0
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void addSensitiveWordToHashMap(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //关键字
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //转换成char型
				Object wordMap = nowMap.get(keyChar);       //获取
				if(wordMap != null){        //如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //不是最后一个
					nowMap.put(keyChar, newWorMap);
					//nowMap = newWorMap;
				}
				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //最后一个
				}
				 //System.out.println("封装敏感词库过程："+sensitiveWordMap);
			}
			//System.out.println("查看敏感词库数据:" + sensitiveWordMap);
		}
	}
	private void addSensitiveWordToHashMap_1(Set<String> keyWordSet) {
		sensitiveWordMap = new HashMap(keyWordSet.size());     //初始化敏感词容器，减少扩容操作
		String key = null;
		Map nowMap = null;
		Map<String, String> newWorMap = null;
		//迭代keyWordSet
		Iterator<String> iterator = keyWordSet.iterator();
		while(iterator.hasNext()){
			key = iterator.next();    //关键字
			nowMap = sensitiveWordMap;
			for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);
				Map baseMap = nowMap;
				for (int j = 0; j < i; j++) {
					char keyChar_j = key.charAt(j);
					baseMap=(Map) baseMap.get(keyChar_j);
				}
				HashMap hashMap=(HashMap) baseMap.get(keyChar);
				if(null!=hashMap){
					String bs = (String)hashMap.get("isEnd");
					if(i==key.length()-1){
						hashMap.put("isEnd", "1");     //不是最后一个
						baseMap.put(keyChar, hashMap);
					}
				}else{
					newWorMap = new HashMap<String,String>();
					if(i==key.length()-1){
						newWorMap.put("isEnd", "1");     //不是最后一个
					}else{
						newWorMap.put("isEnd", "0");     //是最后一个
					}
					baseMap.put(keyChar, newWorMap);
				}
			} 
			/*for(int i = 0 ; i < key.length() ; i++){
				char keyChar = key.charAt(i);       //转换成char型
				Object wordMap = nowMap.get(keyChar);       //获取
				if(wordMap != null){        //如果存在该key，直接赋值
					nowMap = (Map) wordMap;
				}else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
					newWorMap = new HashMap<String,String>();
					newWorMap.put("isEnd", "0");     //不是最后一个
					nowMap.put(keyChar, newWorMap);
					//nowMap = newWorMap;
				}
				if(i == key.length() - 1){
					nowMap.put("isEnd", "1");    //最后一个
				}
			}
			for(int i = 0 ; i < key.length() ; i++){
				if(i==0){
					char keyChar = key.charAt(i);
					Object wordMap = nowMap.get(keyChar); 
					if(wordMap != null){        //如果存在该key，直接赋值
						nowMap = (Map) wordMap;
					}else{     //不存在则，则构建一个map，同时将isEnd设置为0，因为他不是最后一个
						newWorMap = new HashMap<String,String>();
						if(i==key.length()-1){
							newWorMap.put("isEnd", "0");     //不是最后一个
						}else{
							newWorMap.put("isEnd", "1");     //是最后一个
						}
						nowMap.put(keyChar, newWorMap);
					}	
				}else{
					char keyChar = key.charAt(i);
					Map baseMap = null;
					for (int j = 0; j < i; j++) {
						if(baseMap==null){
							baseMap=nowMap;
						}else{
							char keyChar_j = key.charAt(j);
							baseMap=(Map) baseMap.get(keyChar_j);
						}
					}
					newWorMap = new HashMap<String,String>();
					if(i==key.length()-1){
						newWorMap.put("isEnd", "0");     //不是最后一个
					}else{
						newWorMap.put("isEnd", "1");     //是最后一个
					}
					baseMap.put(keyChar, newWorMap);
				}
			}*/ 
			
			
		}
	}

	/**
	 * 读取敏感词库中的内容，将内容添加到set集合中
	 * @author chenming
	 * @date 2014年4月20日 下午2:31:18
	 * @return
	 * @version 1.0
	 * @throws Exception
	 */
	@SuppressWarnings("resource")
	private Set<String> readSensitiveWordFile() throws Exception{
		Set<String> set = null;

		File file = new File(HelloController.SensitiveWordFilePath);    //读取文件
		InputStreamReader read = new InputStreamReader(new FileInputStream(file),"utf-8");
		try {
			if(file.isFile() && file.exists()){      //文件流是否存在
				set = new HashSet<String>();
				BufferedReader bufferedReader = new BufferedReader(read);
				String txt = null;
				while((txt = bufferedReader.readLine()) != null){    //读取文件，将文件内容放入到set中
					//System.out.println(txt);
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
}
