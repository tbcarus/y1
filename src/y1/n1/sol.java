package y1.n1;

import java.io.*;

public class sol {
    public static void main(String[] args) throws IOException {
        BufferedReader fin = new BufferedReader(new FileReader("input.txt"));
        PrintStream out = new PrintStream(new File("output.txt"));
        int N = Integer.parseInt(fin.readLine());
        String str = fin.readLine();
        while (str != null) {
            String[] s = str.split(",");
            String fio = s[0] + s[1] + s[2];
            int d = Integer.parseInt(s[3]);
            int m = Integer.parseInt(s[4]);
            int y = Integer.parseInt(s[5]);

            int unique = fio.replaceAll("(.)(?=.*?\\1)", "").length();
            int k1 = d / 10 + d % 10 + m / 10 + m % 10;
            int firstCh = s[0].toUpperCase().charAt(0) - 64;
            int sum = unique + k1 * 64 + firstCh * 256;
            String hex = Integer.toHexString(sum).toUpperCase();
            String finalString = null;
            if (hex.length() < 3) {
                hex = "000" + hex;

            }
            finalString = hex.substring(hex.length() - 3, hex.length());
            out.print(finalString + " ");
            str = fin.readLine();
        }

        out.close();
    }
}
