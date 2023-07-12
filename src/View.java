import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class View extends JFrame {
    private final Model model;
    private int seasonSelected = -1;
    private final ArrayList<JLabel> jLabels = new ArrayList<>();
    private final ArrayList<JButton> jLabel2s = new ArrayList<>();

    public View(Model model) {
        this.setTitle("开房小助手，老手都在用");
        this.setSize(600, 800);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.GRAY);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.model = model;
        addLabels();
        addButtons();
    }

    public void addLabels() {
        JLabel label1 = new JLabel("请选择淡旺季");
        label1.setBounds(220, 10, 150, 50);
        label1.setFont(new Font("MS Song", Font.BOLD, 20));
        add(label1);
    }

    private void addButtons() {
        JButton button1 = new JButton("点我添加入住成员");
        JButton button2 = new JButton("清空所有成员");
        JButton button3 = new JButton("点我计算总费用");
        JRadioButton radioButton1 = new JRadioButton("淡季模式");
        JRadioButton radioButton2 = new JRadioButton("旺季模式");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        button1.setBounds(170, 500, 270, 50);
        button2.setBounds(170, 600, 270, 50);
        button3.setBounds(170, 700, 270, 50);
        radioButton1.setBounds(110, 47, 180, 50);
        radioButton2.setBounds(420, 47, 180, 50);
        radioButton1.setFont(new Font("MS Song", Font.BOLD, 20));
        radioButton2.setFont(new Font("MS Song", Font.BOLD, 20));
        button1.setBackground(Color.gray);
        button2.setBackground(Color.gray);
        button3.setBackground(Color.ORANGE);
        button1.setFont(new Font("MS Song", Font.PLAIN, 25));
        button2.setFont(new Font("MS Song", Font.PLAIN, 25));
        button3.setFont(new Font("MS Song", Font.BOLD, 30));

        button1.addActionListener(e -> {
            if (seasonSelected == -1) {
                JOptionPane.showMessageDialog(null, "请先选择淡旺季");
                return;
            }
            AddingFrame addingFrame = new AddingFrame(View.this);
            addingFrame.setVisible(true);
        });
        button2.addActionListener(e -> {
            model.getPeople().clear();
            for (JLabel jLabel : jLabels) {
                this.remove(jLabel);
            }
            for (JButton jLabel : jLabel2s) {
                this.remove(jLabel);
            }
            jLabels.clear();
            jLabel2s.clear();
            this.repaint();
        });
        button3.addActionListener(e -> JOptionPane.showMessageDialog(null, "当前总费用为:" + model.calculatePrice() + "元"));
        radioButton1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                seasonSelected = 0;
                model.setPeakSeason(false);
            }
        });
        radioButton2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                seasonSelected = 1;
                model.setPeakSeason(true);
            }
        });
        add(button1);
        add(button2);
        add(button3);
        add(radioButton1);
        add(radioButton2);
    }

    public void updateLabels() {
        assert model.getPeople().size() > 0;
        int index = model.getPeople().size() - 1;
        Person p = model.getPeople().get(index);
        JLabel jLabel = new JLabel();
        JButton jLabel2 = new JButton();
        jLabel.setText("第" + (index + 1) + "位旅客   " + p.toString());
        jLabel2.setText("移除此人");
        jLabel.setBounds(20, 58 + model.getPeople().size() * 30, 550, 50);
        jLabel2.setBounds(460, 70 + model.getPeople().size() * 30, 100, 30);
        jLabel.setForeground(Color.MAGENTA);
        jLabel.setFont(new Font("MS Song", Font.BOLD, 20));
        jLabel2.setForeground(Color.RED);
        jLabel2.setFont(new Font("MS Song", Font.BOLD, 15));
        jLabels.add(jLabel);
        jLabel2s.add(jLabel2);
        jLabel2.addActionListener(e -> {
            int i = jLabel2s.indexOf(jLabel2);
            model.getPeople().remove(i);
            this.remove(jLabels.get(i));
            this.remove(jLabel2s.get(i));
            jLabels.remove(i);
            jLabel2s.remove(i);
            for (int j = 0; j < jLabels.size(); j++) {
                jLabels.get(j).setBounds(20, 58 + (j + 1) * 30, 550, 50);
            }
            for (int j = 0; j < jLabel2s.size(); j++) {
                jLabel2s.get(j).setBounds(460, 70 + (j + 1) * 30, 100, 30);
            }
            this.repaint();
        });
        add(jLabel);
        add(jLabel2);
        this.repaint();
    }

    public Model getModel() {
        return model;
    }
}
