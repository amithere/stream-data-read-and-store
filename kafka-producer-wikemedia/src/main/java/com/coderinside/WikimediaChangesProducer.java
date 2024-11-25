package com.coderinside;

import com.fasterxml.jackson.databind.JsonNode;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.sse.EventSource;
import okhttp3.sse.EventSourceListener;
import okhttp3.sse.EventSources;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.concurrent.TimeUnit;

@Service
public class WikimediaChangesProducer {

    @Value("${stream.url}")
    private String steamUrl;

    private static final Logger LOGGER = LoggerFactory.getLogger(WikimediaChangesProducer.class);

    private KafkaTemplate<String, String> kafkaTemplate;

    public WikimediaChangesProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage() throws InterruptedException {
        String topic = "wikimedia_recent_change";
        // To read realtime stream data from wikimedia, we use event source
        OkHttpClient client = new OkHttpClient();
        Request request = new Request.Builder()
                .url(steamUrl)
                .build();

        EventSource.Factory factory = EventSources.createFactory(client);
        factory.newEventSource(request, new EventSourceListener() {
            @Override
            public void onEvent(EventSource eventSource, String id, String type, String data) {
                    kafkaTemplate.send(topic, data);
            }
        });
        TimeUnit.MINUTES.sleep(10);
    }
}
