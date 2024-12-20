import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws Exception {
        Socket socket = new Socket("203.162.10.109", 2208);
        String send = "B21DCCN791;zSgYyzTG";
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        bw.write(send);
        bw.newLine();
        bw.flush();
        String res = br.readLine();
        int cnt[] = new int[256];
        for (char x : res.toCharArray()) {
            if (Character.isAlphabetic(x) || Character.isDigit(x)) {
                cnt[x]++;
            }
        }
        String result = "";
        for (char x : res.toCharArray()) {
            if (cnt[x] >= 2) {
                result += x + ":" + cnt[x] + ",";
                cnt[x] = 0;
            }
        }
        bw.write(result);
        bw.newLine();
        bw.flush();
    }
}