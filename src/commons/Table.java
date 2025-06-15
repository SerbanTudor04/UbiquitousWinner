package commons;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Table<T extends Serializable> implements Serializable{
    @Serial
    private static final long serialVersionUID = 1L;


    private List<String> headers;
    private List<Integer> widths;
    private String headerSeparator;
    private ArrayList<ArrayList<T>> data;
    public static final Integer headerColumnsSeparatorSize=3;

    public Table(){
        this.headers = new ArrayList<String>();
        this.data = new ArrayList<>();
        this.widths = new ArrayList<>();

    }


    private String formatHeaderCell(String cell){
        return cell.replace(" ", "_").toLowerCase();
    }

    public void calculateWidths() {
        widths = new ArrayList<>();
        if(headerSeparator == null){
            headerSeparator = "";
        }
        for (String header : headers) {
            widths.add(header.length()+headerColumnsSeparatorSize);
            headerSeparator = headerSeparator.concat("-".repeat(header.length()+headerColumnsSeparatorSize));
        }
    }

    private void remakeHeaderSeparatorByWidths(){
        headerSeparator="";
        for (Integer width : widths) {
            headerSeparator = headerSeparator.concat("-".repeat(width));
        }
    }


    public void display(){
        if(widths.isEmpty() || headers.size() != widths.size()){
            calculateWidths();
        }

        for (int i = 0;i<headers.size();i++) {
            System.out.printf("%-"+widths.get(i)+"s", headers.get(i));
        }
        System.out.println();

        System.out.println(headerSeparator);

        for(int i = 0;i<data.size();i++){
            ArrayList<?> row = data.get(i);
            // adds the index in front of every row for the column #
            System.out.printf("%-"+widths.getFirst()+"s", i);
            for (int j =0;j<row.size();j++) {
                System.out.printf("%-" + widths.get(j+1) + "s", row.get(j));
            }
            System.out.println();
        }
        System.out.println();

    }


    public Table<T> setHeaders(List<String> headers) {
        this.headers= new ArrayList<>();
        this.headers.add("#");
        this.headers.addAll(headers);
        return this;

    }

    public Boolean addRow(ArrayList<T> data){
        if(data.size() != widths.size()-1){
            System.out.println("ERROR: data size is not equal to width size");
            return false;
        }
        ArrayList<T> tmp = new ArrayList<>(data);
        //recalibrate the header widths for columns
        for (int i = 0;i<tmp.size();i++){
            T row = tmp.get(i);
            if(row.toString().length()>widths.get(i+1)-headerColumnsSeparatorSize){
                Integer tmpWidt = row.toString().length()+headerColumnsSeparatorSize;
                widths.set(i+1,tmpWidt);
                remakeHeaderSeparatorByWidths();
            }


        }
        this.data.add(tmp);
        return true;
    }


}
