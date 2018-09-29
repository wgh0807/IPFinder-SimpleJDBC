package com.wgh.util;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class IPHandler {
    private static BufferedReader br ;
    private static File inputFile;
    private static String fileUrl = "/Users/www1/Desktop/ip.txt";


    //读取文件
    public static void readFile(){
        String url ="jdbc:mysql:///?user=java&password=java&useUnicode=true&characterEncoding=UTF-8";
        String sql = "insert into javaee.ipfinder(startip,endip,param) value(?,?,?)";
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            new com.mysql.jdbc.Driver();
            connection = DriverManager.getConnection(url);
            connection.setAutoCommit(false);
            preparedStatement = connection.prepareStatement(sql);


            inputFile = new File(fileUrl);
            br = new BufferedReader(new FileReader(inputFile));
            String input =null;
            while((input=br.readLine())!=null){
                String res[]=new String[]{"","",""};
                String[] strs = input.split(" ");
                int num=0;
                for (String str:strs) {
                    if(str.length()>0){
                        if(num==0){
                            res[num]=str;
                            num++;
                        }else if(num==1){
                            res[num]=str;
                            num++;
                        }else{
                            res[num]+=" "+str;
                        }
                    }
                }

                System.out.println("起始IP:" + res[0] + "；转换后：" + ipTrim(res[0]));
                System.out.println("结束IP:" + res[1] + "；转换后：" + ipTrim(res[1]));
                System.out.println("IP描述:"+res[2]);


                // TODO: 2018/9/29 连接数据库并且插入ip数据
                preparedStatement.setInt(1, ipTrim(res[0]));
                preparedStatement.setInt(2, ipTrim(res[1]));
                preparedStatement.setString(3, res[2]);

                preparedStatement.executeUpdate();
            }

            connection.commit();
            System.out.println("插入成功!");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * @param ip IPv4 address
     * @apiNote turn ipv4 address to decimal
     * @return The ip address of the decimal
     */
    public static int ipTrim(String ip){
        String ipPart[] = ip.split("\\.");
//        System.out.println(ipPart[0]);
//        System.out.println(ipPart[1]);
//        System.out.println(ipPart[2]);
//        System.out.println(ipPart[2]);
        Integer p1 = Integer.parseInt(ipPart[0]);
        Integer p2 = Integer.parseInt(ipPart[1]);
        Integer p3 = Integer.parseInt(ipPart[2]);
        Integer p4 = Integer.parseInt(ipPart[3]);
//        int sum =(((p1*256)+p2)*256+p3)*256+p4;
        return (((p1*256)+p2)*256+p3)*256+p4;
    }




    public static void main(String args[]){
        long begin = System.currentTimeMillis();
        readFile();
        System.out.println(System.currentTimeMillis()-begin);
    }

}
