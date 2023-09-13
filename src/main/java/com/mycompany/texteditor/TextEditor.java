/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.texteditor;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

/**
 *
 * @author Shubham
 */
public class TextEditor implements ActionListener{
    
    //declaring the properties of editor
    JFrame frame;
    JMenuBar menuBar;
    JMenu file, edit;
    //file menu items
    JMenuItem newfile, openfile, savefile;
    //edit menu items
    JMenuItem cut, copy, paste, selectAll, close;
    
    JTextArea textArea;
    
    TextEditor(){
        
        //initialize a frame
        frame = new JFrame();
        
        //initialize menu
        file = new JMenu("File");
         //initialize file menu items
        newfile = new JMenuItem("New Window");
        openfile = new JMenuItem("Open File");
        savefile = new JMenuItem("Save");
        
        //add action listener
        newfile.addActionListener(this);
        openfile.addActionListener(this);
        savefile.addActionListener(this);
        
        
        file.add(newfile);
        file.add(openfile);
        file.add(savefile);
        
        
        edit = new JMenu("Edit");
        
        //initialize edit menu items
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("Copy");
        paste = new JMenuItem("Paste");
        selectAll = new JMenuItem("Select All");
        close = new JMenuItem("Close");
        
        //add action listener to edit menu
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);
        
        
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);
    
        
        //initialize a menubar
        
        menuBar = new JMenuBar();
        textArea = new JTextArea();
        
        menuBar.add(file);
        menuBar.add(edit);
        
        //set menubar to frame
        frame.setJMenuBar(menuBar);
        
        //create a panel to set the borders in the frame
        JPanel panel = new JPanel();
        panel.setBorder(new EmptyBorder(5,5,5,5));
        panel.setLayout(new BorderLayout(0,0));
        
        //add the text area at center
        panel.add(textArea, BorderLayout.CENTER);
        
        //create a scroll pane
        JScrollPane scroll = new JScrollPane(textArea, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        
        //add scroll pane to the panel
        panel.add(scroll);
        
        //add panel to frame
        frame.add(panel);
        //set the size of the frame
        frame.setBounds(300,200,700,500);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);
        
    }

    public static void main(String[] args) {
       TextEditor textEditor = new TextEditor();
       
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        if(e.getSource()==cut){
            //perform cut operation
            textArea.cut();
        }
        if(e.getSource()==copy){
            //perform copy operation
            textArea.copy();
        }
        if(e.getSource()==paste){
            //perform paste operation
            textArea.paste();
        }
        if(e.getSource()==selectAll){
            //perform selectall operation
            textArea.selectAll();
        }
        if(e.getSource()==close){
            //perform close operation
            System.exit(0);
        }
        if(e.getSource()==openfile){
            //open a file chooser
            JFileChooser filechooser = new JFileChooser("C:");
            int chooseOption = filechooser.showOpenDialog(null);
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                //getting the selected file
                File file = filechooser.getSelectedFile();
                
                //get the path of the selected file
                String filepath = file.getPath();
                
                try {
                    //initialize the file reader
                    FileReader filereader = new FileReader(filepath);
                    
                    BufferedReader bufferedreader = new BufferedReader(filereader);
                    String intermediate = "", output = "";
                    
                    while((intermediate=bufferedreader.readLine())!=null){
                        output+=intermediate+"\n";
                    }
                    //set the output string to the text area
                    textArea.setText(output);
                    
                }catch (FileNotFoundException filenotfoundException) {
                         filenotfoundException.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                
            }
        }
        if(e.getSource()==savefile){
            //initialize file picker
            JFileChooser filechooser = new JFileChooser();
            //get the choose option
            int chooseOption = filechooser.showSaveDialog(null);
            
            if(chooseOption==JFileChooser.APPROVE_OPTION){
                File file = new File(filechooser.getSelectedFile().getAbsolutePath()+".txt");
                
                try{
                    //initialize the file writer 
                    FileWriter filewriter = new FileWriter(file);
                    
                    //initialize the buffered writer
                    BufferedWriter bufferedwriter = new BufferedWriter(filewriter);
                    
                    //writer the content of the text area to the file
                    textArea.write(bufferedwriter);
                    bufferedwriter.close();
                    
                    
                }catch(IOException io){
                    io.printStackTrace();
                    
                }
            }
        }
        if(e.getSource()==newfile){
            // create a new texteditor window
            TextEditor newTextEditor = new TextEditor();
        }
           
    }
}
