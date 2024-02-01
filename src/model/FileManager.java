package model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import utils.Validation;

public class FileManager {

    private final ArrayList<Student> sList = new ArrayList<>();
    private final ArrayList<ITStudent> ITList = new ArrayList<>();
    private final ArrayList<BizStudent> BizList = new ArrayList<>();

    private static final File FILE = new File("src");
    private static final String PATH = FILE.getAbsolutePath();
    private static final String FILE_FULL = "\\model\\student.txt";
    private static final String FILE_NAME_GPA = "\\model\\studentGPA.txt";

    Validation val = new Validation();
    ITStudent it;
    BizStudent biz;
    Address address;

    public FileManager() {
        loadData();
    }

    public void loadData() {
        String std = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(PATH + FILE_FULL));
            while ((std = br.readLine()) != null) {
                String[] i = std.split(", ");
                Address address = new Address(i[1], i[2], i[3], i[4]);
                if (!val.checkDuplicateITID(ITList, i[0])) {
                    if (i[0].startsWith("DE")) { // IT Student
                        ITStudent it = new ITStudent(i[0], i[1], address, Double.parseDouble(i[5]), Double.parseDouble(i[6]), Double.parseDouble(i[7]));
                        ITList.add(it);
                        sList.add(it);
                    }
                } else if (!val.checkDuplicateBizID(BizList, i[0])) {
                    if (i[0].startsWith("DS")) { // Biz Student
                        BizStudent biz = new BizStudent(i[0], i[1], address, Double.parseDouble(i[5]), Double.parseDouble(i[6]), Double.parseDouble(i[7]));
                        BizList.add(biz);
                        sList.add(biz);
                    }
                }

            }
            br.close();
        } catch (IOException io) {
            System.out.println(io.getMessage());
        }
    }

    public void saveData() {
        loadData();
        File file = new File(PATH + FILE_NAME_GPA);
        try {
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE + FILE_FULL)));
            for (ITStudent it : ITList) {
                String line = (it.getID() + ", " + it.getFullName() + ", " + address + ", " + it.getJavaScore() + ", " + it.getCssScore() + ", " + it.getAverageScore());
                bw.write(line);
                bw.newLine();
            }
            for (BizStudent biz : BizList) {
                String line = (biz.getID() + ", " + biz.getFullName() + ", " + address + ", " + biz.getAccountingScore() + ", " + biz.getMarketingScore() + ", " + biz.getAverageScore());
                bw.write(line);
                bw.newLine();
            }
            bw.close();
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }
}
