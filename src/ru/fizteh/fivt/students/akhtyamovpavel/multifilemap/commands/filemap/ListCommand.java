package ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.commands.filemap;

import ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.FileMap;
import ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.commands.Command;

import java.util.ArrayList;

/**
 * Created by user1 on 06.10.2014.
 */
public class ListCommand implements Command {
    private FileMap fileMap;

    public ListCommand(FileMap link) {
        fileMap = link;
    }

    @Override
    public void executeCommand(ArrayList<String> arguments) throws Exception {
        if (!arguments.isEmpty()) {
            throw new Exception("usage: list");
        }
        if (fileMap == null) {
            System.out.println("no table");
        }

        for (String currentKey : fileMap.keySet()) {
            System.out.print(currentKey + " ");
        }
        System.out.println();
    }

    @Override
    public String getName() {
        return "list";
    }
}
