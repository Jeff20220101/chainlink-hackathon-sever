package com.scope.utils;

import org.web3j.codegen.SolidityFunctionWrapperGenerator;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/8 11:30
 * Description: GeneratorAbiAndBinUtils
 */
public class GeneratorAbiAndBinUtils {

    /**
     * 利用abi信息 与 bin信息 生成对应的abi,bin文件
     * @param abi 合约编译后的abi信息
     * @param bin 合约编译后的bin信息
     */
    public static void generateABIAndBIN(String abi,String bin,String abiFileName,String binFileName){

        File abiFile = new File("src/main/resources/"+abiFileName);
        File binFile = new File("src/main/resources/"+binFileName);
        BufferedOutputStream abiBos = null;
        BufferedOutputStream binBos = null;
        try{
            FileOutputStream abiFos = new FileOutputStream(abiFile);
            FileOutputStream binFos = new FileOutputStream(binFile);
            abiBos = new BufferedOutputStream(abiFos);
            binBos = new BufferedOutputStream(binFos);
            abiBos.write(abi.getBytes());
            abiBos.flush();
            binBos.write(bin.getBytes());
            binBos.flush();
        }catch (Exception e){
            e.printStackTrace();
//            throw new BlogException(201,"留言写入过程出现错误");
        }finally {
            if(abiBos != null){
                try{
                    abiBos.close();;
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
            if(binBos != null){
                try {
                    binBos.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static void generateClass(String abiFile,String binFile,String generateFile){
        String[] args = Arrays.asList(
                "-a",abiFile,
//                "-b",binFile,
                "-p","",
                "-o",generateFile
        ).toArray(new String[0]);
        Stream.of(args).forEach(System.out::println);
        SolidityFunctionWrapperGenerator.main(args);
    }
}