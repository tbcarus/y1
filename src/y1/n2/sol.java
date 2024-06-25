package y1.n2;

import java.io.*;
import java.util.*;

public class sol {
    public static void main(String[] args) throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader("input.txt"));
        PrintStream out = new PrintStream(new File("output.txt"));
        int N = Integer.parseInt(fin.readLine());
        String str = fin.readLine();
        List<Job> jobList = new ArrayList<>();
        while (str != null) {
            String[] s = str.split(" ");
            if (!s[4].equals("B")) {
                jobList.add(new Job(Long.parseLong(s[3]),
                        Integer.parseInt(s[0]),
                        Integer.parseInt(s[1]),
                        Integer.parseInt(s[2]),
                        s[4]));
            }
            str = fin.readLine();
        }
        Collections.sort(jobList);
        Map<Long, Integer> map = new HashMap<>();
        List<Long> idList = new ArrayList<>();
        for (int i = 0; i < jobList.size(); i += 2) {
            Job jobStart = jobList.get(i);
            Job jobEnd = jobList.get(i + 1);
            int time = jobEnd.day * 24 * 60 + jobEnd.hour * 60 + jobEnd.minute - (jobStart.day * 24 * 60 + jobStart.hour * 60 + jobStart.minute);
            if (map.containsKey(jobStart.id)) {
                map.put(jobStart.id, map.get(jobStart.id) + time);
            } else {
                map.put(jobStart.id, time);
                idList.add(jobStart.id);
            }
        }
        for (long id : idList) {
            out.print(map.get(id) + " ");
        }
        out.close();
    }

    static class Job implements Comparable<Job> {
        long id;
        int day;
        int hour;
        int minute;
        String status;

        public Job(long id, int day, int hour, int minute, String status) {
            this.id = id;
            this.day = day;
            this.hour = hour;
            this.minute = minute;
            this.status = status;
        }

        @Override
        public int compareTo(Job o) {
            int result = Long.compare(this.id, o.id);
            if (result == 0) {
                result = Integer.compare(this.day, o.day);
            }
            if (result == 0) {
                result = Integer.compare(this.hour, o.hour);
            }
            if (result == 0) {
                result = Integer.compare(this.minute, o.minute);
            }
            return result;
        }
    }
}
