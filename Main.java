import java.io.*;
import java.util.*;

class Student {
    private int id;x
    private String name;
    private double marks;

    public Student(int id, String name, double marks) {
        this.id = id;
        this.name = name;
        this.marks = marks;
    }

    public String toString() {
        return id + " " + name + " " + marks;
    }
}

class StudentManager {
    public void writeStudent() {
        Scanner sc = new Scanner(System.in);
        try {
            System.out.print("Enter id: ");
            int id = sc.nextInt();
            sc.nextLine(); 
            System.out.print("Enter name: ");
            String name = sc.nextLine();
            System.out.print("Enter marks: ");
            double marks = sc.nextDouble();
            
            FileWriter fw = new FileWriter("students.txt", true);
            fw.write(id + "," + name + "," + marks + "\n");
            fw.close();
            System.out.println("Student added successfully.");
        } catch (InputMismatchException e) {
            System.out.println("Invalid input. Please enter correct types.");
        } catch (IOException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    public void readStudents() {
        try {
            FileReader fr = new FileReader("students.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            System.out.println("Student Records:");
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    try {
                        int id = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        double marks = Double.parseDouble(parts[2]);
                        System.out.println("ID: " + id + ", Name: " + name + ", Marks: " + marks);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid record format: " + line);
                    }
                } else {
                    System.out.println("Invalid record: " + line);
                }
            }
            br.close();
        } catch (FileNotFoundException e) {
            System.out.println("File does not exist. No records to display.");
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
}

class Main {
    public static void main(String[] args) {
        StudentManager sm = new StudentManager();
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("1. Add Student");
            System.out.println("2. View Students");
            System.out.println("3. Exit");
            System.out.print("Choose: ");
            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        sm.writeStudent();
                        break;
                    case 2:
                        sm.readStudents();
                        break;
                    case 3:
                        System.exit(0);
                    default:
                        System.out.println("Invalid choice.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid choice. Please enter a number.");
                sc.nextLine(); 
            }
        }
    }
}