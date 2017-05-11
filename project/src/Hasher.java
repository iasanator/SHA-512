/**
 * Created by iassona on 5/5/2017.
 */
public class Hasher {

    byte[] padded;

    byte[] internalState = new byte[64];

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
