package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyWindow extends JFrame implements ActionListener {
    JLabel statusBar;
    public MyWindow(){
        setSize(600, 600);
        setTitle("The First window");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ImageIcon img = new ImageIcon("blue.png");
        setIconImage(img.getImage());

        JPanel p = new JPanel(new GridBagLayout());
        add(p, BorderLayout.WEST);


        JMenuBar menuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenu edit = new JMenu("Edit");
        menuBar.add(edit);

        JMenuItem open = new JMenuItem("Open..");
        open.addActionListener(this);
        fileMenu.add(open);
        JMenuItem save = new JMenuItem("Save..");
        fileMenu.add(save);
        JMenuItem saveAS = new JMenuItem("Save As..");
        save.addActionListener(this);
        fileMenu.add(saveAS);
        JMenuItem close = new JMenuItem("Close");
        fileMenu.add(close);
        close.addActionListener(this);
        //Edit menu
        JMenuItem copy = new JMenuItem("Copy");
        edit.add(copy);
        JMenuItem paste = new JMenuItem("Paste");
        edit.add(paste);

        statusBar = new JLabel("....Status");
        add(statusBar,BorderLayout.SOUTH);
        GridBagConstraints c = new GridBagConstraints();


        JButton btn = new JButton("Click Me");
        JButton btn1 = new JButton("Submit");

        c.insets = new Insets(10,10,10,10);
       // c.gridx = 0;
       // c.gridy = 1;
      //  p.add(btn,c);
        c.gridx = 0;
        c.gridy = 2;
        p.add(btn1);

        btn.setSize(80, 80);
        //add(btn, BorderLayout.SOUTH);


        setJMenuBar(menuBar);
    }
    public static void main(String[] argv){
        MyWindow win = new MyWindow();
        win.setVisible(true);
        win.setLocationRelativeTo(null);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JOptionPane.showMessageDialog(this, e.getActionCommand());
        if("Open..".equals(e.getActionCommand())){
            JOptionPane.showMessageDialog(null, "Hello");
        }
        if("Save..".equals(e.getActionCommand())){
            statusBar.setText("There is nothing to save");
        }
        if("Close".equals(e.getActionCommand())){
            System.exit(0);
        }
    }
}
