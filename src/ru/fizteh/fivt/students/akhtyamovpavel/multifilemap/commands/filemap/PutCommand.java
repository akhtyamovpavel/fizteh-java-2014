package ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.commands.filemap;

import ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.FileMap;
import ru.fizteh.fivt.students.akhtyamovpavel.multifilemap.commands.Command;

import java.util.ArrayList;

/**
 * Created by user1 on 06.10.2014.
 */
public class PutCommand implements Command {
    private FileMap fileMap;

    public PutCommand(FileMap link) {
        fileMap = link;
    }

    @Override
    public void executeCommand(ArrayList<String> arguments) throws Exception {
        if (arguments.size() != 2) {
            throw new Exception("usage: put key value");
        }
        if (fileMap == null) {
            System.out.println("no table");
        }
        if (fileMap.containsKey(arguments.get(0))) {
            System.out.println("overwrite");
            System.out.println(fileMap.get(arguments.get(0)));
        } else {
            System.out.println("new");
        }
        fileMap.put(arguments.get(0), arguments.get(1));
    }

    @Override
    public String getName() {
        return "put";
    }
}
