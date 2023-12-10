package com.scope.utils;

import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.protocol.http.HttpService;
import org.web3j.tx.gas.DefaultGasProvider;

import java.math.BigInteger;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * Created with IntelliJ IDEA.
 *
 * @author garen
 * Date: 2023/12/8 16:33
 * Description: RevokeContractUtils
 */

public class RevokeContractUtils {

    public static String revokeContract(String walletId) throws ExecutionException, InterruptedException {
        // BSC测试网络节点URL
        String bscTestnetUrl = "https://data-seed-prebsc-1-s1.binance.org:8545";
//        String bscTestnetUrl = "https://bsc-testnet.s.chainbase.online/v1/2RVR8xPwOjamF188aNQnSp3PxrA";

        // 合约地址和ABI
        String contractAddress = "0xeADF56A25CdEddD11E5F98b06C9B4Be2BC203A96";

        // 你的私钥
        String privateKey = "86f9b0c8e47a67bd15c57bcd9b26b5ffe02e1f2399288e9c80b93bf00df4bf8d";

        // 连接到BSC测试网络节点
        Web3j web3 = Web3j.build(new HttpService(bscTestnetUrl));

        // 加载合约ABI和地址
//        MySmartContract contract = MySmartContract.load(contractAddress, web3, getCredentials(privateKey), new DefaultGasProvider());
        MySmartContract contract = MySmartContract.load(contractAddress, web3, getCredentials(privateKey), new BigInteger("5000000000"), new BigInteger("9000000"));
        byte[] byteValue = walletId.getBytes();
        byte[] byteValueLen32 = new byte[32];
        System.arraycopy(byteValue, 0, byteValueLen32, 0, byteValue.length);
        Bytes32 bytes32 = new Bytes32(byteValueLen32);

        RemoteFunctionCall<TransactionReceipt> transactionReceiptRemoteFunctionCall = contract.emitRevokeLog(bytes32.getValue());
        CompletableFuture<TransactionReceipt> transactionReceiptCompletableFuture = transactionReceiptRemoteFunctionCall.sendAsync();
        TransactionReceipt transactionReceipt = transactionReceiptCompletableFuture.get();
        System.out.println(transactionReceipt);
        return transactionReceipt.getTransactionHash();
    }

    // 根据私钥获取凭证
    private static Credentials getCredentials(String privateKey) {
        return Credentials.create(privateKey);
    }
}