import java.lang.*;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

// create table student(RID int,Name varchar(255),Salary int);
// Database table / Schema
class Student {
    public int RID;
    public String Name;
    public int Salary;

    public static int Generator;
    static {
        Generator = 0;
    }

    public Student(String str, int value) {
        this.RID = ++Generator;
        this.Name = str;
        this.Salary = value;
    }

    public void DisplayData() {
        System.out.format("| %5s | %10s | %10s |", this.RID, this.Name, this.Salary);
        System.out.println();
        // System.out.println("| "+this.RID + "\t\t |" + this.Name + "\t\t |" +
        // this.Salary+"\t\t |");
        System.out.println("+-------+------------+------------+");
    }
}

class DBMS {
    public LinkedList<Student> lobj;

    public DBMS() {
        lobj = new LinkedList<>();
    }

    public void StartDBMS() {
        Scanner sobj = new Scanner(System.in);

        System.out.println("Customised DBMS started successfully....");
        String Query = "";
        while (true) {
            System.out.println("DBMS console >");
            Query = sobj.nextLine();
            String LQuery = Query.toLowerCase();

            String tokens[] = LQuery.split(" ");
            int QuerySize = tokens.length;
            if (QuerySize == 1) {
                if ("help".equals(tokens[0])) {
                    System.out.println(" This application is used to demonstrate customised DBMS System");
                    System.out.println();
                    System.out.println(" Command : Exit ");
                    System.out.println(" Description : Terminate DBMS");
                    System.out.println();
                    System.out.println(" Command : select * from Student \n Description : Display all data");
                    System.out.println();
                    System.out.println(
                            " Command : select * from Student where RID = Student_RID \n Description : Display  data According to RID");
                    System.out.println();
                    System.out.println(
                            " Command : select * from Student where name = Student_Name \n Description : Display  data According to Name : ");
                    System.out.println();
                    System.out.println(
                            " Command : Insert into Student Name Salary \n Description : Insert records into Student table");
                    System.out.println();
                    System.out.println(
                            " Command : select max salary from student \n Description : Display Maximum Salary");
                    System.out.println();
                    System.out.println(
                            " Command : select min salary from student \n Description : Display Minimum Salary ");
                    System.out.println();
                    System.out.println(
                            " Command : select sum salary from student \n Description : Display Summation of Salary ");
                    System.out.println();
                    System.out.println(
                            " Command : select avg salary from student \n Description : Display Average Salary ");
                    System.out.println();
                    System.out.println(
                            " Command : delete from student where RID = Student_RID \n Description : Delete record by RID ");
                    System.out.println();
                    System.out.println(
                            " Command : delete from student where Name = Student_Name \n Description : Delete record by Name ");
                    System.out.println();
                    System.out.println(" Command : select count * from student  \n Description : Count records ");
                    System.out.println();
                    System.out.println(
                            " Command : select count rid from student where salary >= amount \n Description : Count Salary ");
                    System.out.println();
                    System.out.println(
                            " Command : select count rid from student where salary <= amount \n Description : Count Salary ");
                    System.out.println();

                } else if ("exit".equals(tokens[0])) {
                    System.out.println("Thank you for using DBMS Application");
                    break;
                }

            }

            else if ("select".equals(tokens[0]) && (QuerySize == 4 || QuerySize == 8 || QuerySize == 5 || QuerySize == 9)) {
                // select * from student
                // select * from Student where RollNo = 11
                    if (("*".equals(tokens[1])) && QuerySize == 4) {
                        if ("from".equals(tokens[2]) && "student".equals(tokens[3])) {
                            DisplayAll();
                        } else {
                            System.out.println("ERROR : Command not Found");
                        }

                    } else if (("*".equals(tokens[1])) && QuerySize == 8) {
                        if ("from".equals(tokens[2]) && "student".equals(tokens[3]) && "where".equals(tokens[4])) {
                            if ("rid".equals(tokens[5]) && "=".equals(tokens[6])) {
                                DisplaySpecific(Integer.parseInt(tokens[7]));
                            } else if ("name".equals(tokens[5]) && "=".equals(tokens[6])) {
                                DisplaySpecific(tokens[7]);
                            } else {
                                System.out.println("ERROR : Command not Found");
                            }
                        } else {
                            System.out.println("ERROR : Command not Found");
                        }
                    }
                    // SELECT COUNT rid FROM Student WHERE Salary >= 75000
                    else if (("count".equals(tokens[1])) && ((QuerySize == 5) || (QuerySize == 9))) {
                        if ("*".equals(tokens[2]) && "from".equals(tokens[3]) && "student".equals(tokens[4])) {
                            AggregateCount();
                        } 
                        else if ("rid".equals(tokens[2]) && "from".equals(tokens[3]) && "student".equals(tokens[4]) && "where".equals(tokens[5]) && "salary".equals(tokens[6])) {
                            if(">=".equals(tokens[7]))
                            {
                                CountGreater(Integer.parseInt(tokens[8]));
                            }
                            else if ("<=".equals(tokens[7])) {
                            CountLess(Integer.parseInt(tokens[8]));
                            }
                            else if ("==".equals(tokens[7])) {
                            CountEqual(Integer.parseInt(tokens[8]));
                            }
                            else
                            {
                                System.out.println("ERROR : Command not Found");
                            }
                    }
                }
                    // select min salary from student
                    else if ("min".equals(tokens[1]) && "salary".equals(tokens[2]) && "from".equals(tokens[3]) && "student".equals(tokens[4])) {
                        AggregateMin();
                    } else if ("max".equals(tokens[1]) && "salary".equals(tokens[2]) && "from".equals(tokens[3]) && "student".equals(tokens[4])) {
                        AggregateMax();
                    } else if ("sum".equals(tokens[1]) && "salary".equals(tokens[2]) && "from".equals(tokens[3]) && "student".equals(tokens[4])) {
                        AggregateSum();
                    } else if ("avg".equals(tokens[1]) && "salary".equals(tokens[2]) && "from".equals(tokens[3]) && "student".equals(tokens[4])) {
                        AggregateAvg();
                    }
                    else 
                    {
                        System.out.println("ERROR : Command not Found");
                    }
                }

            else if ("insert".equals(tokens[0]) && QuerySize == 6) {
                // Insert into student values Rutuja 1000
                if ("into".equals(tokens[1]) && "student".equals(tokens[2]) && "values".equals(tokens[3])) {
                    InsertData(tokens[4], Integer.parseInt(tokens[5]));
                }
                else
                {
                    System.out.println("ERROR : Command not Found");
                }
            }

            else if ("delete".equals(tokens[0]) && QuerySize == 7) {
                // Delete from student where RID = 22
                if ("from".equals(tokens[1]) && "student".equals(tokens[2]) && "where".equals(tokens[3])) {
                    if ("rid".equals(tokens[4]) && "=".equals(tokens[5])) {
                        DeleteSpecific(Integer.parseInt(tokens[6]));
                    } else if ("name".equals(tokens[4]) && "=".equals(tokens[5])) {
                        DeleteSpecific(tokens[6]);
                    }
                    else
                    {
                        System.out.println("ERROR : Command not Found");
                    }
                }
                else
                {
                    System.out.println("ERROR : Command not Found");
                }
                
            }

            // update student set salary = 20000 where rid = 10
            else if ("update".equals(tokens[0]) && QuerySize == 10) {
                if ("student".equals(tokens[1]) && "set".equals(tokens[2]) && "salary".equals(tokens[3]) && "=".equals(tokens[4]) && "where".equals(tokens[6])) {
                    if ("rid".equals(tokens[7]) && "=".equals(tokens[8])) {
                        UpdateSpecific(Integer.parseInt(tokens[9]), Integer.parseInt(tokens[5]));
                    }
                    else if ("name".equals(tokens[7])&& "=".equals(tokens[8])) {
                        UpdateSpecific(tokens[9], Integer.parseInt(tokens[5]));
                    }
                    else
                    {
                        System.out.println("ERROR : Command not Found");
                    }

                }
                else
                {
                    System.out.println("ERROR : Command not Found");
                }
            }
            else
            {
                System.out.println("ERROR : Command not Found");
            }
        }

    }

