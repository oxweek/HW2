import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Main1 {

    private static ArrayList<String> todoList = new ArrayList<>();
    private static DefaultListModel<String> listModel = new DefaultListModel<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("TODO MEMO 프로그램");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 할 일 목록 표시
        JList<String> todoJList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(todoJList);
        panel.add(scrollPane, BorderLayout.CENTER);

        // 하단 버튼 패널
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton addButton = new JButton("추가");
        JButton deleteButton = new JButton("삭제");

        buttonPanel.add(addButton);
        buttonPanel.add(deleteButton);

        panel.add(buttonPanel, BorderLayout.SOUTH);

        frame.add(panel);

        // 버튼 이벤트 처리
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String todo = JOptionPane.showInputDialog(frame, "추가할 할 일을 입력하세요:", "할 일 추가", JOptionPane.PLAIN_MESSAGE);
                if (todo != null && !todo.trim().isEmpty()) {
                    todoList.add(todo);
                    listModel.addElement(todo);
                }
            }
        });

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = todoJList.getSelectedIndex();
                if (selectedIndex != -1) {
                    todoList.remove(selectedIndex);
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "삭제할 항목을 선택하세요.", "오류", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        frame.setVisible(true);
    }
}
