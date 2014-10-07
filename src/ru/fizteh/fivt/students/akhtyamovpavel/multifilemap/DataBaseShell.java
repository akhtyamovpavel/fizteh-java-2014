package ru.fizteh.fivt.students.akhtyamovpavel.multifilemap;


import ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.commands.Command;
import ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.commands.table.CreateTableCommand;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

/**
 * Created by akhtyamovpavel on 30.09.2014.
 */
public class DataBaseShell extends AbstractShell implements AutoCloseable {
    private Path dataBaseDirectory;
    private boolean isInteractive;
    private String openedTable;
    private FileMap fileMap;


    public DataBaseShell() {
        initDataBaseDirectory();
        try {
            onLoadCheck();
        } catch (Exception e) {
            printException(e.getMessage());
        }
        initCommands();

    }

    private void initDataBaseDirectory() {
        dataBaseDirectory = Paths.get(System.getProperty("user.dir")).resolve(System.getProperty("fizteh.db.dir"));
        fileMap = null;
    }

    private void initCommands() {
        commandNames = new HashMap<String, Command>();
        addCommand(new CreateTableCommand(this));
    }

    private void onLoadCheck() throws Exception {
        if (!Files.exists(dataBaseDirectory)) {
            throw new Exception("connect: no such database");
        }
        if (!Files.isDirectory(dataBaseDirectory)) {
            throw new Exception("connect: path isn't a directory");
        }
        if (!Files.isReadable(dataBaseDirectory)) {
            throw new Exception("connect: permission denied");
        }
        if (!Files.isWritable(dataBaseDirectory)) {
            throw new Exception("connect: permission denied");
        }

        File currentFolder = dataBaseDirectory.toFile();
        String[] listOfFiles = currentFolder.list();
        for (String name : listOfFiles) {
            Path newPath = Paths.get(dataBaseDirectory.toString(), name);
            if (!Files.isDirectory(newPath)) {
                throw new Exception("connect: table " + name + " is extra");
            }
            if (!Files.isReadable(newPath)) {
                throw new Exception("connect: table " + name + ": permission denied");
            }
            if (!Files.isWritable(newPath)) {
                throw new Exception("connect: table " + name + ": permission denied");
            }
        }

    }

    @Override
    public void close() throws Exception {
        fileMap.saveMap();
    }

    private void addCommand(Command command) {
        commandNames.put(command.getName(), command);
    }

    public FileMap getFileMap() {
        return fileMap;
    }


    public Path getDataBaseDirectory() {
        return dataBaseDirectory;
    }

    public void setFileMap(FileMap fileMap) {
        this.fileMap = fileMap;
    }


}
