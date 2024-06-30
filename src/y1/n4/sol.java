package y1.n4;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

// Работает верно, но почему-то при проверке на сайте время выполнения на несколько соток больше заданного...

public class sol {
    public static void main(String[] args) throws IOException {
        try (BufferedReader fin = new BufferedReader(new FileReader("input.txt"));
             PrintStream out = new PrintStream(new File("output.txt"))) {
            int N = Integer.parseInt(fin.readLine()); // количество заказов;
            List<Order> orderList = new ArrayList<>(N);
            for (int i = 0; i < N; i++) {
                String[] order = fin.readLine().split(" ");
                orderList.add(new Order(Integer.parseInt(order[0]), Integer.parseInt(order[1]), Integer.parseInt(order[2])));
            }
            fin.readLine();
//            int Q = Integer.parseInt(fin.readLine()); // Количество запросов
            String str = fin.readLine();
            StringBuilder strOut = new StringBuilder();
            int sum = 0;
            while (str != null) {
                String[] s = str.split(" ");
                int start = Integer.parseInt(s[0]);
                int end = Integer.parseInt(s[1]);
                byte type = Byte.parseByte(s[2]);
                if (type == 1) {
                    sum = 0;
                    for (Order order : orderList) {
                        if (order.isStart(start, end)) {
                            sum += order.getCost();
                        }
                    }
                }
                if (type == 2) {
                    sum = 0;
                    for (Order order : orderList) {
                        if (order.isEnd(start, end)) {
                            sum += order.getDuration();
                        }
                    }
                }
                strOut.append(sum).append(" ");
                str = fin.readLine();
            }
            out.print(strOut);
        }
    }

    static class Order {
        int start;
        int end;
        int cost;
        int duration; // поле добавлено в попытке уменьшить количество вычислений

        public boolean isStart(int start, int end) {
            return (this.start >= start) && (this.start <= end);
        }

        public boolean isEnd(int start, int end) {
            return (this.end >= start) && (this.end <= end);
        }

        public Order(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
            this.duration = end - start;
        }

        public int getStart() {
            return start;
        }

        public void setStart(int start) {
            this.start = start;
        }

        public int getEnd() {
            return end;
        }

        public void setEnd(int end) {
            this.end = end;
        }

        public int getCost() {
            return cost;
        }

        public void setCost(int cost) {
            this.cost = cost;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }
    }
}