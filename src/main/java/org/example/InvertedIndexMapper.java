package org.example;

import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;
import java.util.StringTokenizer;

public class InvertedIndexMapper extends Mapper<LongWritable, Text, Text, Text> {
    private Text word = new Text();
    private Text url = new Text();

    public void map(LongWritable key, Text value, Mapper.Context context) throws IOException, InterruptedException {
        String line = value.toString();
        int firstSpace = line.indexOf(" ");
        if (firstSpace == -1) return;

        String urlStr = line.substring(0, firstSpace).trim();
        String content = line.substring(firstSpace + 1).toLowerCase().replaceAll("[^a-z0-9]", " ");

        url.set(urlStr);

        StringTokenizer tokenizer = new StringTokenizer(content);
        while (tokenizer.hasMoreTokens()) {
            word.set(tokenizer.nextToken());
            context.write(word, url);
        }
    }
}
