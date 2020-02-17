import java.util.ArrayList;
import java.util.Scanner;

/**
 * Simple Student Manager
 *
 * @author Regino
 */
// 7处优化
class Student {
    private String sid;
    private String name;
    private String age;
    private String address;

    Student() {
    }

    Student(String sid, String name, String age, String address) {
        this.sid = sid;
        this.name = name;
        this.age = age;
        this.address = address;
    }

    String getSid() {
        return sid;
    }

    void setSid(String sid) {
        this.sid = sid;
    }

    String getName() {
        return name;
    }

    void setName(String name) {
        this.name = name;
    }

    String getAge() {
        return age;
    }

    void setAge(String age) {
        this.age = age;
    }

    String getAddress() {
        return address;
    }

    void setAddress(String address) {
        this.address = address;
    }
}

public class test {
    public static void main(String[] args) {
        ArrayList<Student> array = new ArrayList<>();
        while (true) {
            System.out.println("--------欢迎来到学生管理系统--------");
            System.out.println("1 添加学生");
            System.out.println("2 删除学生");
            System.out.println("3 修改学生");
            System.out.println("4 查看所有学生");
            System.out.println("5 退出");
            System.out.println("请输入你的选择：");

            Scanner sc = new Scanner(System.in);
            String line = sc.nextLine();
            switch (line) {
                case "1":
                    addStudent(array);
                    break;
                case "2":
                    deleteStudent(array);
                    break;
                case "3":
                    updateStudent(array);
                    break;
                case "4":
                    findAllStudent(array);
                    break;
                case "5":
                    System.out.println("谢谢使用！");
                    System.exit(0);// JVM退出
            }
        }
    }

    static void addStudent(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
        String sid;
        while (true) {
            System.out.println("请输入学生学号：\r\n" + "（输入\"b\"返回主菜单）");
            sid = sc.nextLine();
            // 优化1：返回管理主菜单
            if (sid.equals("b")) {
                return;
            } else {
                boolean flag = isUsed(array, sid);
                if (flag) {
                    System.out.println("你输入的学号已经被占用，请重新输入！");
                } else {
                    break;
                }
            }
        }

        System.out.println("请输入学生姓名：");
        String name = sc.nextLine();
        System.out.println("请输入学生年龄：");
        String age = sc.nextLine();
        System.out.println("请输入学生居住地：");
        String address = sc.nextLine();
        Student s = new Student();
        s.setSid(sid);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);
        array.add(s);
        System.out.println("添加学生成功!");
    }

    static boolean isUsed(ArrayList<Student> array, String sid) {
        boolean flag = false;
        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            if (s.getSid().equals(sid)) {
                flag = true;
                break;
            }
        }
        return flag;
    }

    static void findAllStudent(ArrayList<Student> array) {
        if (array.size() == 0) {
            System.out.println("无信息，请先添加信息再查询!");
            return;
        }

        System.out.println("学号\t\t姓名\t\t年龄\t\t居住地");
        for (int i = 0; i < array.size(); i++) {
            Student s = array.get(i);
            // 优化2：改正间隔上下不一致
            System.out.println(s.getSid() + "\t\t" + s.getName() + "\t\t" + s.getAge() + "岁\t\t" + s.getAddress());
        }
    }

    static void deleteStudent(ArrayList<Student> array) {
        Scanner sc = new Scanner(System.in);
        // 优化3：信息不存在则在修改页面重新编辑
        int index = -1;
        while (index == -1) {
            System.out.println("请输入你要删除的学生的学号：\r\n" + "（输入\"b\"返回主菜单）");
            String sid = sc.nextLine();
            // 优化4：返回管理主菜单
            if (sid.equals("b")) {
                return;
            } else {
                for (int i = 0; i < array.size(); i++) {
                    Student s = array.get(i);
                    if (s.getSid().equals(sid)) {
                        index = i;
                        break;
                    }
                }
                if (index == -1) {
                    System.out.println("该信息不存在，请重新输入!");
                } else {
                    array.remove(index);
                    System.out.println("删除学生成功!");
                }
            }
        }
    }

    static void updateStudent(ArrayList<Student> array) {
        // 优化5：检索是否存在成员
        boolean flag = false;
        Scanner sc = new Scanner(System.in);
        String sid = null;
        String name = null;
        String age = null;
        String address = null;
        while (!flag) {
            System.out.println("请输入你要修改的学生学号: \r\n" + "（输入\"b\"返回主菜单）");
            sid = sc.nextLine();
            // 优化6：返回管理主菜单
            if (sid.equals("b")) {
                return;
            } else {
                for (int i = 0; i < array.size(); i++) {
                    Student student = array.get(i);
                    if (student.getSid().equals(sid)) {
                        flag = true;
                        name = student.getName();
                        age = student.getAge();
                        address = student.getAddress();
                        break;
                    }
                }
                if (!flag) {
                    System.out.println("该信息不存在，请重新输入!");
                }
            }
        }
        // 优化7：可选择性更改学生信息
        System.out.println("-----修改学生: \"" + sid + "\"-----");
        System.out.println("1修改学生的多组信息");
        System.out.println("2仅修改学生的姓名");
        System.out.println("3仅修改学生的年龄");
        System.out.println("4仅修改学生的地址");
        System.out.println("5返回主菜单");
        System.out.println("请输入你的选择：");
        String line = sc.nextLine();
        switch (line) {
            case "1":
                System.out.println("请输入学生新姓名：");
                name = sc.nextLine();
                System.out.println("请输入学生新年龄：");
                age = sc.nextLine();
                System.out.println("请输入学生新居住地：");
                address = sc.nextLine();
                break;
            case "2":
                System.out.println("请输入学生新姓名：");
                name = sc.nextLine();
                break;
            case "3":
                System.out.println("请输入学生新年龄：");
                age = sc.nextLine();
                break;
            case "4":
                System.out.println("请输入学生新居住地：");
                address = sc.nextLine();
                break;
            case "5":
                return;
        }

        Student s = new Student();
        s.setSid(sid);
        s.setName(name);
        s.setAge(age);
        s.setAddress(address);
        for (int i = 0; i < array.size(); i++) {
            Student student = array.get(i);
            if (student.getSid().equals(sid))
                array.set(i, s);
        }
        System.out.println("修改学生成功");
    }
}
