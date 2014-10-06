package ru.fizteh.fivt.students.akhtyamovpavel.filemap.commands;

import ru.fizteh.fivt.students.akhtyamovpavel.filemap.FileMap;

import java.util.ArrayList;

/**
 * Created by user1 on 06.10.2014.
 */
public class ListCommand implements Command{
    private FileMap fileMap;

    public ListCommand(FileMap link) {
        fileMap = link;
    }

    @Override
    public void executeCommand(ArrayList<String> arguments) throws Exception {
        if (!arguments.isEmpty()) {
            throw new Exception("usage: list");
        }
        for (String currentKey: fileMap.keySet()) {
            System.out.print(currentKey + " ");
        }
        System.out.println();
    }

    @Override
    public String getName() {
        return "list";
    }
}
