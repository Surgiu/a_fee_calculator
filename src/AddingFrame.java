import javax.swing.*;
import java.awt.*;
import java.util.Objects;

public class AddingFrame extends JFrame {
    View view;
    JPanel jp;
    JLabel label1;
    JLabel label2;
    JLabel label3;
    JComboBox<String> box;
    JTextField name;
    JTextField age;
    JButton Close;
    int liveWith = -1;
    int room = 0;

    public AddingFrame(View view) {
        this.setTitle("添加旅客");
        this.setBounds(720, 410, 320, 385);
        this.setLocationRelativeTo(null);
        this.setLayout(new FlowLayout());
        this.setVisible(true);
        this.view = view;

        label1 = new JLabel("请输入姓名:");
        name = new JTextField("", 21);
        label2 = new JLabel("请输入年龄:");
        age = new JTextField("", 21);
        label3 = new JLabel("若该旅客未满18岁请选择以下入住方式:");
        box = new JComboBox<>();
        box.addItem("请选择小孩入住方式(如无小孩请忽略)");
        box.addItem("加床");
        box.addItem("不加床");
        box.addItem("单间房");
        jp = new JPanel();
        Close = new JButton("保存并关闭");
        add(label1);
        add(name);
        add(label2);
        add(age);
        add(label3);
        add(box);
        add(jp);
        jp.add(Close);
        addButtons();
    }

    public void addButtons() {
        box.addActionListener(e -> {
            switch ((String) Objects.requireNonNull(box.getSelectedItem())) {
                case "加床" -> liveWith = 0;
                case "不加床" -> liveWith = 1;
                case "单间房" -> {
                    liveWith = 2;
                    room = 1;
                }
            }
            this.repaint();
        });
        Close.addActionListener(e -> {
            if (this.age.getText().equals("") || this.age.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "姓名或年龄为空", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Integer.parseInt(this.age.getText()) < 0) {
                JOptionPane.showMessageDialog(null, "年龄不能为负数", "错误", JOptionPane.ERROR_MESSAGE);
                return;
            }
            if (Integer.parseInt(this.age.getText()) < 18 && liveWith == -1) {
                JOptionPane.showMessageDialog(null, "请为小孩选择入住方式", "提示", JOptionPane.ERROR_MESSAGE);
                return;
            } else {
                view.getModel().addPerson(this.name.getText(), Integer.parseInt(this.age.getText()), liveWith);
                view.getModel().setSingleRoomNum(view.getModel().getSingleRoomNum() + room);
                view.updateLabels();
                liveWith = -1;
                this.name.setText("");
                this.age.setText("");
                AddingFrame.this.room = 0;
            }
            view.addLabels();
            AddingFrame.this.dispose();
        });
    }
}
