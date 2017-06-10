package com.demo.kafka;

import me.ele.arch.metric.core.*;
import org.codehaus.jackson.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lg
 *         Date: 5/3/17
 *         Time: 5:20 PM
 */
public class MetricsEncodeDecode {

    private static JsonFactory jsonFactory = new JsonFactory();

    public static List<Metric> decodeMetrics(byte[] data) throws IOException {
        JsonParser parser = null;
        try {
            ByteArrayInputStream input = new ByteArrayInputStream(data);
            parser = jsonFactory.createJsonParser(input);
            return decodeMetrics(parser);
        } finally {
            if (parser != null) {
                parser.close();
            }
        }
    }

    public static byte[] encodeMetrics(List<Metric> metrics) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        JsonGenerator generator = null;
        try {
            generator = jsonFactory.createJsonGenerator(baos, JsonEncoding.UTF8);
            generator.writeStartArray();
            for(Metric metric : metrics){
                if(metric != null){
                    generator.writeStartObject();
                    generator.writeStringField("metricType", metric.getMetricType());
                    generator.writeStringField("group", metric.getGroup());
                    generator.writeStringField("name", metric.getName());
                    generator.writeNumberField("timestamp", metric.getTimestamp());
                    generator.writeFieldName("tags");
                    generator.writeStartObject();
                    Map<String, String> tags = metric.getTags();
                    if(tags != null && tags.size() > 0){
                        for(Map.Entry<String, String> entry : tags.entrySet()){
                            generator.writeStringField(entry.getKey(), entry.getValue());
                        }
                    }
                    generator.writeEndObject();
                    if(metric instanceof Apdex){
                        Apdex apdex = (Apdex) metric;
                        generator.writeNumberField("total", apdex.getTotal());
                        generator.writeNumberField("satisfied", apdex.getSatisfied());
                        generator.writeNumberField("tolerating", apdex.getTolerating());
                        generator.writeNumberField("frustrated", apdex.getFrustrated());
                        generator.writeStringField("satisfiedMsg", apdex.getSatisfiedMsg());
                        generator.writeStringField("toleratingMsg", apdex.getToleratingMsg());
                        generator.writeStringField("frustratedMsg", apdex.getFrustratedMsg());
                    }else if(metric instanceof Counter){
                        Counter counter = (Counter) metric;
                        generator.writeNumberField("value", counter.getValue());
                        generator.writeStringField("sampling", counter.getSampling());
                    }else if(metric instanceof Gauge){
                        Gauge gauge = (Gauge) metric;
                        generator.writeNumberField("value", gauge.getValue());
                        generator.writeStringField("sampling", gauge.getSampling());
                    }else if(metric instanceof Ratio){
                        Ratio ratio = (Ratio) metric;
                        generator.writeNumberField("numerator", ratio.getNumerator());
                        generator.writeNumberField("denominator", ratio.getDenominator());
                    }else if(metric instanceof Payload){
                        Payload payload = (Payload) metric;
                        generator.writeNumberField("sum", payload.getSum());
                        generator.writeNumberField("count", payload.getCount());
                        generator.writeNumberField("min", payload.getMin());
                        generator.writeNumberField("max", payload.getMax());
                        generator.writeStringField("sampling", payload.getSampling());
                    }else if(metric instanceof Timer){
                        Timer timer = (Timer) metric;
                        generator.writeNumberField("sum", timer.getSum());
                        generator.writeNumberField("count", timer.getCount());
                        generator.writeNumberField("min", timer.getMin());
                        generator.writeNumberField("max", timer.getMax());
                        generator.writeStringField("maxMsg", timer.getMaxMsg());
                    }
                    generator.writeEndObject();
                }
            }
            generator.writeEndArray();
        }finally {
            if (generator != null) {
                generator.close();
            }
            baos.flush();
        }
        return baos.toByteArray();
    }

    private static List<Metric> decodeMetrics(JsonParser parser) throws IOException {
        JsonToken token = parser.nextToken();
        if (token == JsonToken.VALUE_NULL) {
            return null;
        }
        if (token != JsonToken.START_ARRAY) {
            throw new IllegalArgumentException("not a metric obj");
        }
        token = parser.nextToken();//move to first field
        if (token == JsonToken.END_ARRAY) {
            return null;
        }
        List<Metric> ret = new ArrayList();
        while (token != JsonToken.END_ARRAY && token != null) {
            if (token == JsonToken.START_OBJECT){
                decodeMetric(parser, token, ret);
            }
            token = parser.nextToken();
        }
        return ret;
    }

    private static JsonToken decodeMetric(JsonParser parser, JsonToken token, List<Metric> ret) throws IOException {
        byte index = 0;
        Metric metric = null;
        String metricType = null;
        token = passTwo(parser);
        while (token != JsonToken.END_OBJECT && token != null) {
            if (token != JsonToken.VALUE_NULL) {
                switch (index) {
                    case 0:
                        metricType = parser.getText();
                        if ("apdex".equals(metricType)) {
                            metric = new Apdex();
                        } else if ("counter".equals(metricType)) {
                            metric = new Counter();
                        } else if ("gauge".equals(metricType)) {
                            metric = new Gauge();
                        } else if ("ratio".equals(metricType)) {
                            metric = new Ratio();
                        } else if ("payload".equals(metricType)) {
                            metric = new Payload();
                        } else if ("timer".equals(metricType)) {
                            metric = new Timer();
                        } else {
                            return null;
                        }
                        break;
                    case 1:
                        metric.setGroup(parser.getText());
                        break;
                    case 2:
                        metric.setName(parser.getText());
                        break;
                    case 3:
                        metric.setTimestamp(parser.getLongValue());
                        break;
                    case 4:
                        if (token == JsonToken.START_OBJECT) {
                            token = parser.nextToken();//move first tag key
                        }
                        while (token != null && token != JsonToken.END_OBJECT) {
                            String key = parser.getCurrentName();
                            parser.nextToken();//move to value
                            String value = parser.getText();
                            metric.addTag(key, value);
                            token = parser.nextToken();//move to next key
                        }
                        break;
                    case 5:
                        if ("apdex".equals(metricType)) {
                            Apdex apdex = (Apdex) metric;
                            apdex.setTotal(parser.getLongValue());
                            passTwo(parser);
                            apdex.setSatisfied(parser.getLongValue());
                            passTwo(parser);
                            apdex.setTolerating(parser.getLongValue());
                            passTwo(parser);
                            apdex.setFrustrated(parser.getLongValue());
                            passTwo(parser);
                            apdex.setSatisfiedMsg(parser.getText());
                            passTwo(parser);
                            apdex.setToleratingMsg(parser.getText());
                            passTwo(parser);
                            apdex.setFrustratedMsg(parser.getText());
                        } else if ("counter".equals(metricType)) {
                            Counter counter = (Counter) metric;
                            counter.setValue(parser.getLongValue());
                            passTwo(parser);
                            counter.setSampling(parser.getText());
                        } else if ("gauge".equals(metricType)) {
                            Gauge gauge = (Gauge) metric;
                            gauge.setValue(parser.getDoubleValue());
                            passTwo(parser);
                            gauge.setSampling(parser.getText());
                        } else if ("ratio".equals(metricType)) {
                            Ratio ratio = (Ratio) metric;
                            ratio.setNumerator(parser.getLongValue());
                            passTwo(parser);
                            ratio.setDenominator(parser.getLongValue());
                        } else if ("payload".equals(metricType)) {
                            Payload payload = (Payload) metric;
                            payload.setSum(parser.getLongValue());
                            passTwo(parser);
                            payload.setCount(parser.getLongValue());
                            passTwo(parser);
                            payload.setMin(parser.getLongValue());
                            passTwo(parser);
                            payload.setMax(parser.getLongValue());
                            passTwo(parser);
                            payload.setSampling(parser.getText());
                        } else if ("timer".equals(metricType)) {
                            Timer timer = (Timer) metric;
                            timer.setSum(parser.getLongValue());
                            passTwo(parser);
                            timer.setCount(parser.getLongValue());
                            passTwo(parser);
                            timer.setMin(parser.getLongValue());
                            passTwo(parser);
                            timer.setMax(parser.getLongValue());
                            passTwo(parser);
                            timer.setMaxMsg(parser.getText());
                        }
                        break;

                }
            }
            token = parser.nextToken();
            if(token != JsonToken.END_OBJECT){
                token = parser.nextToken();
            }
            index++;
        }
        if(metric != null){
            ret.add(metric);
        }
        return token;
    }

    private static JsonToken passTwo(JsonParser parser) throws IOException {
        parser.nextToken();
        return parser.nextToken();
    }
}