    public void InsertData(String str, int value) {
        Student sobj = new Student(str, value);
        if(lobj.add(sobj)==true)
        {
            System.out.println("1 record inserted successfully");
        }
        else
        {
            System.out.println("Failed to insert record");
        }
    }

    public void DisplayAll() {
        System.out.println("+-------+------------+------------+");
        System.out.printf("| %5s | %10s | %10s |", "RID", "NAME", "SALARY");
        System.out.println();
        System.out.println("+-------+------------+------------+");
        for (Student sref : lobj) {
            sref.DisplayData();
        }
    }

    public void DisplaySpecific(int rid) {
        System.out.println("+-------+------------+------------+");
        System.out.printf("| %5s | %10s | %10s |", "RID", "NAME", "SALARY");
        System.out.println();
        System.out.println("+-------+------------+------------+");
        for (Student sref : lobj) {
            if (sref.RID == rid) {
                sref.DisplayData();
                break;
            }
        }
    }

    public void DisplaySpecific(String name) {
        System.out.println("+-------+------------+------------+");
        System.out.printf("| %5s | %10s | %10s |", "RID", "NAME", "SALARY");
        System.out.println();
        System.out.println("+-------+------------+------------+");
        for (Student sref : lobj) {
            if ((sref.Name).equals(name)) {
                sref.DisplayData();
            }
        }
    }

