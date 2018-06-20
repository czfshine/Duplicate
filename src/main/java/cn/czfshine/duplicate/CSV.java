package cn.czfshine.duplicate;

import java.io.*;

/**
 * @author:czfshine
 * @date:2018/6/19 21:29
 */

public class CSV {
    OutputStreamWriter outputStreamWriter;
    public CSV(String filename) throws FileNotFoundException {
        outputStreamWriter = new OutputStreamWriter(new BufferedOutputStream(new FileOutputStream(new File(filename))));
    }

    public void write(int data) throws IOException {
        outputStreamWriter.write(String.valueOf(data));
        outputStreamWriter.write(",\n");
    }
    public void write(int data,int count) throws IOException {
        outputStreamWriter.write(String.valueOf(data));
        outputStreamWriter.write(",");
        outputStreamWriter.write(String.valueOf(count));
        outputStreamWriter.write(",\n");
    }

    @Override
    protected void finalize() throws Throwable {
        outputStreamWriter.close();
        super.finalize();
    }
}
