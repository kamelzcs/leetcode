//Implement the class TweetCounts that supports two methods:
//
// 1. recordTweet(string tweetName, int time) 
//
// 
// Stores the tweetName at the recorded time (in seconds). 
// 
//
// 2. getTweetCountsPerFrequency(string freq, string tweetName, int startTime, i
//nt endTime) 
//
// 
// Returns the total number of occurrences for the given tweetName per minute, h
//our, or day (depending on freq) starting from the startTime (in seconds) and end
//ing at the endTime (in seconds). 
// freq is always minute, hour or day, representing the time interval to get the
// total number of occurrences for the given tweetName. 
// The first time interval always starts from the startTime, so the time interva
//ls are [startTime, startTime + delta*1>, [startTime + delta*1, startTime + delta
//*2>, [startTime + delta*2, startTime + delta*3>, ... , [startTime + delta*i, min
//(startTime + delta*(i+1), endTime + 1)> for some non-negative number i and delta
// (which depends on freq). 
// 
//
// 
// Example: 
//
// 
//Input
//["TweetCounts","recordTweet","recordTweet","recordTweet","getTweetCountsPerFre
//quency","getTweetCountsPerFrequency","recordTweet","getTweetCountsPerFrequency"]
//
//[[],["tweet3",0],["tweet3",60],["tweet3",10],["minute","tweet3",0,59],["minute
//","tweet3",0,60],["tweet3",120],["hour","tweet3",0,210]]
//
//Output
//[null,null,null,null,[2],[2,1],null,[4]]
//
//Explanation
//TweetCounts tweetCounts = new TweetCounts();
//tweetCounts.recordTweet("tweet3", 0);
//tweetCounts.recordTweet("tweet3", 60);
//tweetCounts.recordTweet("tweet3", 10);                             // All twee
//ts correspond to "tweet3" with recorded times at 0, 10 and 60.
//tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 59); // return [
//2]. The frequency is per minute (60 seconds), so there is one interval of time: 
//1) [0, 60> - > 2 tweets.
//tweetCounts.getTweetCountsPerFrequency("minute", "tweet3", 0, 60); // return [
//2, 1]. The frequency is per minute (60 seconds), so there are two intervals of t
//ime: 1) [0, 60> - > 2 tweets, and 2) [60,61> - > 1 tweet.
//tweetCounts.recordTweet("tweet3", 120);                            // All twee
//ts correspond to "tweet3" with recorded times at 0, 10, 60 and 120.
//tweetCounts.getTweetCountsPerFrequency("hour", "tweet3", 0, 210);  // return [
//4]. The frequency is per hour (3600 seconds), so there is one interval of time: 
//1) [0, 211> - > 4 tweets.
// 
//
// 
// Constraints: 
//
// 
// There will be at most 10000 operations considering both recordTweet and getTw
//eetCountsPerFrequency. 
// 0 <= time, startTime, endTime <= 10^9 
// 0 <= endTime - startTime <= 10^4 
// 
// Related Topics Design


package leetcode.editor.en;

import java.util.AbstractMap;
import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.TreeSet;

public class TweetCountsPerFrequency {
    public static void main(String[] args) {
        TweetCountsPerFrequency solution = new TweetCountsPerFrequency();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class TweetCounts {
        Map<String, Integer> freqMap;
        Map<String, TreeMap<Integer, Integer>> tweetMap;
        public TweetCounts() {
            freqMap = new HashMap<>();
            freqMap.put("minute", 60);
            freqMap.put("hour", 60 * 60);
            freqMap.put("day", 60 * 60 * 24);
            tweetMap = new HashMap<>();
        }

        public void recordTweet(String tweetName, int time) {
            tweetMap.computeIfAbsent(tweetName, x -> new TreeMap<>());
            TreeMap<Integer, Integer> existing = tweetMap.get(tweetName);
            existing.put(time, existing.getOrDefault(time, 0) + 1);
        }

        public List<Integer> getTweetCountsPerFrequency(String freq, String tweetName, int startTime, int endTime) {
            int freqInSeconds = freqMap.get(freq);
            List<Integer> result = new ArrayList<>();
            TreeMap<Integer, Integer> tweets = tweetMap.getOrDefault(tweetName, new TreeMap<>());
            for (int delta = 0; startTime + delta * freqInSeconds <= endTime; delta++) {
                result.add(tweets.subMap(startTime + delta * freqInSeconds, Math.min(startTime + (delta + 1) * freqInSeconds, endTime + 1)).values().stream().mapToInt(x -> x).sum());
            }
            return result;
        }
    }

/**
 * Your TweetCounts object will be instantiated and called as such:
 * TweetCounts obj = new TweetCounts();
 * obj.recordTweet(tweetName,time);
 * List<Integer> param_2 = obj.getTweetCountsPerFrequency(freq,tweetName,startTime,endTime);
 */
//leetcode submit region end(Prohibit modification and deletion)

}