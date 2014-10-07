package ru.fizteh.fivt.students.akhtyamovpavel.multifilemap;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;

/**
 * Created by user1 on 30.09.2014.
 */
public class FileMap extends HashMap<String, String> {
    Path dataBasePath;

    public FileMap(Path path) throws Exception {
        dataBasePath = path;
    }

    public void loadMap() throws Exception {

        try (DataInputStream inputStream = new DataInputStream(Files.newInputStream(dataBasePath))) {
            clear();

            while (inputStream.available() > 0) {
                int keyLength = inputStream.readInt();
                byte[] keyByteFormat = new byte[keyLength];
                int readKeyBytes = inputStream.read(keyByteFormat, 0, keyLength);
                if (readKeyBytes < keyByteFormat.length) {
                    throw new Exception("read from database failed");
                }
                int valueLength = inputStream.readInt();
                byte[] valueByteFormat = new byte[valueLength];
                int readBytes = inputStream.read(valueByteFormat, 0, valueLength);
                if (readBytes < valueByteFormat.length) {
                    throw new Exception("read from database failed");
                }
                put(new String(keyByteFormat, "UTF-8"), new String(valueByteFormat, "UTF-8"));
            }
        } catch (IOException ioe) {
            throw new Exception("read from database failed");
        }
    }

    public void saveMap() throws Exception {
        try (DataOutputStream outputStream = new DataOutputStream(Files.newOutputStream(dataBasePath))) {
            for (Entry<String, String> entry : entrySet()) {
                byte[] key = entry.getKey().getBytes("UTF-8");
                byte[] value = entry.getValue().getBytes("UTF-8");
                outputStream.writeInt(key.length);
                outputStream.write(key);
                outputStream.writeInt(value.length);
                outputStream.write(value);
            }
        } catch (IOException e) {
            throw new Exception("writing to database failed");
        }
    }

}
