package com.event.collector.lambda;

import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.BasicSessionCredentials;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.sqs.AmazonSQS;
import com.amazonaws.services.sqs.AmazonSQSClient;
import com.amazonaws.services.sqs.model.*;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.map.ObjectReader;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.influxdb.dto.BatchPoints;
import org.influxdb.dto.Point;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

public class EventCollector {
    private final static ObjectMapper objectMapper = new ObjectMapper();
    private final static ObjectReader objectReader = objectMapper.reader(Map.class);

    public static void main(String[] args) throws Throwable {


        /**
         * IN Real Time this should be getting from InstanceServiceProviderCredentials
         */
        String accessKeyId = "XYZ";
        String secretAccessKey = "XYZ";
        String sessionToken = "XYZ";
        BasicSessionCredentials basicCred = new BasicSessionCredentials(accessKeyId, secretAccessKey, sessionToken);
        ClientConfiguration clientConfiguration = getClientConfiguration("proxy.abc.com", 8080);
        AmazonSQS sqs = new AmazonSQSClient(basicCred, clientConfiguration);
        Region usEast1 = Region.getRegion(Regions.US_EAST_1);
        sqs.setRegion(usEast1);
        String myQueueUrl = "https://sqs.us-east-1.amazonaws.com/1234/metrics-event-queue";
        System.out.println("Start of function DBEventCollector on time :" + System.currentTimeMillis());
        ReceiveMessageRequest receiveMessageRequest = null;
        int mstToDelete = 0;
        InfluxDB influxDB = null;
        String dbName = "demodb";
        boolean connection = false;
        BatchPoints batchPoints = null;
        DeleteMessageBatchRequest delMsgBatchReq = null;
        String measureMentName = "";
        while (true) {
            mstToDelete = 0;
            delMsgBatchReq = new DeleteMessageBatchRequest();
            try {
                batchPoints = BatchPoints.database(dbName).tag("async", "true").retentionPolicy("default")
                        .consistency(InfluxDB.ConsistencyLevel.ALL).build();
                influxDB = InfluxDBFactory.connect("http://localhost:8086", "root", "root");
                if (!influxDB.ping().getVersion().equalsIgnoreCase("unknown"))
                    connection = true;
                else
                    break;
                receiveMessageRequest = new ReceiveMessageRequest(myQueueUrl).withMaxNumberOfMessages(10);
                List<Message> messages = sqs.receiveMessage(receiveMessageRequest.withMessageAttributeNames("All")).getMessages();
                for (Message msg : messages){
                    System.out.println("Hi Muktesh msg "+msg);
                };
                System.out.println("Message count" + messages.size());
                mstToDelete = messages.size();
                if (mstToDelete == 0)
                    break;
                for (Message message : messages) {


                    Map<String, Object> jsonMap = jsonToMap(message.getBody());
                    // Insert data into influx
                    Point.Builder builder = Point.measurement(" ")
                            .time(Long.parseLong(message.getMessageAttributes().get("timestamp").getStringValue()), TimeUnit.MILLISECONDS);

                        HashMap<String, Object> map = (HashMap<String, Object>)jsonMap.get("Meters");
                        HashMap<String, Object> meterAttributMap = (HashMap<String, Object>)map.get("requestCount");
                        for (String meterAttr:meterAttributMap.keySet()) {
                            System.out.println("Key is :"+meterAttr);
                            System.out.println("Value is :"+meterAttributMap.get(meterAttr));
                            builder.addField(meterAttr, (String)meterAttributMap.get(meterAttr));
                        }


                    batchPoints.point(builder.build());
                    delMsgBatchReq.withEntries(
                            new DeleteMessageBatchRequestEntry(message.getMessageId(), message.getReceiptHandle()))
                            .withQueueUrl(myQueueUrl);
                }
                influxDB.write(batchPoints);
                System.out.println("After influx write");

                sqs.deleteMessageBatch(delMsgBatchReq);
            } catch (Exception e) {
                System.out.println("Caught Exception");
                e.printStackTrace();
                break;
            }
        }
        System.out.println("End of function DBEventCollector on time : " + System.currentTimeMillis());
    }

    private static ClientConfiguration getClientConfiguration(String proxyHost, int proxyPort) {
        ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setProxyHost(proxyHost);
        clientConfiguration.setProxyPort(proxyPort);
        clientConfiguration.setProxyUsername("xyz");
        clientConfiguration.setProxyPassword("xyz");
        return clientConfiguration;
    }

    public static Map<String, Object> jsonToMap(String jsonString) {
        try {
            return objectReader.readValue(jsonString);
        } catch (IOException e) {
            throw new RuntimeException("Unable to parse JSON", e);
        }
    }

    public static String getPropertyFile(String fileURL) throws Exception {
        URL url = new URL(fileURL);
        URLConnection uc = url.openConnection();
        InputStream is = uc.getInputStream();
        String str = "";
        StringBuffer buf = new StringBuffer();
        if (is != null) {
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                if (is != null) {
                    while ((str = reader.readLine()) != null) {
                        buf.append(str + ":" );
                    }
                }
            } finally {
                try { is.close(); } catch (Throwable ignore) {}
            }
        }
        return buf.toString();
    }
}
