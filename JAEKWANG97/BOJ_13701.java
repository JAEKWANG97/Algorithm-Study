import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.BitSet;

public class Main {
    static final int MAX_NUMBER = 33_554_432;
    static BitSet bitSet = new BitSet(MAX_NUMBER + 1);

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        init();
        System.out.println(sb);
    }

    private static void init() throws IOException {
        FastInput.initFI(); // FastInput 초기화
        while (true) {
            int number = FastInput.nextInt();
            if (number == -1) {
                break; // -1이 반환되면 입력 종료
            }

            if (!bitSet.get(number)) {
                bitSet.set(number);
                sb.append(number).append(" ");
            }
        }
    }
}

class FastInput {
    private static final int DEFAULT_BUFFER_SIZE = 1 << 26;
    private static DataInputStream inputStream;
    private static byte[] buffer;
    private static int curIdx, maxIdx;

    protected static void initFI() {
        inputStream = new DataInputStream(System.in);
        buffer = new byte[DEFAULT_BUFFER_SIZE];
        curIdx = maxIdx = 0;
    }

    protected static int nextInt() throws IOException {
        int ret = 0;
        boolean negative = false;
        byte c = read();
        if (c == -1) {
            return -1; // 시작 부분에서 스트림의 끝 검사
        }

        while (c <= ' ') {
            c = read();
            if (c == -1) {
                return -1; // 스트림의 끝에 도달하면 -1 반환
            }
        }
        if (c == '-') { // 음수 처리
            negative = true;
            c = read();
            if (c == -1) {
                return -1; // 음수 기호 후 바로 스트림 끝이면 -1 반환
            }
        }
        do {
            if (c == -1) {
                return -1; // 숫자 읽는 도중 끝에 도달하면 -1 반환
            }
            ret = ret * 10 + c - '0';
        } while ((c = read()) >= '0' && c <= '9');
        return negative ? -ret : ret;
    }


    private static byte read() throws IOException {
        if (curIdx == maxIdx) {
            maxIdx = inputStream.read(buffer, curIdx = 0, DEFAULT_BUFFER_SIZE);
            if (maxIdx == -1) {
                return -1; // 더 이상 읽을 데이터가 없을 때 -1 반환
            }
        }
        return buffer[curIdx++];
    }
}
