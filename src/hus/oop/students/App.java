package hus.oop.students;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class App {
    private static final String COMMA_DELIMITER = ",";

    public static void readListData(String filePath) {
        BufferedReader dataReader = null;
        try {
            String line;
            dataReader = new BufferedReader(new FileReader(filePath));
            StudentManager studentManager = StudentManager.getInstance();

            while ((line = dataReader.readLine()) != null) {
                List<String> dataList = parseDataLineToList(line);
                if (dataList.size() != 7) {
                    continue;
                }

                if (dataList.get(0).equals("id")) {
                    continue;
                }

                String id = dataList.get(0);
                String lastName = dataList.get(1);
                String firstName = dataList.get(2);
                int yearOfBirth = Integer.parseInt(dataList.get(3));
                double mathsGrade = Double.parseDouble(dataList.get(4));
                double physicsGrade = Double.parseDouble(dataList.get(5));
                double chemistryGrade = Double.parseDouble(dataList.get(6));

                Student newStudent = new Student.StudentBuilder(id)
                        .withLastname(lastName)
                        .withFirstname(firstName)
                        .withYearOfBirth(yearOfBirth)
                        .withMathsGrade(mathsGrade)
                        .withPhysicsGrade(physicsGrade)
                        .withChemistryGrade(chemistryGrade)
                        .build();

                studentManager.append(newStudent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (dataReader != null)
                    dataReader.close();
            } catch (IOException crunchifyException) {
                crunchifyException.printStackTrace();
            }
        }
    }

    public static List<String> parseDataLineToList(String dataLine) {
        List<String> result = new ArrayList<>();
        if (dataLine != null) {
            String[] splitData = dataLine.split(COMMA_DELIMITER);
            for (int i = 0; i < splitData.length; i++) {
                result.add(splitData[i]);
            }
        }
        return result;
    }

    public static String[] parseDataLineToArray(String dataLine) {
        if (dataLine == null) {
            return null;
        }

        return dataLine.split(COMMA_DELIMITER);
    }

    public static void main(String[] args) {
        init();
        testOriginalData();
        testSortStudentsByName();
        testSortAverageGradeIncreasing();
        testSortAverageGradeDecreasing();
        testFilterPassStudents();
        testFilterFailureStudents();

        /* Yêu cầu:
        - Hoàn thiện code chương trình theo mẫu và theo yêu cầu đã cho.
        - Viết code để test cho tất cả các hàm test.

        - Thực hiện chạy từng hàm test, lưu kết quả chạy chương trình và file text được đặt tên
          là <TenSinhVien_MaSinhVien_StudentManager>.txt (Ví dụ, NguyenVanA_123456_StudentManager.txt).
        - Nén các file source code và file text kết quả chạy chương trình vào file zip có tên
          <TenSinhVien_MaSinhVien_StudentManager>.zip (Ví dụ, NguyenVanA_123456_StudentManager.zip),
          nộp lên classroom.
         */
    }

    public static void init() {
        String filePath = "C:/Users/FPT/IdeaProjects/untitled/src/data/students.csv";
        readListData(filePath);
    }

    /*
     * Test in ra danh sách các sinh viên theo thứ tự đọc vào ban đầu.
     * Mỗi sinh viên trên một dòng, theo format được định nghĩa trong hàm toString() của Student.
     */
    public static void testOriginalData() {
        System.out.println("Test original data:");
        StudentManager studentManager = StudentManager.getInstance();
        List<Student> students = new ArrayList<>();
        for (int i = 0; i < studentManager.numberOfStudents(); i++) {
            students.add(studentManager.studentAt(i));
        }
        StudentManager.print(students);
        System.out.println("\n");
    }

    /*
     * Test in ra danh sách sinh viên được sắp xếp theo thứ tự tăng dần, đầu tiên tính đến tên, sau đó tính đến họ.
     */
    public static void testSortStudentsByName() {
        System.out.println("Test sort students by name:");
        StudentManager studentManager = StudentManager.getInstance();
        List<Student> sortedStudents = studentManager.sortStudentsByName();
        StudentManager.print(sortedStudents);
        System.out.println("\n");
    }

    /*
     * Test in ra danh sách sinh viên sắp xếp theo thứ tự điểm trung bình tăng dần.
     * Mỗi sinh viên trên một dòng, theo format được định nghĩa trong hàm toString() của Student.
     */
    public static void testSortAverageGradeIncreasing() {
        System.out.println("Test sort students by average grade increasing:");
        StudentManager studentManager = StudentManager.getInstance();
        List<Student> sortedStudents = studentManager.sortAverageGradeIncreasing();
        StudentManager.print(sortedStudents);
        System.out.println("\n");
    }

    /*
     * Test in ra danh sách sinh viên sắp xếp theo thứ tự điểm trung bình giảm dần.
     * Mỗi sinh viên trên một dòng, theo format được định nghĩa trong hàm toString() của Student.
     */
    public static void testSortAverageGradeDecreasing() {
        System.out.println("Test sort students by average grade decreasing:");
        StudentManager studentManager = StudentManager.getInstance();
        List<Student> sortedStudents = studentManager.sortAverageGradeDecreasing();
        StudentManager.print(sortedStudents);
        System.out.println("\n");
    }

    /*
     * Test in ra danh sách sinh viên thi đỗ.
     * Mỗi sinh viên trên một dòng, theo format được định nghĩa trong hàm toString() của Student.
     */
    public static void testFilterPassStudents() {
        System.out.println("Test filter pass students:");
        StudentManager studentManager = StudentManager.getInstance();
        List<Student> passStudents = studentManager.filterPassStudents();
        StudentManager.print(passStudents);
        System.out.println("\n");
    }

    /*
     * Test in ra danh sách sinh viên thi trượt.
     * Mỗi sinh viên trên một dòng, theo format được định nghĩa trong hàm toString() của Student.
     */
    public static void testFilterFailureStudents() {
        System.out.println("Test filter failure students:");
        StudentManager studentManager = StudentManager.getInstance();
        List<Student> failureStudents = studentManager.filterFailureStudents(studentManager.numberOfStudents());
        StudentManager.print(failureStudents);
        System.out.println("\n");
    }
}
