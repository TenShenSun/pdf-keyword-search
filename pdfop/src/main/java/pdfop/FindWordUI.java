package pdfop;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

public class FindWordUI {

    static String selectedDir = null;
    static String selectedSaveDir = null;
    static String keyWord = null;


    public static void main(String[] args) {
        initUI();

    }


    public  static void initUI(){

        //1.1创建一个顶级容器，也就是空白窗口，并为此窗口设置属性（窗口名称，大小，显示位置，关闭设置）

        // 用JFrame创建一个名为frame的顶级容器，需要添加的包名为javax.swing.JFrame
        JFrame frame=new JFrame();
        //设置窗口名称
        frame.setTitle("Find KeyWord In The File");
        //设置窗口大小
        frame.setSize(540,427);
        //设置窗口位于屏幕中央
        frame.setLocationRelativeTo(null);
        //参数为3时，表示关闭窗口则程序退出
        frame.setDefaultCloseOperation(3);



        //1.2设置窗体上组件的布局，此处使用流式布局FlowLayout，流式布局类似于word的布局
        //用FlowLayout创建一个名为f1的对象,需要添加的包名为java.awt.FlowLayout，其中LEFT表示左对齐，CENTER表示居中对齐，RIGHT表示右对齐
        FlowLayout f1=new FlowLayout(FlowLayout.LEFT);
        //frame窗口设置为f1的流式左对齐
        frame.setLayout(f1);



        //创建一个空的JLabel，它的长度宽度为110,30，因为窗口是流式左对齐，为了将”账号”一栏添加在正中间，所以左侧由空的JLabel填充
        JLabel qnd=new JLabel();
        //设置空JLabel长度大小，此处不能使用setSize设置大小，setSize只能设置顶级容器大小，此处用setPreferredSize，Dimension给出大小，需要添加的包名为java.awt.Dimension.
        qnd.setPreferredSize(new Dimension(60,30));
        //将空JLabel添加入窗口
        frame.add(qnd);




        //1.3在窗体上添加图片，文字

        //在添加图片之前，先把图片从磁盘中加载到内存中来，使用ImageIcon，需要添加的包名为javax.swing.ImageIcon,括号中为图片路径，路径中要使用”/”,不能使用”\”
        ImageIcon imag1=new ImageIcon("img/logo.jpg");
        //JLabel可在顶级容器中添加图片文字，需要添加的包名javax.swing.JLabel,此处将上面加载的图片创建为一个JLabel对象
        JLabel pic1=new JLabel(imag1);
        //将创建的图片对象添加到 窗口上
        frame.add(pic1);



        //创建一个空的JLabel，它的长度宽度为110,30，因为窗口是流式左对齐，为了将”账号”一栏添加在正中间，所以左侧由空的JLabel填充
        JLabel name1=new JLabel();
        //设置空JLabel长度大小，此处不能使用setSize设置大小，setSize只能设置顶级容器大小，此处用setPreferredSize，Dimension给出大小，需要添加的包名为java.awt.Dimension.
        name1.setPreferredSize(new Dimension(110,30));
        //将空JLabel添加入窗口
        frame.add(name1);

        //同上，此处添加的不是空JLabel，而是内容为“账号”的JLabel
        JLabel name=new JLabel("关键词：    ");
        frame.add(name);

        //JTextField在窗口上添加一个可输入可见文本的文本框，需要添加的包名为javax.swing.JTextField.
        final JTextField keyWordFeild=new JTextField();
        //设置文本框大小
        keyWordFeild.setPreferredSize(new Dimension(220, 30));
        //添加到窗口上
        frame.add(keyWordFeild);



        //占位符
        JLabel zw1=new JLabel();
        zw1.setPreferredSize(new Dimension(110,30));
        frame.add(zw1);

        JLabel zw2=new JLabel();
        zw2.setPreferredSize(new Dimension(110,30));
        frame.add(zw2);


        JLabel fileLabel=new JLabel("文件目录：");
        frame.add(fileLabel);


        final JTextField filePathField = new JTextField();
        //设置field为只读
        filePathField.setEditable(false);
        filePathField.setPreferredSize(new Dimension(220, 30));
        //textField.setColumns(20);
        frame.add(filePathField);

        //选择文件
        JButton chooseDirButton = new JButton("选择目录");

        chooseDirButton.addActionListener(new ActionListener() {

            JFileChooser fileChooser = new JFileChooser();

            public void actionPerformed(ActionEvent e) {
                //指示只显示目录
                fileChooser.setFileSelectionMode(fileChooser.DIRECTORIES_ONLY);
                int i = fileChooser.showOpenDialog(null);// 显示文件选择对话框

                // 判断用户单击的是否为“打开”按钮
                if (i == JFileChooser.APPROVE_OPTION) {

                    selectedDir = fileChooser.getSelectedFile().getAbsolutePath();// 获得选中的目录路径
                    filePathField.setText(selectedDir);// 显示选中目录的路径
                }
            }
        });
        frame.add(chooseDirButton);



        //占位符
        JLabel zw3=new JLabel();
        zw3.setPreferredSize(new Dimension(110,30));
        frame.add(zw3);

        /*JLabel zw4=new JLabel();
        zw4.setPreferredSize(new Dimension(110,30));
        frame.add(zw4);*/


        JLabel saveLabel =new JLabel("保存文件：");
        frame.add(saveLabel);


        final JTextField saveFileField = new JTextField();
        //设置field为只读
        saveFileField.setEditable(false);
        saveFileField.setPreferredSize(new Dimension(220, 30));
        //textField.setColumns(20);
        frame.add(saveFileField);

        //选择文件
        JButton chooseSaveDirButton = new JButton("保存位置");
        chooseSaveDirButton.addActionListener(new ActionListener() {

            JFileChooser fileChooser = new JFileChooser();

            public void actionPerformed(ActionEvent e) {
                //指示只显示目录
                fileChooser.setFileSelectionMode(fileChooser.APPROVE_OPTION);
                int i = fileChooser.showSaveDialog(null);// 显示文件选择对话框

                // 判断用户单击的是否为“打开”按钮
                if (i == JFileChooser.APPROVE_OPTION) {

                    selectedSaveDir = fileChooser.getSelectedFile().getAbsolutePath();// 获得选中的目录路径
                    saveFileField.setText(selectedSaveDir);// 显示选中目录的路径
                }
            }
        });
        frame.add(chooseSaveDirButton);





        //占位符
        JLabel zw5=new JLabel();
        zw5.setPreferredSize(new Dimension(110,30));
        frame.add(zw5);

        JLabel zw6=new JLabel();
        zw6.setPreferredSize(new Dimension(110,30));
        frame.add(zw6);

        //选择文件
        JButton startButton = new JButton("搜索");
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                ArrayList<String> fileNameList = new ArrayList<>();
                StringBuffer sb = new StringBuffer();
                FindWord.getAllFileName(selectedDir,fileNameList);
                for (String fileName:fileNameList
                ) {
                    String keyWord = keyWordFeild.getText();
                    String str = FindWord.findKeyWordFromFile(selectedDir, fileName, keyWord);
                    if(str!=null){
                        sb.append(str + "\n");
                    }

                }
                FindWord.save(saveFileField.getText(),sb.toString());

            }
        });
        frame.add(startButton);






        //设置窗口可见，此句一定要在窗口属性设置好了之后才能添加，不然无法正常显示
        frame.setVisible(true);
    }
}
