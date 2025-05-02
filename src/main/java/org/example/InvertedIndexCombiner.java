package org.example;

import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class InvertedIndexCombiner extends Reducer<Text, Text, Text, Text> {
    @Override
    public void reduce(Text key, Iterable<Text> values, Context context)
            throws IOException, InterruptedException {

        // Count frequency of each URL for this word
        Map<String, Integer> urlCountMap = new HashMap<>();

        for (Text value : values) {
            String url = value.toString();
            urlCountMap.put(url, urlCountMap.getOrDefault(url, 0) + 1);
        }

        // Emit partial aggregation: word => "url:count"
        for (Map.Entry<String, Integer> entry : urlCountMap.entrySet()) {
            context.write(key, new Text(entry.getKey() + ":" + entry.getValue()));
        }
    }
}
