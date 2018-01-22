package com.ejunhai.qutihuo.statistical.utils;

import com.google.gson.JsonObject;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    public static void writeJson2file(JSONObject jsonObject, String filePath){
        FileOutputStream fop = null;
        File file;
        try {
            file = new File(filePath);
            fop = new FileOutputStream(file);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            }
            // get the content in bytes
            byte[] contentInBytes = jsonObject.toString().getBytes();
            fop.write(contentInBytes);
            fop.flush();
            fop.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fop != null) {
                    fop.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String readJsonfile(String filePath) throws FileNotFoundException{
        StringBuilder sb = new StringBuilder();
        try {
            File file = new File(filePath);
            // 读取文件，并且以utf-8的形式写出去
            BufferedReader bufread;
            String read;
            bufread = new BufferedReader(new FileReader(file));
            while ((read = bufread.readLine()) != null) {
                sb.append(read);
            }
            bufread.close();
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return sb.toString();
    }

    public static double[][] fromJsonString(String input){
        JSONArray jsonMatrix = JSONArray.fromObject(input);
        int matrixSize = jsonMatrix.size();
        JSONArray jsonArray = (JSONArray) jsonMatrix.get(0);
        int arraySize = jsonArray.size();
        double[][] result = new double[matrixSize][arraySize];
        if(1==matrixSize){
            result[0]=fromJsonArray(jsonArray);
        }else{
            for(int i=0;i<matrixSize;i++){
                result[i]=fromJsonArray((JSONArray) jsonMatrix.get(i));
            }
        }
        return result;
    }

    private static double[] fromJsonArray(JSONArray jsonArray){
        int arraySize = jsonArray.size();
        double[] result = new double[arraySize];
        for(int index=0;index<arraySize;index++){
            String str = (String)jsonArray.get(index);
            if("".equals(str)){
                result[index]=0;
            }else {
                result[index]=Double.valueOf(str);
            }
        }
        return result;
    }


    public static void main(String[] args){
        String jsonStr = null;
        try{
            jsonStr = readJsonfile("hiveMetaData.json");
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        if(null != jsonStr){
            System.out.println(jsonStr);
        }
    }
}
