package ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.commands.filemap;

import ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.FileMap;
import ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.commands.Command;

import java.util.ArrayList;

/**
 * Created by user1 on 06.10.2014.
 */
public class RemoveCommand implements Command {
    private FileMap fileMap;

    public RemoveCommand(FileMap link) {
        fileMap = link;
    }

    @Override
    public void executeCommand(ArrayList<String> arguments) throws Exception {
        if (arguments.size() != 1) {
            throw new Exception("usage: remove key");
        }
        if (fileMap == null) {
            System.out.println("no table");
        }
        if (fileMap.containsKey(arguments.get(0))) {
            fileMap.remove(arguments.get(0));
            System.out.println("removed");
        } else {
            System.out.println("not found");
        }
    }

    @Override
    public String getName() {
        return "remove";
    }
}
