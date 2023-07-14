import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.util.ArrayList;

public class View extends JFrame {
    private final Model model;
    private int seasonSelected = -1;
    JLabel label1;
    private final ArrayList<JLabel> jLabels = new ArrayList<>();
    private final ArrayList<JButton> jLabel2s = new ArrayList<>();

    public View(Model model) {
        this.setTitle("开房小助手，老手都在用");
        this.setSize(600, 800);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.getContentPane().setBackground(new Color(28, 77, 77));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.model = model;
        addLabels();
        addButtons();
        this.repaint();
    }

    public void addLabels() {
        label1 = new JLabel("请选择淡旺季");
        label1.setBounds(240, 10, 250, 50);
        label1.setFont(new Font("MS Song", Font.BOLD, 20));
        label1.setVisible(true);
        add(label1);
    }

    private void addButtons() {
        JButton button1 = new JButton("点我添加入住成员");
        JButton button2 = new JButton("清空所有成员");
        JButton button3 = new JButton("点我计算总费用");
        JButton button4 = new JButton("查看收费标准");
        JRadioButton radioButton1 = new JRadioButton("淡季模式");
        JRadioButton radioButton2 = new JRadioButton("旺季模式");
        ButtonGroup buttonGroup = new ButtonGroup();
        buttonGroup.add(radioButton1);
        buttonGroup.add(radioButton2);
        button1.setBounds(172, 500, 270, 50);
        button2.setBounds(172, 600, 270, 50);
        button3.setBounds(172, 700, 270, 50);
        button4.setBounds(500, 12, 90, 25);
        radioButton1.setBounds(100, 47, 120, 50);
        radioButton2.setBounds(400, 47, 120, 50);
        radioButton1.setFont(new Font("MS Song", Font.BOLD, 20));
        radioButton2.setFont(new Font("MS Song", Font.BOLD, 20));
        radioButton1.setBackground(new Color(28, 77, 77));
        radioButton2.setBackground(new Color(28, 77, 77));
        button1.setBackground(new Color(104, 222, 86));
        button2.setBackground(Color.gray);
        button3.setBackground(Color.ORANGE);
        button4.setBackground(Color.LIGHT_GRAY);
        button1.setFont(new Font("MS Song", Font.BOLD, 25));
        button2.setFont(new Font("MS Song", Font.PLAIN, 25));
        button3.setFont(new Font("MS Song", Font.BOLD, 30));
        button4.setFont(new Font("MS Song", Font.PLAIN, 9));
        button1.addActionListener(e -> {
            if (seasonSelected == -1) {
                JOptionPane.showMessageDialog(null, "请选择淡旺季");
                return;
            }
            AddingFrame addingFrame = new AddingFrame(View.this);
            addingFrame.setVisible(true);
        });
        button2.addActionListener(e -> {
            int t = JOptionPane.showConfirmDialog(null, "确定要清空所有成员吗？", "提示", JOptionPane.YES_NO_OPTION);
            if (t == JOptionPane.NO_OPTION) return;
            model.getPeople().clear();
            model.setSingleRoomNum(0);
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
        button3.addActionListener(e -> {
            int r = JOptionPane.showConfirmDialog(null, "当前总费用为: " + model.calculatePrice() + " 元。\n是否查看账目明细？", "费用统计", JOptionPane.YES_NO_OPTION);
            if (r == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(null, model.getDetail());
            }
        });
        radioButton1.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                seasonSelected = 0;
                radioButton1.setForeground(Color.WHITE);
                radioButton2.setForeground(Color.gray);
                label1.setText("");
                add(label1);
                this.repaint();
                model.setPeakSeason(false);
            }
        });
        button4.addActionListener(e -> JOptionPane.showMessageDialog(this, """
                                        
                                            收费标准
                                        
                    一个成人的价格是1799，一个儿童不要床位的价格是1500，如果儿童需要床位则价格变为2099,
                如果需要单间房则需要补淡季1400，旺季1600的单间房差价
                除此之外65岁以上老人在成人基础上要加300元老人费用
                然后上面就是基础套餐，两个人的套餐，
                然后下面会新增超过两个人的要求，和在旺季情况下增加的费用               \s
                旺季情况下每个人都需要增加500元的旺季涨价费用                              \s
                    现在有个超比例费用概念：
                因为优惠针对的是24到65岁的人，所以如果一个符合年龄段的人加上一个不符合年龄段的人我们称为不超比例，
                也就是1:1（不超:超），我们不收取超比例费用，
                如果超过这个比例，比如1:2（不超：超）我们便按超过一个人比例的费用多收取300元，
                同理1:3便多收取600元，而2:1（不超：超）不收取超比例费用，可以理解为一个符合年龄段的人自动可以
                同化一个不符合年龄段的人，不符合年龄段的人额外要多收300元
                """));
        radioButton2.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                seasonSelected = 1;
                radioButton2.setForeground(Color.WHITE);
                radioButton1.setForeground(Color.gray);
                label1.setText("");
                this.repaint();
                model.setPeakSeason(true);
            }
        });
        button1.setVisible(true);
        button2.setVisible(true);
        button3.setVisible(true);
        button4.setVisible(true);
        radioButton1.setVisible(true);
        radioButton2.setVisible(true);
        add(button1);
        add(button2);
        add(button3);
        add(button4);
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
        jLabel2.setBounds(480, 70 + model.getPeople().size() * 30, 100, 30);
        jLabel.setForeground(Color.WHITE);
        jLabel.setFont(new Font("MS Song", Font.BOLD, 20));
        jLabel2.setForeground(new Color(37, 37, 37));
        jLabel2.setBackground(new Color(234, 93, 247));
        jLabel2.setFont(new Font("MS Song", Font.BOLD, 15));
        jLabels.add(jLabel);
        jLabel2s.add(jLabel2);
        jLabel2.addActionListener(e -> {
            int i = jLabel2s.indexOf(jLabel2);
            Person person = model.getPeople().get(i);
            if (person.getAge() < 18) {
                if (((Child) person).getLiveWith() == 2) {
                    model.setSingleRoomNum(model.getSingleRoomNum() - 1);
                }
            }
            model.getPeople().remove(i);
            this.remove(jLabels.get(i));
            this.remove(jLabel2s.get(i));
            jLabels.remove(i);
            jLabel2s.remove(i);
            for (int j = 0; j < jLabels.size(); j++) {
                jLabels.get(j).setBounds(20, 58 + (j + 1) * 30, 550, 50);
                jLabels.get(j).setVisible(true);
                jLabels.get(j).setText("第" + (j + 1) + "位旅客   " + model.getPeople().get(j).toString());
            }
            for (int j = 0; j < jLabel2s.size(); j++) {
                jLabel2s.get(j).setBounds(480, 70 + (j + 1) * 30, 100, 30);
                jLabel2s.get(j).setVisible(true);
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
