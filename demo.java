import java.util.ArrayList;
import java.util.Scanner;

public class demo {

    private static ArrayList<String> todoList = new ArrayList<>();

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("\n 메모 프로그램 \n");

        while (running) {
            System.out.println("1. 할 일 추가");
            System.out.println("2. 할 일 목록 보기");
            System.out.println("3. 할 일 삭제");
            System.out.println("4. 종료");
            System.out.print("선택: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addTodo(scanner);
                    break;
                case 2:
                    viewTodos();
                    break;
                case 3:
                    deleteTodo(scanner);
                    break;
                case 4:
                    running = false;
                    System.out.println("프로그램을 종료합니다.");
                    break;
                default:
                    System.out.println("잘못된 입력입니다. 다시 선택해주세요.");
            }
        }

        scanner.close();
    }

    private static void addTodo(Scanner scanner) {
        System.out.print("추가할 할 일을 입력하세요: ");
        String todo = scanner.nextLine();
        todoList.add(todo);
        System.out.println("할 일이 추가되었습니다.\n");
    }

    private static void viewTodos() {
        System.out.println("\n===== 할 일 목록 =====");
        if (todoList.isEmpty()) {
            System.out.println("등록된 할 일이 없습니다.\n");
        } else {
            for (int i = 0; i < todoList.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, todoList.get(i));
            }
            System.out.println();
        }
    }

    private static void deleteTodo(Scanner scanner) {
        if (todoList.isEmpty()) {
            System.out.println("삭제할 할 일이 없습니다.\n");
            return;
        }

        System.out.println("\n===== 삭제할 할 일 선택 =====");
        viewTodos();
        System.out.print("삭제할 번호를 입력하세요: ");
        int index = scanner.nextInt();
        scanner.nextLine(); // 버퍼 비우기

        if (index < 1 || index > todoList.size()) {
            System.out.println("잘못된 번호입니다.\n");
        } else {
            todoList.remove(index - 1);
            System.out.println("할 일이 삭제되었습니다.\n");
        }
    }
}