    public void DeleteSpecific(int rid) {
        int index = 0;
        for (Student sref : lobj) {
            if (sref.RID == rid) {
                lobj.remove(index);
                System.out.println("1 Record deleted successfully");
                break;
            }
            index++;
        }
        if(index==lobj.size())
        {
            System.out.println("There is no such record");
        }
    }

    public void DeleteSpecific(String name) {
        int index = 0;
        for (Student sref : lobj) {
            if ((sref.Name).equals(name)) {
                lobj.remove(index);
                System.out.println("1 Record deleted successfully");
                break;
            }
            index++;
        }
        if(index==lobj.size())
        {
            System.out.println("There is no such record");
        }
    }

    public void UpdateSpecific(String name, int salary) {

        for (Student sref : lobj) {
            if ((sref.Name).equals(name)) {
                sref.Salary = salary;
                System.out.println("Record updated successfully");
                break;
            }

        }
    }

    public void UpdateSpecific(int rid, int salary) {

        for (Student sref : lobj) {
            if ((sref.RID) == (rid)) {
                sref.Salary = salary;
                System.out.println("Record updated successfully");
                break;
            }

        }
    }

    public void CountEqual(int salary) {
        int iCnt = 0;
        for (Student sref : lobj) {
            if (sref.Salary == salary) {
                iCnt++;
            }
        }
        System.out.println("Students having salary equal to " + salary + " are : " + iCnt);

    }

    public void CountGreater(int salary) {
        int iCnt = 0;
        for (Student sref : lobj) {
            if (sref.Salary >= salary) {
                iCnt++;
            }
        }
        System.out.println("Students having salary greater than " + salary + " are : " + iCnt);

    }

    public void CountLess(int salary) {
        int iCnt = 0;
        for (Student sref : lobj) {
            if (sref.Salary <= salary) {
                iCnt++;
            }
        }
        System.out.println("Students having salary less than " + salary + " are : " + iCnt);

    }

    public void AggregateMax() {
        int iMax = 0;
        Student temp = null;
        for (Student sref : lobj) {
            if (sref.Salary > iMax) {
                iMax = sref.Salary;
                temp = sref;
            }

        }
        System.out.println("Information of student having Maximum Salary ");
        temp.DisplayData();
    }

    public void AggregateMin() {
        int iMin = (lobj.getFirst()).Salary;
        Student temp = lobj.getFirst();
        for (Student sref : lobj) {
            if (sref.Salary < iMin) {
                iMin = sref.Salary;
                temp = sref;
            }

        }
        System.out.println("Information of student having Minimum Salary ");
        temp.DisplayData();
    }

    public void AggregateSum() {
        int iSum = 0;
        for (Student sref : lobj) {
            iSum += sref.Salary;

        }
        System.out.println("Summation of Salaries : " + iSum);
    }

    public void AggregateAvg() {
        long iSum = 0;
        for (Student sref : lobj) {
            iSum += sref.Salary;

        }
        long iAvg = iSum / (lobj.size());
        System.out.println("Average Salary is : " + iAvg);
    }

    public void AggregateCount() {
        System.out.println("Total number of Students : " + lobj.size());
    }
}

class MyDBMS {
    public static void main(String arg[]) {
        DBMS dobj = new DBMS();
        dobj.StartDBMS();

    }
}
