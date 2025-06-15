package commons;

import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HelpTable {
    private final static HelpTable instance = new HelpTable();
    private Table<String> table;
    private ArrayList<String> commands;

    public static HelpTable getInstance() {
        return instance;
    }

    public HelpTable() {
        table = new Table<>();
        List<String> headers = new ArrayList<>();
        headers.add("Command");
        headers.add("Usage");
        headers.add("Description");
        table.setHeaders(headers);
        table.calculateWidths();

        commands = new ArrayList<>();
        loadFields();
    }


    private void loadFields(){
        String fileName= "help-table.csv";
        File file = Paths.get(fileName).toFile();
        Scanner scanner = null;
        try {
            scanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

        while(scanner.hasNextLine()){
            String line = scanner.nextLine();
            String[] fields = line.split(",");
            String command = fields[0];
            String usage = fields[1];
            String description = fields[2];

            addRowToTable(command, usage, description);


        }
    }

    private void addRowToTable(String command, String usage, String description){
        ArrayList<String> row = new ArrayList<>();
        row.add(command);
        row.add(usage);
        row.add(description);
        table.addRow(row);
        commands.add(command.toLowerCase());
    }

    public Boolean validateCommand(String command){
        return commands.contains(command.toLowerCase());
    }

    public void display(){
        table.display();
    }
    public Table<String> getTable(){
        return table;
    }
}
