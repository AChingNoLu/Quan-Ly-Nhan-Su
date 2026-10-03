package emplyeemangagement;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class Remove extends JFrame implements ActionListener {
    Choice cEmpId;
    JButton delete, back;
    JLabel lblname, lblphone, lblemail;

    Remove() {
        // Màu nền trắng
        getContentPane().setBackground(Color.WHITE);
        setLayout(null);

        // Tiêu đề
        JLabel heading = new JLabel("Remove Employee");
        heading.setBounds(330, 20, 400, 40);
        heading.setFont(new Font("SansSerif", Font.BOLD, 28));
        heading.setForeground(new Color(30, 30, 30));
        add(heading);

        // Label chọn ID
        JLabel labelempId = new JLabel("Select Employee ID:");
        labelempId.setBounds(100, 100, 180, 30);
        labelempId.setFont(new Font("SansSerif", Font.PLAIN, 18));
        add(labelempId);

        // Dropdown chọn ID
        cEmpId = new Choice();
        cEmpId.setBounds(300, 100, 200, 30);
        add(cEmpId);

        // Load danh sách nhân viên
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM employee";
            ResultSet rs = c.s.executeQuery(query);
            while (rs.next()) {
                cEmpId.add(rs.getString("emID"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        // Label & thông tin nhân viên
        JLabel labelname = new JLabel("Name:");
        labelname.setBounds(100, 160, 100, 30);
        labelname.setFont(new Font("SansSerif", Font.PLAIN, 18));
        add(labelname);

        lblname = new JLabel();
        lblname.setBounds(250, 160, 250, 30);
        lblname.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(lblname);

        JLabel labelphone = new JLabel("Phone:");
        labelphone.setBounds(100, 200, 100, 30);
        labelphone.setFont(new Font("SansSerif", Font.PLAIN, 18));
        add(labelphone);

        lblphone = new JLabel();
        lblphone.setBounds(250, 200, 250, 30);
        lblphone.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(lblphone);

        JLabel labelemail = new JLabel("Email:");
        labelemail.setBounds(100, 240, 100, 30);
        labelemail.setFont(new Font("SansSerif", Font.PLAIN, 18));
        add(labelemail);

        lblemail = new JLabel();
        lblemail.setBounds(250, 240, 250, 30);
        lblemail.setFont(new Font("SansSerif", Font.PLAIN, 16));
        add(lblemail);

        // Lấy thông tin ban đầu
        loadEmployeeDetails(cEmpId.getSelectedItem());

        // Khi chọn nhân viên khác
        cEmpId.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent ie) {
                loadEmployeeDetails(cEmpId.getSelectedItem());
            }
        });

        // Nút Xóa
        delete = new JButton("Delete");
        delete.setBounds(220, 320, 150, 40);
        delete.setBackground(new Color(220, 53, 69));
        delete.setForeground(Color.WHITE);
        delete.setFont(new Font("SansSerif", Font.BOLD, 16));
        delete.setFocusPainted(false);
        delete.setBorderPainted(false);
        delete.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        delete.addActionListener(this);
        add(delete);

        // Nút Quay lại
        back = new JButton("Back");
        back.setBounds(400, 320, 150, 40);
        back.setBackground(new Color(33, 37, 41));
        back.setForeground(Color.WHITE);
        back.setFont(new Font("SansSerif", Font.BOLD, 16));
        back.setFocusPainted(false);
        back.setBorderPainted(false);
        back.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        back.addActionListener(this);
        add(back);

        // Cấu hình cửa sổ
        setSize(750, 450);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void loadEmployeeDetails(String empId) {
        try {
            Conn c = new Conn();
            String query = "SELECT * FROM employee WHERE emID = ?";
            PreparedStatement ps = c.c.prepareStatement(query);
            ps.setString(1, empId);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                lblname.setText(rs.getString("name"));
                lblphone.setText(rs.getString("phone"));
                lblemail.setText(rs.getString("email"));
            } else {
                lblname.setText("");
                lblphone.setText("");
                lblemail.setText("");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == delete) {
            try {
                Conn c = new Conn();
                String query = "DELETE FROM employee WHERE emID = ?";
                PreparedStatement ps = c.c.prepareStatement(query);
                ps.setString(1, cEmpId.getSelectedItem());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null, "Employee deleted successfully!");
                setVisible(false);
                new Home();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getSource() == back) {
            setVisible(false);
            new Home();
        }
    }

    public static void main(String[] args) {
        new Remove();
    }
}
