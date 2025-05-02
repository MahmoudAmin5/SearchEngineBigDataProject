package org.example;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InvertedIndexReducer extends Reducer<Text, Text, Text, Text> {
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        Map<String, Integer> urlCountMap = new HashMap<>();

        for (Text value : values) {
            String[] parts = value.toString().split(":");
            String url = parts[0];
            int count = parts.length > 1 ? Integer.parseInt(parts[1]) : 1;

            urlCountMap.put(url, urlCountMap.getOrDefault(url, 0) + count);
        }

        // Build output string: url1:count1 url2:count2 ...
        StringBuilder result = new StringBuilder();
        for (Map.Entry<String, Integer> entry : urlCountMap.entrySet()) {
            result.append(entry.getKey()).append(":").append(entry.getValue()).append(" ");
        }

        context.write(key, new Text(result.toString().trim()));
    }
}
