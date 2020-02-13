import java.util.ArrayList;
import java.util.Scanner;

// 7���Ż�
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

public class StudentManager {
	public static void main(String[] args) {
		ArrayList<Student> array = new ArrayList<>();
		while (true) {
			System.out.println("--------��ӭ����ѧ������ϵͳ--------");
			System.out.println("1 ���ѧ��");
			System.out.println("2 ɾ��ѧ��");
			System.out.println("3 �޸�ѧ��");
			System.out.println("4 �鿴����ѧ��");
			System.out.println("5 �˳�");
			System.out.println("���������ѡ��");

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
				System.out.println("ллʹ�ã�");
				System.exit(0);// JVM�˳�
			}
		}
	}

	static void addStudent(ArrayList<Student> array) {
		Scanner sc = new Scanner(System.in);
		String sid;
		while (true) {
			System.out.println("������ѧ��ѧ�ţ�\r\n" + "������\"b\"�������˵���");
			sid = sc.nextLine();
			// �Ż�1�����ع������˵�
			if (sid.equals("b")) {
				return;
			} else {
				boolean flag = isUsed(array, sid);
				if (flag) {
					System.out.println("�������ѧ���Ѿ���ռ�ã����������룡");
				} else {
					break;
				}
			}
		}

		System.out.println("������ѧ��������");
		String name = sc.nextLine();
		System.out.println("������ѧ�����䣺");
		String age = sc.nextLine();
		System.out.println("������ѧ����ס�أ�");
		String address = sc.nextLine();
		Student s = new Student();
		s.setSid(sid);
		s.setName(name);
		s.setAge(age);
		s.setAddress(address);
		array.add(s);
		System.out.println("���ѧ���ɹ�!");
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
			System.out.println("����Ϣ�����������Ϣ�ٲ�ѯ!");
			return;
		}

		System.out.println("ѧ��\t\t����\t\t����\t\t��ס��");
		for (int i = 0; i < array.size(); i++) {
			Student s = array.get(i);
			// �Ż�2������������²�һ��
			System.out.println(s.getSid() + "\t\t" + s.getName() + "\t\t" + s.getAge() + "��\t\t" + s.getAddress());
		}
	}

	static void deleteStudent(ArrayList<Student> array) {
		Scanner sc = new Scanner(System.in);
		// �Ż�3����Ϣ�����������޸�ҳ�����±༭
		int index = -1;
		while (index == -1) {
			System.out.println("��������Ҫɾ����ѧ����ѧ�ţ�\r\n" + "������\"b\"�������˵���");
			String sid = sc.nextLine();
			// �Ż�4�����ع������˵�
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
					System.out.println("����Ϣ�����ڣ�����������!");
				} else {
					array.remove(index);
					System.out.println("ɾ��ѧ���ɹ�!");
				}
			}
		}
	}

	static void updateStudent(ArrayList<Student> array) {
		// �Ż�5�������Ƿ���ڳ�Ա
		boolean flag = false;
		Scanner sc = new Scanner(System.in);
		String sid = null;
		String name = null;
		String age = null;
		String address = null;
		while (!flag) {
			System.out.println("��������Ҫ�޸ĵ�ѧ��ѧ��: \r\n" + "������\"b\"�������˵���");
			sid = sc.nextLine();
			// �Ż�6�����ع������˵�
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
					System.out.println("����Ϣ�����ڣ�����������!");
				}
			}
		}
		// �Ż�7����ѡ���Ը���ѧ����Ϣ
		System.out.println("-----�޸�ѧ��: \"" + sid + "\"-----");
		System.out.println("1�޸�ѧ���Ķ�����Ϣ");
		System.out.println("2���޸�ѧ��������");
		System.out.println("3���޸�ѧ��������");
		System.out.println("4���޸�ѧ���ĵ�ַ");
		System.out.println("5�������˵�");
		System.out.println("���������ѡ��");
		String line = sc.nextLine();
		switch (line) {
		case "1":
			System.out.println("������ѧ����������");
			name = sc.nextLine();
			System.out.println("������ѧ�������䣺");
			age = sc.nextLine();
			System.out.println("������ѧ���¾�ס�أ�");
			address = sc.nextLine();
			break;
		case "2":
			System.out.println("������ѧ����������");
			name = sc.nextLine();
			break;
		case "3":
			System.out.println("������ѧ�������䣺");
			age = sc.nextLine();
			break;
		case "4":
			System.out.println("������ѧ���¾�ס�أ�");
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
		System.out.println("�޸�ѧ���ɹ�");
	}
}
