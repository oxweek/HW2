import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Main2 {

    private static ArrayList<String> todoList = new ArrayList<>();
    private static DefaultListModel<JCheckBox> listModel = new DefaultListModel<>();

    public static void main(String[] args) {
        JFrame frame = new JFrame("TODO MEMO 프로그램");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());

        // 할 일 목록 표시
        JList<JCheckBox> todoJList = new JList<>(listModel);
        todoJList.setCellRenderer(new CheckboxListCellRenderer());
        todoJList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int index = todoJList.locationToIndex(e.getPoint());
                if (index != -1) {
                    JCheckBox checkBox = listModel.getElementAt(index);
                    checkBox.setSelected(!checkBox.isSelected());
                    todoJList.repaint();
                }
            }
        });

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
                    JCheckBox checkBox = new JCheckBox(todo);
                    listModel.addElement(checkBox);
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

    static class CheckboxListCellRenderer extends JPanel implements ListCellRenderer<JCheckBox> {
        @Override
        public Component getListCellRendererComponent(JList<? extends JCheckBox> list, JCheckBox value, int index, boolean isSelected, boolean cellHasFocus) {
            setLayout(new BorderLayout());
            removeAll(); // 이전 컴포넌트 제거

            if (isSelected) {
                setBackground(list.getSelectionBackground());
                value.setBackground(list.getSelectionBackground());
            } else {
                setBackground(list.getBackground());
                value.setBackground(list.getBackground());
            }
            add(value, BorderLayout.CENTER);
            return this;
        }
    }
}
