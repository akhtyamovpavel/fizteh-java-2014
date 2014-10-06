package ru.fizteh.fivt.students.akhtyamovpavel.filemap;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by user1 on 30.09.2014.
 */
public class DataBaseShell extends AbstractShell implements AutoCloseable {
    private Path dataBasePath;
    private boolean isInteractive;


    DataBaseShell() {
        initDataBaseFile();
        initCommands();
    }

    private void initDataBaseFile() {
        dataBasePath = Paths.get(System.getProperty("user.dir")).resolve(System.getProperty("db.file"));
    }

    private void initCommands() {

    }

    private void open() throws Exception {
        if (!Files.exists(dataBasePath)) {
            throw new Exception("connect: database file doesn't exists");
        }
        if (Files.isDirectory(dataBasePath) || Files.isSymbolicLink(dataBasePath)) {
            throw new Exception("connect: database destination is a directory or a symbolic link");
        }
        if (!Files.isReadable(dataBasePath)) {
            throw new Exception("connect: permission to database file denied");
        }

    }

    @Override
    public void close() throws Exception {

    }

}
