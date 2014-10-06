package ru.fizteh.fivt.students.akhtyamovpavel.filemap;

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
                inputStream.read(keyByteFormat, 0, keyLength);
                int valueLength = inputStream.readInt();
                byte[] valueByteFormat = new byte[valueLength];
                inputStream.read(valueByteFormat, 0, valueLength);

                put(new String(keyByteFormat, "UTF-8"), new String(valueByteFormat, "UTF-8"));
            }
        } catch (IOException ioe) {
            throw new Exception("database: read from database failed");
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
            throw new Exception("database: writing to database failed");
        }
    }

}
