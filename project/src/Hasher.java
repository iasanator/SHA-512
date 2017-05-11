import java.util.Arrays;

/**
 * Created by iassona on 5/5/2017.
 */
public class Hasher {

    byte[] padded;

    byte[] internalState = new byte[64];

    byte[][] roundConst; //This is where the round constants get initialized to.

    public Hasher() {

    }

    public String hash(String message) {

        int bits = (message.length() * 16);
        int blocks = (int)Math.ceil((bits + 128) / 1024);

        generatePaddedMessage(message);

        for (int i = 0; i < blocks; i++) {
            internalState = wordByWordAddition(internalState, compressionFunction(getBlock(i), internalState));
        }


        return formatState(internalState);

    }

    private byte[] wordByWordAddition(byte[] state, byte[] functionOutput) {

        byte[] output = new byte[64];

        for (int i = 0; i < 8; i++) {
            byte[] stateWord = Arrays.copyOfRange(state, 8 * i, 8 * (i + 1));
            byte[] functionOutputWord = Arrays.copyOfRange(state, 8 * i, 8 * (i + 1));

            byte[] addedWord = wordAdd(stateWord, functionOutputWord);

            for (int j = 0; j < 8; j++) {
                output[8*i + j] = addedWord[j];
            }
        }

        return output;

    }

    private byte[] compressionFunction(byte[] messageBlock, byte[] state) {

        byte[][] messageSchedule = generateMessageSchedule(messageBlock);
        byte[] currentState = state;

        for (int i = 0; i < 80; i++) {
            currentState = performRound(messageSchedule[i], roundConst[i], state); // The other input is the internal state which is held as a class-wide variable
        }

        return currentState;

    }

    private byte[] performRound(byte[] messageChunk, byte[] roundConst, byte[] state) {
        byte[] outputState = new byte[8];

        outputState[0] = findA(state);

        outputState[1] = state[0];
        outputState[2] = state[1];
        outputState[3] = state[2];

        outputState[4] = findE(state);

        outputState[5] = state[4];
        outputState[6] = state[5];
        outputState[7] = state[6];
    }



    private void generatePaddedMessage(String message) {

        int bits = (message.length() * 16);
        int blocks = (int)Math.ceil((bits + 128) / 1024);

        padded = new byte[128 * blocks];
        byte[] buf = message.getBytes();

        int i = 0;


        for(byte c : buf) {
            System.out.format("%d ", c);
        }
        System.out.println();

        while (i < buf.length) {
            padded[i] = buf[i];
            i++;
        }


        if (i < padded.length - 16) {
            padded[i] = -128;
            i++;


            while (i < padded.length - 16) {
                padded[i] = 0;
                i++;
            }
        }

        String lengthString = String.format("%128s", Integer.toBinaryString(18)).replace(' ', '0');

        for (int j = 0; j < 16; j++){

            String nextByteString = lengthString.substring(8*j, 8*(j+1));

            short nextByteShort = Short.parseShort(nextByteString, 2);
            if (nextByteShort > 127) {
                nextByteShort -= 256;
            }

            padded[i] = (byte)nextByteShort;
            i++;

        }

        System.out.println(padded.toString());

    }

    private String formatState(byte[] state) {

        StringBuilder builder = new StringBuilder();

        for (Byte b : state) {
            builder.append(String.format("%02x", b));
        }

        return builder.toString();

    }

}
