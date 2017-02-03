package com.azhen.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

/**
 * Created by azhen on 17-1-26.
 */
public class DatabaseUtil {
    private String user = "qcmr";
    private String password = "qcmr!@#";
    private String database = "qcmr2";
    private String filepath = "/home/azhen/qcmr.sql";
    private String ip = "localhost";
    private boolean isLinux = true;

    public DatabaseUtil() {

    }
    public DatabaseUtil(String user, String password, String database, String filepath, String ip) {
        this.user = user;
        this.password = password;
        this.database = database;
        this.filepath = filepath;
        this.ip = ip;
    }

    public void backupInSh() {
        StringBuilder sb = new StringBuilder("mysqldump -h ");
        sb.append(ip);
        sb.append(" -u'").append(user).append("'");
        sb.append(" -p'").append(password).append("'");
        sb.append("  --skip-lock-tables ");
        sb.append( database);
        sb.append(" > ").append(filepath);
        String sql = sb.toString();
        System.out.println(sql);
        try {
            Process process = null;
            if(isLinux) {
                process  = Runtime.getRuntime().exec(new String[] { "sh", "-c", sql });
            } else {
                process  = Runtime.getRuntime().exec(new String[] { "cmd", "/c", sql });
            }
            InputStreamReader inputStreamReader = new InputStreamReader(process.getErrorStream(), "utf8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                System.out.println(line);
            }
            int rtnVal = process.waitFor();
            if(0 == rtnVal) {
                System.out.printf("备份成功");
            } else {
                System.out.println("备份失败");
            }

        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void dropInSh() {
        StringBuilder sb = new StringBuilder("mysqladmin -h ");
        sb.append(ip);
        sb.append(" -u'").append(user).append("'");
        sb.append(" -p'").append(password).append("'");
        sb.append("  drop ");
        sb.append( database);
        String sql = sb.toString();
        System.out.println(sql);

        try {
            Process process = null;
            if(isLinux) {
                process  = Runtime.getRuntime().exec(new String[] { "sh", "-c", sql });
            } else {
                process  = Runtime.getRuntime().exec(new String[] { "cmd", "/c", sql });
            }
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(process.getOutputStream(),"utf-8");
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);
            bufferedWriter.write("y");
            bufferedWriter.flush();
            bufferedWriter.close();
            outputStreamWriter.close();

            InputStreamReader inputStreamReader = new InputStreamReader(process.getErrorStream(), "utf8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                System.out.println(line);
            }

            int rtnVal = process.waitFor();
            if(0 == rtnVal) {
                System.out.println("卸载数据库成功");
            } else {
                System.out.println("卸载数据库失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createInSh() {
        StringBuilder sb = new StringBuilder("mysqladmin -h ");
        sb.append(ip);
        sb.append(" -u'").append(user).append("'");
        sb.append(" -p'").append(password).append("'");
        sb.append("  create ");
        sb.append( database);
        String sql = sb.toString();
        System.out.println(sql);

        try {
            Process process = null;
            if(isLinux) {
                process  = Runtime.getRuntime().exec(new String[] { "sh", "-c", sql });
            } else {
                process  = Runtime.getRuntime().exec(new String[] { "cmd", "/c", sql });
            }
            InputStreamReader inputStreamReader = new InputStreamReader(process.getErrorStream(), "utf8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                System.out.println(line);
            }

            int rtnVal = process.waitFor();
            if(0 == rtnVal) {
                System.out.println("创建数据库成功");
            } else {
                System.out.println("创建数据库失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadInSh() {

        StringBuilder sb = new StringBuilder("mysql -h ");
        sb.append(ip);
        sb.append(" -u'").append(user).append("'");
        sb.append(" -p'").append(password).append("' ");
        sb.append( database).append(" < ").append(filepath);
        String sql = sb.toString();
        System.out.println(sql);
        try {
            Process process = null;
            if(isLinux) {
                process  = Runtime.getRuntime().exec(new String[] { "sh", "-c", sql });
            } else {
                process  = Runtime.getRuntime().exec(new String[] { "cmd", "/c", sql });
            }
            InputStreamReader inputStreamReader = new InputStreamReader(process.getErrorStream(), "utf-8");
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            while((line = bufferedReader.readLine())!= null){
                System.out.println(line);
            }

            int rtnVal = process.waitFor();
            if(0 == rtnVal) {
                System.out.println("数据已从 " + filepath + " 导入到数据库中");
            } else {
                System.out.println("恢复失败");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        DatabaseUtil backupDatabase = new DatabaseUtil();
        backupDatabase.loadInSh();
    }
}
