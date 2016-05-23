package springbook.learningtest.template;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Calculator {
	public Integer calcSum(String filepath) throws IOException{
		LineCallBack<Integer> sumCallback = new LineCallBack<Integer>(){
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value + Integer.valueOf(line) ;
			}
		};
		return lineReadTemplate(filepath,sumCallback, 0);
	}
	public Integer calcMultiply(String filepath) throws IOException{
		LineCallBack<Integer> multiplyCallback = new LineCallBack<Integer>(){
			@Override
			public Integer doSomethingWithLine(String line, Integer value) {
				return value * Integer.valueOf(line) ;
			}
		};
		return lineReadTemplate(filepath,multiplyCallback, 1);
	}
	public String concatenate(String filepath) throws IOException{
		LineCallBack<String> concatenateCallback = new LineCallBack<String>(){
			@Override
			public String doSomethingWithLine(String line, String value) {
				return value + line ;
			}
		};
		return lineReadTemplate(filepath,concatenateCallback, "");
	}
	
	public <T> T lineReadTemplate(String filepath, LineCallBack<T> callback, T initVal) throws IOException{
		BufferedReader br = null;
		try{
			br =  new BufferedReader(new FileReader(filepath));
			T res = initVal;
			String line = null;
			while((line = br.readLine())!=null){
				res = callback.doSomethingWithLine(line, res);
			}
			return res;
		}catch(IOException e){
			System.out.println(e.getMessage());
			throw e;
		}finally{
			if(br != null){ 
				try{br.close();}
				catch(IOException e){System.out.println(e.getMessage());}
			}
		}
	}
	
	public Integer fileReadTemplate(String filepath, BufferedReaderCallback callback) throws IOException{
		BufferedReader br = null;
		try{
			br =  new BufferedReader(new FileReader(filepath));
			Integer ret = callback.doSomethingWithReader(br);
			return ret;
		}catch(IOException e){
			System.out.println(e.getMessage());
			throw e;
		}finally{
			if(br != null){ 
				try{br.close();}
				catch(IOException e){System.out.println(e.getMessage());}
			}
		}
	}
	
	public Integer calcSum_old(String filePath) throws IOException{
		BufferedReader br = new BufferedReader(new FileReader(filePath));
		Integer sum = 0;
		String line = null;
		while((line = br.readLine())!=null){
			sum += Integer.valueOf(line);
		}
		br.close();
		return sum;
	}
}
