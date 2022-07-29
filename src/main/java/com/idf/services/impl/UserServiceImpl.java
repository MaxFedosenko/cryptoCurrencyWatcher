package com.idf.services.impl;


import com.idf.entities.CryptoCurrency;
import com.idf.entities.User;
import com.idf.repositories.CryptoRepository;
import com.idf.repositories.UserRepository;
import com.idf.services.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CryptoRepository cryptoRepository;
    
    public User notify(String userName, String symbol) {
        CryptoCurrency bySymbol = cryptoRepository.findBySymbol(symbol);
        User user = userRepository.findByUserName(userName);
        if (user == null) {
            return userRepository.save(new User(null, userName, bySymbol, bySymbol.getPriceUsd()));
        }
        return user;
    }
    
    @Scheduled(fixedDelay = 5000)
    public void scheduledMessage() {
        List<User> users = userRepository.findAll();
        int size = users.size();
        double result;
        for (int i = 0; i < size; i++) {
            Double priceUsdInit = users.get(i).getPriceUsd();
            Double priceUsdChange = users.get(i).getCryptoCurrency().getPriceUsd();
            result = ((priceUsdChange - priceUsdInit) / priceUsdInit) * 100;
            String message = "%s цена на вашу криптовалюту %s выросла на %s%%";
            String userName = users.get(i).getUserName();
            String cryptoCurrency = users.get(i).getCryptoCurrency().getSymbol();
            String resultFormatted = String.format("%.2f", result);
            if (result > 1) {
                log.warn(String.format(message, userName, cryptoCurrency, resultFormatted));
            }
        }
    }
    /*2022-07-29 02:48:00.436 ERROR 10000 --- [   scheduling-1] o.s.s.s.TaskUtils$LoggingErrorHandler    : Unexpected error occurred in scheduled task

feign.RetryableException: Read timed out executing GET https://api.coinlore.net/api/ticker/?id=48543
	at feign.FeignException.errorExecuting(FeignException.java:268) ~[feign-core-11.8.jar:na]
	at feign.SynchronousMethodHandler.executeAndDecode(SynchronousMethodHandler.java:129) ~[feign-core-11.8.jar:na]
	at feign.SynchronousMethodHandler.invoke(SynchronousMethodHandler.java:89) ~[feign-core-11.8.jar:na]
	at feign.ReflectiveFeign$FeignInvocationHandler.invoke(ReflectiveFeign.java:100) ~[feign-core-11.8.jar:na]
	at jdk.proxy2/jdk.proxy2.$Proxy125.getById(Unknown Source) ~[na:na]
	at com.idf.services.impl.CryptoServiceImpl.scheduledUpdate(CryptoServiceImpl.java:42) ~[classes/:na]
	at jdk.internal.reflect.GeneratedMethodAccessor75.invoke(Unknown Source) ~[na:na]
	at java.base/jdk.internal.reflect.DelegatingMethodAccessorImpl.invoke(DelegatingMethodAccessorImpl.java:43) ~[na:na]
	at java.base/java.lang.reflect.Method.invoke(Method.java:567) ~[na:na]
	at org.springframework.scheduling.support.ScheduledMethodRunnable.run(ScheduledMethodRunnable.java:84) ~[spring-context-5.3.21.jar:5.3.21]
	at org.springframework.scheduling.support.DelegatingErrorHandlingRunnable.run(DelegatingErrorHandlingRunnable.java:54) ~[spring-context-5.3.21.jar:5.3.21]
	at org.springframework.scheduling.concurrent.ReschedulingRunnable.run(ReschedulingRunnable.java:95) ~[spring-context-5.3.21.jar:5.3.21]
	at java.base/java.util.concurrent.Executors$RunnableAdapter.call(Executors.java:515) ~[na:na]
	at java.base/java.util.concurrent.FutureTask.run(FutureTask.java:264) ~[na:na]
	at java.base/java.util.concurrent.ScheduledThreadPoolExecutor$ScheduledFutureTask.run(ScheduledThreadPoolExecutor.java:304) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor.runWorker(ThreadPoolExecutor.java:1130) ~[na:na]
	at java.base/java.util.concurrent.ThreadPoolExecutor$Worker.run(ThreadPoolExecutor.java:630) ~[na:na]
	at java.base/java.lang.Thread.run(Thread.java:831) ~[na:na]
Caused by: java.net.SocketTimeoutException: Read timed out
	at java.base/sun.nio.ch.NioSocketImpl.timedRead(NioSocketImpl.java:283) ~[na:na]
	at java.base/sun.nio.ch.NioSocketImpl.implRead(NioSocketImpl.java:309) ~[na:na]
	at java.base/sun.nio.ch.NioSocketImpl.read(NioSocketImpl.java:350) ~[na:na]
	at java.base/sun.nio.ch.NioSocketImpl$1.read(NioSocketImpl.java:803) ~[na:na]
	at java.base/java.net.Socket$SocketInputStream.read(Socket.java:976) ~[na:na]
	at java.base/sun.security.ssl.SSLSocketInputRecord.read(SSLSocketInputRecord.java:478) ~[na:na]
	at java.base/sun.security.ssl.SSLSocketInputRecord.readHeader(SSLSocketInputRecord.java:472) ~[na:na]
	at java.base/sun.security.ssl.SSLSocketInputRecord.bytesInCompletePacket(SSLSocketInputRecord.java:70) ~[na:na]
	at java.base/sun.security.ssl.SSLSocketImpl.readApplicationRecord(SSLSocketImpl.java:1454) ~[na:na]
	at java.base/sun.security.ssl.SSLSocketImpl$AppInputStream.read(SSLSocketImpl.java:1060) ~[na:na]
	at java.base/java.io.BufferedInputStream.fill(BufferedInputStream.java:244) ~[na:na]
	at java.base/java.io.BufferedInputStream.read1(BufferedInputStream.java:284) ~[na:na]
	at java.base/java.io.BufferedInputStream.read(BufferedInputStream.java:343) ~[na:na]
	at java.base/sun.net.www.http.HttpClient.parseHTTPHeader(HttpClient.java:788) ~[na:na]
	at java.base/sun.net.www.http.HttpClient.parseHTTP(HttpClient.java:723) ~[na:na]
	at java.base/sun.net.www.protocol.http.HttpURLConnection.getInputStream0(HttpURLConnection.java:1676) ~[na:na]
	at java.base/sun.net.www.protocol.http.HttpURLConnection.getInputStream(HttpURLConnection.java:1577) ~[na:na]
	at java.base/java.net.HttpURLConnection.getResponseCode(HttpURLConnection.java:527) ~[na:na]
	at java.base/sun.net.www.protocol.https.HttpsURLConnectionImpl.getResponseCode(HttpsURLConnectionImpl.java:308) ~[na:na]
	at feign.Client$Default.convertResponse(Client.java:109) ~[feign-core-11.8.jar:na]
	at feign.Client$Default.execute(Client.java:105) ~[feign-core-11.8.jar:na]
	at feign.SynchronousMethodHandler.executeAndDecode(SynchronousMethodHandler.java:119) ~[feign-core-11.8.jar:na]
	... 16 common frames omitted

2022-07-29 07:00:44.515  WARN 10000 --- [l-1 housekeeper] com.zaxxer.hikari.pool.HikariPool        : HikariPool-1 - Thread starvation or clock leap detected (housekeeper delta=49m33s895ms770µs738ns).
*/
}
