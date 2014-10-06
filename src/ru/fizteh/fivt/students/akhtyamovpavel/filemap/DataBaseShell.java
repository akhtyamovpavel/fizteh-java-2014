package ru.fizteh.fivt.students.akhtyamovpavel.filemap;

import ru.fizteh.fivt.students.akhtyamovpavel.filemap.commands.Command;
import ru.fizteh.fivt.students.akhtyamovpavel.filemap.commands.ExitCommand;
import ru.fizteh.fivt.students.akhtyamovpavel.filemap.commands.GetCommand;
import ru.fizteh.fivt.students.akhtyamovpavel.filemap.commands.PutCommand;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by user1 on 30.09.2014.
 */
public class DataBaseShell extends AbstractShell implements AutoCloseable {
    private Path dataBasePath;
    private boolean isInteractive;
    private FileMap fileMap;


    DataBaseShell() {
        initDataBaseFile();
        try {
            open();
        } catch (Exception e) {
            printException(e.getMessage());
        }
        initCommands();

    }

    private void initDataBaseFile() {
        dataBasePath = Paths.get(System.getProperty("user.dir")).resolve(System.getProperty("db.file"));
    }

    private void initCommands() {
        commandNames = new HashMap<String, Command>();

        addCommand(new ExitCommand(this));
        addCommand(new PutCommand(fileMap));
        addCommand(new GetCommand(fileMap));
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
        fileMap = new FileMap(dataBasePath);
        fileMap.loadMap();

    }

    @Override
    public void close() throws Exception {
        fileMap.saveMap();
    }

    private void addCommand(Command command) {
        commandNames.put(command.getName(), command);
    }

}
